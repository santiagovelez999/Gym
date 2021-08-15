package com.ceiba.suscripcion.modelo.entidad;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Suscripcion {

    private Long idSuscripcion;
    private Long idCliente;
    private BigDecimal valorSuscripcion;
    private String tipoSuscripcion;
    private LocalDateTime fechaRegistro;

    public Suscripcion(Long idSuscripcion, Long idCliente, BigDecimal valorSuscripcion,
                       String tipoSuscripcion, LocalDateTime fechaRegistro) {
        this.idSuscripcion = idSuscripcion;
        this.idCliente = idCliente;
        this.valorSuscripcion = valorSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaRegistro = fechaRegistro;
    }
}
