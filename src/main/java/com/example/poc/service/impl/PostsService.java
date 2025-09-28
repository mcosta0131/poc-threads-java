package com.example.poc.service.impl;

import com.example.poc.model.CatFact;
import com.example.poc.model.Post;
import com.example.poc.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostsService {

    private final ApiService apiService;

    public void executaPostSobreGatosParalelo() {
        Instant start = Instant.now();
        log.info("Iniciando execução paralela...");

        try {
            // Dispara as duas chamadas ao mesmo tempo (Spring @Async cuida das threads)
            CompletableFuture<CatFact> catFactFuture = apiService.getCatFactAsync();
            CompletableFuture<Post> postFuture = apiService.getPostAsync();

            // Aguarda ambas completarem
            CompletableFuture.allOf(catFactFuture, postFuture).join();

            // Obtém os resultados
            CatFact catFact = catFactFuture.join();
            Post post = postFuture.join();

            log.info("CatFact recebido: {}", catFact);
            log.info("Post recebido: {}", post);

        } catch (CompletionException e) {
            // Captura se alguma das futures falhar
            log.error("Erro durante execução paralela", e.getCause());
        } finally {
            long elapsed = Duration.between(start, Instant.now()).toMillis();
            log.info("Tempo total: {} ms", elapsed);
        }
    }
}
