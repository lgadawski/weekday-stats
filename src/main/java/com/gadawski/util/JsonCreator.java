package com.gadawski.util;

import com.google.gson.JsonObject;

public final class JsonCreator {

    private JsonCreator() {
        // empty
    }

    /**
     * Creates json base on single kay-value pair.
     *
     * @return JsonObject converted to String.
     */
    public static String singleElement(String key, String value) {
        JsonObject json = new JsonObject();
        json.addProperty(key, value);

        return json.toString();
    }
}
