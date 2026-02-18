package com.mvalentin.bffagendadortarefas.business.dto.out;

import com.mvalentin.bffagendadortarefas.business.dto.out.EnderecoResponseDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.TelefoneResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDto {

    private String nome;
    private String email;
    // Impede que a senha seja exposta em respostas JSON (Serialização),
    // mas permite que seja recebida em requisições de cadastro (Desserialização).
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)-porém como nossa senha é criptograda então não vamos utilizar
    private String senha;
    private List<TelefoneResponseDto> telefones;
    private List<EnderecoResponseDto> enderecos;
}