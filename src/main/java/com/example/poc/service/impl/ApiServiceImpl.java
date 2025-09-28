package com.example.poc.service.impl;

import com.example.poc.model.CatFact;
import com.example.poc.model.Post;
import com.example.poc.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    private static final String CAT_FACT_API = "https://catfact.ninja/fact";
    private static final String POSTS_API = "https://jsonplaceholder.typicode.com/posts/1";

    private final WebClient webClient;

    @Override
    public CatFact getCatFact() {
        return webClient.get()
                .uri(CAT_FACT_API)
                .retrieve()
                .bodyToMono(CatFact.class)
                .block();
    }

    @Override
    public Post getPost() {
        return webClient.get()
                .uri(POSTS_API)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }

    @Async
    public CompletableFuture<CatFact> getCatFactAsync() {
        return CompletableFuture.completedFuture(getCatFact());
    }

    @Async
    public CompletableFuture<Post> getPostAsync() {
        return CompletableFuture.completedFuture(getPost());
    }

}
