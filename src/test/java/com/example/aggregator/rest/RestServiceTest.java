package com.example.aggregator.rest;


import com.example.aggregator.model.*;
import com.example.aggregator.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(RestService.class)
@AutoConfigureMockMvc
public class RestServiceTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    VideoCamDtoService videoCamDtoService;
    @MockBean
    Dispatcher dispatcher;

    @Test
    public void getVideoCam() throws Exception {
        List<VideoCamDto> records = new ArrayList<>(Arrays.asList(
                new VideoCamDto("1", "LIVE", "rtsp://127.0.0.1/1", "fa4b588e-249b-11e9-ab14-d663bd873d93", "120"),
                new VideoCamDto("20", "ARCHIVE", "rtsp://127.0.0.1/2", "fa4b5b22-249b-11e9-ab14-d663bd873d93", "60"))
        );

        Mockito.when(videoCamDtoService.getVideoCamDtos()).thenReturn(records);

        mockMvc.perform(get("/api/videocams")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder("1", "20")))
                .andExpect(jsonPath("$[*].urlType", containsInAnyOrder("ARCHIVE", "LIVE")))
                .andExpect(jsonPath("$[*].videoUrl", containsInAnyOrder("rtsp://127.0.0.1/1", "rtsp://127.0.0.1/2")))
                .andExpect(jsonPath("$[*].value", containsInAnyOrder("fa4b5b22-249b-11e9-ab14-d663bd873d93", "fa4b588e-249b-11e9-ab14-d663bd873d93")))
                .andExpect(jsonPath("$[*].ttl", containsInAnyOrder("60", "120")));
    }

    @Test
    public void newSource() throws Exception {

        List<MainSourceVideoData> videoCams = new ArrayList<>(Arrays.asList(
                new MainSourceVideoData("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"),
                new MainSourceVideoData("http://www.mocky.io/v2/5c51b9dd3400003252129fb5")
        ));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/videocams")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(videoCams));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
}