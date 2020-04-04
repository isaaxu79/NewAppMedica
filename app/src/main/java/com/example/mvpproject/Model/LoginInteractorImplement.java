package com.example.mvpproject.Model;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.example.mvpproject.Resources.ConnectionApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

public class LoginInteractorImplement implements LoginInteractor {

    private ConnectionApi API_REST = new ConnectionApi();
    private static final Logger logger = Logger.getLogger(LoginInteractorImplement.class.getName());

    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(username)){
                    listener.OnUsernameError();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    listener.OnPasswordError();
                    return;
                }
                RequestParams params = new RequestParams();
                params.put("username",username);
                params.put("password",password);
                logger.log(Level.SEVERE, "estoy entrando");
                API_REST.login(params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try{
                            JSONObject object = new JSONObject(new String(responseBody));
                            String tkn = object.getString("token");
                            int id = Integer.parseInt(object.getString("id"));
                            String nombre = object.getString("nombre");
                            String apellidop = object.getString("apellidop");
                            String apellidom = object.getString("apellidom");
                            String especialidad = object.getString("especialidad");
                            String tipo = object.getString("type");
                            String superUser = "sa";
                            listener.OnSucess(tkn,id);
                        }catch (Exception e){}
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        listener.onError();
                        logger.log(Level.SEVERE, "error de conecxion");
                    }
                });
            }
        },1000);
    }


}
