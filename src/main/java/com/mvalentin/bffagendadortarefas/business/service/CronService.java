package com.mvalentin.bffagendadortarefas.business.service;
;
import com.mvalentin.bffagendadortarefas.business.dto.in.LoginRequestDto;
import com.mvalentin.bffagendadortarefas.business.dto.out.TarefasResponseDto;
import com.mvalentin.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final UsuarioService usuarioService;
    private final EmailService emailService;

    // metodos estáticos
    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        // TODO - Retornar nessa classe para debugar e resolver a questão da diferença de horas

        // 0.  Define o fuso horário de Brasília
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

        // 1. Realiza o login para obter um token de autenticação válido

        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");

        // 2. Define o intervalo de busca: exatamente daqui a 1 hora
        LocalDateTime horaFutura = LocalDateTime.now(zoneId).plusHours(1);

        // 3. Define a margem de segurança de 5 minutos (alinhada com a execução da Cron)
        LocalDateTime horaFuturaMaisCinto = LocalDateTime.now(zoneId).plusHours(5);

        // 4. Chama o serviço passando o período e o token para autorização
        List<TarefasResponseDto> listaTarefas = tarefasService.buscaTarefasAgendadorPorPeriodo(horaFutura,
                horaFuturaMaisCinto, token);
        log.info("Tarefas Encontradas" + listaTarefas);

        listaTarefas.forEach(tarefa ->{ emailService.enviaEmail(tarefa);
            log.info("Notificação enviada para o e-mail do usuário");
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);});
        log.info("Finalizada busca e notificação de tarefas");
    }

    public String login (LoginRequestDto dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDto converterParaRequestDTO(){
        return LoginRequestDto.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}