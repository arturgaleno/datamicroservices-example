package io.github.arturgaleno.sinks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by artur on 07/05/17.
 */
@EnableBinding(Sink.class)
public class LogSink {

    private Logger LOGGER = LoggerFactory.getLogger(LogSink.class);

    @StreamListener(value = Sink.INPUT)
    public synchronized void logMessage(Object msg) {
        LOGGER.info("Received: " + msg);
    }
}
