package io.github.arturgaleno.processors;

import com.google.gson.Gson;
import io.github.arturgaleno.model.AggregatedValue;
import io.github.arturgaleno.model.ApiResponse;
import io.github.arturgaleno.model.EnrichedInfo;
import io.github.arturgaleno.model.Municipality;
import io.github.arturgaleno.service.IbgeMunicipalityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

/**
 * Created by artur on 07/05/17.
 */
@EnableBinding(Processor.class)
public class IbgeMunicipalityProcessor {

    private static final Gson GSON = new Gson();

    @Autowired
    private IbgeMunicipalityClient ibgeMunicipalityClient;

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public EnrichedInfo transform(String msg) {

        AggregatedValue aggregatedValue = GSON.fromJson(msg, AggregatedValue.class);

        ApiResponse apiResponse = ibgeMunicipalityClient.getMunicipality(aggregatedValue.getMunicipalityId());

        Municipality municipality = apiResponse.getMunicipalities().get(0);

        EnrichedInfo enrichedInfo = EnrichedInfo.from(aggregatedValue);
        enrichedInfo.setMunicipality(municipality);

        return enrichedInfo;
    }

}
