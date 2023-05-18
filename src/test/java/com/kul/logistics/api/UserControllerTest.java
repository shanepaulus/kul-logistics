package com.kul.logistics.api;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kul.logistics.domain.User;
import com.kul.logistics.model.request.UserLoginRequest;
import com.kul.logistics.model.request.UserRegisterRequest;
import com.kul.logistics.service.JwtService;
import com.kul.logistics.service.impl.UserServiceImpl;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 10-Sep-2022
 */

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  private static final String USER_EMAIL = "shane@test.com";
  private static final String USER_NAME = "Shane";
  private static final String USER_PASSWORD = "password";

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private UserServiceImpl userService;
  @MockBean
  private JwtService jwtService;
  @MockBean
  private AuthenticationManager authenticationManager;

  @BeforeEach
  public void before() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testUserRegister_created() throws Exception {
    UserRegisterRequest userLoginRequest = new UserRegisterRequest();
    userLoginRequest.setName(USER_NAME);
    userLoginRequest.setEmail(USER_EMAIL);
    userLoginRequest.setPassword(USER_PASSWORD);

    doReturn(new User()).when(userService).registerUser(any(User.class));
    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapObjectToJson(userLoginRequest)))
        .andExpect(status().isCreated())
        .andReturn();

    verify(userService, times(1)).registerUser(any(User.class));
  }

  @Test
  public void testUserRegister_badRequest() throws Exception {
    UserRegisterRequest userLoginRequest = new UserRegisterRequest();
    userLoginRequest.setName(USER_NAME);
    userLoginRequest.setPassword(USER_PASSWORD);

    doReturn(new User()).when(userService).registerUser(any(User.class));
    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapObjectToJson(userLoginRequest)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Email required")))
        .andReturn();

    verify(userService, times(0)).registerUser(any(User.class));
  }

  @Test
  public void testUserLogin_ok() throws Exception {
    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.setEmail(USER_EMAIL);
    userLoginRequest.setPassword(USER_PASSWORD);
    String token = "ThisIsTheToken!";
    UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(
        USER_EMAIL, USER_PASSWORD,
        Collections.emptyList());

    when(authenticationManager.authenticate(
        any(UsernamePasswordAuthenticationToken.class))).thenReturn(userAuth);
    when(jwtService.generateToken(USER_EMAIL)).thenReturn(token);
    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapObjectToJson(userLoginRequest)))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(token)))
        .andReturn();

    verify(authenticationManager, times(1)).authenticate(
        any(UsernamePasswordAuthenticationToken.class));
    verify(jwtService, times(1)).generateToken(USER_EMAIL);
  }

  @Test
  public void testUserLogin_badCredentials() throws Exception {
    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.setEmail(USER_EMAIL);

    doThrow(BadCredentialsException.class).when(authenticationManager)
        .authenticate(any(UsernamePasswordAuthenticationToken.class));
    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapObjectToJson(userLoginRequest)))
        .andExpect(status().isForbidden())
        .andReturn();

    verify(authenticationManager, times(1)).authenticate(
        any(UsernamePasswordAuthenticationToken.class));
  }

  private String mapObjectToJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }
}
