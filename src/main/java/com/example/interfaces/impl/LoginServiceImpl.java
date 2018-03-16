package com.example.interfaces.impl;

import com.example.entiey.Login;
import com.example.interfaces.LoignService;
import com.example.util.BaseDataAccess;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl extends BaseDataAccess implements LoignService{

    @Override
    public List login(Login login) {
        String sql = "select * from Login where username = '"+login.getUsername()+"'" +
                     " and userpwd = '"+login.getUserpwd()+"'";
        return transactionNo(sql);
    }

    @Override
    public List cookisLogin(String cookieValue) {
        String sql = "select * from Login where loginid = '"+cookieValue+"'";
        return transactionNo(sql);
    }
}
