package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.config.SecurityConfig;
import com.bookstore.bookstore.dto.CartDTO;
import com.bookstore.bookstore.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.bookstore.bookstore.filter.JwtAuthenticationFilter;

import org.instancio.Instancio;

import java.util.List;
import java.util.stream.Stream;

import static org.instancio.Select.field;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CartController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class}, excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class), @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)})
public class CartControllerTest {

    @MockitoBean
    private CartService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Get tests

    @Test
    public void getAllCarts_shouldReturnOk() throws Exception{
        List<CartDTO> response = Instancio.createList(CartDTO.class);
        when(service.getAllCarts()).thenReturn(response);

        mockMvc.perform(get("/api/carts")).andExpect(status().isOk());
    }

    @Test
    public void getCartById_shouldReturnCart() throws Exception{
        CartDTO response = Instancio.create(CartDTO.class);
        when(service.getCartById(response.getId())).thenReturn(response);

        mockMvc.perform(get("/api/carts/{id}", response.getId())).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(response.getId()));
    }

    //Post tests

    @ParameterizedTest
    @MethodSource("provideValidCreateCartRequest")
    public void createCart_ShouldReturnOK(CartDTO request) throws Exception {
        mockMvc.perform(post("/api/carts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());
    }

    private static Stream<Arguments> provideValidCreateCartRequest() {
        return Stream.of(Arguments.of(Instancio.create(CartDTO.class)));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCreateCartRequest")
    public void createCart_atLeastOneParameterIsMissing_shouldReturnBadRequest(CartDTO request) throws Exception {
        mockMvc.perform(post("/api/carts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidCreateCartRequest() {
        return Stream.of(Arguments.of(Instancio.of(CartDTO.class).set(field(CartDTO::getCartLines), null).create()));
    }


    //Delete Tests:
    @Test
    public void deleteCart_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/carts/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
