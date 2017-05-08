package io.github.arturgaleno.processors;

import com.google.gson.Gson;
import io.github.arturgaleno.model.AggregatedValue;
import io.github.arturgaleno.model.ApiValue;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.MessageChannel;

import java.util.List;

/**
 * Created by artur on 01/05/17.
 */
@EnableBinding(Processor.class)
public class ApiAggregator {

    private static final Gson GSON = new Gson();
//--spring.cloud.stream.bindings.input.destination=apiagregator-output
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
    public String correlateBy(String item) {
        ApiValue apiValue = GSON.fromJson(item, ApiValue.class);
        return apiValue.getMunicipalityId().toString()
                + apiValue.getYear().toString();
    }

    @ReleaseStrategy
    public boolean canMessagesBeReleased(List<String> msgs) {
        return msgs.size() > 2;
    }
}
