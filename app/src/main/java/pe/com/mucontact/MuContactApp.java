package pe.com.mucontact;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import pe.com.mucontact.models.Reward;
import pe.com.mucontact.network.NewApiService;

/**
 * Created by romer on 25/7/2017.
 */

public class MuContactApp extends Application {
    private static MuContactApp instance;
    private NewApiService newApiService;

    public MuContactApp() {
        super();
        instance = this;
    }

    public static MuContactApp getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        newApiService = new NewApiService();
    }

    public MuContactApp setCurrentReward(Reward reward) {
        newApiService.setCurrentReward(reward);
        return this;
    }

    public Reward getCurrentReward() {
        return newApiService.getCurrentReward();
    }
}