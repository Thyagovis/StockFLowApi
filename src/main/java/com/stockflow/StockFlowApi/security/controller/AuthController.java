package com.stockflow.StockFlowApi.security.controller;

import com.stockflow.StockFlowApi.security.doc.AuthControllerDoc;
import com.stockflow.StockFlowApi.security.dto.TokenResponseDTO;
import com.stockflow.StockFlowApi.security.service.JwtService;
import com.stockflow.StockFlowApi.shared.exceptions.NotFoundException;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioLoginDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioMapper;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerDoc {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UsuarioLoginDTO loginDTO) {

        var loginSenha = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());
        var autenticado = authenticationManager.authenticate(loginSenha);

        if (autenticado.getPrincipal() instanceof Usuario usuario) {
            var token = jwtService.gerarToken(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }

        throw new BadCredentialsException("Invalid credentials");
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getMe() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        if (auth.getPrincipal() instanceof Usuario usuario) {
            var response = UsuarioMapper.paraResponseDTO(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        throw new NotFoundException("Usuário não encontrado");
    }

}
