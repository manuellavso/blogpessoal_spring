package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
//Herdei JpaRepository (contém os métodos padrões) 
//Nos <> passo a tipagem da Model e tipo de dado guardado na chave primária
}
