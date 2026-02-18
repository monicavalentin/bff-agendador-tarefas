package com.mvalentin.bffagendadortarefas.business.service;

import com.mvalentin.bffagendadortarefas.business.dto.out.TarefasResponseDto;
import com.mvalentin.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void EnviaEmail(TarefasResponseDto dto){
        emailClient.enviaEmail(dto);
    }

}
