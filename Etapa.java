package br.com.alura.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Etapa")
public class Etapa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int numero;
	private String descricao;
	
	public Etapa() {
		
	}
	
	public Etapa(int numero, String descricao) {
		this.numero = numero;
		this.descricao = descricao;
	}
	
	
	
	
}
