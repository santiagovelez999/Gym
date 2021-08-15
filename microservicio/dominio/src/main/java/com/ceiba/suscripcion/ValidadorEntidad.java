package com.ceiba.suscripcion;

import com.ceiba.suscripcion.excepcion.ExcepcionTipoServicioInvalido;

public class ValidadorEntidad {

    private static final String TIPO_SUSCRIPCION_POR_MES = "XXX";
    private static final String TIPO_SUSCRIPCION_POR_QUINCENA = "XV";

    public static void validarTipoSuscripcion(String tipoSuscripcion, String message){
         if(tipoSuscripcion != TIPO_SUSCRIPCION_POR_MES &&
            tipoSuscripcion != TIPO_SUSCRIPCION_POR_QUINCENA){
             throw new ExcepcionTipoServicioInvalido(message);
        }
    }
}
