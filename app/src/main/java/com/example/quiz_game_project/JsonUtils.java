package com.example.quiz_game_project;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonUtils {

    public static List<String> getBreedsFromJson(JsonElement message) {
        List<String> list = new ArrayList<>();
        JsonObject jsonObject = message.getAsJsonObject();
        Set<String> keySet = jsonObject.keySet();
        for (String key : keySet) {
            JsonArray array = jsonObject.get(key).getAsJsonArray();
            if (array.size() == 0) {
                list.add(key);
            } else {
                for (int i = 0; i < array.size(); i++) {
                    String breedDesc = array.get(i).getAsString();
                    list.add(breedDesc + " " + key);
                }
            }
        }
        return list;
    }
}
