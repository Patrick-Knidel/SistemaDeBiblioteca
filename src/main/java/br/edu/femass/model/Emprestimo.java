package br.edu.femass.model;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;

import java.time.LocalDate;
@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevisaoDevolucao;
    private LocalDate dataDevolucao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Leitor leitor;
    @ManyToOne(cascade = CascadeType.ALL)
    private Exemplar exemplar;

    public Emprestimo(){

    }

    public Emprestimo(Leitor leitor, Exemplar exemplar) {
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataDevolucao;
        this.dataPrevisaoDevolucao = leitor.getPrazoMaximoDevolucao();
        this.leitor = leitor;
        this.exemplar = exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevisaoDevolucao() {
        return leitor.getPrazoMaximoDevolucao();
    }

    public void setDataPrevisaoDevolucao(LocalDate dataPrevisaoDevolucao) {
        this.dataPrevisaoDevolucao = dataPrevisaoDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Exemplar getExemplar(){
        return exemplar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    @Override
    public String toString() {
        return (getDataEmprestimo().toString() + " : " + exemplar.toString());
    }
}

