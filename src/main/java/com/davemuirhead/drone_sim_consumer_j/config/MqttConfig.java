package com.davemuirhead.drone_sim_consumer_j.config;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

/**
 * MqttConfig is responsible for configuring the MQTT client and setting
 * up the necessary beans for connecting to the MQTT broker and subscribing
 * to the configured topic.
 */
@Configuration
public class MqttConfig {

    private final MqttProperties mqttProperties;

    public MqttConfig(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        var options = new MqttConnectOptions();
        options.setServerURIs(mqttProperties.brokerUrls().toArray(new String[0]));
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);

        var factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        var uniqueClientId = buildUniqueClientId(mqttProperties.clientId());
        var adapter = new MqttPahoMessageDrivenChannelAdapter(
                uniqueClientId,
                mqttClientFactory(),
                mqttProperties.topic());

        // so payloads arrive as String rather than byte[]
        adapter.setConverter(new DefaultPahoMessageConverter());

        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /*
     * The max length of an MQTT client ID is 23 characters.
     * To ensure uniqueness, we append a random UUID to the
     * base client ID and then truncate it to 23 characters.
     * That means the base client ID should be somewhat less
     * than 23 characters if you want any randomness...
     */
    private String buildUniqueClientId(String baseClientId) {
        var clientId = baseClientId + "-" + UUID.randomUUID().toString();
        return clientId.substring(0, 23); // MQTT client ID must be <= 23 characters
    }

}
