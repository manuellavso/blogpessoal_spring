package com.generation.blogpessoal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//CREATE TABLE tb_tema();
@Entity
@Table(name = "tb_temas")
public class Tema {//MODEL
	
	//CONSTRAINTS
	@Id // PRIMARY KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
	private Long id;

	@NotBlank(message = "O atributo descrição é obrigatório!")
	@Size(min = 4, max = 255, message = "O atributo descrição deve ter no mínimo 4 e no máximo 255 caracteres.")
	@Column(length = 255) //DEFINE TAMANHO VARCHAR NO BD
	private String descricao;

	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
}
