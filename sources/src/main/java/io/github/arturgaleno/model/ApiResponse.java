package io.github.arturgaleno.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by artur on 17/04/17.
 */
public class ApiResponse {

    @SerializedName("valores")
    private List<ApiValue> values;

    public List<ApiValue> getValues() {
        return values;
    }

    public void setValues(List<ApiValue> values) {
        this.values = values;
    }
}
