package com.ceiba.suscripcion.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import com.ceiba.suscripcion.comando.manejador.ManejadorActualizarSuscripcion;
import com.ceiba.suscripcion.comando.manejador.ManejadorCrearSuscripcion;
import com.ceiba.suscripcion.comando.manejador.ManejadorEliminarSuscripcion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/suscripcion")
@Api(tags = { "Controlador comando suscripcion"})
public class ComandoControladorSuscripcion {

    private final ManejadorCrearSuscripcion manejadorCrearSuscripcion;
    private final ManejadorActualizarSuscripcion manejadorActualizarSuscripcion;
    private final ManejadorEliminarSuscripcion manejadorEliminarSuscripcion;
    private final String URL_PERMITIDA = "http://localhost:4200";

    @Autowired
    public ComandoControladorSuscripcion(ManejadorCrearSuscripcion manejadorCrearSuscripcion,
                                         ManejadorActualizarSuscripcion manejadorActualizarSuscripcion,
                                         ManejadorEliminarSuscripcion manejadorEliminarSuscripcion){
        this.manejadorCrearSuscripcion = manejadorCrearSuscripcion;
        this.manejadorActualizarSuscripcion = manejadorActualizarSuscripcion;
        this.manejadorEliminarSuscripcion = manejadorEliminarSuscripcion;
    }

    @PostMapping
    @CrossOrigin(origins = URL_PERMITIDA)
    @ApiOperation("Crear Suscripcion")
    public ComandoRespuesta<Map<String, String>> crear(@RequestBody ComandoSuscripcion comandoSuscripcion) {
        return manejadorCrearSuscripcion.ejecutar(comandoSuscripcion);
    }

    @PutMapping(value="/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation("Actualizar Suscripcion")
    public void actualizar(@RequestBody ComandoSuscripcion comandoSuscripcion, @PathVariable Long id) {
        comandoSuscripcion.setIdSuscripcion(id);
        manejadorActualizarSuscripcion.ejecutar(comandoSuscripcion);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar Suscripcion")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarSuscripcion.ejecutar(id);
    }
}
