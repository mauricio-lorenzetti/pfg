/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.measuredata

import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.measuredata.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import okhttp3.*

/**
 * Activity displaying the app UI. Notably, this binds data from [MainViewModel] to views on screen,
 * and performs the permission check when enabling measure data.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    private val viewModel: MainViewModel by viewModels()

    private lateinit var webSocketListener: WebSocketListener
    private val okHttpClient = OkHttpClient()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                when (result) {
                    true -> {
                        Log.i(TAG, "Body sensors permission granted")
                        // Only measure while the activity is at least in STARTED state.
                        // MeasureClient provides frequent updates, which requires increasing the
                        // sampling rate of device sensors, so we must be careful not to remain
                        // registered any longer than necessary.
                        lifecycleScope.launchWhenStarted {
                            viewModel.measureHeartRate()
                        }
                    }
                    false -> Log.i(TAG, "Body sensors permission not granted")
                }
            }

        // Bind viewmodel state to the UI.
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                updateViewVisiblity(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.heartRateAvailable.collect {
                binding.statusText.text = getString(R.string.measure_status, it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.heartRateBpm.collect {
		binding.lastMeasuredValue.text = String.format("%.1f", it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        webSocketListener = WebSocketListener()
        viewModel.webSocket = okHttpClient.newWebSocket(createRequest(), webSocketListener)
        permissionLauncher.launch(android.Manifest.permission.BODY_SENSORS)
    }

    private fun createRequest(): Request {
        // piesocket.com credentials
        val CLUSTER_ID = "s9203.nyc1"
        val API_KEY = "uEH8CHNettvVmFD9sY5NLPhjyPRKjglTOq74VsLG"
        val websocketURL = "wss://${CLUSTER_ID}.piesocket.com/v3/1?api_key=${API_KEY}"

        return Request.Builder()
            .url(websocketURL)
            .build()
    }

    private fun updateViewVisiblity(uiState: UiState) {
        (uiState is UiState.Startup).let {
            binding.progress.isVisible = it
        }
        // These views are visible when heart rate capability is not available.
        (uiState is UiState.HeartRateNotAvailable).let {
            binding.brokenHeart.isVisible = it
            binding.notAvailable.isVisible = it
        }
        // These views are visible when the capability is available.
        (uiState is UiState.HeartRateAvailable).let {
            binding.statusText.isVisible = it
            binding.lastMeasuredLabel.isVisible = it
            binding.lastMeasuredValue.isVisible = it
            binding.heart.isVisible = it
        }
    }
}


class WebSocketListener(): okhttp3.WebSocketListener() {

    private val TAG = "Test"

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        webSocket.send("Android Device Connected")
        Log.d(TAG, "onOpen:")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.d(TAG, "onMessage: $text")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.d(TAG, "onClosing: $code $reason")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d(TAG, "onClosed: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d(TAG, "onFailure: ${t.message} $response")
        super.onFailure(webSocket, t, response)
    }
}
