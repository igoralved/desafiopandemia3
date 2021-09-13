package br.com.alura.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.model.Atendimento;
import br.com.alura.model.Etapa;
import br.com.alura.model.UnidadeSaude;

public class EtapaDAO {
	private EntityManager em;

	public EtapaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Etapa e) {
		this.em.persist(e);
	}
	
	public void atualizar(Etapa e) {
		this.em.merge(e);
	}
	
	//não pode remover entidade no estado detatched
	public void remover(Etapa e) {
		e = this.em.merge(e);
		this.em.remove(e);
	}
	
	
	public List<UnidadeSaude> buscarTodos(){
		String jpql = "SELECT e FROM Etapa e";
		return em.createQuery(jpql).getResultList();
	}
}
