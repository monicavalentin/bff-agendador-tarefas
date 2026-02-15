package com.mvalentin.bffagendadortarefas.infrastructure.client;


import com.mvalentin.bffagendadortarefas.business.dto.EnderecoDto;
import com.mvalentin.bffagendadortarefas.business.dto.TelefoneDto;
import com.mvalentin.bffagendadortarefas.business.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDto buscaUsuario(@RequestParam("email")String email,
                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDto salvaUsuario(@RequestBody UsuarioDto usuarioDto);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDto usuarioDto);

    @PostMapping("/endereco")
    EnderecoDto cadastraEndereco(@RequestBody EnderecoDto enderecoDto,
                                 @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDto cadastraTelefone(@RequestBody TelefoneDto telefoneDto,
                                 @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDto atualizaDadoUsuario(@RequestBody UsuarioDto dto,
                                   @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDto atualizaEndereco(@RequestBody EnderecoDto enderecoDto,
                                                 @RequestParam("id") Long id,
                                                 @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDto atualizaTelefone(@RequestBody TelefoneDto telefoneDto,
                                 @RequestParam("id") Long id,
                                 @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deletaUsuarPorEmail(@PathVariable String email,
                             @RequestHeader("Authorization") String token);

}
