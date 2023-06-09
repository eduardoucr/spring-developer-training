package com.pfcti.springjms.pubsub;
import com.pfcti.springdata.dto.CuentaDto;
import com.pfcti.springdata.model.Cuenta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionEjecutivos {

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajeParaEnvioFormatoEjecutivo(Message<CuentaDto> message) {
        CuentaDto cuentaDto = message.getPayload();
        log.info("consumirMensajeParaEnvioFromatoEjecutivo");
        //puede tener la logica que necesitamos
        //Envio SMS con formato a Ejecutivo
        return MessageBuilder.withPayload("Mensaje recibido por ProcesadorNotificacionEjecutivos")
                .build();
    }

}
