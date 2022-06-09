package org.udemy.appmokito.exemplos.repositories.impl;

import org.udemy.appmokito.exemplos.Dados;
import org.udemy.appmokito.exemplos.models.Exame;
import org.udemy.appmokito.exemplos.repositories.ExameRepository;

import java.util.List;

public class ExameRepositoryImpl implements ExameRepository {
    @Override
    public List<Exame> findaAll() {
        System.out.println("Chamando ExameRepositoryImpl findaAll IMPL");
        return List.of(new Exame(5l,"Matematica"), new Exame(5l,"Linguas")
                ,new Exame(5l,"Historia") );
    }

    @Override
    public Exame guardar(Exame exame) {
        System.out.println("Chamando ExameRepositoryImpl guardar IMPL");
        return Dados.EXAMES.stream().filter(x->x.getId()==exame.getId()).findFirst().orElse(exame);
    }
}
