package com.mvalentin.bffagendadortarefas.infrastructure.client;

import com.mvalentin.bffagendadortarefas.business.dto.in.TarefasRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.TarefasResponseDto;
import com.mvalentin.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasResponseDto salvarTarefa(@RequestBody TarefasRequestDto tarefasDto,
                                    @RequestHeader("Authorization") String token);
    @GetMapping("/eventos")
    List<TarefasResponseDto> buscaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                    @RequestHeader("Authorization") String token);
    @GetMapping
    List<TarefasResponseDto> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasResponseDto alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                               @RequestParam ("id") String id,
                                               @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasResponseDto updateTarefas(@RequestBody TarefasRequestDto tarefasDto,
                                     @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);
}
