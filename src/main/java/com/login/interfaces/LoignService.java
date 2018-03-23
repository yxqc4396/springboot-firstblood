package com.login.interfaces;

import com.login.entiey.User;

import java.util.List;

public interface LoignService {
    List login(User user);

    List cookisLogin(String cookieValue);

    List findByName(String name);

    void saveUser(User user1);
}
