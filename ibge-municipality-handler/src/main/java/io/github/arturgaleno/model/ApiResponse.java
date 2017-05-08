package io.github.arturgaleno.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by artur on 17/04/17.
 */
public class ApiResponse {

    @SerializedName("municipios")
    private List<Municipality> municipalities;

    public List<Municipality> getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(List<Municipality> municipalities) {
        this.municipalities = municipalities;
    }
}
