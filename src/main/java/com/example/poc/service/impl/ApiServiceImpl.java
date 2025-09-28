package com.example.poc.service.impl;

import com.example.poc.model.CatFact;
import com.example.poc.model.Post;
import com.example.poc.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private final RestTemplate restTemplate;

    @Override
    public CatFact getCatFact() {
        log.info("Buscando fatos sobre gatos...");
        ResponseEntity<CatFact> response = restTemplate.getForEntity(
            CAT_FACT_API, 
            CatFact.class
        );
        return response.getBody();
    }

    @Override
    public Post getPost() {
        log.info("Buscando post...");
        ResponseEntity<Post> response = restTemplate.getForEntity(
            POSTS_API,
            Post.class
        );
        return response.getBody();
    }

    @Async
    public CompletableFuture<CatFact> getCatFactAsync() {
        log.info("getCatFactAsync thread={}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(getCatFact());
    }

    @Async
    public CompletableFuture<Post> getPostAsync() {
        log.info("getPostAsync thread={}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(getPost());
    }
}
