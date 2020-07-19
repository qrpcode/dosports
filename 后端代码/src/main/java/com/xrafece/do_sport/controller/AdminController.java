package com.xrafece.do_sport.controller;

import com.xrafece.do_sport.model.Admin;
import com.xrafece.do_sport.service.AdminService;
import com.xrafece.do_sport.service.KeepRecordService;
import com.xrafece.do_sport.service.MemberService;
import com.xrafece.do_sport.service.PayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xrafece
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private KeepRecordService keepRecordService;
    @Autowired
    private PayRecordService payRecordService;

    @PostMapping("login")
    public Map loginCheck(String username, String password) {
        Map<Object, Object> map = new HashMap<>();
        if (adminService.queryForLogin(username, password)) {
            String token = username;
            map.put("fail", 1);
            map.put("token", token);
        } else {
            map.put("fail", -1);
        }
        return map;
    }

    @PostMapping("login/{username}/{password}")
    public Map restLoginCheck(@PathVariable String username, @PathVariable String password) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (adminService.queryForLogin(username, password)) {
            String token = username;
            map.put("fail", 1);
            map.put("token", token);
        }
        return map;
    }

    @PostMapping("logout")
    public Map logout(String username, String password) {
        Map<Object, Object> map = new HashMap<>();
        if (adminService.queryForLogin(username, password)) {
            String token = username;
            map.put("fail", 1);
            map.put("token", token);
        } else {
            map.put("fail", -1);
        }
        return map;
    }

    @PostMapping("logout/{token}")
    public Map restLogout(@PathVariable String username, @PathVariable String password, @PathVariable String token) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (adminService.queryForLogin(username, password)) {
            map.put("fail", 1);
            map.put("token", token);
        }
        return map;
    }

    @RequestMapping("all")
    public Map allAdminList() {
        //String token
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        List<Admin> admins = adminService.allAdminList();
        if (admins != null) {
            map.put("fail", 1);
            //数量
            map.put("adminNum", admins.size());
            map.put("admin", admins);
        }
        return map;
    }

    @RequestMapping("add")
    public Map addAdmin(String token, Admin admin) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if(adminService.addAdmin(admin)){
            map.put("fail", 1);
        }
        return map;
    }

    @RequestMapping("delete")
    public Map deleteAdmin(String token, int adminId) {
        //鉴权

        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (adminService.deleteAdminById(adminId)) {
            map.put("fail", 1);
        }
        return map;
    }

    @RequestMapping("update")
    public Map updateAdmin(String token, int userId, String password) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", 1);
        return map;
    }

    @RequestMapping("getInfo")
    public Map adminGetInfo(String token) {
        //解析token
        Admin admin = adminService.queryAdminByUsername(token);
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (admin != null) {
            map.put("fail", 1);
            map.put("id", admin.getId());
            map.put("userName", admin.getUsername());
            map.put("level", admin.getLevel());
            map.put("allUser", memberService.allMemberNum());
            map.put("dayCome", keepRecordService.todayKeepRecord());
            map.put("monthPrice", payRecordService.monthPayMoney());
            map.put("monthUser", keepRecordService.monthKeepRecord());
            map.put("sex0", memberService.memberNumByGender(0));
            map.put("sex1", memberService.memberNumByGender(2));
            map.put("sex2", memberService.memberNumByGender(1));
        }
        return map;
    }

    @RequestMapping("get")
    public Map adminGetCount() {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        map.put("allUser", memberService.allMemberNum());
        map.put("dayCome", keepRecordService.todayKeepRecord());
        map.put("monthPrice", payRecordService.monthPayMoney());
        map.put("monthUser", keepRecordService.monthKeepRecord());
        return map;
    }

}
