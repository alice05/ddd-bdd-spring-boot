package com.santoshkc.leaser.unit.infratstructure.web.freelancer;

import com.santoshkc.leaser.LeaserApplication;
import com.santoshkc.leaser.application.freelancer.DTOs.FreelancerDto;
import com.santoshkc.leaser.application.freelancer.FreelancerApplication;
import com.santoshkc.leaser.infrastructure.web.freelancer.FreelancerController;
import com.santoshkc.leaser.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LeaserApplication.class})
public class FreelancerControllerTest {

    @Autowired
    FreelancerController controller;

    private MockMvc mvc;

    @MockBean
    private FreelancerApplication application;

    private FreelancerDto dto;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        initializeDTO();
    }

    private void initializeDTO() {
        dto = new FreelancerDto();
        dto.setFirstName("first name");
        dto.setLastName("last name");
        dto.setCity("test city");
        dto.setZipCode("test zip");
    }

    @Test
    public void whenGetAll_thenReturnJsonArray() throws Exception {
        List<FreelancerDto> dtos = Stream.of(dto).collect(Collectors.toList());
        Mockito.when(application.getAll()).thenReturn(dtos);

        mvc.perform(
                get("/freelancer/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(dto.getFirstName())));
    }

    @Test
    public void whenRequestedToCreate_thenShouldPassDtoToSave() throws Exception {
        mvc.perform(
                post("/freelancer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Success")));
        Mockito.verify(application, Mockito.times(1)).create(dto);
    }

    @Test
    public void whenRequestedTest_shouldReturnHello() throws Exception {
        mvc.perform(
                get("/freelancer/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Hello world"))
        );
    }

}

