package com.example.aggregator.service;

import com.example.aggregator.model.MainSourceVideoData;
import com.example.aggregator.rest.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class Dispatcher{
    private final VideoCamAggregate videoCamAggregate;
    private final RestClient client;

    /**
     *
     * @param videoCamAggregate - бин, агрегирует данные каждой видеокамеры
     * @param client - выполняет запросы к различным внешним веб-сервисам
     */
    @Autowired
    public Dispatcher(VideoCamAggregate videoCamAggregate, RestClient client) {
        this.videoCamAggregate = videoCamAggregate;
        this.client = client;
    }

    /**
     * Метод перебирает коллекцию List параметризованной классом MainSourceVideoData,
     * где каждое значение поля url передает объекту класса VideoCamAggregate в отдельном потоке
     *
     * @param urls - коллекция, содержащая объекты с адресами внешних веб-сервисов
     */
    public void addVideoCams(List<MainSourceVideoData> urls){
        for(MainSourceVideoData url : urls) {
            videoCamAggregate.execute(client.findAll(url.getUrl()));
        }
    }
}