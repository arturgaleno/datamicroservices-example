package io.github.arturgaleno.sinks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created by artur on 07/05/17.
 */
@EnableBinding(Sink.class)
public class LogSink {

    Logger LOGGER = LoggerFactory.getLogger(LogSink.class);

    @ServiceActivator(inputChannel = Sink.INPUT)
    public synchronized void logMessage(Object msg) {
        LOGGER.info("Received: " + msg);
    }
}
