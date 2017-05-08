package io.github.arturgaleno.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.arturgaleno.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by artur on 16/04/17.
 */
@Service
public class IbgeMunicipalityClient {

    private static final Type API_RESONSE_TYPE = new TypeToken<ArrayList<ApiResponse>>(){}.getType();

    private static final String WEB_RESOURCE = "http://cidades.ibge.gov.br/services/jSonpMuns.php?busca=%s";

    @Autowired
    private RestTemplate restTemplate;

    public ApiResponse getMunicipality(Long municipalityId) {
        restTemplate.setMessageConverters(Collections.singletonList(httpMessageConverter));
        return restTemplate.getForObject(String.format(WEB_RESOURCE, municipalityId), ApiResponse.class);
    }

    private HttpMessageConverter httpMessageConverter = new HttpMessageConverter() {

        private Gson GSON = new Gson();

        @Override
        public List<MediaType> getSupportedMediaTypes() {
            return Collections.singletonList(MediaType.ALL);
        }

        @Override
        public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
            return GSON.fromJson(new BufferedReader(new InputStreamReader(httpInputMessage.getBody())), aClass);
        }

        @Override
        public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        }

        @Override
        public boolean canWrite(Class aClass, MediaType mediaType) {
            return false;
        }

        @Override
        public boolean canRead(Class aClass, MediaType mediaType) {
            return true;
        }
    };
}
