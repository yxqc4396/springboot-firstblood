package com.example.controller;

import com.example.entiey.Login;
import com.example.interfaces.LoignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("loginCtr")
public class LoginCtr {
    @Autowired
    private LoignService loignService;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest request;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("welcome")
    public String welcome(){
        return "loadingmodule/login";
    }
    @RequestMapping("welcomePage")
    public String welcomePage(){
        return "firstpage";
    }

    /**
     * login
     * @param login
     * @param checked 复选框状态
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("login")
    public void login(Login login, boolean checked) throws IOException {
        String status = "";
        List list = loignService.login(login);
        if(checked == false && list.size() == 1) {
            status = "success";
        } else
        if(checked == true && list.size() == 1) {
            Object[] objects = (Object[])list.get(0);
            // 用户id
            String cookieValue = objects[0].toString();
            // 存储Cookie
            Cookie cookie = new Cookie("cookieValue",cookieValue);
            // 设置有效期7天
            cookie.setMaxAge(24*60*60*100*7);
            cookie.setPath("/");
            response.addCookie(cookie);
            status = "success";
        } else
        if (list.size() == 0){
            status = "输入账户有误";
        } else if(list.size() >= 1) {
            logger.info("登陆用户ID重复");
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(status);
    }

    /**
     * Cookie登陆
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("cookisLogin")
    public void cookisLogin() throws IOException {
        String cookieValue = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                // 根据Cookiekey取value值
                if(cookie.getName().equals("cookieValue")) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        List list = loignService.cookisLogin(cookieValue);
        if(list.size() == 1) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("success");
        }
    }

    /**
     * 删除Cookie
     */
    @ResponseBody
    @RequestMapping("deleteCookie")
    public void deleteCookie() {
        Cookie cookie = new Cookie("cookieValue","");
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}