package com.example.aggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Представляет собой набор данных видеокамеры, получаемых от внешней веб-службы
 */

@Data
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class VideoCam {
    private String id;
    private String sourceDataUrl;
    private String tokenDataUrl;
}
