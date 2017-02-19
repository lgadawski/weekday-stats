package com.gadawski.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class PushMessageUtil {

    private static final PahoDemo pahoDemo;

    static {
        try {
            pahoDemo = new PahoDemo(new MqttClient("tcp://127.0.0.1:1883", "gadawski"));
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
    public static void send(double temperature, double tolerance) {
        String topic = "home-assistant/lukasz/thermostat_settings";
        String msg = "{\"temperature\": " + temperature + ", \"tolerance\": " + tolerance + "}";

        pahoDemo.send(topic, msg);
    }
}
