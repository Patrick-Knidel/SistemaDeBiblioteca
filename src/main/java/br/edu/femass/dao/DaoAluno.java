package br.edu.femass.dao;

import br.edu.femass.model.Aluno;

import java.util.List;

public class DaoAluno extends Dao<Aluno>{

    public List<Aluno> buscarTodos(){
        return em.createQuery("select a from Aluno a order by a.nome").getResultList();
    }
    public List<Aluno> buscarTodosPorId(){
        return em.createQuery("select a from Aluno a order by a.id").getResultList();
    }
}
