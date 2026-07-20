package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@Size(max = 255, message = "O atributo descrição deve ter no máximo 255 caracteres.")
	@Column(length = 255)
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tema", cascade = CascadeType.REMOVE) 
	@JsonIgnoreProperties(value = "tema", allowSetters = true) //AllowSetters permite que os métodos SET sejam executados
	private List<Postagem> postagem;
	/*LAZY = CARREGA OS DADOS AO SER SOLICITADO/consulta
	mappedBy INDICA QUAL É A CHAVE ESTRANGEIRA
	cascade CONFIGURA COMO O CRUD VAI SE COMPORTAR COM ESSE RELACIONAMENTO*/
	
	
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
	
	public List<Postagem> getPostagem() {
		return postagem;
	}
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
		
	
}
