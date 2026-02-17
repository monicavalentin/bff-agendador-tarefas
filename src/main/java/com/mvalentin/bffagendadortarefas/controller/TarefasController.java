package com.mvalentin.bffagendadortarefas.controller;

import com.mvalentin.bffagendadortarefas.business.dto.TarefasDto;
import com.mvalentin.bffagendadortarefas.business.service.TarefasService;
import com.mvalentin.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.mvalentin.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary="Salva tarefas de usuários ",description=" Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<TarefasDto> salvarTarefa(@RequestBody TarefasDto tarefasDto,
                                            @RequestHeader("Authorization")String token){

        return ResponseEntity.ok(tarefasService.salvarTarefa(token, tarefasDto));
    }

    @GetMapping("/eventos")
    @Operation(summary="Busca tarefas por período ",description=" Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDto>> buscaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadorPorPeriodo(dataInicial,dataFinal,token));
    }

    @GetMapping
    @Operation(summary="Busca lista de tarefas por e-mail de usuário",description=" Busca lista de  tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDto>> buscaTarefasPorEmail(@RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasByEmail(token));
    }

    @DeleteMapping
    @Operation(summary="Deleta tarefas por Id",description="Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200", description = "Tarefas deletada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token){
        tarefasService.deletaTarefaById(id,token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary="Altera status de tarefas",description="Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDto> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                              @RequestParam ("id") String id,
                                                              @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefasService.alteraStatus(status,id,token));
    }

    @PutMapping
    @Operation(summary="Altera dados das tarefas",description="Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDto> updateTarefas(@RequestBody TarefasDto tarefasDto,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDto,id,token));
    }

}