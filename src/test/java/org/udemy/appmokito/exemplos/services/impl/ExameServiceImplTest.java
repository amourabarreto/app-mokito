package org.udemy.appmokito.exemplos.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.ExameRepository;
import org.udemy.appmokito.exemplos.repositories.PerguntaRepository;
import org.udemy.appmokito.exemplos.services.ExameService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ExameServiceImplTest {
    @Mock
    ExameRepository repository;
    @Mock
    PerguntaRepository perguntaRepository;
    @InjectMocks
    ExameServiceImpl service;


    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
        /*repository = mock(ExameRepository.class);
         perguntaRepository = mock(PerguntaRepository.class);
         service = new ExameServiceImpl(repository,perguntaRepository);*/

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
    void testPerguntaExame() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        when(perguntaRepository.findPerguntasPorExameId(5l)).thenReturn(Dados.PERGUNTAS);
        Exame exame = service.findExameComNomeComPerguntas("Matematica");
        assertEquals(5, exame.getPerguntas().size());
        assertTrue(exame.getPerguntas().contains("aritmetica"));
    }

    @Test
    void testPerguntaExameVerify() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        when(perguntaRepository.findPerguntasPorExameId(5l)).thenReturn(Dados.PERGUNTAS);
        Exame exame = service.findExameComNomeComPerguntas("Matematica");
        assertEquals(5, exame.getPerguntas().size());
        assertTrue(exame.getPerguntas().contains("aritmetica"));
        verify(repository).findaAll();
        verify(perguntaRepository).findPerguntasPorExameId(anyLong());
    }

    @Test
    void testNoExistePerguntaExameVerify() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        when(perguntaRepository.findPerguntasPorExameId(anyLong())).thenReturn(Dados.PERGUNTAS);
        Exame exame = service.findExameComNomeComPerguntas("Matematica");
        assertNotNull(exame);

        verify(repository).findaAll();
        verify(perguntaRepository).findPerguntasPorExameId(anyLong());
    }
}
