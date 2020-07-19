package com.xrafece.do_sport.controller;

import com.xrafece.do_sport.dto.WxSession;
import com.xrafece.do_sport.util.Constant;
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
    public WxSession wxLogin(String code, Integer gender, String nickName, String avatar) {
        System.out.println(gender + " -" + nickName + "-" + avatar);
        Map<String, String> param = new HashMap<>();
        param.put("appid", Constant.WX_APPID);
        param.put("secret", Constant.WX_SECRET);
        param.put("js_code", code);
        param.put("grant_type", Constant.WX_GRANT_TYPE);

        String wxResult = HttpClientUtil.doGet(Constant.WX_REQUEST_URL, param);
        System.out.println(wxResult);

        WxSession wxSession = JsonUtil.jsonToPojo(wxResult, WxSession.class);

        return wxSession;
    }
}
