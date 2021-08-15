package com.ceiba.suscripcion.excepcion;

public class ExcepcionTipoServicioInvalido extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionTipoServicioInvalido(String message){super(message);}
}
