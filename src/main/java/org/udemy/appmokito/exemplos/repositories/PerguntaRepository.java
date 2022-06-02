package org.udemy.appmokito.exemplos.repositories;

import org.udemy.appmokito.exemplos.models.Exame;

import java.util.List;

public interface PerguntaRepository {
    List<String> findPerguntasPorExameId(Long id);
    Exame findExamePorNomeComPerguntas(String nome);
    void gardarVarias(List<String> perguntas);
}
