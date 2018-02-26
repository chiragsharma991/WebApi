package com.example.csuthar.webapi.Utils;

/**
 * Created by csuthar on 23/02/18.
 */

public enum  AppUrl {

    GETAPI("https://api.androidhive.info/volley/person_object.json"),
    AUTHLOGIN ("https://smdm.manthan.com/v1/login");

    private String url;

    AppUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
