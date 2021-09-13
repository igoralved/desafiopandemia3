package br.com.alura.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Atendimento")
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private boolean relacionadoComPandemia = false;
	private boolean descartouPossibilidadeDeContagio = false;
	
	private LocalDate data = LocalDate.now();
	//private LocalTime tempot = LocalTime.now();
	private Integer tempo;
	
	@OneToMany
	private List<Etapa> etapa = new ArrayList<>();
	
	@OneToMany
	private List<Teste> teste = new ArrayList<>();
	
	
	public Atendimento() {
		
	}
	
	public Atendimento(String descricao, Integer tempo) {
		this.descricao = descricao;
		this.tempo = tempo;
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String nova) {
		this.descricao = nova;
	}
	
	public boolean eRelacionadoComPandemia() {
		return relacionadoComPandemia;
	}
	
	
	public void setRelacionadoComPandemia() {
		relacionadoComPandemia = !relacionadoComPandemia;
	}
	
	public boolean descartouPossibilidadeDeContagio() {
		return descartouPossibilidadeDeContagio;
	}
	
	public void setDescartouPossibilidadeDeContagio() {
		descartouPossibilidadeDeContagio = !descartouPossibilidadeDeContagio;
	}
	
	public void setTime(Integer time) {
		this.tempo = time;
	}
	
	public Integer getTime() {
		return tempo;
	}
	
	public void addEtapa(Etapa e) {
		etapa.add(e);
	}
	
	public void addTeste(Teste t) {
		if(teste.size() < 2) {
			teste.add(t);
		}
	}
	
	public String toString() {
		String result = "Descricao: " + "\n" + descricao;
		if(relacionadoComPandemia) {
			result += "\n é relacionado com pandemia";
		}else {
			result += "\n não é relacionado com pandemia";
		}if(descartouPossibilidadeDeContagio) {
			result += "\n descartou possibilidadeDeContagio";
		}else {
			result += "\n não descartou possibilidadeDeContagio";
		}result += "\n data: " + data.toString();
		result += "\n tempo: " + tempo;
		return result;
	}
	
}
