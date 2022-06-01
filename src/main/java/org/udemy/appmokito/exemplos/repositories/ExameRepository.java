package org.udemy.appmokito.exemplos.repositories;

import org.udemy.appmokito.exemplos.models.Exame;

import java.util.List;

public interface ExameRepository {
    List<Exame> findaAll();
}
