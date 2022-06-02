package org.udemy.appmokito.exemplos.services.impl;

import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.ExameRepository;
import org.udemy.appmokito.exemplos.repositories.PerguntaRepository;
import org.udemy.appmokito.exemplos.services.ExameService;

import java.util.List;
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

    @Override
    public Exame findExameComNomeComPerguntas(String nome) {
        Optional<Exame> exameOptional = findExamePorNome(nome);
        Exame exame = null;
        if(exameOptional.isPresent()){
            exame = exameOptional.orElseThrow();
            List<String> perguntas = perguntaRepository.findPerguntasPorExameId(exame.getId());
            exame.setPerguntas(perguntas);
        }
        return exame;
    }

    @Override
    public Exame guardar(Exame exame) {
        if(!exame.getPerguntas().isEmpty()){
            perguntaRepository.gardarVarias(exame.getPerguntas());
        }
        return exameRepository.guardar(exame);
    }


}
