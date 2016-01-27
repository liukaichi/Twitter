package com.example.liukaichi.twitter;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liukaichi on 1/25/2016.
 */
public class Message {
    private ArrayList<String> keys;
    private HashMap<String, String> keysValues;
    public static String tag = "chat message";
    public static String messageKey = "message";


    public Message() {
        keysValues = new HashMap<String, String>();
        keys = new ArrayList<>();
        keys.add(messageKey);
    }

    public void setValue(String key, String value) {
        if (keys.contains(key)) {
            keysValues.put(key, value);
        } else {
            Log.d(tag, "didn't add " + keyValueAsString(key, value) + "key wasn't in list");
        }
    }

}
