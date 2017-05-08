package io.github.arturgaleno.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artur on 07/05/17.
 */
public class Municipality {

    @SerializedName("c")
    private Long municipalityId;
    @SerializedName("n")
    private String name;
    @SerializedName("s")
    private String state;

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
