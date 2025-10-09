package com.servicepro.alpha.mensageria;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper obj;


    public void enviaMensagem(String nomefila,Object mensagem) {
        try {
            //converte a mensagem para um json e envia
            String mensagemjson = this.obj.writeValueAsString(mensagem);
            this.rabbitTemplate.convertAndSend(nomefila,mensagemjson);

        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
