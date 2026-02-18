package com.mvalentin.bffagendadortarefas.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneResponseDto {

    private Long id;
    private String numero;
    private String ddd;
}