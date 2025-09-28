package com.example.poc.service;

import com.example.poc.model.CatFact;
import com.example.poc.model.Post;

import java.util.concurrent.CompletableFuture;

public interface ApiService {
    CatFact getCatFact();
    Post getPost();
    CompletableFuture<CatFact> getCatFactAsync();
    CompletableFuture<Post> getPostAsync();

}
