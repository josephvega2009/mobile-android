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

public class Craftmen extends SugarRecord{
    private String name;
    private String description;
    private String phone;
    private String level;

    public Craftmen(String name, String description, String phone, String level) {
        this.setName(name);
        this.setDescription(description);
        this.setPhone(phone);
        this.setLevel(level);
    }

    public Craftmen() {
    }

    public String getName() {
        return name;
    }

    public Craftmen setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Craftmen setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Craftmen setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public Craftmen setLevel(String level) {
        this.level = level;
        return this;
    }

    public static Craftmen build(JSONObject jsonCraftmen) {
        if(jsonCraftmen == null) return null;
        Craftmen craftmen = new Craftmen();
        try {
            craftmen.setName(jsonCraftmen.getString("name"))
                    .setDescription(jsonCraftmen.getString("description"))
                    .setPhone(jsonCraftmen.getString("phone"))
                    .setLevel(jsonCraftmen.getString("level"));
            return craftmen;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Craftmen> build(JSONArray jsonCraftmen) {
        if(jsonCraftmen == null) return null;
        int length = jsonCraftmen.length();
        List<Craftmen> craftmen = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                craftmen.add(Craftmen.build(jsonCraftmen.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return craftmen;
    }
}
