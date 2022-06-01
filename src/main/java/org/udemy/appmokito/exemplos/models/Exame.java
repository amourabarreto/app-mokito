package org.udemy.appmokito.exemplos.models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Exame {
    @NonNull
    private Long id;
    @NonNull
    private String nome;

    private List<String> perguntas;

}
