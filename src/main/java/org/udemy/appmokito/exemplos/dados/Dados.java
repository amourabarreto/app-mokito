package org.udemy.appmokito.exemplos.dados;

import org.udemy.appmokito.exemplos.models.Exame;

import java.util.List;

public class Dados {
    public static final List<Exame> EXAMES = List.of(new Exame(5l,"Matematica"), new Exame(5l,"Linguas")
            ,new Exame(5l,"Historia"));
}
