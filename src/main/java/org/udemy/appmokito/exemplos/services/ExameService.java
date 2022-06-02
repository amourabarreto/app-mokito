package org.udemy.appmokito.exemplos.services;

import org.udemy.appmokito.exemplos.models.Exame;

import java.util.Optional;

public interface ExameService {
    Optional<Exame> findExamePorNome(String nome);
    Exame findExameComNomeComPerguntas(String nome);
}
