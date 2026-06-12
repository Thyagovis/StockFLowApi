package com.stockflow.StockFlowApi.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.stockflow.StockFlowApi.security.dto.TokenResponseDTO;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String SECRET;

    @Value("${security.jwt.expiration}")
    private Long EXPIRATION;

    @Value("${security.jwt.issuer}")
    private String ISSUER;

    public TokenResponseDTO gerarToken(Usuario usuario) {
        var token = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getLogin())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET));

        return new TokenResponseDTO(token, EXPIRATION);
    }

    public String validarToken(String token) {
        var tokenDec = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        if (!tokenDec.getIssuer().equals(ISSUER)
            || tokenDec.getExpiresAt() == null
        ) {
            throw new JWTVerificationException("Token invalido");
        }

        return tokenDec.getSubject();
    }

}
