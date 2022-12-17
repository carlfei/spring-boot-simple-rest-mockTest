package com.simple.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.model.Coche;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.simple.repository.CochesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebMvcTest(HDosController.class)
class HDosControllerTest {

    @MockBean
    private CochesRepository cochesRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void controllerTestAll()  throws Exception{
       List<Coche> coches = new ArrayList<>(Arrays.asList(new Coche(1, "sedan")));
      //  mockMvc.perform(get("http://127.0.0.1:9090/coches/all")).andExpect(status().isOk())
        when(cochesRepository.findAll()).thenReturn(coches);
        mockMvc.perform(get("/coches/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(coches.size()))
                //.andExpect(jsonPath("$.get(0).tipo").value(coches.get(0).getTipo()))
                .andDo(print());

    }

    @Test
    void controllerTestId() throws Exception {
        int id=1;
        Coche coche = new Coche(1,"sedan");

        when(cochesRepository.findById(id)).thenReturn(Optional.of(coche));
        mockMvc.perform(get("/coches/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.tipo").value(coche.getTipo()))

                .andDo(print());
    }


}