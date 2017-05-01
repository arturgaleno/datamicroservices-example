package io.github.arturgaleno.service;

import com.google.gson.reflect.TypeToken;
import io.github.arturgaleno.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by artur on 16/04/17.
 */
@Service
public class MecTimeSeriesClient {

    private static final Type API_RESONSE_TYPE = new TypeToken<ArrayList<ApiResponse>>(){}.getType();

    private static final String WEB_RESOURCE = "http://api.pgi.gov.br/api/1/serie/%s.json";

    @Autowired
    private RestTemplate restTemplate;

    public ApiResponse getSisuTimeSeries() {
        return restTemplate.getForObject(String.format(WEB_RESOURCE, "1756"), ApiResponse.class);
    }

    public ApiResponse getFiesTimeSeries() {
        return restTemplate.getForObject(String.format(WEB_RESOURCE, "1743"), ApiResponse.class);
    }
}
