package com.ceiba.suscripcion.servicio;

import com.ceiba.suscripcion.comando.ComandoSuscripcion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ComandoSuscripcionTestDataBuilder {

        private Long idSuscripcion;
        private Long idCliente;
        private BigDecimal valorSuscripcion;
        private String tipoSuscripcion;
        private LocalDateTime fechaRegistro;

        public ComandoSuscripcionTestDataBuilder(){
            idCliente = 1L;
            valorSuscripcion = new BigDecimal(70000);
            tipoSuscripcion = "XXX";
            fechaRegistro =  LocalDateTime.of(2021,8,12,
                                              11,30,0);
        }

        public ComandoSuscripcion build() {
            return new ComandoSuscripcion(idSuscripcion, idCliente, valorSuscripcion, tipoSuscripcion, fechaRegistro);
        }

}
