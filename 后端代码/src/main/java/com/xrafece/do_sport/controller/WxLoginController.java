package com.xrafece.do_sport.controller;

import com.xrafece.do_sport.dto.WxSession;
import com.xrafece.do_sport.util.HttpClientUtil;
import com.xrafece.do_sport.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xrafece
 */
@RestController
@RequestMapping("wx")
public class WxLoginController {
    @RequestMapping("login")
    public WxSession wxLogin(String code, Integer gender, String nickName, String avatar){
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        System.out.println(gender+" -"+nickName+"-"+avatar);
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wx5a239421be7bc332");
        param.put("secret", "d08713115d270cf452d9291671fa8c3e");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);

        WxSession wxSession = JsonUtil.jsonToPojo(wxResult, WxSession.class);

        return wxSession;
    }
}
