package com.example.aggregator.rest;

import com.example.aggregator.model.VideoCam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component()
@Scope("prototype")
public class RestClient {

    private final Client client;

    public RestClient() {
        client = ClientBuilder.newClient();
    }

    /**
     * Запрашивает у внешней веб-службы JSON объекты
     *
     * @param uri - адрес веб-службы
     * @return возвращает коллекцию объектов VideoCam полученных от веб-службы
     * @throws ClientErrorException
     */
    public List<VideoCam> findAll(String uri) throws ClientErrorException {
        return client.target(uri).request(MediaType.APPLICATION_JSON).get(new GenericType<List<VideoCam>>(){});
    }

    /**
     * Запрашивает у внешней веб-службы JSON объекты
     *
     * @param responceType - класс объекта JSON получаемого от веб-службы, например: SourceDataUrl.class
     * @param uri - адрес веб-службы
     * @return возвращает объект типа T
     * @throws ClientErrorException
     */
    public <T>T findAllDataUrl(Class<T> responceType, String uri) throws ClientErrorException {
        return client.target(uri).request(MediaType.APPLICATION_JSON).get(responceType);
    }
}
