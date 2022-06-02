package org.udemy.appmokito.exemplos.models;

import java.util.List;



public class Exame {
    public Exame(Long id,String nome){
        this.id=id;
        this.nome=nome;
    }

    public Exame(Long id,String nome,List<String> perguntas){
        this.id=id;
        this.nome=nome;
        this.perguntas=perguntas;
    }

    private Long id;

    private String nome;

    private List<String> perguntas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<String> perguntas) {
        this.perguntas = perguntas;
    }
}
