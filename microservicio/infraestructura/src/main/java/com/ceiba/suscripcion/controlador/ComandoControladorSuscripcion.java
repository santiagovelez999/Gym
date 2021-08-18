package com.ceiba.suscripcion.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import com.ceiba.suscripcion.comando.manejador.ManejadorCrearSuscripcion;
import com.ceiba.usuario.comando.ComandoUsuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/suscripcion")
@Api(tags = { "Controlador comando suscripcion"})
public class ComandoControladorSuscripcion {

    private final ManejadorCrearSuscripcion manejadorCrearSuscripcion;

    @Autowired
    public ComandoControladorSuscripcion(ManejadorCrearSuscripcion manejadorCrearSuscripcion){
        this.manejadorCrearSuscripcion = manejadorCrearSuscripcion;

    }

    @PostMapping
    @ApiOperation("Crear Suscripcion")
    public ComandoRespuesta<HashMap<String, String>> crear(@RequestBody ComandoSuscripcion comandoSuscripcion) {
        return manejadorCrearSuscripcion.ejecutar(comandoSuscripcion);
    }
}
