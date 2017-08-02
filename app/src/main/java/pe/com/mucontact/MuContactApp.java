package pe.com.mucontact;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import pe.com.mucontact.models.Craftman;
import pe.com.mucontact.models.Musician;
import pe.com.mucontact.models.Publication;
import pe.com.mucontact.models.Reward;
import pe.com.mucontact.models.User;
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

    public MuContactApp setCurrentCraftman(Craftman craftman){
        newApiService.setCurrentCraftman(craftman);
        return this;
    }

    public Craftman getCurrentCraftman() {
        return newApiService.getCurrentCraftman();
    }

    public MuContactApp setCurrentUser(User user){
        newApiService.setCurrentUser(user);
        return this;
    }

    public User getCurrentUser() {
        return newApiService.getCurrentUser();
    }

    public MuContactApp setCurrentToken(String token){
        newApiService.setCurrentToken(token);
        return this;
    }

    public String getCurrentToken() {
        return newApiService.getCurrentToken();
    }

    public MuContactApp setCurrentPublication(Publication publication){
        newApiService.setCurrentPublication(publication);
        return this;
    }

    public Publication getCurrentPublication() {
        return newApiService.getCurrentPublication();
    }

    public MuContactApp setCurrentMusician(Musician musician){
        newApiService.setCurrentMusician(musician);
        return this;
    }

    public Musician getCurrentMusician() {
        return newApiService.getCurrentMusician();
    }
}