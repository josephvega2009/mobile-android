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
    private String name;
    private String description;
    private String phone;
    private String level;

    public Craftman(String name, String description, String phone, String level) {
        this.setName(name);
        this.setDescription(description);
        this.setPhone(phone);
        this.setLevel(level);
    }

    public Craftman() {
    }

    public String getName() {
        return name;
    }

    public Craftman setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Craftman setDescription(String description) {
        this.description = description;
        return this;
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

    public static Craftman build(JSONObject jsonCraftmen) {
        if(jsonCraftmen == null) return null;
        Craftman craftman = new Craftman();
        try {
            craftman.setName(jsonCraftmen.getString("name"))
                    .setDescription(jsonCraftmen.getString("description"))
                    .setPhone(jsonCraftmen.getString("phone"))
                    .setLevel(jsonCraftmen.getString("level"));
            return craftman;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Craftman> build(JSONArray jsonCraftmen) {
        if(jsonCraftmen == null) return null;
        int length = jsonCraftmen.length();
        List<Craftman> craftman = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                craftman.add(Craftman.build(jsonCraftmen.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return craftman;
    }
}
