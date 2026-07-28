package com.generation.blogpessoal.util;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;

public class TestBuilder {
	public static Usuario criarUsuario(Long id, String nome, String usuario, String senha) {
		Usuario novoUsuario = new Usuario(); //Criar usuario para teste
		novoUsuario.setId(id);
		novoUsuario.setNome(nome);
		novoUsuario.setUsuario(usuario);
		novoUsuario.setSenha(senha);
		novoUsuario.setFoto("-");
		return novoUsuario;
	}

	public static UsuarioLogin criarUsuarioLogin(String usuario, String senha) {
		UsuarioLogin usuarioLogin = new UsuarioLogin(); //Criar usuarioLogin para teste
		usuarioLogin.setUsuario(usuario);
		usuarioLogin.setSenha(senha);
		return usuarioLogin;
	}
}