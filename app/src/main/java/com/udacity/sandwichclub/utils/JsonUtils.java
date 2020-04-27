package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich selectedSandwich = null;

        try {
            JSONObject selectedSandwichJsonObject = new JSONObject(json);
            JSONObject sanwichNameJsonObject = selectedSandwichJsonObject.getJSONObject("name");

            selectedSandwich = new Sandwich(
                    getJSONString(sanwichNameJsonObject, "mainName"),
                    convertJSONArrayToList(getJSONArray(sanwichNameJsonObject, "alsoKnownAs")),
                    getJSONString(selectedSandwichJsonObject, "placeOfOrigin"),
                    getJSONString(selectedSandwichJsonObject, "description"),
                    getJSONString(selectedSandwichJsonObject, "image"),
                    convertJSONArrayToList(getJSONArray(selectedSandwichJsonObject, "ingredients"))
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return selectedSandwich;
    }

    private static String getJSONString(JSONObject sandwichObject, String id) throws JSONException {
        return sandwichObject.getString(id);
    }

    private static JSONArray getJSONArray(JSONObject sandwichObject, String id) throws JSONException {
        return sandwichObject.getJSONArray(id);
    }

    private static List<String> convertJSONArrayToList(JSONArray jsonArray) {
        List<String> jsonList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonList.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonList;
    }
}
