package com.ceiba.suscripcion.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.suscripcion.comando.ComandoSuscripcion;
import com.ceiba.suscripcion.servicio.ComandoSuscripcionTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorSuscripcion.class)
public class ComandoControladorSuscripcionTest {

    private static final String TIPO_SUSCRIPCION_POR_MES = "XXX";
    private static final String TIPO_SUSCRIPCION_POR_QUINCENA = "XV";
    private static final int DIAS_MES = 30;
    private static final int DIAS_QUINCENA = 15;
    private static final String ERROR_ENTIDAD = "HA OCURRIDO UN ERROR";
    private static final String[] DIAS_SEMANA_DESCUENTO = {"WEDNESDAY","THURSDAY"};
    private static String valorDescuento = "$4900";
    private static String valorSinDescuento = "$0";


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception{
        // arrange
        ComandoSuscripcion suscripcion = new ComandoSuscripcionTestDataBuilder().
                conIdCliente(10L).
                build();

        // act - assert
        mocMvc.perform(post("/suscripcion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcion)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor':{'descuento':'"+validarSiLaFechaTieneDescuento(suscripcion.getFechaRegistro())+"','fechaDeVencimientoDeLaSuscripcion':'"+calcularFecha(suscripcion.getFechaRegistro(), suscripcion.getTipoSuscripcion())+"'}}"));
    }

    @Test
    public void validarExistente() throws Exception{
        // arrange
        ComandoSuscripcion suscripcion = new ComandoSuscripcionTestDataBuilder().
                conIdCliente(50L).
                build();

        // act - assert
        mocMvc.perform(post("/suscripcion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcion)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad','mensaje':'El usuario 50 ya tiene una suscripción activa, aun tiene (29) días disponibles.'}"));
    }

    @Test
    public void actualizar() throws Exception{
        // arrange
        Long id = 50L;
        ComandoSuscripcion suscripcion = new ComandoSuscripcionTestDataBuilder().build();

        // act - assert
        mocMvc.perform(put("/suscripcion/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcion)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminar() throws Exception{
        // arrange
        Long id = 50L;
        ComandoSuscripcion suscripcion = new ComandoSuscripcionTestDataBuilder().build();

        // act - assert
        mocMvc.perform(delete("/suscripcion/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(suscripcion)))
                .andExpect(status().isOk());
    }


    public String calcularFecha(LocalDateTime fechaRegistro, String tipoSuscripcion){
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_MES)){
            return fechaRegistro.plusDays(DIAS_MES).format(formatoFecha);
        }else if (tipoSuscripcion.equals(TIPO_SUSCRIPCION_POR_QUINCENA)){
            return fechaRegistro.plusDays(DIAS_QUINCENA).format(formatoFecha);
        }else {
            return ERROR_ENTIDAD;
        }
    }

    public String validarSiLaFechaTieneDescuento(LocalDateTime fechaRegistro) {
        List<String> listDays = Arrays.asList(DIAS_SEMANA_DESCUENTO);
        return listDays.contains(fechaRegistro.getDayOfWeek().toString())?valorDescuento:valorSinDescuento;
    }
}
