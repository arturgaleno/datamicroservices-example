package io.github.arturgaleno.processors;

import com.google.gson.Gson;
import io.github.arturgaleno.model.AggregatedValue;
import io.github.arturgaleno.model.ApiValue;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;

import java.util.List;

/**
 * Created by artur on 01/05/17.
 */
@EnableBinding(Processor.class)
public class ApiAggregator {

    private static final Gson GSON = new Gson();

    @Aggregator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public AggregatedValue aggregatingMethod(List<String> items) {

        AggregatedValue aggregatedValue = new AggregatedValue();

        items.forEach(s -> {
            ApiValue apiValue = GSON.fromJson(s, ApiValue.class);
            aggregatedValue.setYear(apiValue.getYear());
            aggregatedValue.setMunicipalityId(apiValue.getMunicipalityId());
            handleSourceType(apiValue, aggregatedValue);
        });

        return aggregatedValue;
    }

    private void handleSourceType(ApiValue item, AggregatedValue aggregatedValue) {
        switch (item.getSourceType()) {
            case FIES:
                aggregatedValue.setFiesValue(item.getValue());
                break;
            case SISU:
                aggregatedValue.setSisuValue(item.getValue());
                break;
        }
    }

    @CorrelationStrategy
    public String correlateBy(String item) {
        ApiValue apiValue = GSON.fromJson(item, ApiValue.class);
        return apiValue.getMunicipalityId().toString()
                + apiValue.getYear().toString();
    }

    @ReleaseStrategy
    public boolean canMessagesBeReleased(List<String> msgs) {
        return msgs.size() == 2;
    }
}
