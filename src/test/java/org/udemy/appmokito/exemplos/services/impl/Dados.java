package org.udemy.appmokito.exemplos.services.impl;

import org.udemy.appmokito.exemplos.models.Exame;

import java.util.List;

public class Dados {
    public static final List<Exame> EXAMES = List.of(new Exame(5l,"Matematica"), new Exame(2l,"Linguas")
            ,new Exame(1l,"Historia"));
    public final static List<String> PERGUNTAS = List.of("aritmetica","integral","trigonometria","geometria","derivada");

    public static final List<Exame> EXAMES_ID_NULL = List.of(new Exame(null,"Matematica"), new Exame(null,"Linguas")
            ,new Exame(null,"Historia"));
    public static final List<Exame> EXAMES_ID_NEGATIVO = List.of(new Exame(-5l,"Matematica"), new Exame(-2l,"Linguas")
            ,new Exame(-1l,"Historia"));

    public final static Exame EXAME_ID_NULL = new Exame(null,"Fisica");
    public final static Exame EXAME_COM_PERGUNTAS = new Exame(null,"Fisica",PERGUNTAS);

}
