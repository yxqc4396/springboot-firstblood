package com.example.interfaces.login;

import com.example.entiey.login.Login;

import java.util.List;

public interface LoignService {
    List login(Login login);

    List cookisLogin(String cookieValue);
}
