package com.ceiba.suscripcion;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.math.BigDecimal;

public class ValidadorEntidad {

    private static final String TIPO_SUSCRIPCION_POR_MES = "XXX";
    private static final String TIPO_SUSCRIPCION_POR_QUINCENA = "XV";

    private static final BigDecimal VALOR_SUSCRIPCION_MENSUAL = new BigDecimal(70000.00);
    private static final BigDecimal VALOR_SUSCRIPCION_QUINCENAL = new BigDecimal(40000.00);;

    private ValidadorEntidad(){};

    public static void validarTipoSuscripcion(String tipoSuscripcion, String mensaje){
         if(!tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_MES) &&
                 !tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_QUINCENA)){
             throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public static void validarValorSuscripcion(BigDecimal valorSuscripcion, String mensaje){
        if(!valorSuscripcion.equals(VALOR_SUSCRIPCION_MENSUAL) &&
                !valorSuscripcion.equals(VALOR_SUSCRIPCION_QUINCENAL)) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }
}
