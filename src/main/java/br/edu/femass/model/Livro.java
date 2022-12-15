package br.edu.femass.model;


import javax.persistence.*;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    private String ano;

    public Livro(){

    }

    public Livro(String titulo, Autor autor, String ano){
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public Autor getAutor() {
        return autor;
    };
    public String getAno() {
        return ano;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String toString(){
        return (getTitulo() + " - " + getAutor() + " - " + getAno());
    }

}

