package com.example.aggregator.service;

import com.example.aggregator.model.VideoCam;
import com.example.aggregator.rest.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class VideoCamAggregateTest {
    @Autowired
    VideoCamAggregate videoCamAggregate;
    @MockBean
    VideoCamDtoService videoCamDtoService;
    @MockBean
    RestClient restClient;

    @Test
    void execute() {
        List<VideoCam> lists = new ArrayList<>(Arrays.asList(new VideoCam("1", "sourceDataUrl", "tokenDataUrl")));
        Assertions.assertNotNull(videoCamAggregate);
        videoCamAggregate.execute(lists);
    }
}