package com.login.interfaces.impl;

import com.login.entiey.User;
import com.login.interfaces.LoignService;
import com.login.util.BaseDataAccess;
import com.login.util.Md5Util;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl extends BaseDataAccess implements LoignService{

    @Override
    public List login(User user) {
        // 查询之前进行对密码二次加密
        String jmOne = Md5Util.MD5(user.getUserpwd());// 密码一次加密
        String userpwd = Md5Util.MD5toMD5(jmOne);     // 密码二次加密
        String sql = "select * from User where username = '"+user.getUsername()+"'" +
                     " and userpwd = '"+userpwd+"'";
        return transactionNo(sql);
    }

    @Override
    public List cookisLogin(String cookieValue) {
        String sql = "select * from User where loginid = '"+cookieValue+"'";
        return transactionNo(sql);
    }

    @Override
    public List findByName(String name) {
        String sql = "select * from User where username = '"+name+"'";
        return transactionNo(sql);
    }

    @Override
    public void saveUser(User user1) {
        // 添加之前进行对密码二次加密
        String jmOne = Md5Util.MD5(user1.getUserpwd());// 密码一次加密
        String userpwd = Md5Util.MD5toMD5(jmOne);      // 密码二次加密
        String sql = "insert into User (username, userpwd) values ('"+user1.getUsername()+"', '"+userpwd+"')";
        transactionYes(sql);
    }
}
