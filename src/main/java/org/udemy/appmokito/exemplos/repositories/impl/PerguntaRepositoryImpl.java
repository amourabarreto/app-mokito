package org.udemy.appmokito.exemplos.repositories.impl;

import org.udemy.appmokito.exemplos.Dados;
import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.PerguntaRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PerguntaRepositoryImpl implements PerguntaRepository {
    @Override
    public List<String> findPerguntasPorExameId(Long id) {
        System.out.println("Chamando PerguntaRepositoryImpl findPerguntasPorExameId IMPL");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Dados.PERGUNTAS;
    }

    @Override
    public Exame findExamePorNomeComPerguntas(String nome) {
        System.out.println("Chamando PerguntaRepositoryImpl findExamePorNomeComPerguntas IMPL");
        return Dados.EXAME_COM_PERGUNTAS ;
    }

    @Override
    public void gardarVarias(List<String> perguntas) {
        System.out.println("Chamando PerguntaRepositoryImpl gardarVarias IMPL");
    }
}
