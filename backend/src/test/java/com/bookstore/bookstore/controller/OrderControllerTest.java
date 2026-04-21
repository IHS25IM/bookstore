package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.config.SecurityConfig;
import com.bookstore.bookstore.dto.CartDTO;
import com.bookstore.bookstore.dto.OrderDTO;
import com.bookstore.bookstore.filter.JwtAuthenticationFilter;
import com.bookstore.bookstore.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
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

import java.util.List;
import java.util.stream.Stream;

import static org.instancio.Select.field;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class}, excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class), @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)})

public class OrderControllerTest {
    @MockitoBean
    private OrderService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Get tests
    @Test
    public void getAllOrders_shouldReturnOk() throws Exception{
        List<OrderDTO> response = Instancio.createList(OrderDTO.class);
        when(service.getAllOrders()).thenReturn(response);

        mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
    }

    @Test
    public void getOrderById_shouldReturnCart() throws Exception{
        OrderDTO response = Instancio.create(OrderDTO.class);
        when(service.getOrderById(response.getId())).thenReturn(response);

        mockMvc.perform(get("/api/orders/{id}", response.getId())).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(response.getId()));
    }

    //Post tests
    @ParameterizedTest
    @MethodSource("provideValidCreateOrderRequest")
    public void createOrder_ShouldReturnOK(OrderDTO request) throws Exception {
        mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());
    }

    private static Stream<Arguments> provideValidCreateOrderRequest() {
        return Stream.of(Arguments.of(Instancio.create(OrderDTO.class)));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCreateOrderRequest")
    public void createOrder_atLeastOneParameterIsMissing_shouldReturnBadRequest(OrderDTO request) throws Exception {
        mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidCreateOrderRequest() {
        return Stream.of(Arguments.of(Instancio.of(OrderDTO.class).set(field(OrderDTO::getOrderLine), null).create()));
    }


    //Delete Tests:
    @Test
    public void deleteOrder_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/orders/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
