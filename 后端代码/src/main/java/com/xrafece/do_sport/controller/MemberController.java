package com.xrafece.do_sport.controller;

import com.xrafece.do_sport.dto.WxSession;
import com.xrafece.do_sport.model.Member;
import com.xrafece.do_sport.service.MemberService;
import com.xrafece.do_sport.util.HttpClientUtil;
import com.xrafece.do_sport.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xrafece
 */
@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("all")
    public Map allMemberList() {
        List<Member> members = memberService.allMemberList();
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (members != null) {
            map.put("fail", 1);
            map.put("memberNum", memberService.allMemberNum());
            map.put("MemberList", members);
        }
        return map;
    }

    @RequestMapping("part")
    public Map memberListPart(String token, int nextNum) {
        List<Member> members = memberService.memberList(nextNum, 20);
        Member member = members.get(members.size() - 1);
        Member lastMember = memberService.lastMember();
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (members != null) {
            map.put("fail", 1);
            map.put("lastUser", -1);
            if (lastMember.getId().equals(member.getId())) {
                map.put("lastUser", 1);
            }
            map.put("getUser", members.size());
            map.put("userList", members);
        }
        return map;
    }

    @RequestMapping("delete")
    public Map deleteMember(String token, int userId) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (memberService.deleteMemberNById(userId)) {
            map.put("fail", 1);
        }
        return map;
    }

    @RequestMapping("getById")
    public Map getMemberById(String token, int userId) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        Member member = memberService.getMemberById(userId);
        if (member != null) {
            map.put("fail", 1);
            member.setId(null);
            map.put("user", member);
        }
        return map;
    }

    @RequestMapping("info")
    public Object memberInfo(String wxId) {
        System.out.println(wxId);
        Member memberByWxId = memberService.getMemberByWxId(wxId);
        System.out.println(memberByWxId);
        if (memberByWxId != null) {
            return memberByWxId;
        } else {
            return Boolean.FALSE.toString();
        }
    }

    @RequestMapping("login")
    public WxSession wxLogin(String code, Integer gender, String nickName, String avatar) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        System.out.println(gender + "-" + nickName + "-" + avatar);
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wx5a239421be7bc332");
        param.put("secret", "d08713115d270cf452d9291671fa8c3e");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String wxResult = HttpClientUtil.doGet(url, param);
        WxSession wxSession = JsonUtil.jsonToPojo(wxResult, WxSession.class);

        if (wxSession != null) {
            String wxId = wxSession.getOpenid();
            System.out.println("已经执行到wxid");
            Member memberByWxId = memberService.getMemberByWxId(wxId);
            System.out.println("正在检查是否由此用户");
            if (memberByWxId == null) {
                Member member = new Member();
                member.setWx_avatar(avatar);
                member.setWx_gender(gender);
                member.setWx_id(wxSession.getOpenid());
                member.setWx_nickName(nickName);
                member.setCard_expireTime(new Date());
                int row = memberService.addMember(member);
                System.out.println("添加用户行数：" + row);
            }
        }
        return wxSession;
    }
}
