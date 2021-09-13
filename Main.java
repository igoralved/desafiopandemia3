package br.com.alura.cidade;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.dao.AtendimentoDAO;
import br.com.alura.dao.UnidadeSaudeDAO;
import br.com.alura.model.Atendimento;
import br.com.alura.model.UnidadeSaude;
import br.com.alura.util.JPAUtil;




public class Main {

	public static void main(String[] args) {
		cadastrar();
		EntityManager em = JPAUtil.getEntityManager();
		UnidadeSaudeDAO udao = new UnidadeSaudeDAO(em);
		HashMap<UnidadeSaude, Integer> mapa1 = udao.min(LocalDate.now());
		HashMap<UnidadeSaude, Integer> mapa2 = udao.max(LocalDate.now());
		HashMap<UnidadeSaude, Double> mapa3 = udao.med(LocalDate.now());
		if(mapa1 != null) {
			mapa1.forEach((s, i) -> System.out.println(s.toString() + "\n" + i));
		}else {
			System.out.println("null");
		}
		if(mapa2 != null) {
			mapa2.forEach((s, i) -> System.out.println(s.toString() + "\n" + i));
		}else {
			System.out.println("null");
		}
		if(mapa3 != null) {
			mapa3.forEach((s, i) -> System.out.println(s.toString() + "\n" + i));
		}else {
			System.out.println("null");
		}
		List<UnidadeSaude> lista = udao.relatorioSemanal(LocalDate.now());
		if(lista != null) {
			lista.forEach(u -> System.out.println(u.toString()));
		}else {
			System.out.println("null");
		}
	}
	
	private static void cadastrar() {
		Atendimento a1 = new Atendimento("ótimo", 10);
		Atendimento a2 = new Atendimento("bem", 5);
		Atendimento a3 = new Atendimento("impressionante", 3);
		Atendimento a4 = new Atendimento("legal", 3);
		UnidadeSaude u1 = new UnidadeSaude("Moinhos", 10);
		UnidadeSaude u2 = new UnidadeSaude("Moinhos", 12);
		u1.addAtendimento(a1);
		u1.addAtendimento(a2);
		u2.addAtendimento(a3);
		EntityManager em = JPAUtil.getEntityManager();
		UnidadeSaudeDAO udao = new UnidadeSaudeDAO(em);
		AtendimentoDAO adao = new AtendimentoDAO(em);
		em.getTransaction().begin();
		adao.cadastrar(a1);
		adao.cadastrar(a2);
		adao.cadastrar(a3);
		adao.cadastrar(a4);
		udao.cadastrar(u1);
		udao.cadastrar(u2);
		em.getTransaction().commit();
		em.close();
	}
}
