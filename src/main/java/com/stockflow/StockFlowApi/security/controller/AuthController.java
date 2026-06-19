package com.stockflow.StockFlowApi.security.controller;

import com.stockflow.StockFlowApi.security.doc.AuthControllerDoc;
import com.stockflow.StockFlowApi.security.dto.TokenResponseDTO;
import com.stockflow.StockFlowApi.security.service.JwtService;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioLoginDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioRegisterDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import com.stockflow.StockFlowApi.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthControllerDoc {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
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

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody @Valid UsuarioRegisterDTO registerDTO) {
        var response = usuarioService.saveUsuario(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
