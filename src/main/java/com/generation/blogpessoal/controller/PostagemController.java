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
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

//DEFINIÇÕES DA CONTROLLER
@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*") //ACEITE REQUISIÇÕES QUE VIEREM DE FORA DO MEU BACK-END
public class PostagemController {
	
	//AUTOWIRED INDICA "USA ISSO AQUI COMO UM BEAN"
	@Autowired //TRAZ MÉTODOS PARA INTERAGIR COM BANCO DE DADOS > Indica que se trata de Injeção de Dependência
	private PostagemRepository postagemRepository;
	
	//MÉTODO GET
	//LISTAR TUDO
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		
		// SELECT * FROM tb_postagens;
	}
	
	//CONSULTA POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){ //PATHVARIABLE FAZ O 'CAMINHO' ENTRE A VÁRIAVEL ID DO URL E A DO MÉTODO
		return postagemRepository.findById(id) //PEGA A RESPOSTA DO FINDBYID
				.map(resposta  -> ResponseEntity.ok(resposta)) //GUARDA A RESPOSTA NA "RESPOSTA", SE TIVER ALGO ELE DEVOLVE 'OK' (200)
				.orElse(ResponseEntity.notFound().build()); //SE NÃO ACHAR NADA (NULL) DEVOLVE UM 'NOTFOUND' 404 - ESSA RESPOSTA É CONSTRUÍDA PELO BUILD
	
		//SELECT * FROM tb_postagens WHERE id = ?;
	}
	
	//PESQUISA POR TÍTULO
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getAllByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	
		//SELECT + FROM tb_postagens WHERE titulo LIKE "%?%";
	}
	
	
	//MÉTODO POST
	//CADASTRO
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){ 
		//VALID = VÁLIDA OS DADOS (PARÂMETRO MODEL)  & REQUESTBODY = MANDA UM JSON DENTRO DA REQUISIÇÃO (body)
		//Pega o JSON enviado no corpo da requisição e o transforma em um objeto Postagem.
		
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		/*VOU MANDAR TÍTULO E TEXTO E DEVOLVER COMO RESPOSTA: ID, DATAEHORA, TITULO E TEXTO.
		SAVE É O INSERT - Depois dele, o banco gera o ID e a DATAEHORA.
		O CREATED > Define que a resposta terá o status 201 Created
		
		INSERT INTO tb_postagens(titulo, texto) VALUES (?, ?);*/
		
	}
	
	
	//MÉTODO PUT
	//ATUALIZAÇÃO (PRECISA TER O ID - Nesse caso, o ID está dentro da própria postagem)
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){ 
		
		if(postagemRepository.existsById(postagem.getId()))
			return ResponseEntity.ok(postagemRepository.save(postagem));
		//UPDATE tb_postagens SET titulo = ?, texto = ? WHERE id = ?;
		
		return ResponseEntity.notFound().build();
	}
	
	
	//MÉTODO DELETE
	@ResponseStatus(HttpStatus.NO_CONTENT) //SE DER CERTO, RETORNA O NO_CONTENT (204)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		//SE NÃO ENCONTRAR A POSTAGEM (id) RETORNA NOT FOUND
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		//SE ENCONTRAR, ELE DELETA
		postagemRepository.deleteById(id);
			
		//DELETE * FROM tb_postagem WHERE id = ?;
	}
	
	
	
}
