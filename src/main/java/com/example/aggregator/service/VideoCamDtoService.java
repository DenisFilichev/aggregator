package com.example.aggregator.service;

import com.example.aggregator.model.VideoCamDto;

import java.util.List;

public interface VideoCamDtoService {


    /**
     * Метод добавляет новые экземпляры
     *     в общее хранилице объектов VideoCamDto
     * @param videoCamDtos - коллекция объектов VideoCamDto
     */
    void addVideoCamDtos(List<VideoCamDto> videoCamDtos);

    /**
     * Возвращает содержимое хранилища объектов VideoCamDto
     *
     * @return - коллекция объектов VideoCamDto
     */
    List<VideoCamDto> getVideoCamDtos();
}
