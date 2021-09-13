package br.com.alura.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UnidadeSaude")
public class UnidadeSaude {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private int numeroPacientes = 0;
	
	@OneToMany
	private List<Atendimento> atendimento = new ArrayList<>();
	
	private LocalDate datat = LocalDate.now();
	private String data = datat.toString();
	
	public UnidadeSaude() {
		
	}
	
	public UnidadeSaude(String nome, int numeroPacientes) {
		this.nome = nome;
		this.numeroPacientes = numeroPacientes;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getNumeroPacientes() {
		return numeroPacientes;
	}
	
	public void setNumeroPacientes(int numero) {
		this.numeroPacientes = numero;
	}
	
	public String getData() {
		return data.toString();
	}

	
	public List<Atendimento> getAtendimento() {
		return atendimento;
	}
	
	public void addAtendimento(Atendimento a) {
		atendimento.add(a);
	}
	
	public String toString() {
		return "nome = " + nome + "; numero de pacientes = " + numeroPacientes
				+ "; data = " + data;
	}
	
	public void setTime(int anos, int meses, int dias) {
		this.datat = LocalDate.of(anos, meses, dias);
		this.data = datat.toString();
	}
	
}
