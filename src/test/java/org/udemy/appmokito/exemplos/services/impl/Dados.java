package org.udemy.appmokito.exemplos.services.impl;

import org.udemy.appmokito.exemplos.models.Exame;

import java.util.List;

public class Dados {
    public static final List<Exame> EXAMES = List.of(new Exame(5l,"Matematica"), new Exame(2l,"Linguas")
            ,new Exame(1l,"Historia"));
    public final static List<String> PERGUNTAS = List.of("aritmetica","integral","trigonometria","geometria","derivada");
}
