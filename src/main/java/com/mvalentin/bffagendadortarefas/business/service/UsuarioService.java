package com.mvalentin.bffagendadortarefas.business.service;

import com.mvalentin.bffagendadortarefas.business.dto.EnderecoDto;
import com.mvalentin.bffagendadortarefas.business.dto.TelefoneDto;
import com.mvalentin.bffagendadortarefas.business.dto.UsuarioDto;
import com.mvalentin.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDto salvaUsuario(UsuarioDto usuarioDto) {
        return client.salvaUsuario(usuarioDto);
    }

    public String loginUsuario(UsuarioDto usuarioDto) {
        return client.login(usuarioDto);
    }

    public UsuarioDto buscaUsuarioByEmail(String email, String token) {
        return client.buscaUsuario(email, token);
    }

    public void deletaUsuarioByEmail(String email, String token) {
        client.deletaUsuarPorEmail(email, token);
    }

    public UsuarioDto atualizaDadosUsuario( UsuarioDto dto, String token) {
        return client.atualizaDadoUsuario(dto, token);
    }

    public EnderecoDto atualizaDadosEndereco(Long idEndereco, EnderecoDto enderecoDto, String token) {
        return client.atualizaEndereco(enderecoDto,idEndereco,token);
    }

    public TelefoneDto atualizaDadosTelefone(Long idTelefone, TelefoneDto telefoneDto, String token) {
        return client.atualizaTelefone(telefoneDto,idTelefone,token);

    }

    public EnderecoDto cadastraEndereco(String token, EnderecoDto enderecoDto){
        return client.cadastraEndereco(enderecoDto, token);

    }

    public TelefoneDto cadastraTelefone(String token, TelefoneDto telefoneDto){
        return client.cadastraTelefone(telefoneDto, token);

    }
}