package io.github.arturgaleno.model;

/**
 * Created by artur on 01/05/17.
 */
public class EnrichedInfo {

    private Long sisuValue;
    private Long fiesValue;
    private Municipality municipality;
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

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public static EnrichedInfo from(AggregatedValue aggregatedValue) {

        EnrichedInfo enrichedInfo = new EnrichedInfo();
        enrichedInfo.setSisuValue(aggregatedValue.getSisuValue());
        enrichedInfo.setFiesValue(aggregatedValue.getFiesValue());
        enrichedInfo.setYear(aggregatedValue.getYear());

        return enrichedInfo;
    }
}
