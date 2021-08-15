package com.ceiba.suscripcion.controlador;

import com.ceiba.suscripcion.consulta.ManejadorListaSuscripcion;
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

    private final ManejadorListaSuscripcion manejadorListaSuscripcion;

    public ConsultaControladorSuscripcion(ManejadorListaSuscripcion manejadorListaSuscripcion){
        this.manejadorListaSuscripcion = manejadorListaSuscripcion;
    }

    @GetMapping
    @ApiOperation("Listar Suscripciones")
    public List<DtoSuscripcion> List(){
        return manejadorListaSuscripcion.ejecutar();
    }


}




