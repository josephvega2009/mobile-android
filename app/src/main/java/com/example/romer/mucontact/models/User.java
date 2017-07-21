package com.example.romer.mucontact.models;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romer on 20/7/2017.
 */

public class User extends SugarRecord {
    private String email;
    private String displayName;
    private String password;
    private String typeUser;

    public User() {
    }

    public User(String email, String displayName, String password, String typeUser) {
        this.email = email;
        this.displayName = displayName;
        this.password = password;
        this.typeUser = typeUser;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public User setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public User setTypeUser(String typeUser) {
        this.typeUser = typeUser;
        return this;
    }

    public static User build(JSONObject jsonUser) {
        if(jsonUser == null) return null;
        User user = new User();
        try {
            user.setEmail(jsonUser.getString("email"))
                    .setDisplayName(jsonUser.getString("displayName"))
                    .setTypeUser(jsonUser.getString("typeUser"));
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> build(JSONArray jsonUsers) {
        if(jsonUsers == null) return null;
        int length = jsonUsers.length();
        List<User> users = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                users.add(User.build(jsonUsers.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return users;
    }
}
