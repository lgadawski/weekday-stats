package com.gadawski.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PahoDemo {

    private final MqttClient client;

    public PahoDemo(MqttClient client) {
        this.client = client;
    }

    public void send(String topic, String msg) {
        try {
            client.connect();
            client.publish(topic, new MqttMessage(msg.getBytes()));
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
