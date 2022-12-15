package br.edu.femass.model;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Professor extends Leitor{

    private String disciplina;

    public Professor(){

    }

    public Professor(String nome, String endereco, String telefone, String disciplina) {
        super(nome, endereco, telefone);
        this.disciplina = disciplina;
        setPrazoMaximoDevolucao(LocalDate.now().plusDays(30));

    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String toString(){
        return (getNome() + " - " + getTelefone() + " - " + getDisciplina() + " - " + getPrazoMaximoDevolucao());
    }
}

