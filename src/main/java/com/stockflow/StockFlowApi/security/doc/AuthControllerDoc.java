package com.stockflow.StockFlowApi.security.doc;

import com.stockflow.StockFlowApi.security.dto.TokenResponseDTO;
import com.stockflow.StockFlowApi.shared.exceptions.ErrorMessageResponse;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

}
