package com.gadawski.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

public class PahoTest {

    @Test
    public void testLocalMqtt() {
        PahoDemo pahoDemo = null;
        try {
            pahoDemo = new PahoDemo(new MqttClient("tcp://127.0.0.1:1883", "gadawski"));
        } catch (MqttException e) {
            e.printStackTrace();
        }

        String topic = "home-assistant/lukasz/thermostat_settings";

        String temperature = "25";
        String tolerance = "0.9";
        String msg = "{\"temperature\": " + temperature + ", \"tolerance\": " + tolerance + "}";

        pahoDemo.send(topic, msg);
    }
}
