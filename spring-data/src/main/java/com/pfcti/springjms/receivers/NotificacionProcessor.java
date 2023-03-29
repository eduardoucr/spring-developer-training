package com.pfcti.springjms.receivers;

import com.pfcti.springjms.dto.NotificacionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificacionProcessor {

    @JmsListener(destination = "smsReceiverJms")
    public void processMessage(NotificacionDto notificacionDto){
        //Logica de envio de SMS
        //aqui va el código para llamar a twilio
        log.info("Sms info: {}" , notificacionDto);
    };

}
