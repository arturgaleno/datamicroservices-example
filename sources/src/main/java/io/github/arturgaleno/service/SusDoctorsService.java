package io.github.arturgaleno.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.github.arturgaleno.model.DoctorsResponse;
import io.github.arturgaleno.model.SusDoctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by artur on 16/04/17.
 */
@Service
public class SusDoctorsService {

    private static final Type susDoctorsListType = new TypeToken<ArrayList<SusDoctors>>(){}.getType();

    private static final String WEB_RESOURCE = "http://www.geoservicos.ibge.gov.br/geoserver/wms?" +
                                                "service=WFS&version=1.0.0&" +
                                                "request=GetFeature&" +
                                                "typeName=CGEO:vw_razao_medicos_sus_1000_hab&" +
                                                "outputFormat=JSON";

    @Autowired
    private RestTemplate restTemplate;

    private int currentIndex = 0;

    private List<SusDoctors> susDoctors;

    private List<SusDoctors> getSusDoctors() {
        DoctorsResponse response = restTemplate.getForObject(WEB_RESOURCE, DoctorsResponse.class);

        return new Gson().fromJson(response.getFeatures(), susDoctorsListType);
    }

    public SusDoctors getNext() {
        if (susDoctors == null) {
            susDoctors = getSusDoctors();
        }
        try {
            return susDoctors.get(++currentIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
