package org.udemy.appmokito.exemplos.repositories.impl;

import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.ExameRepository;

import java.util.List;

public class ExameRepositoryImpl implements ExameRepository {
    @Override
    public List<Exame> findaAll() {
        return List.of(new Exame(5l,"Matematica"), new Exame(5l,"Linguas")
                ,new Exame(5l,"Historia") );
    }

    @Override
    public Exame guardar(Exame exame) {
        return null;
    }
}
