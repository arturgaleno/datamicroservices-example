package io.github.arturgaleno.sources;

import io.github.arturgaleno.model.ApiValue;
import io.github.arturgaleno.model.SourceType;
import io.github.arturgaleno.service.MecTimeSeriesClient;
import io.github.arturgaleno.service.StatefulTimeSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by artur on 16/04/17.
 */
@EnableBinding(Source.class)
public class SisuSource {

    @Autowired
    private MecTimeSeriesClient mecTimeSeriesClient;

    private StatefulTimeSeriesService statefulTimeSeriesService;

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "1"))
    public MessageSource<ApiValue> sisuMessageSource() {
        if (statefulTimeSeriesService == null) {
            statefulTimeSeriesService = new StatefulTimeSeriesService(
                    mecTimeSeriesClient.getSisuTimeSeries().getValues()
            );
        }
        return () -> {
            ApiValue next = statefulTimeSeriesService.getNext();
            next.setSourceType(SourceType.SISU);
            return new GenericMessage<>(next);
        };
    }

}
