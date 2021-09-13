package br.com.alura.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Teste")
public class Teste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int numero;
	private String resultado;
	
	public Teste() {
		
	}
	
	public Teste(int numero, String resultado) {
		this.numero = numero;
		this.resultado = resultado;
	}
	
}
