package com.example.romer.mucontact.models;

import android.os.Build;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by romer on 4/7/2017.
 */

public class DataService {

    public DataService() {
        initPublications();
    }

    private void initPublications() {
        if(Publication.listAll(Publication.class).isEmpty() ) {
            (new Publication("Guitarra", "Mantenimiento", "Lima", new Date(), "Renzo Romero")).save();
            (new Publication("Bajo", "Reparación", "Chorrillos", new Date(), "Leonardo Caycho")).save();
        }
    }

    public List<Publication> getPublications() {
        return Publication.listAll(Publication.class);
    }

    public boolean addPublication(Publication publication) {
        return publication.save() > 0;

    }
}
