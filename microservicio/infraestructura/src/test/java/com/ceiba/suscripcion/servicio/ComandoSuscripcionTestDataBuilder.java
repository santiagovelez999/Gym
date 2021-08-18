package com.ceiba.suscripcion.servicio;

import com.ceiba.suscripcion.comando.ComandoSuscripcion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            fechaRegistro =  cambiarFormatoFecha("2021-08-12 12:00:00");
        }

        public ComandoSuscripcion build() {
            return new ComandoSuscripcion(idSuscripcion, idCliente, valorSuscripcion, tipoSuscripcion, fechaRegistro);
        }

    private LocalDateTime cambiarFormatoFecha(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(fecha, formato);
    }

}
