package br.edu.femass.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAquisicao;
    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro;
    public Exemplar(){

    }

    public Exemplar(Livro livro){
        this.dataAquisicao = LocalDate.now();
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Livro getLivro(){
        return livro;
    }

    public String toString(){
        return (livro.getTitulo() +" "+ livro.getAutor().getNome()+" "+ livro.getAno());
    }
}

