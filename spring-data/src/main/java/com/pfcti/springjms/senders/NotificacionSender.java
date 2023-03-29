package com.pfcti.springjms.senders;

import com.pfcti.springjms.dto.NotificacionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificacionSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendSms(NotificacionDto notificacionDto){
        log.info("Enviando sms a la cola {} con numero de telefono", notificacionDto.getPhoneNumber());
        jmsTemplate.convertAndSend("smsReceiverJms",notificacionDto);
    }

}
