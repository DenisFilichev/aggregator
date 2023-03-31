package com.example.aggregator.service;

import com.example.aggregator.rest.RestClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DispatcherTest {

    @Autowired
    VideoCamAggregate videoCamAggregate;
    @Autowired
    RestClient client;

    @Test
    void addVideoCams() {
        assertNotNull(videoCamAggregate);
        assertNotNull(client);
    }
}