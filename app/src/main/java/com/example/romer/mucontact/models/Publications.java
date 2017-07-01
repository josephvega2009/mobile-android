package com.example.romer.mucontact.models;

/**
 * Created by romer on 26/6/2017.
 */

public class Publications {
    private String instrument;
    private String locationReference;
    private Integer ranking;

    public Publications() {

    }

    public Publications(String instrument, String locationReference, Integer ranking) {
        this.setInstrument(instrument);
        this.setLocationReference(locationReference);
        this.setRanking(ranking);
    }

    public String getInstrument() {
        return instrument;
    }

    public Publications setInstrument(String instrument) {
        this.instrument = instrument;
        return this;
    }

    public String getLocationReference() {
        return locationReference;
    }

    public Publications setLocationReference(String locationReference) {
        this.locationReference = locationReference;
        return this;
    }

    public Integer getRanking() {
        return ranking;
    }

    public Publications setRanking(Integer ranking) {
        this.ranking = ranking;
        return this;
    }
}
