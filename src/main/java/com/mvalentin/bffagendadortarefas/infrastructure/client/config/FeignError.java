package com.mvalentin.bffagendadortarefas.infrastructure.client.config;

import com.mvalentin.bffagendadortarefas.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        // Extrai a mensagem de erro do corpo da resposta da API externa
        String msgErro = msgErro(response);

        switch (response.status()) {
            case 401:
                return new UnauthorizedException("Erro " + msgErro);
            case 400:
                return new IllegalArgumentsException("Erro " + msgErro);
            case 403:
                return new ResourceNotFoundException("Erro " + msgErro);
            case 409:
                return new ConflictException("Erro " + msgErro);
            default: return new BusinessExcetion("Erro " + msgErro);
        }

    }
    /**
     * Método auxiliar para converter o corpo (body) da resposta em String.
     * Utiliza StandardCharsets.UTF_8 para evitar erros de acentuação.
     */
    private String msgErro(Response response){
        try {
            // CORREÇÃO: Se o corpo for nulo, retorna vazio para evitar NullPointerException
            if(Objects.isNull(response.body())){
                return "";
            }
            // Lê os bytes do InputStream e converte para texto
            return new String (response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            // Se houver falha na leitura dos dados, lança erro de tempo de execução
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
