package br.edu.femass.dao;

import br.edu.femass.model.Exemplar;

import java.util.List;

public class DaoExemplar extends Dao<Exemplar>{

    public List<Exemplar> buscarTodos(){
        return em.createQuery("select c from Exemplar c order by c.nome").getResultList();    }

    public List<Exemplar> buscarTodosPorId(){
        return em.createQuery("select c from Exemplar c order by c.id").getResultList();
    }

}
