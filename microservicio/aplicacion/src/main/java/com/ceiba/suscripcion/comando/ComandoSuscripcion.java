package com.ceiba.suscripcion.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSuscripcion {

    private Long idSuscripcion;
    private Long idCliente;
    private BigDecimal valorSuscripcion;
    private String tipoSuscripcion;
    private LocalDateTime fechaRegistro;
}
