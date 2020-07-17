package com.xrafece.do_sport.controller;

import com.xrafece.do_sport.dto.PayData;
import com.xrafece.do_sport.service.AdminService;
import com.xrafece.do_sport.service.PayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xrafece
 */
@RestController
@RequestMapping("pay")
public class PayDataController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PayDataService payDataService;

    @RequestMapping("part")
    public Map payDataListPart(String token, int nextNum) {
        List<PayData> payDataList = payDataService.payDataList(nextNum, 20);
        PayData payData = payDataList.get(payDataList.size() - 1);
        PayData lastPayData = payDataService.lastPayData();
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (adminService.queryAdminByUsername(token) != null) {
            map.put("fail", 1);
            map.put("lastPay", -1);
            if (lastPayData.getPay_time().equals(payData.getPay_time())) {
                map.put("lastPay", 1);
            }
            map.put("getPay", payDataList.size());
            map.put("userList", payDataList);
        }
        return map;
    }

    @RequestMapping("getById")
    public Map getPayDataById(String token, int userId, int allShow) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        List<PayData> payDataList = payDataService.getPayDataById(userId);
        if (payDataList != null) {
            map.put("fail", 1);
            map.put("lastOne", 1);
            map.put("getData", payDataList.size());
            map.put("payData", payDataList);
        } else {
            map.put("fail", 1);
            map.put("lastOne", 1);
            map.put("getData", 0);
            map.put("payData", payDataList);
        }
        return map;

    }

    @RequestMapping("getByWxId")
    public Map getPayDataByWxId(String wxId, Integer payNum, Integer allShow) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        int pageSize = 10;
        List<PayData> payDataList = payDataService.getPayDataByWxId(wxId, payNum, pageSize + 1);
        if (payDataList != null) {
            map.put("fail", 1);
            if (payDataList.size() == pageSize + 1) {
                map.put("lastOne", -1);
                payDataList.remove(payDataList.size() - 1);
            } else {
                map.put("lastOne", 1);
            }
            map.put("getData", payDataList.size());
            map.put("payData", payDataList);
        } else {
            map.put("fail", 1);
            map.put("lastOne", 1);
            map.put("getData", 0);
            map.put("payData", payDataList);
        }
        return map;

    }
}
