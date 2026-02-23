package com.mvalentin.bffagendadortarefas.controller;

import com.mvalentin.bffagendadortarefas.business.dto.in.LoginRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.EnderecoResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.TelefoneResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.UsuarioResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.EnderecoRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.TelefoneRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.UsuarioRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.ViaCepResponseDto;
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
    @Operation(summary = "Salva Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponseDto> salvaUsuario(@RequestBody UsuarioRequestDto usuarioDto) {
        // 1. CHAMA O SERVICE: É aqui que a mágica (validação, senha, save) acontece
        UsuarioResponseDto usuarioSalvo = usuarioService.salvaUsuario(usuarioDto);

        // 2. RETORNA A RESPOSTA: Com o objeto que o Service processou e salvou
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PostMapping("/login")
    @Operation(summary = "Login  Usuário", description = "Login do  usuário")
    @ApiResponse(responseCode = "200",description="Usuário logao")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "500",description="Errode servidor")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginDto) {
        return ResponseEntity.ok(usuarioService.loginUsuario(loginDto));
    }


    @PostMapping("/endereco")
    @Operation(summary = "Cadastra Endereço", description = "Cadastra endereço de usuários")
    @ApiResponse(responseCode = "200",description="Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "500",description="Erro de servidor")
    public  ResponseEntity<EnderecoResponseDto> cadastraEndereco(@RequestBody EnderecoRequestDto enderecoDto,
                                                                 @RequestHeader(value = "Authorization", required=false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,enderecoDto));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Cadastra telefone", description = "Cadastra telefone de usuários")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "200",description="Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "500",description="Erro de servidor")
    public  ResponseEntity<TelefoneResponseDto> cadastraTelefone(@RequestBody TelefoneRequestDto telefoneDto,
                                                                 @RequestHeader(value = "Authorization", required=false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,telefoneDto));
    }

    @GetMapping
    @Operation(summary = "Busca dados de usuários por e-mail", description = "Busca dados de usuários")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponseDto> buscaUsuario(@RequestParam("email") String email,
                                                           @RequestHeader(value = "Authorization", required=false) String token){
        UsuarioResponseDto dto = usuarioService.buscaUsuarioByEmail(email,token);
        return ResponseEntity.ok(dto);
    }
    @PutMapping
    @Operation(summary = "Atualiza dados de usuários", description = "Atualizar dados de usuários")
    @ApiResponse(responseCode = "200", description = " Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponseDto> atualizaDadoUsuario(@RequestBody UsuarioRequestDto dto,
                                                                  @RequestHeader(value = "Authorization", required=false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuários", description = "Atualiza endereço de usuários")
    @ApiResponse(responseCode = "200", description = " Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoResponseDto> atualizaEndereco(@RequestBody EnderecoRequestDto enderecoDto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(value = "Authorization", required=false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosEndereco(id,enderecoDto,token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuários", description = "Atualiza telefone de usuários")
    @ApiResponse(responseCode = "200", description = " Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneResponseDto> atualizaTelefone(@RequestBody TelefoneRequestDto telefoneDto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(value = "Authorization", required=false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(id,telefoneDto,token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuários por Id", description = "Deleta  usuários por ID")
    @ApiResponse(responseCode = "200", description = "Usuário deletado")
    @ApiResponse(responseCode = "401",description="Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontradoj")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarPorEmail(@PathVariable String email,
                                                    @RequestHeader(value = "Authorization", required=false) String token){
       usuarioService.deletaUsuarioByEmail(email, token);
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca dados de endereco pelo  cep", description = "Busca dados de  endereço recebendo um  cep")
    @ApiResponse(responseCode = "200", description = "Dadoas de endereço retornado com sucesso")
    @ApiResponse(responseCode = "400", description = "Cep inválido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepResponseDto> buscarDadosEnderecoPorCep(@PathVariable("cep") String cep) {
       return ResponseEntity.ok(usuarioService.buscarDadosEnderecoPorCep(cep));
    }

}