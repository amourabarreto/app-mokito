package org.udemy.appmokito.exemplos.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.udemy.appmokito.exemplos.Dados;
import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.ExameRepository;
import org.udemy.appmokito.exemplos.repositories.PerguntaRepository;
import org.udemy.appmokito.exemplos.repositories.impl.ExameRepositoryImpl;
import org.udemy.appmokito.exemplos.repositories.impl.PerguntaRepositoryImpl;
import org.udemy.appmokito.exemplos.services.ExameService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ExameServiceImplTest {
    @Mock
    ExameRepositoryImpl repository;
    @Mock
    PerguntaRepositoryImpl perguntaRepository;
    @InjectMocks
    ExameServiceImpl service;

    @Captor
    ArgumentCaptor<Long> captor;



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
        //GIVEN
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        when(perguntaRepository.findPerguntasPorExameId(anyLong())).thenReturn(Dados.PERGUNTAS);
        //THEN
        Exame exame = service.findExameComNomeComPerguntas("Matematica");
        assertNotNull(exame);
        //WHEN
        verify(repository).findaAll();
        verify(perguntaRepository).findPerguntasPorExameId(anyLong());
    }

    @Test
    void gardarExame() {
        //GIVEN
        when(repository.guardar(any(Exame.class))).then(new Answer<Exame>(){
            Long sequencia = 8L;
            @Override
            public Exame answer(InvocationOnMock invocationOnMock) throws Throwable {
                Exame exame = invocationOnMock.getArgument(0);
                exame.setId(++sequencia);
                return exame;
            }
        });

        //WHEN
        Exame exame = service.guardar(Dados.EXAME_COM_PERGUNTAS);
        //assertNull(exame.getId());
        assertEquals(9L,exame.getId());
        assertEquals("Fisica",exame.getNome());

        //THEN
        verify(repository).guardar(any(Exame.class));
        verify(perguntaRepository).gardarVarias(anyList());

    }


    void testException() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        when(perguntaRepository.findPerguntasPorExameId(anyLong())).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,()->service.findExamePorNome("Matematica"));
    }

    @Test
    void testArgumentMatchers() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        when(perguntaRepository.findPerguntasPorExameId(anyLong())).thenReturn(Dados.PERGUNTAS);
        service.findExameComNomeComPerguntas("Matematica");

        verify(repository).findaAll();
       // verify(perguntaRepository).findPerguntasPorExameId(argThat(arg -> arg !=null && arg.equals(5L)));
        //verify(perguntaRepository).findPerguntasPorExameId(eq(5l));
        verify(perguntaRepository).findPerguntasPorExameId(argThat(new MyArgsMatchers()));
    }
    //Com classe podemos customizar as mensagens
    public static class MyArgsMatchers implements ArgumentMatcher<Long>{
        private Long aLong;
        @Override
        public boolean matches(Long aLong) {
            this.aLong = aLong;
            return aLong!=null && aLong >0;
        }

        @Override
        public String toString() {
            return "Deve ser um inteiro Positivo "+this.aLong ;
        }
    }

    @Test
    void testArgsCapture() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        when(perguntaRepository.findPerguntasPorExameId(anyLong())).thenReturn(Dados.PERGUNTAS);
        service.findExameComNomeComPerguntas("Matematica");

//        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(perguntaRepository).findPerguntasPorExameId(captor.capture());

        assertEquals(5L,captor.getValue());
    }

    @Test
    void testDoThrow() {
        doThrow(IllegalArgumentException.class).when(perguntaRepository).gardarVarias(anyList());
        assertThrows(IllegalArgumentException.class, () -> service.guardar(Dados.EXAME_COM_PERGUNTAS));
    }

    @Test
    void testDoAnswer() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        //when(perguntaRepository.findPerguntasPorExameId(anyLong())).thenReturn(org.udemy.appmokito.exemplos.Dados.PERGUNTAS);
        doAnswer(invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            return id == 5L? Dados.PERGUNTAS:null;
        }).when(perguntaRepository).findPerguntasPorExameId(anyLong());
        Exame exame = service.findExameComNomeComPerguntas("Matematica");
        assertEquals(5L,exame.getId());
    }

    @Test
    void testDoCallMethod() {
        when(repository.findaAll()).thenReturn(Dados.EXAMES);
        //Não chama o método real, pois é um mock
        when(perguntaRepository.findPerguntasPorExameId(anyLong())).thenReturn(org.udemy.appmokito.exemplos.Dados.PERGUNTAS);
        //hibrido: é um mock que chama um método real.
        doCallRealMethod().when(perguntaRepository).findPerguntasPorExameId(anyLong());
        Exame exame = service.findExameComNomeComPerguntas("Matematica");
        assertEquals(5L,exame.getId());
        assertEquals("Matematica",exame.getNome());
    }

    @Test
    void testSpy() {
        ExameRepository exameRepository = spy(ExameRepositoryImpl.class);
        PerguntaRepository perguntaRepository = spy(PerguntaRepositoryImpl.class);
        ExameService exameService = new ExameServiceImpl(exameRepository,perguntaRepository);
        Exame exame = exameService.findExameComNomeComPerguntas("Matematica");
        assertEquals(5L,exame.getId());
    }
}
