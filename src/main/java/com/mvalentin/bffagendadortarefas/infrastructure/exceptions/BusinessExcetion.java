package com.mvalentin.bffagendadortarefas.infrastructure.exceptions;

public class BusinessExcetion extends  RuntimeException{

    public BusinessExcetion (String mensagem){
        super(mensagem);
    }

    public BusinessExcetion (String mensagem, Throwable throwable){
        super(mensagem);
    }

}
