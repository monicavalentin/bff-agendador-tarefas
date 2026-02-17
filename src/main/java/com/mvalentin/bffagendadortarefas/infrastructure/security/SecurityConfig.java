package com.mvalentin.bffagendadortarefas.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

// Anotação que define o esquema de segurança para toda a documentação da API
@SecurityScheme(
        // Dá um nome interno para esta configuração, usando a constante da classe
        name = SecurityConfig.SECURITY_SCHEME,
        // Define que a segurança é baseada no protocolo HTTP
        type = SecuritySchemeType.HTTP,
        // Informa ao Swagger que o token está no formato JWT
        bearerFormat = "JWT",
        // Define o esquema como "bearer" (aquele que adiciona a palavra 'Bearer' antes do token)
        scheme = "bearer"
)public class SecurityConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";

}
