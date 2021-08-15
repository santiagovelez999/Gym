package com.ceiba.suscripcion.modelo.entidad;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.suscripcion.ValidadorEntidad.validarTipoSuscripcion;

@Getter
public class Suscripcion {

    private static final String SE_DEBE_INGRESAR_ID_DEL_CLIENTE = "Se debe ingresar el id del cliente";
    private static final String SE_DEBE_INGRESAR_VALOR_DE_SUSCRIPCION = "Se debe ingresar el valor de la suscripción";
    private static final String TIPO_SUSCRIPCION_INVALIDO = "El tipo de suscripción es invalido";

    private Long idSuscripcion;
    private Long idCliente;
    private BigDecimal valorSuscripcion;
    private String tipoSuscripcion;
    private LocalDateTime fechaRegistro;

    public Suscripcion(Long idSuscripcion, Long idCliente, BigDecimal valorSuscripcion,
                       String tipoSuscripcion, LocalDateTime fechaRegistro) {

        validarObligatorio(idCliente, SE_DEBE_INGRESAR_ID_DEL_CLIENTE);
        validarObligatorio(valorSuscripcion, SE_DEBE_INGRESAR_VALOR_DE_SUSCRIPCION);
        validarTipoSuscripcion(tipoSuscripcion, TIPO_SUSCRIPCION_INVALIDO);

        this.idSuscripcion = idSuscripcion;
        this.idCliente = idCliente;
        this.valorSuscripcion = valorSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaRegistro = fechaRegistro;
    }
}
