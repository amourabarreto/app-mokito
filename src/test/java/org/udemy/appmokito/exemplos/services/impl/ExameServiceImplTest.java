package org.udemy.appmokito.exemplos.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.udemy.appmokito.exemplos.dados.Dados;
import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.ExameRepository;
import org.udemy.appmokito.exemplos.repositories.PerguntaRepository;
import org.udemy.appmokito.exemplos.repositories.impl.ExameRepositoryImpl;
import org.udemy.appmokito.exemplos.services.ExameService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExameServiceImplTest {
    ExameRepository repository;
    ExameService service;
    @BeforeEach
    void setUp() {
         repository = mock(ExameRepository.class);
         service = new ExameServiceImpl(repository);

    }

    @Test
    @DisplayName("Find por Nome")
    void findExamePorNome() {

        when(repository.findaAll()).thenReturn(Dados.EXAMES );


        Optional<Exame> optional = service.findExamePorNome("Matematica");

        assertTrue(optional.isPresent());
        assertEquals(5l,optional.orElseThrow().getId());
        assertEquals("Matematica",optional.orElseThrow().getNome());
    }

    @Test
    void findExamePorNomeListaVazia() {
    }
}
