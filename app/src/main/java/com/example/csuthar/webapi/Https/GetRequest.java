package com.example.csuthar.webapi.Https;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Set;

/**
 * Created by csuthar on 23/02/18.
 */

public class GetRequest {

    private final responser callback;
    private Context context;
    private String url;
    private String TAG;
    private int id;

    public  GetRequest(Context context,String url, String TAG, int id){

        this.url=url;
        this.context=context;
        this.TAG=TAG;
        this.id=id;
        callback=(responser)context;  // callback for Activity
        setApi();
    }

    public void setApi() {
        Log.e(TAG, "setApi: "+url );

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(context.getClass().getSimpleName(), response.toString());
                        callback.onSucess(id);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(context.getClass().getSimpleName(), "Error: " + error.getMessage());
                callback.onFailed(id);
                // hide the progress dialog
            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, TAG);


    }

    public interface responser{

          void onSucess(int id);

          void onFailed(int id);
    }








}
