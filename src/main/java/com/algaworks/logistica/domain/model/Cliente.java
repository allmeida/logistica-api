package com.algaworks.logistica.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank 					// permite a entrada de valor null e vazio.
	@Size(max = 60)				// valor máximo de caracteres.
	private String nome;
	
	@NotBlank					// permite a entrada de valor null e vazio.
	@Email						// valida a sintaxe correta de um email.
	@Size(max = 255)			// valor máximo de caracteres.
	private String email;
	
	@NotBlank					// permite a entrada de valor null e vazio.
	@Size(max = 20)				// valor máximo de caracteres.
	@Column(name = "fone")
	private String telefone;
	
}
