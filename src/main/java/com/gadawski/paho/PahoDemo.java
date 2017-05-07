package com.gadawski.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PahoDemo {

    private static final Logger log = LoggerFactory.getLogger(PahoDemo.class);

    private final MqttClient client;

    public PahoDemo(MqttClient client) {
        this.client = client;
    }

    public void send(String topic, String msg) {
        log.info("Sending message - '{}' on topic - '{}'", msg, topic);

        try {
            client.connect();
            client.publish(topic, new MqttMessage(msg.getBytes()));
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
