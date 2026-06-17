package com.stockflow.StockFlowApi.security.doc;

import com.stockflow.StockFlowApi.security.dto.TokenResponseDTO;
import com.stockflow.StockFlowApi.shared.exceptions.ErrorMessageResponse;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioLoginDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioRegisterDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Autenticação e Cadastro",
        description = "Operações para Autenticação e Cadastro de usuários"
)
public interface AuthControllerDoc {


    @Operation(
            summary = "Autenticar usuários",
            description = "Autentica usuários previamente cadastrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuário autenticado com sucesso",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = TokenResponseDTO.class,
                                            examples = """
                                                    {
                                                      "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cC...",
                                                      "tokenType": "bearer",
                                                      "expiresIn": 10800,
                                                      "usuario": {
                                                            "id": 1,
                                                            "nome": "João",
                                                            "email": "joao@email.com",
                                                            "cargo": "ADMINISTRADOR"
                                                      }
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Não autenticado",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 401,
                                                      "error": "Unauthorized",
                                                      "message": "Falha ao Autenticar",
                                                      "path": "/login"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Corpo JSON mal formado",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "Corpo JSON mal formado",
                                                      "path": "/login"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    ResponseEntity<TokenResponseDTO> login(

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credenciais de acesso",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioLoginDTO.class),
                            examples = @ExampleObject(
                                    name = "Login Válido",
                                    value = """
                                            {
                                                "login": "meuLogin@123",
                                                "senha": "senha@123"
                                            }
                                            """

                            )
                    )
            )
            UsuarioLoginDTO loginDTO
    );



    @Operation(
            summary = "Registra usuários",
            description = """
                    Registra um novo usuário no sistema
                    
                    Endpoint restrito a administradores.
            
                    Necessário enviar:
                    Authorization: Bearer <jwt>
            
                    Permissão requerida: ADMINISTRADOR
                    """,
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuário cadastrado com sucesso",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = UsuarioResponseDTO.class,
                                            example = """
                                                    {
                                                      "id": 1,
                                                      "nome": "João",
                                                      "email": "joao@gmail.com",
                                                      "login": "meuLogin@123",
                                                      "cargo": "ADMINISTRADOR",
                                                      "dataCriacao": "9999-12-12T00:00:00.000Z",
                                                      "dataAtualizacao": "9999-12-12T00:00:00.000Z",
                                                      "ativo": true
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Corpo JSON mal formado",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "Corpo JSON mal formado",
                                                      "path": "/register"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Requisitante falhou na autenticação, token invalido ou expirado",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 401,
                                                      "error": "Unauthorized",
                                                      "message": "Falha ao Autenticar",
                                                      "path": "/register"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Acesso negado, requisitante não tem permissão suficiente para o serviço",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 403,
                                                      "error": "Forbidden",
                                                      "message": "Acesso negado",
                                                      "path": "/register"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Usuário em conflito com o estado do servidor ou o banco. Geralmente por credenciais únicas já registradas",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 409,
                                                      "error": "Conflict",
                                                      "message": "Conflito",
                                                      "path": "/register"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    ResponseEntity<UsuarioResponseDTO> registrar(

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Usuário a ser registrado",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioRegisterDTO.class),
                            examples = @ExampleObject(
                                    name = "Login Válido",
                                    description = "Um usuário válido com todos os campos no formato esperado",
                                    value = """
                                            {
                                              "nome": "João",
                                              "email": "joao@gmail.com",
                                              "login": "meuLogin@123",
                                              "senha": "senha@123",
                                              "cargo": "ADMINISTRADOR"
                                            }
                                            """
                            )
                    )
            )
            UsuarioRegisterDTO registerDTO

    );

}
