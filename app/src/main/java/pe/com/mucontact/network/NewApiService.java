package pe.com.mucontact.network;

import pe.com.mucontact.models.Craftman;
import pe.com.mucontact.models.Reward;
import pe.com.mucontact.models.User;

/**
 * Created by romer on 25/7/2017.
 */

public class NewApiService {
    public static String SIGNIN_URL = "https://mucontact.herokuapp.com/api/signin";
    public static String REWARD_URL = "https://mucontact.herokuapp.com/api/reward";
    public static String PUBLICATION_URL = "https://mucontact.herokuapp.com/api/publication";
    public static String PUBLICATION_USER_URL = "https://mucontact.herokuapp.com/api/publication/user/{user_id}";
    public static String USERS_URL = "https://mucontact.herokuapp.com/api/user";
    public static String CRAFTMAN_URL = "https://mucontact.herokuapp.com/api/craftman";
    private Reward currentReward;
    private Craftman currentCraftman;
    private User currentUser;
    private String currentToken;

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

    public User getCurrentUser() {
        return currentUser;
    }

    public NewApiService setCurrentUser(User currentUser){
        this.currentUser = currentUser;
        return this;
    }

    public String getCurrentToken() {
        return currentToken;
    }

    public NewApiService setCurrentToken(String currentToken){
        this.currentToken = currentToken;
        return this;
    }
}
