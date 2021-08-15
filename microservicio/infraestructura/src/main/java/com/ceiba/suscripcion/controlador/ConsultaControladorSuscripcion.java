package com.ceiba.suscripcion.controlador;

import com.ceiba.suscripcion.consulta.ManejadorListarSuscripcion;
import com.ceiba.suscripcion.modelo.dto.DtoSuscripcion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suscripcion")
@Api(tags={"Controlador consulta suscripcion usuario"})
public class ConsultaControladorSuscripcion {

    private final ManejadorListarSuscripcion manejadorListarSuscripcion;

    public ConsultaControladorSuscripcion(ManejadorListarSuscripcion manejadorListarSuscripcion){
        this.manejadorListarSuscripcion = manejadorListarSuscripcion;
    }

    @GetMapping
    @ApiOperation("Listar Suscripciones")
    public List<DtoSuscripcion> Listar(){
        return manejadorListarSuscripcion.ejecutar();
    }


}




