package com.davemuirhead.drone_sim_consumer_j.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MqttProperties holds the configuration properties for connecting to the MQTT
 * broker.
 * 
 * @param brokerUrls list of URLs of the MQTT brokers to connect to
 * @param clientId   the client ID for the MQTT connection
 * @param topic      the topic to subscribe to
 */
@ConfigurationProperties(prefix = "drone.mqtt")
public record MqttProperties(List<String> brokerUrls, String clientId, String topic) {
}