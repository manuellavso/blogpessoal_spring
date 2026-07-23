package com.generation.blogpessoal.security;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	//CHAVE CRIPTOGRAFADA PARA VALIDAR TOKENS JWT
    private String secret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private Duration expiration = Duration.ofMinutes(60); 
    //CLASSE DURATION QUE CONVERTE PARA MILISSEGUNDOS.
    
    //SECRETKEY É CLASSE QUE VAI GERAR A ASSINATURA
    private SecretKey signingKey;
        
    private SecretKey getSigningKey() {
        if (signingKey == null) {
            byte[] keyBytes = Decoders.BASE64.decode(secret); //Algoritmos de criptografia
            signingKey = Keys.hmacShaKeyFor(keyBytes);
        }
        return signingKey;
    }
    
    //ABRE O PAYLOAD E TIRA TUDO QUE ESTÁ LA DENTRO
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    
    //PEGA APENAS O NOME DO USUÁRIO
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    //PEGA APENAS A PROPRIEDADE EXP
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    //VALIDA O TOKEN: COMPARA O SUBJECT DO PAYLOAD COM O USERDETAILS
    //subject do payload deve ser igual o userdetails
    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject().equals(userDetails.getUsername()) &&  //VALIDA O USUÁRIO
               claims.getExpiration().after(new Date()); //VALIDA A DATA E HORA DE EXPIRAÇÃO
    }

    
    //CONSTRUIR O TOKEN
    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
            .subject(username) //USERNAME
            .issuedAt(Date.from(now)) //DATA E HORA ATUAL
            .expiration(Date.from(now.plus(expiration))) //PEGA DATA DE EXPIRAÇÃO
            .signWith(getSigningKey()) //PEGA A CHAVE DE ASSINATURA
            .compact(); //COMPACTA E DEVOLVE O TOKEN
    }
    
}