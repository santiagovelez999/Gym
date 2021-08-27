package com.ceiba.suscripcion.servicio;

import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ComandoSuscripcionTestDataBuilder {

        private Long idSuscripcion;
        private Long idCliente;
        private BigDecimal valorSuscripcion;
        private String tipoSuscripcion;
        private LocalDateTime fechaRegistro;

        public ComandoSuscripcionTestDataBuilder(){
            idCliente = 50L;
            valorSuscripcion = new BigDecimal(70000);
            tipoSuscripcion = "XXX";
            fechaRegistro =  LocalDateTime.now();
        }

        public ComandoSuscripcionTestDataBuilder conIdCliente(Long idCliente){
            this.idCliente = idCliente;
            return this;
        }

        public ComandoSuscripcion build() {
            return new ComandoSuscripcion(idSuscripcion, idCliente, valorSuscripcion, tipoSuscripcion, fechaRegistro);
        }

}
