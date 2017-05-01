package io.github.arturgaleno.processors;

import io.github.arturgaleno.model.AggregatedValue;
import io.github.arturgaleno.model.ApiValue;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;

import java.util.List;

/**
 * Created by artur on 01/05/17.
 */
@EnableBinding(Processor.class)
public class ApiAggregator {

    @Aggregator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public AggregatedValue aggregatingMethod(List<ApiValue> items) {
        AggregatedValue aggregatedValue = new AggregatedValue();
        aggregatedValue.setYear(items.get(0).getYear());
        aggregatedValue.setMunicipalityId(items.get(0).getMunicipalityId());
        //TODO find a way to define values
        aggregatedValue.setFiesValue(items.get(0).getValue());
        aggregatedValue.setSisuValue(items.get(1).getValue());
        return aggregatedValue;
    }

    @CorrelationStrategy
    public String correlateBy(ApiValue item) {
        return item.getMunicipalityId().toString()
                + item.getYear().toString();
    }
}
