package com.acltabontabon.openwealth.services;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class AsyncQuery<T> {

    public void fetchAsync(Consumer<T> success, Consumer<Throwable> error) {
        CompletableFuture.supplyAsync(this::execute)
            .thenAcceptAsync(success)
            .exceptionally(ex -> {
                error.accept(ex);
                return null;
            });
    }

    protected abstract T execute();
}
