package com.stockflow.StockFlowApi.security.controller;

import com.stockflow.StockFlowApi.user.entity.User;
import com.stockflow.StockFlowApi.user.entity.UserMapper;
import com.stockflow.StockFlowApi.user.entity.UserRegisterDTO;
import com.stockflow.StockFlowApi.user.entity.UserResponseDTO;
import com.stockflow.StockFlowApi.user.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO registerDTO) {

        var hashPass = passwordEncoder.encode(registerDTO.password());

        User user = new User(
                registerDTO.name(),
                registerDTO.email(),
                registerDTO.login(),
                hashPass,
                registerDTO.role()
        );

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponseDTO(user));
    }
}
