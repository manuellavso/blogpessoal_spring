package com.generation.blogpessoal.util;
 
import java.util.Objects;
 
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
 
import com.generation.blogpessoal.model.UsuarioLogin;
 
public class JwtHelper {//Classe utilitária
 
    private JwtHelper() {}
 
    public static String obterToken(TestRestTemplate testRestTemplate, String email, String senha) {
        UsuarioLogin login = TestBuilder.criarUsuarioLogin(email, senha);
        HttpEntity<UsuarioLogin> request = new HttpEntity<>(login);
 
        ResponseEntity<UsuarioLogin> response = testRestTemplate
            .exchange("/usuarios/logar", HttpMethod.POST, request, UsuarioLogin.class); //endereço, método, requisição e a resposta
 
        UsuarioLogin body = response.getBody(); //Acessa o corpo da requisição
        if (body != null && body.getToken() != null) { //Extrai o token e devolve para ser usado nos testes
            return body.getToken();
        }
 
        throw new RuntimeException("Falha no login: " + email + " - status: " + response.getStatusCode()); //caso de problema, manda a mensagem de erro
    }
 
    //Métodos para criar requisições
    //Cria requisição com token e com corpo (Post and Put)
    public static <T> HttpEntity<T> criarRequisicaoComToken(T body, String token) {
        Objects.requireNonNull(token, "token não pode ser nulo");
 
        HttpHeaders headers = new HttpHeaders();
        String tokenLimpo = token.startsWith("Bearer ") ? token.substring(7) : token; //substring tira o bearer, vai pegar um pedaço da string (tira os 7 primeiros caracteres)
        headers.setBearerAuth(tokenLimpo); //guardar o token no authorization - cabeçalho
        return new HttpEntity<>(body, headers); //devolvo uma nova entidade com o corpo vazio e com o cabeçalho com o authorization token
    }
 
    //Cria requisição com token sem corpo (Get and Delete)
    public static HttpEntity<Void> criarRequisicaoComToken(String token) {
        return criarRequisicaoComToken(null, token);
    }
}