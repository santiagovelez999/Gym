package com.ceiba.suscripcion.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import com.ceiba.suscripcion.comando.fabrica.FabricaSuscripcion;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.servicio.ServicioActualizarSuscripcion;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarSuscripcion implements ManejadorComando<ComandoSuscripcion> {

    private final FabricaSuscripcion fabricaSuscripcion;
    private final ServicioActualizarSuscripcion servicioActualizarSuscripcion;

    public ManejadorActualizarSuscripcion(FabricaSuscripcion fabricaSuscripcion,
                                          ServicioActualizarSuscripcion servicioActualizarSuscripcion){
        this.fabricaSuscripcion = fabricaSuscripcion;
        this.servicioActualizarSuscripcion = servicioActualizarSuscripcion;
    }

    public void ejecutar(ComandoSuscripcion comandoSuscripcion) {
        Suscripcion suscripcion = fabricaSuscripcion.crear(comandoSuscripcion);
        this.servicioActualizarSuscripcion.ejecutar(suscripcion);
    }
}
