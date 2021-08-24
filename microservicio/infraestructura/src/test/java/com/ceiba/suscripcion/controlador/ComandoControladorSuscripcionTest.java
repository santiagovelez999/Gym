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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorSuscripcion.class)
public class ComandoControladorSuscripcionTest {

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
                .andExpect(content().json("{'valor':{'descuento':'$4900','fechaDeVencimientoDeLaSuscripcion':'11/09/2021'}}"));
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
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad','mensaje':'El usuario 50 ya tiene una suscripción activa, aun tiene (18) días disponibles.'}"));
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
}
