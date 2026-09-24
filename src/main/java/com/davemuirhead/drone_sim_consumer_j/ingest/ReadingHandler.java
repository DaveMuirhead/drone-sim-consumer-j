package com.davemuirhead.drone_sim_consumer_j.ingest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * ReadingHandler is responsible for handling incoming drone
 * readings from the MQTT broker and processing them accordingly.
 */
@Component
public class ReadingHandler {

    private static final Logger logger = LoggerFactory.getLogger(ReadingHandler.class);

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handle(Message<String> message) {
        var topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC, String.class);
        var payload = message.getPayload();
        logger.info("{} -> {}", topic, payload);
    }
}
