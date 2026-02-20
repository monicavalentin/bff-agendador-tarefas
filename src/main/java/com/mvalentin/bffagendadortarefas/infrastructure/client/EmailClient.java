package com.mvalentin.bffagendadortarefas.infrastructure.client;

import com.mvalentin.bffagendadortarefas.business.dto.out.TarefasResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviaEmail (@RequestBody TarefasResponseDto dto);

}