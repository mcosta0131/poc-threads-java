package com.example.poc.service.impl;

import com.example.poc.model.CatFact;
import com.example.poc.model.Post;
import com.example.poc.service.ApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
@AllArgsConstructor
public class PostsService {

    private final ApiService apiService;
    private final Executor executor = Executors.newFixedThreadPool(2);

    public void executaPostSobreGatosParalell() {
        long startTime = System.currentTimeMillis();

        try {
            CompletableFuture<CatFact> catFactFuture = apiService.getCatFactAsync();
            CompletableFuture<Post> futurePost = apiService.getPostAsync();

            CompletableFuture.allOf(catFactFuture, futurePost).join();
            CatFact catFact = catFactFuture.join();
            Post post = futurePost.join();


        } catch (CompletionException e) {
            log.error(e.getMessage());
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            log.info("Tempo total de execução do paralelo: {} ms", duration);
        }
    }
}
