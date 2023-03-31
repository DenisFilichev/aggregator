package com.example.aggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Представляет собой набор объединенных данных видеокамеры,
 * получаемых из разных внешних источников (веб-служб)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VideoCamDto {
    @EqualsAndHashCode.Include
    private String id;
    private String urlType;
    @EqualsAndHashCode.Include
    private String videoUrl;
    private String value;
    private String ttl;
}
