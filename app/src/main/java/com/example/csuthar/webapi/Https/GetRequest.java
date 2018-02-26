package com.example.csuthar.webapi.Https;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by csuthar on 23/02/18.
 */

public class GetRequest {

    private final responser callback;
    private Context context;
    private String url;
    private String TAG;
    private int id;
    private Queue<JsonObjectRequest> requestQueue;
    private String auth_code;

    public  GetRequest(Context context,String url, String TAG, int id){

        this.url=url;
        this.context=context;
        this.TAG=TAG;
        this.id=id;
        callback=(responser)context;  // callback for Activity
        setApi();
    }

    public void setApi() {
        Log.e( "setApi: ",url );



    /*    RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(), future, future);
        requestQueue.add(request);

        try {
            JSONObject response = future.get(); // this will block
        } catch (InterruptedException e) {
            // exception handling
        } catch (ExecutionException e) {
            // exception handling
        }*/




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

        }
        )

      //  JsonObjectRequest jsonObjReq = new JsonObjectRequest(){ get header  }
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                auth_code = "Basic " + Base64.encodeToString(("ARUNTEST" + ":" + "ARUNTEST").getBytes(), Base64.NO_WRAP); //Base64.NO_WRAP flag
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", auth_code);
                return params;
            }
        };


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, TAG);


    }

    public interface responser{

          void onSucess(int id);

          void onFailed(int id);
    }








}
