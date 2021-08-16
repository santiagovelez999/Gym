package com.ceiba.suscripcion.modelo.entidad;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.suscripcion.ValidadorEntidad.validarTipoSuscripcion;
import static com.ceiba.suscripcion.ValidadorEntidad.validarValorSuscripcion;
import static com.ceiba.suscripcion.ValidadorEntidad.validarRelacionTipoSuscripcionYValorSuscripcion;
import static com.ceiba.suscripcion.ValidadorEntidad.validarDescuentoValorSuscripcion;

@Getter
public class Suscripcion {

    private static final String SE_DEBE_INGRESAR_ID_DEL_CLIENTE = "Se debe ingresar el id del cliente";
    private static final String SE_DEBE_INGRESAR_VALOR_DE_SUSCRIPCION = "Se debe ingresar el valor de la suscripción";
    private static final String TIPO_SUSCRIPCION_INVALIDO = "El tipo de suscripción es invalido";
    private static final String VALOR_SUSCRIPCION_INVALIDO = "El valor de la suscripción no es valido";
    private static final String VALOR_TIPO_SUSCRIPCION_Y_VALOR_SUSCRIPCION_INVALIDO = "El valor de la suscripción no es valido para el tipo de suscripción";

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
        validarValorSuscripcion(valorSuscripcion, VALOR_SUSCRIPCION_INVALIDO);
        validarRelacionTipoSuscripcionYValorSuscripcion(tipoSuscripcion, valorSuscripcion,
                VALOR_TIPO_SUSCRIPCION_Y_VALOR_SUSCRIPCION_INVALIDO);
        validarDescuentoValorSuscripcion(tipoSuscripcion, valorSuscripcion, fechaRegistro);

        this.idSuscripcion = idSuscripcion;
        this.idCliente = idCliente;
        this.valorSuscripcion = valorSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaRegistro = fechaRegistro;
    }
}
