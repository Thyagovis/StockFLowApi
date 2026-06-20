package com.stockflow.StockFlowApi.usuario.doc;

import com.stockflow.StockFlowApi.shared.exceptions.ErrorMessageResponse;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioPatchDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioRegisterDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(
        name = "Serviços de Usuários",
        description = """
                Operações de listagem, procura, edição e deleção de usuários
                Endpoints restrito a administradores.
            
                Necessário enviar:
                Authorization: Bearer <jwt>
            
                Permissão requerida: ADMINISTRADOR
                """
)
public interface UsuarioControllerDoc {


    @Operation(
            summary = "Procura um usuário",
            description = "Procura um usuário pelo id informado no path",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuário encontrado",
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
                                                      "path": "/usuarios/{id}"
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
                                                      "path": "/usuarios/{id}"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 404,
                                                      "error": "Not Found",
                                                      "message": "Não Encontrado",
                                                      "path": "/usuarios/{id}"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    ResponseEntity<UsuarioResponseDTO> getById(
            @Parameter(
                    description = "ID do usuário",
                    example = "1",
                    required = true
            )
            @PathVariable
            Long id
    );



    @Operation(
            summary = "Lista usuários",
            description = "Lista usuários com suporte a filtro por status de ativação",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuários Listados",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = UsuarioResponseDTO.class,
                                            example = """
                                                    [
                                                        {
                                                          "id": 1,
                                                          "nome": "João",
                                                          "email": "joao@gmail.com",
                                                          "login": "meuLogin@123",
                                                          "cargo": "ADMINISTRADOR",
                                                          "dataCriacao": "9999-12-12T00:00:00.000Z",
                                                          "dataAtualizacao": "9999-12-12T00:00:00.000Z",
                                                          "ativo": true
                                                        },
                                                        {
                                                          "id": 2,
                                                          "nome": "Maria",
                                                          "email": "maria@gmail.com",
                                                          "login": "meuLogin@456",
                                                          "cargo": "ADMINISTRADOR",
                                                          "dataCriacao": "9999-12-12T00:00:00.000Z",
                                                          "dataAtualizacao": "9999-12-12T00:00:00.000Z",
                                                          "ativo": false
                                                        }
                                                    ]
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
                                                      "path": "/usuarios"
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
                                                      "path": "/usuarios"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    ResponseEntity<List<UsuarioResponseDTO>> getAll(
            @Parameter(
                    description = "Filtrar usuários por status de ativação (true = ativos, false = inativos). Se não informado, retorna todos.",
                    example = "true"
            )
            @RequestParam(name = "ativo", required = false)
            Boolean ativo
    );



    @Operation(
            summary = "Deleta Usuário",
            description = "Deleta usuário pelo id informado no path",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Usuário deletado com sucesso (sem body)"
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
                                                      "path": "/usuarios/{id}"
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
                                                      "path": "/usuarios/{id}"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 404,
                                                      "error": "Not Found",
                                                      "message": "Não Encontrado",
                                                      "path": "/usuarios/{id}"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    ResponseEntity<Void> deleteById(
            @Parameter(
                    description = "ID do usuário",
                    example = "1",
                    required = true
            )
            @PathVariable
            Long id
    );


    @Operation(
            summary = "Editar usuários",
            description = "Atualiza parcialmente os dados do usuário. Apenas campos enviados serão alterados.",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuário editado com sucesso",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = UsuarioResponseDTO.class,
                                            example = """
                                                    {
                                                      "id": 1,
                                                      "nome": "João Atualizado",
                                                      "email": "novoemail@gmail.com",
                                                      "login": "meuLogin@123",
                                                      "cargo": "ADMINISTRADOR",
                                                      "dataCriacao": "9999-12-12T00:00:00.000Z",
                                                      "dataAtualizacao": "9999-12-13T00:00:00.000Z",
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
                                                      "path": "/usuarios/{id}"
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
                                                      "path": "/usuarios/{id}"
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
                                                      "path": "/usuarios/{id}"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 404,
                                                      "error": "Not Found",
                                                      "message": "Não Encontrado",
                                                      "path": "/usuarios/{id}"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Informações em conflito com o estado do servidor ou do banco. Pode indicar credenciais ja registradas no banco",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorMessageResponse.class,
                                            example = """
                                                    {
                                                      "timestamp": "9999-12-12T00:00:00.000Z",
                                                      "status": 409,
                                                      "error": "Conflict",
                                                      "message": "Conflito",
                                                      "path": "/usuarios/{id}"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    ResponseEntity<UsuarioResponseDTO> patchById(
            @Parameter(
                    description = "ID do usuário",
                    example = "1",
                    required = true
            )
            @PathVariable
            Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = """
                        Objeto contendo os campos que serão atualizados.
                        
                        Apenas os campos enviados serão modificados.
                        Campos nulos ou ausentes serão ignorados.
                        """,
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UsuarioPatchDTO.class),
                            examples = @ExampleObject(
                                    name = "Atualização parcial",
                                    value = """
                                        {
                                          "nome": "João Atualizado",
                                          "email": "novoemail@gmail.com"
                                        }
                                        """
                            )
                    )
            )
            @Valid
            @RequestBody
            UsuarioPatchDTO patchDTO
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
                                                      "path": "/usuarios"
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
                                                      "path": "/usuarios"
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
                                                      "path": "/usuarios"
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
                                                      "path": "/usuarios"
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
