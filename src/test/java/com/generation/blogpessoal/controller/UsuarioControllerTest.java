package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;
import com.generation.blogpessoal.util.JwtHelper;
import com.generation.blogpessoal.util.TestBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Caso minha aplicação esteja rodando (8080), procure uma porta livre para rodar o teste sem interferir na aplicação
@AutoConfigureTestRestTemplate //Para habilitar o RestTemplate
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Um ciclo por classe, executar todos os métodos em uma única instância
@TestMethodOrder(MethodOrderer.DisplayName.class) //Definir a ordem do método, vai ser realizado ao chamar o nome em sequência
public class UsuarioControllerTest {

	//Injeções de dependências
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//Como é usuario, é /usuarios
	private static final String BASE_URL = "/usuarios";
	private static final String USUARIO = "root@root.com";
	private static final String SENHA = "rootroot";

	
	@BeforeAll //Antes de executar todos os testes, ele vai apagar e recriar a tabela - e criar novo usuário
	void inicio() {
		usuarioRepository.deleteAll(); //Específico para o Windows, ele limpa a memória - apaga a tabela e cria de novo.
		usuarioService.cadastrarUsuario(TestBuilder.criarUsuario(null, "Root", USUARIO, SENHA)); //Chama da Service para criptografar senha e garantir que não é duplicado
	}
	
	//TESTE 1: Checar se é possível cadastrar usuário com sucesso - POST
	@Test
	@DisplayName("01 - Deve cadastrar um novo usuário com sucesso")
	void deveCadastrarUsuario() {
		// GIVEN - prepare the test scenario
		Usuario usuario = TestBuilder.criarUsuario(null, "Manuella Oliveira", "manu@gmail.com.br", "manu1234");
		
				
		// WHEN - execute the principal action
		//Create the Entity and body requisition
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuario);
		
		//Send the requisition
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);
		
		
		//THEN - check the results
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode()); //Checar se o resultado foi created (201)
		assertNotNull(resposta.getBody()); //Checar se o corpo não está nulo
	}
	
		
	
	//TESTE 2: Checar se é possível não cadastrar - POST
	@Test
	@DisplayName("02 - Não deve cadastrar usuário duplicado")
	void naoDeveCadastrarUsuarioDuplicado() {
		// GIVEN
		Usuario usuario = TestBuilder.criarUsuario(null, "Luiza Guimarães", "luiza@gmail.com.br", "luiza1234");
				
		usuarioService.cadastrarUsuario(usuario); //Criar usuario antes de mandar a requisição, justamente para causar o erro
						
		// WHEN
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuario);
				
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);
				
		//THEN
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode()); //400 - indica que o usuário já está cadastrado
		assertNull(resposta.getBody()); 
		}
	
	
	
	//TESTE 3: Checar se é possível listar tudo - GET
	@Test
	@DisplayName("03 - Deve listar todos os usuários")
	void deveListarTodosUsuarios() {
		// GIVEN
		usuarioService.cadastrarUsuario(TestBuilder.criarUsuario(null, "Kaue Dota", "kaue@gmail.com.br", "kaue1234"));
		usuarioService.cadastrarUsuario(TestBuilder.criarUsuario(null, "Edson Nascimento", "edson@gmail.com.br", "edson1234"));
	
		
		// WHEN
		//Obter token
		String token = JwtHelper.obterToken(testRestTemplate, USUARIO, SENHA);
			
		//Heather requisition
		HttpEntity<Void> cabeçalhoRequisicao = JwtHelper.criarRequisicaoComToken(token); //Pego cabeçalho no método criarRequisicaoComToken | Corpo vazio porque é uma consulta, não mando nada
				
		//Send the requisition
		ResponseEntity<Usuario[]> resposta = testRestTemplate.exchange(BASE_URL + "/all", HttpMethod.GET, cabeçalhoRequisicao, Usuario[].class);
				
			
		//THEN
		assertEquals(HttpStatus.OK, resposta.getStatusCode()); //200
		assertNotNull(resposta.getBody()); 
		}
	
	
	//TESTE 4: Checar se é possível atualizar dados - PUT
	@Test
	@DisplayName("04 - Deve atualizar os dados do usuário com sucesso")
	void deveAtualizarUsuario() {
		// GIVEN
		//Criar objeto para fazer o cadastro
		Usuario usuario = TestBuilder.criarUsuario(null, "Daniel", "daniel@gmail.com.br", "daniel1234");
		
		//Fiz o cadastro e guardei os dados em usuarioCadastrado
		Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);
		
		//Preparar objeto com atualização dos dados:
		Usuario usuarioUpdate = TestBuilder.criarUsuario(usuarioCadastrado.get().getId(), "Daniel Araujo", "daniel_araujo@gmail.com.br", "abcd1234");
					
		
		// WHEN
		//Obter token
		String token = JwtHelper.obterToken(testRestTemplate, USUARIO, SENHA);
					
		//Heather requisition
		HttpEntity<Usuario> cabeçalhoRequisicao = JwtHelper.criarRequisicaoComToken(usuarioUpdate, token); //Vai devolver um objeto da classe usuário
						
		//Send requisition
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/atualizar", HttpMethod.PUT, cabeçalhoRequisicao, Usuario.class);
			
			
		//THEN
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
		}
	
	
	//TESTE 5: Checar se é possível listar por ID
	@Test
	@DisplayName("05 - Deve listar usuário por ID")
	void deveListarUsuarioPorId() {
		// GIVEN
		//Criar objeto para fazer o cadastro
		Usuario usuario = TestBuilder.criarUsuario(null, "Rafael", "rafael@gmail.com.br", "rafa1234");
	
		//Fiz o cadastro e guardei os dados em usuarioCriado
		Optional<Usuario> usuarioCriado = usuarioService.cadastrarUsuario(usuario);

		
		// WHEN
		//Obter token
		String token = JwtHelper.obterToken(testRestTemplate, USUARIO, SENHA);
			
		//Heather requisition
		HttpEntity<Void> cabeçalhoRequisicao = JwtHelper.criarRequisicaoComToken(token); //Void pois é uma consulta
				
		//Send the requisition
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/" + usuarioCriado.get().getId(), HttpMethod.GET, cabeçalhoRequisicao, Usuario.class); //Peguei o id e coloquei na URL, após a /
				
			
		//THEN
		assertEquals(HttpStatus.OK, resposta.getStatusCode()); //200
		assertNotNull(resposta.getBody()); 
		}
	
	
}
