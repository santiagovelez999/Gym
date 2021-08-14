package com.ceiba.suscripcion.controlador;

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

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<Object> List(){return null;}


}




