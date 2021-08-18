package com.ceiba.suscripcion;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public class ValidadorEntidad {

    private static final String TIPO_SUSCRIPCION_POR_MES = "XXX";
    private static final String TIPO_SUSCRIPCION_POR_QUINCENA = "XV";
    private static final int DIAS_MES = 30;
    private static final int DIAS_QUINCENA = 15;

    private static final BigDecimal VALOR_SUSCRIPCION_MENSUAL = new BigDecimal("70000");
    private static final BigDecimal VALOR_SUSCRIPCION_QUINCENAL = new BigDecimal("40000");
    private static String VALOR_DESCUENTO = "$4900";

    private static final String[] DIAS_SEMANA_DESCUENTO = {"WEDNESDAY","THURSDAY"};

    private static final String ERROR_ENTIDAD = "HA OCURRIDO UN ERROR";

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
            if(validarSiLaFechaTieneDescuento(fechaRegistro)){
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

    public static String calcularFechaDeVencimientoDeSuscripcion(LocalDateTime fechaRegistro,
                                                                        String tipoSuscripcion){
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_MES)){
            return fechaRegistro.plusDays(DIAS_MES).format(formatoFecha);
        }else if (tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_QUINCENA)){
            return fechaRegistro.plusDays(DIAS_QUINCENA).format(formatoFecha);
        }else {
            return ERROR_ENTIDAD;
        }
    }

    public static String mostrarDescuentoPorSuscripcion(String tipoSuscripcion, LocalDateTime fechaRegistro){
        if(tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_MES)){
            if(validarSiLaFechaTieneDescuento(fechaRegistro)){
                return VALOR_DESCUENTO;
            }else{
                return "$0";
            }
        }else if(tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_QUINCENA)){
            return "$0";
        }else{
            return ERROR_ENTIDAD;
        }
    }

    private static boolean validarSiLaFechaTieneDescuento(LocalDateTime fechaRegistro){
        List<String> listDays = Arrays.asList(DIAS_SEMANA_DESCUENTO);
        return listDays.contains(fechaRegistro.getDayOfWeek().toString());
    }
}
