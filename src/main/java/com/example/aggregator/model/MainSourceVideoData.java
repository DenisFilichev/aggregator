package com.example.aggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Представляет собой данные расположения сторонней веб-службы
 * предоставляющей сведения о видеокамерах
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class MainSourceVideoData {
    private String url;
}
