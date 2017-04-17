package io.github.arturgaleno.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artur on 16/04/17.
 */
public class SusDoctors {

    @SerializedName("nome")
    private String name;

    @SerializedName("UF")
    private String uf;

    @SerializedName("razao_medicos_sus_1000_hab")
    private Double docsBy1000;

    @SerializedName("Pop_est_2009")
    private Long population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
