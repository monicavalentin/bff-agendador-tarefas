package com.mvalentin.bffagendadortarefas.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDto {

    private String nome;
    private String email;
    private String senha;
    private List<TelefoneRequestDto> telefones;
    private List<EnderecoRequestDto> enderecos;
}