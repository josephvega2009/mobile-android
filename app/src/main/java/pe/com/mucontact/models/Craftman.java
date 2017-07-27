package pe.com.mucontact.models;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franklin on 26/07/2017.
 */

public class Craftman extends SugarRecord{
    private String level;
    private String phone;
    private String description;
    private String name;


    public Craftman() {
    }

    public Craftman(String level, String phone, String description, String name) {
        this.phone = level;
        this.level = phone;
        this.description = description;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public Craftman setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public Craftman setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Craftman setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Craftman setName(String name) {
        this.name = name;
        return this;
    }

    public static Craftman build(JSONObject jsonCraftman) {
        if(jsonCraftman == null) return null;
        Craftman craftman = new Craftman();
        try {
            craftman.setLevel(jsonCraftman.getString("level"))
                    .setPhone(jsonCraftman.getString("phone"))
                    .setDescription(jsonCraftman.getString("description"))
                    .setName(jsonCraftman.getString("name"));
            return craftman;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Craftman> build(JSONArray jsonCraftman) {
        if(jsonCraftman == null) return null;
        int length = jsonCraftman.length();
        List<Craftman> craftmen = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                craftmen.add(Craftman.build(jsonCraftman.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return craftmen;
    }


}
