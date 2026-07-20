package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

//DEFINIÇÕES DA CONTROLLER
@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired //INJEÇÃO DE DEPENDÊNCIA
	private TemaRepository temaRepository;
	
	//MÉTODOS GET
	//1. LISTAR TUDO
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
		
		// SELECT * FROM tb_temas;
	}
	
	//2. CONSULTA POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id){
		return temaRepository.findById(id)
				.map(resposta  -> ResponseEntity.ok(resposta)) 
				.orElse(ResponseEntity.notFound().build()); 
		
		//SELECT * FROM tb_temas WHERE id = ?;
	}
	
	//3. PESQUISA POR DESCRIÇÃO
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getAllByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
		
		//SELECT + FROM tb_temas WHERE descricao LIKE "%?%";
	}
	
	
	//MÉTODO POST
	//CADASTRAR TEMA
	@PostMapping
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
			
		//INSERT INTO tb_temas(descricao) VALUES (?);
	}
	
	
	//MÉTODO PUT
	//ATUALIZAÇÃO (PRECISA TER O ID - Nesse caso, o ID está dentro da própria postagem)
	@PutMapping
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema){ 
			
		if(temaRepository.existsById(tema.getId()))
			return ResponseEntity.ok(temaRepository.save(tema));
			//UPDATE tb_temas SET descricao = ? WHERE id = ?;
			
			return ResponseEntity.notFound().build();
	}
	
	
	//MÉTODO DELETE
	@ResponseStatus(HttpStatus.NO_CONTENT) //SE DER CERTO, RETORNA O NO_CONTENT (204)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
			
		Optional<Tema> tema = temaRepository.findById(id);
			
		if(tema.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		temaRepository.deleteById(id);
				
		//DELETE * FROM tb_temas WHERE id = ?;
	}
	
	
}
