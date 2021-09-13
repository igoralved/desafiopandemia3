package br.com.alura.dao;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import br.com.alura.model.Atendimento;
import br.com.alura.model.UnidadeSaude;



public class UnidadeSaudeDAO {

	private EntityManager em;

	public UnidadeSaudeDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(UnidadeSaude u) {
		this.em.persist(u);
	}
	
	public void atualizar(UnidadeSaude u) {
		this.em.merge(u);
	}
	
	//não pode remover entidade no estado detatched
	public void remover(UnidadeSaude u) {
		u = this.em.merge(u);
		this.em.remove(u);
	}
	
	public UnidadeSaude buscarPorId(Long id) {
		return em.find(UnidadeSaude.class, id);
	}
	
	public List<UnidadeSaude> buscarTodos(){
		String jpql = "SELECT u FROM UnidadeSaude u";
		return em.createQuery(jpql).getResultList();
	}
	
	public List<UnidadeSaude> buscarTodosPorSemana(LocalDate data){
		String datas = data.toString();
		System.out.println(datas);
		String dataf = data.plusWeeks(1).toString();
		System.out.println(dataf);
		String jpql = "SELECT u FROM UnidadeSaude u WHERE u.data BETWEEN :begin AND :end";
		return this.em.createQuery(jpql, UnidadeSaude.class).setParameter("begin", datas).setParameter("end", dataf).getResultList();
	}
	
	public List<UnidadeSaude> buscarTodosNomesPorSemana(LocalDate data){
		String jpql = "SELECT u FROM UnidadeSaude u";
		return em.createQuery(jpql, UnidadeSaude.class).getResultList();
	}
	
	public List<UnidadeSaude> relatorioSemanal(LocalDate data){
		LocalDate semanapassada = data.minusWeeks(1);
		List<UnidadeSaude> daultimasemana = buscarTodosPorSemana(semanapassada);
		if(daultimasemana == null || daultimasemana.size() == 0) {
			return null;
		}
		List<UnidadeSaude> dasemanaatual = buscarTodosPorSemana(data);
		if(dasemanaatual == null || dasemanaatual.size() == 0) {
			return null;
		}List<UnidadeSaude> result = new ArrayList<>();
		for(UnidadeSaude u : daultimasemana) {
			for(UnidadeSaude u1 : dasemanaatual) {
				if(u.getNome().equals(u1.getNome()) && u.getNumeroPacientes() < u1.getNumeroPacientes()) {
					result.add(u1);
				}
			}
		}return result;
	}
	
	public HashMap<UnidadeSaude, Integer> min(LocalDate data){
			List<UnidadeSaude> lista = buscarTodosPorSemana(data);
			if(lista == null) {
				return null;
			}
			HashMap<UnidadeSaude, Integer> result = new HashMap<>();
			for(UnidadeSaude u : lista) {
				List<Atendimento> a = u.getAtendimento();
				if(a == null || a.size() == 0) {
					result.put(u, 0);
				}else {
					Integer i = a.get(0).getTime();
					for(Atendimento ai : a) {
						if(ai.getTime() < i) {
							i = ai.getTime();
						}
					}
					result.put(u, i);
				}
			}return result;
	}
	
	public HashMap<UnidadeSaude, Integer> max(LocalDate data){
		List<UnidadeSaude> lista = buscarTodosPorSemana(data);
		if(lista == null) {
			return null;
		}
		HashMap<UnidadeSaude, Integer> result = new HashMap<>();
		for(UnidadeSaude u : lista) {
			List<Atendimento> a = u.getAtendimento();
			if(a == null || a.size() == 0) {
				result.put(u, 0);
			}else {
				Integer i = a.get(0).getTime();
				for(Atendimento ai : a) {
					if(ai.getTime() > i) {
						i = ai.getTime();
					}
				}
				result.put(u, i);
			}
		}return result;
}
	
	public HashMap<UnidadeSaude, Double> med(LocalDate data){
		List<UnidadeSaude> lista = buscarTodosPorSemana(data);
		if(lista == null) {
			return null;
		}
		HashMap<UnidadeSaude, Double> result = new HashMap<>();
		for(UnidadeSaude u : lista) {
			List<Atendimento> a = u.getAtendimento();
			if(a != null && a.size() > 0) {
				Double d = 0.0;
				for(int i = 0; i < a.size(); i++) {
				d += a.get(i).getTime();
				}
				Double media = d / a.size();
				result.put(u, media);
			}else {
				result.put(u, 0.0);
			}
		}return result;
}
	
}

