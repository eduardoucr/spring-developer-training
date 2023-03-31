package com.pfcti.springjms.pubsub;

import com.pfcti.springdata.dto.CuentaDto;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionClientes {

   //Estas tres variables las da la cuenta de TWILIO cuando se crea;
   private final static String TWILIO_ACCOUNT_SID = "";
    private final static String TWILIO_AUTH_TOKEN = "";
    private final static String TWILIO_MESSAGE_SID = "";


    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajeParaEnvioFormatoCliente(Message<CuentaDto> message) {
        CuentaDto cuentaDto = message.getPayload();
        log.info("consumirMensajeParaEnvioFromatoCliente");
        // Puede tener la logica que necesitemos
        String sms = "Hola desde Twilio SMS";
        Twilio.init(TWILIO_ACCOUNT_SID.trim(), TWILIO_AUTH_TOKEN.trim());
        com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+50688356165"),
                TWILIO_MESSAGE_SID.trim(), sms).create();
        //Envio SMS con formato a Cliente
        return MessageBuilder.withPayload("Mensaje recibido por ProcesadorNotificacionClientes")
                .build();
    }

}
