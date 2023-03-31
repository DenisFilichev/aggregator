package com.example.aggregator.service;

import com.example.aggregator.model.VideoCamDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class VideoCamDtoServiceImpl implements VideoCamDtoService{
    private static Set<VideoCamDto> videoCamDtos;
    static {
        videoCamDtos = new LinkedHashSet<>();
    }

    /**
     * Добавляет новые объекты в статическое хранилище Set<VideoCamDto>
     *
     * @param list - коллекция объектов VideoCamDto
     */
    @Override
    public void addVideoCamDtos (List<VideoCamDto> list){
        synchronized (this) {
            videoCamDtos.addAll(list);
        }
    }

    /**
     * Возвращает содержимое хранилища объектов VideoCamDto
     *
     * @return - коллекция объектов VideoCamDto
     */
    @Override
    public List<VideoCamDto> getVideoCamDtos(){
        return new ArrayList<>(videoCamDtos);
    }
}
