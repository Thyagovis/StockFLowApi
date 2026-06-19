package com.stockflow.StockFlowApi.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.stockflow.StockFlowApi.security.dto.TokenResponseDTO;
import com.stockflow.StockFlowApi.shared.exceptions.InvalidTokenException;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioMapper;
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
                .withSubject(usuario.getUsername())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET));

        return new TokenResponseDTO(
                token,
                EXPIRATION,
                UsuarioMapper.paraUsuarioSummaryDTO(usuario)
        );
    }

    public String validarToken(String token) {

        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

        }
        catch (TokenExpiredException e) {
            throw new InvalidTokenException("Token expirado", e);
        }
        catch (JWTVerificationException e) {
            throw new InvalidTokenException("Token invalido", e);
        }

    }

}
