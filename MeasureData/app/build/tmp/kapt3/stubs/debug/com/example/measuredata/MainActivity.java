package com.example.measuredata;

import java.lang.System;

/**
 * Activity displaying the app UI. Notably, this binds data from [MainViewModel] to views on screen,
 * and performs the permission check when enabling measure data.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0015H\u0014J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/measuredata/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/measuredata/databinding/ActivityMainBinding;", "okHttpClient", "Lokhttp3/OkHttpClient;", "permissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "viewModel", "Lcom/example/measuredata/MainViewModel;", "getViewModel", "()Lcom/example/measuredata/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "webSocketListener", "Lcom/example/measuredata/WebSocketListener;", "createRequest", "Lokhttp3/Request;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "updateViewVisiblity", "uiState", "Lcom/example/measuredata/UiState;", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.measuredata.databinding.ActivityMainBinding binding;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> permissionLauncher;
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.measuredata.WebSocketListener webSocketListener;
    private final okhttp3.OkHttpClient okHttpClient = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.example.measuredata.MainViewModel getViewModel() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onStart() {
    }
    
    private final okhttp3.Request createRequest() {
        return null;
    }
    
    private final void updateViewVisiblity(com.example.measuredata.UiState uiState) {
    }
}