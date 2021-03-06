package com.ceiba.suscripcion.controlador;
import com.ceiba.ApplicationMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorSuscripcion.class)
public class ConsultaControladorSuscripcionTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {
        // arrange

        // act - assert
        mocMvc.perform(get("/suscripcion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCliente", is(50)))
                .andExpect(jsonPath("$[0].valorSuscripcion", is(70000.0)))
                .andExpect(jsonPath("$[0].tipoSuscripcion", is("XXX")));
    }
}
