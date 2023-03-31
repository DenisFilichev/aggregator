package com.example.aggregator.rest;

import com.example.aggregator.model.MainSourceVideoData;
import com.example.aggregator.model.VideoCamDto;
import com.example.aggregator.service.Dispatcher;
import com.example.aggregator.service.VideoCamDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/videocams", produces = "application/json")
public class RestService {
    private final Dispatcher dispatcher;
    private final VideoCamDtoService videoCamDtoService;

    @Autowired
    public RestService(Dispatcher dispatcher, VideoCamDtoService videoCamDtoService) {
        this.dispatcher = dispatcher;
        this.videoCamDtoService = videoCamDtoService;
    }

    @GetMapping
    public List<VideoCamDto> getVideoCam (){
        return videoCamDtoService.getVideoCamDtos();
    }

    @PostMapping
    public void newSource(@RequestBody MainSourceVideoData[] urls){

        dispatcher.addVideoCams(Arrays.asList(urls));
    }
}
