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
                    getJsonString(sanwichNameJsonObject, "mainName"),
                    convertJsonArrayToList(getJsonArray(sanwichNameJsonObject, "alsoKnownAs")),
                    getJsonString(selectedSandwichJsonObject, "placeOfOrigin"),
                    getJsonString(selectedSandwichJsonObject, "description"),
                    getJsonString(selectedSandwichJsonObject, "image"),
                    convertJsonArrayToList(getJsonArray(selectedSandwichJsonObject, "ingredients"))
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return selectedSandwich;
    }

    private static String getJsonString(JSONObject sandwichObject, String id) throws JSONException {
        return sandwichObject.getString(id);
    }

    private static JSONArray getJsonArray(JSONObject sandwichObject, String id) throws JSONException {
        return sandwichObject.getJSONArray(id);
    }

    private static List<String> convertJsonArrayToList(JSONArray jsonArray) {
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
