package com.example.mvpproject.Resources;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ConnectionApi {
    private AsyncHttpClient client = new AsyncHttpClient();
    private final String BASE_URL= "http://ec2-52-23-194-178.compute-1.amazonaws.com:9000";
    private final String VERSION = "/api/v1";
    private final String LOGIN = "/login";
    private final String USERS = "/r/registro";
    private final String CAREERS = "/c/carreras";

    public void login(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
        this.client.post(BASE_URL+VERSION+LOGIN,params,asyncHttpResponseHandler);
    }

    public void getUsers(String token, JsonHttpResponseHandler jsonHttpResponseHandler){
        client.addHeader("Authorization", "token "+token);
        client.get(BASE_URL+VERSION+USERS,null, jsonHttpResponseHandler);
    }

    public void getCareers(String token, JsonHttpResponseHandler jsonHttpResponseHandler){
        client.addHeader("Authorization", "token "+token);
        client.get(BASE_URL+VERSION+CAREERS,null, jsonHttpResponseHandler);
    }

    public void setHeaders(String token) {
        client.addHeader("Authorization", "token "+token);
    }

    public AsyncHttpClient getClient(){
        return client;
    }
}
