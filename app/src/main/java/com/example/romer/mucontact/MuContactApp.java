package com.example.romer.mucontact;

import com.example.romer.mucontact.models.DataService;
import com.example.romer.mucontact.models.Publication;
import com.orm.SugarApp;

import java.util.List;

/**
 * Created by romer on 4/7/2017.
 */

public class MuContactApp extends SugarApp {
    private static MuContactApp instance;
    private DataService service;

    public MuContactApp() {
        super();
        instance = this;
    }

    public static MuContactApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        service = new DataService();
    }

    public List<Publication> getPublications() {
        return service.getPublications();
    }

    public boolean addPublication(Publication publication) {
        return service.addPublication(publication);
    }
}
