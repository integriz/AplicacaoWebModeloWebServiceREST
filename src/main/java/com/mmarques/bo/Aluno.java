package com.mmarques.bo;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Aluno {
    private String nome;
    private String turma;
    private double media;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
    
    
}
