package com.util;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void sendMsg() throws ClientException {
        String phoneNumber = "17600570699";
        String randomNum = RandomNumber.createRandomNum(6);
        String jsonContent = "{\"code\":\"" + randomNum + "\"}";

        Map<String, String> paramMap = new HashMap<>();
        // 手机号
        paramMap.put("phoneNumber", phoneNumber);
        // 签名
        paramMap.put("msgSign", "yxqc4396厂长");
        // 模版CODE
        paramMap.put("templateCode", "SMS_127980008");
        // 所发信息
        paramMap.put("jsonContent", jsonContent);
        System.out.print("短信接口请求参数:{'phoneNumber':'"+paramMap.get("phoneNumber")+"','msgSign':'"+paramMap.get("msgSign")+"','templateCode':'"+paramMap.get("templateCode")+"','jsonContent':'"+paramMap.get("jsonContent")+"'}");
        SendSmsResponse sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {

            System.out.println("短信接口返回参数:{'requestId':'"+sendSmsResponse.getRequestId()+"','code':'"+sendSmsResponse.getCode()+"','message':'"+sendSmsResponse.getMessage()+"','bizId':'"+sendSmsResponse.getBizId()+"'}");
        } else {
            System.out.println("短信接口返回参数:{'requestId':'"+sendSmsResponse.getRequestId()+"','code':'"+sendSmsResponse.getCode()+"','message':'"+sendSmsResponse.getMessage()+"','bizId':'"+sendSmsResponse.getBizId()+"'}");
        }
    }
}
