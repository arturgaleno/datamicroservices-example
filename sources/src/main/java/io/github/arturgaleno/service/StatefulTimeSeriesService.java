package io.github.arturgaleno.service;

import io.github.arturgaleno.model.ApiValue;

import java.util.List;

/**
 * Created by artur on 30/04/17.
 */
public class StatefulTimeSeriesService {

    private List<ApiValue> apiValues;
    private int currentIndex = 0;

    public StatefulTimeSeriesService(List<ApiValue> apiValues) {
        this.apiValues = apiValues;
    }

    public ApiValue getNext() {
        try {
            return apiValues.get(currentIndex++);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
