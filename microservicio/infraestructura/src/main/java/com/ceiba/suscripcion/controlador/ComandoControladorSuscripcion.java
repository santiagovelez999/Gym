package com.ceiba.suscripcion.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import com.ceiba.suscripcion.comando.manejador.ManejadorActualizarSuscripcion;
import com.ceiba.suscripcion.comando.manejador.ManejadorCrearSuscripcion;
import com.ceiba.usuario.comando.ComandoUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/suscripcion")
@Api(tags = { "Controlador comando suscripcion"})
public class ComandoControladorSuscripcion {

    private final ManejadorCrearSuscripcion manejadorCrearSuscripcion;
    private final ManejadorActualizarSuscripcion manejadorActualizarSuscripcion;

    @Autowired
    public ComandoControladorSuscripcion(ManejadorCrearSuscripcion manejadorCrearSuscripcion,
                                         ManejadorActualizarSuscripcion manejadorActualizarSuscripcion){
        this.manejadorCrearSuscripcion = manejadorCrearSuscripcion;
        this.manejadorActualizarSuscripcion = manejadorActualizarSuscripcion;

    }

    @PostMapping
    @ApiOperation("Crear Suscripcion")
    public ComandoRespuesta<HashMap<String, String>> crear(@RequestBody ComandoSuscripcion comandoSuscripcion) {
        return manejadorCrearSuscripcion.ejecutar(comandoSuscripcion);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar Suscripcion")
    public void actualizar(@RequestBody ComandoSuscripcion comandoSuscripcion, @PathVariable Long id) {
        comandoSuscripcion.setIdSuscripcion(id);
        manejadorActualizarSuscripcion.ejecutar(comandoSuscripcion);
    }
}
