package com.mvalentin.bffagendadortarefas.controller;


import com.mvalentin.bffagendadortarefas.business.dto.EnderecoDto;
import com.mvalentin.bffagendadortarefas.business.dto.TelefoneDto;
import com.mvalentin.bffagendadortarefas.business.dto.UsuarioDto;
import com.mvalentin.bffagendadortarefas.business.service.UsuarioService;
import com.mvalentin.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salva Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody UsuarioDto usuarioDto) {
        // 1. CHAMA O SERVICE: É aqui que a mágica (validação, senha, save) acontece
        UsuarioDto usuarioSalvo = usuarioService.salvaUsuario(usuarioDto);

        // 2. RETORNA A RESPOSTA: Com o objeto que o Service processou e salvou
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PostMapping("/login")
    @Operation(summary = "Login  Usuário", description = "Login do  usuário")
    @ApiResponse(responseCode="200",description="Usuário logao")
    @ApiResponse(responseCode="401",description="Credenciais inválidas")
    @ApiResponse(responseCode="500",description="Errode servidor")
    public ResponseEntity<String> login(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.loginUsuario(usuarioDto));
    }


    @PostMapping("/endereco")
    @Operation(summary = "Cadastra Endereço", description = "Cadastra endereço de usuários")
    @ApiResponse(responseCode="200",description="Endereço cadastrado com sucesso")
    @ApiResponse(responseCode="500",description="Erro de servidor")
    public  ResponseEntity<EnderecoDto> cadastraEndereco(@RequestBody EnderecoDto enderecoDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,enderecoDto));
    }

    @Operation(summary = "Cadastra telefone", description = "Cadastra telefone de usuários")
    @ApiResponse(responseCode="200",description="Telefone cadastrado com sucesso")
    @ApiResponse(responseCode="500",description="Erro de servidor")
    @PostMapping("/telefone")
    public  ResponseEntity<TelefoneDto> cadastraTelefone(@RequestBody TelefoneDto telefoneDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,telefoneDto));
    }


    @GetMapping
    @Operation(summary = "Buscar dados de usuários por e-mail", description = "Busca dados de usuários")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDto> buscaUsuario(@RequestParam("email") String email,
                                                   @RequestHeader("Authorization") String token){
        UsuarioDto dto = usuarioService.buscaUsuarioByEmail(email,token);
        return ResponseEntity.ok(dto);
    }
    @PutMapping
    @Operation(summary = "Atualiza dados de usuários", description = "Atualiza dados de usuário")
    @ApiResponse(responseCode = "200", description = " Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDto> atualizaDadoUsuario(@RequestBody UsuarioDto dto,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuários", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = " Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDto> atualizaEndereco(@RequestBody EnderecoDto enderecoDto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosEndereco(id,enderecoDto,token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuários", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = " Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDto> atualizaTelefone(@RequestBody TelefoneDto telefoneDto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(id,telefoneDto,token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuários por Id", description = "Deleta  usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontradoj")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarPorEmail(@PathVariable String email,
                                                    @RequestHeader("Authorization") String token){
       usuarioService.deletaUsuarioByEmail(email, token);
       return ResponseEntity.noContent().build(); // Retorna 204 - Indica que a requisição foi processada com sucesso.
    }

}