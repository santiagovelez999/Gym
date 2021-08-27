package com.ceiba.suscripcion.comando.manejador;

import com.ceiba.suscripcion.servicio.ServicioEliminarSuscripcion;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarSuscripcion {

    private final ServicioEliminarSuscripcion servicioEliminarSuscripcion;

    public ManejadorEliminarSuscripcion(ServicioEliminarSuscripcion servicioEliminarSuscripcion) {
        this.servicioEliminarSuscripcion = servicioEliminarSuscripcion;
    }

    public void ejecutar(Long idSuscripcion) {
        this.servicioEliminarSuscripcion.ejecutar(idSuscripcion);
    }
}
