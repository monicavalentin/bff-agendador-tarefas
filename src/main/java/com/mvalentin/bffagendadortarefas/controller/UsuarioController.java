package com.mvalentin.bffagendadortarefas.controller;


import com.mvalentin.bffagendadortarefas.business.dto.EnderecoDto;
import com.mvalentin.bffagendadortarefas.business.dto.TelefoneDto;
import com.mvalentin.bffagendadortarefas.business.dto.UsuarioDto;
import com.mvalentin.bffagendadortarefas.business.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody UsuarioDto usuarioDto) {
        // 1. CHAMA O SERVICE: É aqui que a mágica (validação, senha, save) acontece
        UsuarioDto usuarioSalvo = usuarioService.salvaUsuario(usuarioDto);

        // 2. RETORNA A RESPOSTA: Com o objeto que o Service processou e salvou
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.loginUsuario(usuarioDto));
    }


    @PostMapping("/endereco")
    public  ResponseEntity<EnderecoDto> cadastraEndereco(@RequestBody EnderecoDto enderecoDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,enderecoDto));
    }

    @PostMapping("/telefone")
    public  ResponseEntity<TelefoneDto> cadastraTelefone(@RequestBody TelefoneDto telefoneDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,telefoneDto));
    }


    @GetMapping
    public ResponseEntity<UsuarioDto> buscaUsuario(@RequestParam("email") String email,
                                                   @RequestHeader("Authorization") String token){
        UsuarioDto dto = usuarioService.buscaUsuarioByEmail(email,token);
        return ResponseEntity.ok(dto);
    }
    @PutMapping
    public ResponseEntity<UsuarioDto> atualizaDadoUsuario(@RequestBody UsuarioDto dto,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto, token));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDto> atualizaEndereco(@RequestBody EnderecoDto enderecoDto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosEndereco(id,enderecoDto,token));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDto> atualizaTelefone(@RequestBody TelefoneDto telefoneDto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(id,telefoneDto,token));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarPorEmail(@PathVariable String email,
                                                    @RequestHeader("Authorization") String token){
       usuarioService.deletaUsuarioByEmail(email, token);
       return ResponseEntity.noContent().build(); // Retorna 204 - Indica que a requisição foi processada com sucesso.
    }

}