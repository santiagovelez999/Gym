package com.ceiba.suscripcion;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class ValidadorEntidad {

    private static final String TIPO_SUSCRIPCION_POR_MES = "XXX";
    private static final String TIPO_SUSCRIPCION_POR_QUINCENA = "XV";

    private static final BigDecimal VALOR_SUSCRIPCION_MENSUAL = new BigDecimal(70000.00);
    private static final BigDecimal VALOR_SUSCRIPCION_QUINCENAL = new BigDecimal(40000.00);

    private static final String[] DIAS_SEMANA_DESCUENTO = {"WEDNESDAY","THURSDAY"};

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

    public static void validarRelacionTipoSuscripcionYValorSuscripcion(String tipoSuscripcion,
                                                                       BigDecimal valorSuscripcion,
                                                                       String mensaje){
        if(tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_MES)){
            if(!valorSuscripcion.equals(VALOR_SUSCRIPCION_MENSUAL)){
                throw new ExcepcionValorInvalido(mensaje);
            }
        }else if(tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_QUINCENA)){
            if(!valorSuscripcion.equals(VALOR_SUSCRIPCION_QUINCENAL)){
                throw new ExcepcionValorInvalido(mensaje);
            }
        }
    }

    public static BigDecimal validarDescuentoValorSuscripcion(String tipoSuscripcion,
                                                              BigDecimal valorSuscripcion,
                                                              LocalDateTime fechaRegistro){
        if(tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_MES)){
            List<String> listDays = Arrays.asList(DIAS_SEMANA_DESCUENTO);
            if(listDays.contains(fechaRegistro.getDayOfWeek().toString())){
                return aplicarDescuentoMensual();
            }
        }
        return valorSuscripcion;
    }

    private static BigDecimal aplicarDescuentoMensual(){
        int descuento = (VALOR_SUSCRIPCION_MENSUAL.intValueExact())*7/100;
        BigDecimal valorAplicandoDescuento =
                new BigDecimal(VALOR_SUSCRIPCION_MENSUAL.intValueExact() - descuento);
        return valorAplicandoDescuento;
    }
}
