package com.example.mvpproject.Model;

public interface LoginInteractor {

    interface OnLoginListener{

        void OnPasswordError();

        void OnUsernameError();

        void OnSucess(String token, String superUser);

        void onError();

    }

    void login(String username, String password,OnLoginListener listener);
}
