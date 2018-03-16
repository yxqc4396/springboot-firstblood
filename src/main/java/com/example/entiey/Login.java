package com.example.entiey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

    @Id
    private String loginid;
    // 账户
    private String username;
    // 密码
    private String userpwd;
    // 验证码
    private String logincode;
    // cookie业务对应
    private String cookievalue;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getLogincode() {
        return logincode;
    }

    public void setLogincode(String logincode) {
        this.logincode = logincode;
    }

    public String getCookievalue() {
        return cookievalue;
    }

    public void setCookievalue(String cookievalue) {
        this.cookievalue = cookievalue;
    }
}
