package uni.isw.sigconbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import uni.isw.sigconbackend.dto.PredioRequest;
import uni.isw.sigconbackend.dto.PredioResponse;
import uni.isw.sigconbackend.model.TipoPredio;
import uni.isw.sigconbackend.model.Ubigeo;
import uni.isw.sigconbackend.service.PredioService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PredioController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PredioControllerTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PredioService predioService;

    Ubigeo ubigeo;
    TipoPredio tipoPredio;
    PredioResponse predioResponse;

    @BeforeEach
    public void init() {
        tipoPredio = TipoPredio.builder()
                .idTipoPredio(1)
                .descripcion("Condominio")
                .build();

        ubigeo= Ubigeo.builder()
                .idUbigeo("150136")
                .departamento("Callao")
                .provincia("La Perla")
                .distrito("La Perla").build();


        predioResponse = PredioResponse.builder()
                .idPredio(71L)
                .descripcion("Los Girasoles")
                .ruc("101010101010")
                .telefono("987907888")
                .correo("predio01@uni.pe")
                .direccion("av los jasmines 123")
                .totalMdu(15)
                .tipoPredio(tipoPredio)
                .ubigeo(ubigeo)
                .build();

    }

    @Test
    public void PredioController_findPredioByRuc() throws Exception {
        logger.info(">Test-PredioController_findPredioByRuc: ");

        when(predioService.findPredioByRuc(predioResponse.getRuc())).thenReturn(predioResponse);

        ResultActions response = mockMvc.perform(get("/api/v1/predio/101010101010")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(predioResponse)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.ruc", CoreMatchers.is(predioResponse.getRuc())));
    }

}
