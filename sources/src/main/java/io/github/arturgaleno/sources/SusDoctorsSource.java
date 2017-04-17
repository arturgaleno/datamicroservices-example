package io.github.arturgaleno.sources;

import io.github.arturgaleno.model.SusDoctors;
import io.github.arturgaleno.service.SusDoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by artur on 16/04/17.
 */
@EnableBinding(Source.class)
public class SusDoctorsSource {

    @Autowired
    private SusDoctorsService susDoctorsService;

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT)
    public MessageSource<SusDoctors> susDoctorsMessageSource() {
        return () -> new GenericMessage<>(susDoctorsService.getNext());
    }

}
