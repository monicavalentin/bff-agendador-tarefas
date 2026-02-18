package com.mvalentin.bffagendadortarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneRequestDto {

    private String numero;
    private String ddd;
}