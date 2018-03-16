package com.example.interfaces;

import com.example.entiey.Login;

import java.util.List;

public interface LoignService {
    List login(Login login);

    List cookisLogin(String cookieValue);
}
