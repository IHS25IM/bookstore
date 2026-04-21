package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.auth.AuthResponseDTO;
import com.bookstore.bookstore.dto.auth.LoginRequestDTO;
import com.bookstore.bookstore.dto.auth.RegisterRequestDTO;
import com.bookstore.bookstore.security.JwtService;
import com.bookstore.bookstore.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class CustomerControllerTest {

    @MockitoBean
    private AuthService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtService jwtService;


    //Post Tests
    /*
    @Test
    public void register_shouldReturnOK() throws Exception {
        AuthResponseDTO response = new AuthResponseDTO("some.jwt.token", "johnDoe@bnppf.com", "John", "Doe");
        when(service.register(any())).thenReturn(response);

        RegisterRequestDTO request = Instancio.create(RegisterRequestDTO.class);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").value("some.jwt.token"))
                .andExpect(jsonPath("$.email").value("johnDoe@bnppf.com"));
    }

    @Test
    public void register_emailAlreadyExists_shouldReturnInternalServerError() throws Exception {
        when(service.register(any())).thenThrow(new RuntimeException("Email already in use"));

        RegisterRequestDTO request = Instancio.create(RegisterRequestDTO.class);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void login_shouldReturnOK() throws Exception{
        AuthResponseDTO response = new AuthResponseDTO("some.jwt.token", "johnDoe@bnppf.com", "John", "Doe");
        when(service.login(any())).thenReturn(response);

        LoginRequestDTO request = Instancio.create(LoginRequestDTO.class);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("some.jwt.token"))
                .andExpect(jsonPath("$.email").value("johnDoe@bnppf.com"));
    }

    @Test
    public void login_badCredentials_shouldReturnInternalServerError() throws Exception {
        when(service.login(any())).thenThrow(new RuntimeException("Bad credentials"));

        LoginRequestDTO request = Instancio.create(LoginRequestDTO.class);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }
     */
}
