package io.github.arturgaleno.model;

/**
 * Created by artur on 01/05/17.
 */
public class AggregatedValue {

    private Long sisuValue;
    private Long fiesValue;
    private Long municipalityId;
    private Integer year;

    public Long getSisuValue() {
        return sisuValue;
    }

    public void setSisuValue(Long sisuValue) {
        this.sisuValue = sisuValue;
    }

    public Long getFiesValue() {
        return fiesValue;
    }

    public void setFiesValue(Long fiesValue) {
        this.fiesValue = fiesValue;
    }

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
