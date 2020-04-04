package com.example.mvpproject.View;

import com.example.mvpproject.Model.CurrentUser;

public interface PerfilView {
    void fillProfile(CurrentUser user);

    void showMessageError();
}
