package io.github.arturgaleno.model;

import com.google.gson.JsonArray;

import java.util.List;

/**
 * Created by artur on 17/04/17.
 */
public class DoctorsResponse {

    private JsonArray features;

    public JsonArray getFeatures() {
        return features;
    }

    public void setFeatures(JsonArray features) {
        this.features = features;
    }
}
