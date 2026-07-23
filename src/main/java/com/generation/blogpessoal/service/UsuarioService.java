package com.generation.blogpessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.security.JwtService;

@Service //INDICA QUE É UMA CLASSE DE SERVIÇO
public class UsuarioService {//GUARDA AS REGRAS DE NEGÓCIO, CRUD É FEITO AQUI DENTRO
	
	//INJEÇÕES DE DEPENDÊNCIA
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//MÉTODOS
	//1. LISTAGEM DE USUÁRIOS
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	
	//2. LISTAR PELO ID
	public Optional<Usuario> getById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	
	//CADASTRAR POST
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		
		//VERIFICA SE JÁ TEM USUÁRIO
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty(); //SE TIVER, RETORNA VAZIO
		}
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuario.setId(null);
		
		return Optional.of(usuarioRepository.save(usuario));
		
	}
	
	//ATUALIZAR PUT
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		//VERIFICA SE NÃO TEM USUÁRIO
		if(usuarioRepository.findById(usuario.getId()).isEmpty()) {
			return Optional.empty();
		}
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());
		
		if(usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(usuario.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail já está em uso!", null);
			
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		return Optional.ofNullable(usuarioRepository.save(usuario));
		
	}
	
	//MÉTODO AUTENTICAR
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		 
		if (usuarioLogin.isEmpty()) {
			return Optional.empty();
		}
 
		UsuarioLogin login = usuarioLogin.get();
		
		try {
 
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha()));
 
			return usuarioRepository.findByUsuario(login.getUsuario())
				.map(usuario -> construirRespostaLogin(login, usuario));
 
		} catch (Exception e) {
 
			return Optional.empty();
		}
	}
	
	//MÉTODO CONSTRUIR RESPOSTA LOGIN
	private UsuarioLogin construirRespostaLogin(UsuarioLogin usuarioLogin, Usuario usuario){
		usuarioLogin.setId(usuario.getId());
		usuarioLogin.setNome(usuario.getNome());
		usuarioLogin.setFoto(usuario.getFoto());
		usuarioLogin.setSenha("");
		usuarioLogin.setToken(gerarToken(usuario.getUsuario()));
		
		return usuarioLogin;
	}
	
	
	//MÉTODO GERAR TOKEN
	private String gerarToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}


}
