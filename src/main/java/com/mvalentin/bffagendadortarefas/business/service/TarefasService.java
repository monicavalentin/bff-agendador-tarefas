package com.mvalentin.bffagendadortarefas.business.service;


import com.mvalentin.bffagendadortarefas.business.dto.TarefasDto;
import com.mvalentin.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.mvalentin.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDto salvarTarefa(String token, TarefasDto tarefasDto) {
        return tarefasClient.salvarTarefa(tarefasDto, token);
    }

    public List<TarefasDto> buscaTarefasAgendadorPorPeriodo(LocalDateTime dataInicial,
                                                            LocalDateTime dataFinal,
                                                            String token) {
        return tarefasClient.buscaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDto> buscaTarefasByEmail(String token) {
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaById(String id, String token) {
        tarefasClient.deletaTarefaPorId(id, token);
    }

    public TarefasDto alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(status, id, token);


    }

    public TarefasDto updateTarefas(TarefasDto tarefasDto, String id, String token) {
        return tarefasClient.updateTarefas(tarefasDto, id, token);

    }


}