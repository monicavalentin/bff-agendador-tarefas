package com.mvalentin.bffagendadortarefas.infrastructure.client;


import com.mvalentin.bffagendadortarefas.business.dto.in.EnderecoRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.LoginRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.TelefoneRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.UsuarioRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.EnderecoResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.TelefoneResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.UsuarioResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.ViaCepResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioResponseDto buscaUsuario(@RequestParam("email")String email,
                                    @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioResponseDto salvaUsuario(@RequestBody UsuarioRequestDto usuarioDto);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDto usuarioDto);

    @PostMapping("/endereco")
    EnderecoResponseDto cadastraEndereco(@RequestBody EnderecoRequestDto enderecoDto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneResponseDto cadastraTelefone(@RequestBody TelefoneRequestDto telefoneDto,
                                         @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioResponseDto atualizaDadoUsuario(@RequestBody UsuarioRequestDto dto,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoResponseDto atualizaEndereco(@RequestBody EnderecoRequestDto enderecoDto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneResponseDto atualizaTelefone(@RequestBody TelefoneRequestDto telefoneDto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deletaUsuarPorEmail(@PathVariable String email,
                             @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepResponseDto buscarDadosCep(@PathVariable("cep") String cep);

}
