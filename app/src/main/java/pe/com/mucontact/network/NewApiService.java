package pe.com.mucontact.network;

import pe.com.mucontact.models.Craftman;
import pe.com.mucontact.models.Reward;

/**
 * Created by romer on 25/7/2017.
 */

public class NewApiService {
    public static String SIGNIN_URL = "https://mucontact.herokuapp.com/api/signin";
    public static String REWARD_URL = "https://mucontact.herokuapp.com/api/reward";
    public static String PUBLICATION_URL = "https://mucontact.herokuapp.com/api/publication";
    public static String USERS_URL = "https://mucontact.herokuapp.com/api/user";
    public static String CRAFTMAN_URL = "https://mucontact.herokuapp.com/api/craftman";
    private Reward currentReward;
    private Craftman currentCraftman;

    public Reward getCurrentReward() {
        return currentReward;
    }

    public NewApiService setCurrentReward(Reward currentReward) {
        this.currentReward = currentReward;
        return this;
    }

    public Craftman getCurrentCraftman() {
        return currentCraftman;
    }

    public NewApiService setCurrentCraftman(Craftman currentCraftman){
        this.currentCraftman = currentCraftman;
        return this;
    }
}
