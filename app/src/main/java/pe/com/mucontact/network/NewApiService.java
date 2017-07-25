package pe.com.mucontact.network;

import pe.com.mucontact.models.Reward;

/**
 * Created by romer on 25/7/2017.
 */

public class NewApiService {
    public static String SIGNIN_URL = "https://mucontact.herokuapp.com/api/signin";
    public static String REWARD_URL = "https://mucontact.herokuapp.com/api/reward";
    public static String PUBLICATION_URL = "https://mucontact.herokuapp.com/api/publication";
    public static String USERS_URL = "https://mucontact.herokuapp.com/api/user";
    private Reward currentReward;

    public Reward getCurrentReward() {
        return currentReward;
    }

    public NewApiService setCurrentReward(Reward currentReward) {
        this.currentReward = currentReward;
        return this;
    }
}
