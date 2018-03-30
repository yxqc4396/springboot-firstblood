package com.login.controller;

import com.aliyuncs.exceptions.ClientException;
import com.login.entiey.User;
import com.login.interfaces.LoignService;
import com.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/loginCtr")
public class LoginCtr {

    @Autowired
    private LoignService loignService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * sessionid存储数据库，用作登陆拦截业务。
     */
    public String sesionid = "";

    /**
     * 首页
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "/view/index";
    }

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping("/welcomePage")
    public String welcomePage(){
        return "/view/login/login";
    }

    /**
     * login
     * @param user
     * @param checked 复选框状态
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/login")
    public void login(User user, boolean checked, HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException {
        //Test.sendMsg();
        String status = "";
        List list = loignService.login(user);
        if (list.size() == 1) {
            // 存储session到数据库
            HttpSession session = request.getSession();
            session.setAttribute("loginSession",list);
            sesionid = session.getId();
            // 判断session是否失效
            if (sesionid != "") {
                // 不失效就存储数据库
                loignService.addSessionid(sesionid, user);
            } else {
                //loignService.removeSessionid();
            }
            // list取单个元素的转换
            Object[] objects = (Object[])list.get(0);
            // 数据库解密后的密码
            String jmPwd = Md5Util.JM(objects[4].toString());
            // 原始密码一次加密 (密码验证：数据库解密后的密码与原始密码一次加密后的密码进行比较)
            String userPwd = Md5Util.MD5(user.getUserpwd());
            if (checked == false && jmPwd.equals(userPwd)) {
                status = "success";
            }
            if (checked == true && jmPwd.equals(userPwd)) {
                // 用户id
                String cookieValue = objects[0].toString();
                // 存储Cookie
                Cookie cookie = new Cookie("cookieValue",cookieValue);
                // 设置有效期7天
                cookie.setMaxAge(24*60*60*100*7);
                cookie.setPath("/");
                response.addCookie(cookie);
                status = "success";
            }
        } else if (list.size() == 0) {
            status = "输入账户或密码有误";
        } else if (list.size() > 1) {
            status = "您的账户异常请联系管理员!";
            logger.info("【登陆异常】：登陆用户数据重复");
        }
        // 往ajax回调函数传值
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(status);
    }

    /**
     * Cookie登陆
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/cookisLogin")
    public void cookisLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 根据Cookiekey取value值
                if(cookie.getName().equals("cookieValue")) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        List list = loignService.cookisLogin(cookieValue);
        if (list.size() == 1) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("success");
        }
    }

    /**
     * 删除Cookie
     */
    @ResponseBody
    @RequestMapping("/deleteCookie")
    public void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("cookieValue","");
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 注册
     * @param user
     */
    @ResponseBody
    @RequestMapping("/saveUser")
    public void saveUser(User user) {
        User user1 = new User();
        user1.setUsername("aaa");
        user1.setUserpwd("111");
        loignService.saveUser(user1);
    }

}