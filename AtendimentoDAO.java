package br.com.alura.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.model.Atendimento;
import br.com.alura.model.UnidadeSaude;

public class AtendimentoDAO {

	private EntityManager em;

	public AtendimentoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Atendimento a) {
		this.em.persist(a);
	}
	
	public void atualizar(Atendimento a) {
		this.em.merge(a);
	}
	
	//não pode remover entidade no estado detatched
	public void remover(Atendimento a) {
		a = this.em.merge(a);
		this.em.remove(a);
	}
	
	public Atendimento buscarPorId(Long id) {
		return em.find(Atendimento.class, id);
	}
	
	public List<Atendimento> buscarTodos(){
		String jpql = "SELECT a FROM Atendimento a";
		return em.createQuery(jpql).getResultList();
	}
	
	public List<String> buscarEtapas(){
		String jpql = "SELECT a.etapa.descricao FROM Atendimento a";
		return em.createQuery(jpql).getResultList();
	}
	
	public List<String> buscarResultadosTestes(){
		String jpql = "SELECT a.teste.resultado FROM Atendimento a";
		return em.createQuery(jpql).getResultList();
	}
	
	public Integer min(){
		String jpql = "SELECT min(a.tempo) FROM Atendimento a";
		return em.createQuery(jpql, Integer.class).getSingleResult();
	}
	
	public Integer max(){
		String jpql = "SELECT max(a.tempo) FROM Atendimento a";
		return em.createQuery(jpql, Integer.class).getSingleResult();
	}
	
	public Double med(){
		String jpql = "SELECT avg(a.tempo) FROM Atendimento a";
		return em.createQuery(jpql, Double.class).getSingleResult();
	}
	
}
