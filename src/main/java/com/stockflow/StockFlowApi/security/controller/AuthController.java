package com.stockflow.StockFlowApi.security.controller;

import com.stockflow.StockFlowApi.security.dto.TokenResponseDTO;
import com.stockflow.StockFlowApi.security.service.JwtService;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioLoginDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioMapper;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioRegistroDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.entity.*;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UsuarioLoginDTO loginDTO) {

        var loginSenha = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());
        var autenticado = authenticationManager.authenticate(loginSenha);
        Usuario usuario = (Usuario) autenticado.getPrincipal();

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var token = jwtService.gerarToken(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody @Valid UsuarioRegistroDTO registerDTO) {

        var senhaCodificada = passwordEncoder.encode(registerDTO.senha());

        Usuario usuario = new Usuario(
                registerDTO.nome(),
                registerDTO.email(),
                registerDTO.login(),
                senhaCodificada,
                registerDTO.cargo()
        );

        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.paraResponseDTO(usuario));
    }
}
