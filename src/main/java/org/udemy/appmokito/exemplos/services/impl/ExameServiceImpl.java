package org.udemy.appmokito.exemplos.services.impl;

import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.ExameRepository;
import org.udemy.appmokito.exemplos.repositories.PerguntaRepository;
import org.udemy.appmokito.exemplos.services.ExameService;

import java.util.Optional;

public class ExameServiceImpl implements ExameService {
    private ExameRepository exameRepository;
    private PerguntaRepository perguntaRepository;

    public ExameServiceImpl(ExameRepository exameRepository, PerguntaRepository perguntaRepository) {
        this.exameRepository = exameRepository;
        this.perguntaRepository = perguntaRepository;
    }

    public ExameServiceImpl(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;
    }

    @Override
    public Optional<Exame> findExamePorNome(String nome) {
       return exameRepository.findaAll()
                        .stream()
                        .filter(s -> s.getNome()
                                .equalsIgnoreCase(nome))
                        .findFirst();

    }


}
