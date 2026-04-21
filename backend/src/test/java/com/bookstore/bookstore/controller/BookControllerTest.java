package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.config.SecurityConfig;
import com.bookstore.bookstore.dto.BookDTO;
import com.bookstore.bookstore.service.BookService;
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

@WebMvcTest(controllers = BookController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class}, excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class), @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)})
public class BookControllerTest {

    @MockitoBean
    private BookService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Get tests

    @Test
    public void getAllBooks_shouldReturnOk() throws Exception{
        List<BookDTO> response = Instancio.createList(BookDTO.class);
        when(service.getAllBooks()).thenReturn(response);

        mockMvc.perform(get("/api/books")).andExpect(status().isOk());
    }

    @Test
    public void getBookById_shouldReturnBook() throws Exception{
        BookDTO response = Instancio.create(BookDTO.class);
        when(service.getBookById(response.getId())).thenReturn(response);

        mockMvc.perform(get("/api/books/{id}", response.getId())).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(response.getId()));
    }

    //Post tests

    @ParameterizedTest
    @MethodSource("provideValidCreateBookRequest")
    public void createBook_ShouldReturnOK(BookDTO request) throws Exception {
        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());
    }

    private static Stream<Arguments> provideValidCreateBookRequest() {
        return Stream.of(Arguments.of(Instancio.create(BookDTO.class)));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCreateBookRequest")
    public void createBook_atLeastOneParameterIsMissing_shouldReturnBadRequest(BookDTO request) throws Exception {
        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidCreateBookRequest() {
        return Stream.of(Arguments.of(Instancio.of(BookDTO.class).set(field(BookDTO::getAuthor), null).create()));
    }


    //Delete Tests:
    @Test
    public void deleteBook_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/books/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}
