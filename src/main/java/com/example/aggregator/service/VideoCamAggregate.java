package com.example.aggregator.service;

import com.example.aggregator.model.*;
import com.example.aggregator.rest.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class VideoCamAggregate {
    private final VideoCamDtoService videoCamDtoService;
    private final RestClient restClient;
    private List<VideoCam> videoCams;

    @Autowired
    public VideoCamAggregate(VideoCamDtoService videoCamDtoService, RestClient restClient) {
        this.videoCamDtoService = videoCamDtoService;
        this.restClient = restClient;
    }

    /**
     * Выпоняет запуск агрегатора данных видеокамер в отдельном потоке
     *
     * @param videoCams - коллекция промежуточного состояния видеокамер, полученного из внешнего веб-сервиса
     */
    public void execute(List<VideoCam> videoCams) {
        this.videoCams = videoCams;
        new Thread(new CollectionDataVideocam()).start();
    }

    private class CollectionDataVideocam implements Runnable {

        /**
         * Выполняет в отдельном потоке сбор сведений о видеокамерах из разных источников,
         * агрегирует данные в объектах типа VideoCamDto,
         * передает коллекцию объектов в сервис VideoCamDtoService для хранения.
         */
        @Override
        public void run() {
            List<VideoCamDto> videoCamDtos = new ArrayList<>();
            for (VideoCam e : videoCams) {
                SourceDataUrl sourceDataUrl = restClient.findAllDataUrl(SourceDataUrl.class, e.getSourceDataUrl());
                TokenDataUrl tokenDataUrl = restClient.findAllDataUrl(TokenDataUrl.class, e.getTokenDataUrl());
                videoCamDtos.add(
                        new VideoCamDto(e.getId(),
                                sourceDataUrl.getUrlType(),
                                sourceDataUrl.getVideoUrl(),
                                tokenDataUrl.getValue(),
                                tokenDataUrl.getTtl())
                );
            }
            videoCamDtoService.addVideoCamDtos(videoCamDtos);
        }
    }
}
