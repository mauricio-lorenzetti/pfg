package com.example.measuredata;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J \u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\"\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/measuredata/WebSocketListener;", "Lokhttp3/WebSocketListener;", "()V", "TAG", "", "onClosed", "", "webSocket", "Lokhttp3/WebSocket;", "code", "", "reason", "onClosing", "onFailure", "t", "", "response", "Lokhttp3/Response;", "onMessage", "text", "onOpen", "app_debug"})
public final class WebSocketListener extends okhttp3.WebSocketListener {
    private final java.lang.String TAG = "Test";
    
    public WebSocketListener() {
        super();
    }
    
    @java.lang.Override
    public void onOpen(@org.jetbrains.annotations.NotNull
    okhttp3.WebSocket webSocket, @org.jetbrains.annotations.NotNull
    okhttp3.Response response) {
    }
    
    @java.lang.Override
    public void onMessage(@org.jetbrains.annotations.NotNull
    okhttp3.WebSocket webSocket, @org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    @java.lang.Override
    public void onClosing(@org.jetbrains.annotations.NotNull
    okhttp3.WebSocket webSocket, int code, @org.jetbrains.annotations.NotNull
    java.lang.String reason) {
    }
    
    @java.lang.Override
    public void onClosed(@org.jetbrains.annotations.NotNull
    okhttp3.WebSocket webSocket, int code, @org.jetbrains.annotations.NotNull
    java.lang.String reason) {
    }
    
    @java.lang.Override
    public void onFailure(@org.jetbrains.annotations.NotNull
    okhttp3.WebSocket webSocket, @org.jetbrains.annotations.NotNull
    java.lang.Throwable t, @org.jetbrains.annotations.Nullable
    okhttp3.Response response) {
    }
}