package com.ceiba.suscripcion.servicio;

import com.ceiba.suscripcion.modelo.entidad.Suscripcion;

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
            fechaRegistro = LocalDateTime.now();
        }

        public Suscripcion build() {
            return new Suscripcion(idSuscripcion, idCliente, valorSuscripcion, tipoSuscripcion, fechaRegistro);
        }

}
