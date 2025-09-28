package com.example.poc.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Test
    void executaPostSobreGatosParalell_ShouldExecuteSuccessfully() {
        // Act
        postsService.executaPostSobreGatosParalell();

        // Se chegou até aqui sem lançar exceção, o teste passa
        // Podemos adicionar mais verificações se necessário
    }
}
