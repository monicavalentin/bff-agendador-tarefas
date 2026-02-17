package com.mvalentin.bffagendadortarefas.infrastructure.client;

import com.mvalentin.bffagendadortarefas.business.dto.TarefasDto;
import com.mvalentin.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDto salvarTarefa(@RequestBody TarefasDto tarefasDto,
                            @RequestHeader("Authorization") String token);
    @GetMapping("/eventos")
    List<TarefasDto> buscaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                      @RequestHeader("Authorization") String token);
    @GetMapping
    List<TarefasDto> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
   TarefasDto alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                      @RequestParam ("id") String id,
                                      @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDto updateTarefas(@RequestBody TarefasDto tarefasDto,
                             @RequestParam("id") String id,
                             @RequestHeader("Authorization") String token);
}
