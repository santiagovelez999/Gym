package com.ceiba.suscripcion.comando.fabrica;

import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import com.ceiba.suscripcion.modelo.entidad.Suscripcion;
import org.springframework.stereotype.Component;

@Component
public class FabricaSuscripcion {

    public Suscripcion crear(ComandoSuscripcion comandoSuscripcion){
        return new Suscripcion(comandoSuscripcion.getIdSuscripcion(),
                               comandoSuscripcion.getIdCliente(),
                               comandoSuscripcion.getValorSuscripcion(),
                               comandoSuscripcion.getTipoSuscripcion(),
                               comandoSuscripcion.getFechaRegistro());

    }
}
