package com.example.romer.mucontact.models;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by romer on 26/6/2017.
 */

public class Publication extends SugarRecord {
    private String instrument;
    private String description;
    private String locationReference;
    private Date createdAt;
    private String craftmen;

    public Publication() {

    }


    public Publication(String instrument, String description, String locationReference, Date createdAt, String craftmen) {
        this.setInstrument(instrument);
        this.setDescription(description);
        this.setLocationReference(locationReference);
        this.setCreatedAt(createdAt);
        this.setCraftmen(craftmen);
    }


    public String getInstrument() {
        return instrument;
    }

    public Publication setInstrument(String instrument) {
        this.instrument = instrument;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Publication setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLocationReference() {
        return locationReference;
    }

    public Publication setLocationReference(String locationReference) {
        this.locationReference = locationReference;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Publication setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getCraftmen() {
        return craftmen;
    }

    public Publication setCraftmen(String craftmen) {
        this.craftmen = craftmen;
        return this;
    }
}
