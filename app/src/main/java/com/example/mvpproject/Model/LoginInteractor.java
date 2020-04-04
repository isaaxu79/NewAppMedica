package com.example.mvpproject.Model;

public interface LoginInteractor {

    interface OnLoginListener{

        void OnPasswordError();

        void OnUsernameError();

        void OnSucess(String token, int user);

        void onError();

    }

    void login(String username, String password,OnLoginListener listener);
}
