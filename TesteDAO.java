package br.com.alura.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.model.Etapa;
import br.com.alura.model.Teste;
import br.com.alura.model.UnidadeSaude;

public class TesteDAO {

	private EntityManager em;

	public TesteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Teste t) {
		this.em.persist(t);
	}
	
	public void atualizar(Teste t) {
		this.em.merge(t);
	}
	
	//não pode remover entidade no estado detatched
	public void remover(Teste t) {
		t = this.em.merge(t);
		this.em.remove(t);
	}
	
	
	public List<UnidadeSaude> buscarTodos(){
		String jpql = "SELECT t FROM Teste t";
		return em.createQuery(jpql).getResultList();
	}
}
