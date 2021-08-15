package com.ceiba.suscripcion.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import com.ceiba.suscripcion.comando.fabrica.FabricaSuscripcion;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import com.ceiba.suscripcion.servicio.ServicioCrearSuscripcion;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearSuscripcion implements ManejadorComandoRespuesta<ComandoSuscripcion, ComandoRespuesta<Long>> {

    private final FabricaSuscripcion fabricaSuscripcion;
    private final ServicioCrearSuscripcion servicioCrearSuscripcion;

    public ManejadorCrearSuscripcion(FabricaSuscripcion fabricaSuscripcion,
                                     ServicioCrearSuscripcion servicioCrearSuscripcion){
        this.fabricaSuscripcion = fabricaSuscripcion;
        this.servicioCrearSuscripcion = servicioCrearSuscripcion;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSuscripcion comandoSuscripcion) {
        Suscripcion suscripcion = fabricaSuscripcion.crear(comandoSuscripcion);
        return new ComandoRespuesta<>(this.servicioCrearSuscripcion.ejecutar(suscripcion));
    }
}
