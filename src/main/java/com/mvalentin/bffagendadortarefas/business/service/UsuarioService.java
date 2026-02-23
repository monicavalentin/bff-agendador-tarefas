package com.mvalentin.bffagendadortarefas.business.service;

import com.mvalentin.bffagendadortarefas.business.dto.in.EnderecoRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.LoginRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.TelefoneRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.in.UsuarioRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.EnderecoResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.TelefoneResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.UsuarioResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.ViaCepResponseDto;
import com.mvalentin.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioResponseDto salvaUsuario(UsuarioRequestDto usuarioDto) {
        return client.salvaUsuario(usuarioDto);
    }

    public String loginUsuario(LoginRequestDto usuarioDto) {
        return client.login(usuarioDto);
    }

    public UsuarioResponseDto buscaUsuarioByEmail(String email, String token) {
        return client.buscaUsuario(email, token);
    }

    public void deletaUsuarioByEmail(String email, String token) {
        client.deletaUsuarPorEmail(email, token);
    }

    public UsuarioResponseDto atualizaDadosUsuario(UsuarioRequestDto dto, String token) {
        return client.atualizaDadoUsuario(dto, token);
    }

    public EnderecoResponseDto atualizaDadosEndereco(Long idEndereco, EnderecoRequestDto enderecoDto, String token) {
        return client.atualizaEndereco(enderecoDto,idEndereco,token);
    }

    public TelefoneResponseDto atualizaDadosTelefone(Long idTelefone, TelefoneRequestDto telefoneDto, String token) {
        return client.atualizaTelefone(telefoneDto,idTelefone,token);

    }

    public EnderecoResponseDto cadastraEndereco(String token, EnderecoRequestDto enderecoDto){
        return client.cadastraEndereco(enderecoDto, token);

    }

    public TelefoneResponseDto cadastraTelefone(String token, TelefoneRequestDto telefoneDto){
        return client.cadastraTelefone(telefoneDto, token);

    }

    public ViaCepResponseDto buscarDadosEnderecoPorCep(String cep){
        return client.buscarDadosCep(cep);
    }
}