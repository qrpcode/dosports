package com.xrafece.do_sport.controller;

import com.xrafece.do_sport.dto.KeepData;
import com.xrafece.do_sport.service.KeepDataService;
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
@RequestMapping("keep")
public class KeepDataController {
    @Autowired
    private KeepDataService keepDataService;

    private Map getKeepDataById(int id) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        List<KeepData> keepDateById = keepDataService.getKeepDataById(id);
        if (keepDateById != null) {
            map.put("fail", 1);
            map.put("lastOne", 1);
            map.put("getData", keepDateById.size());
            map.put("keepData", keepDateById);
        }
        return map;
    }

    private Map getKeepDateListByIdPart(int id, int startIndex, int pageSize) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        List<KeepData> keepDataList = keepDataService.getKeepDataListById(id, startIndex, pageSize + 1);
        if (keepDataList != null) {
            map.put("fail", 1);
            if (keepDataList.size() == pageSize + 1) {
                map.put("lastOne", -1);
                keepDataList.remove(keepDataList.size() - 1);
            } else {
                map.put("lastOne", 1);
            }
            map.put("getData", keepDataList.size());
            map.put("keepData", keepDataList);
        }
        return map;
    }

    @RequestMapping("getById")
    public Map getKeepDataById(String token, int userId, int allShow){
        Map map = this.getKeepDataById(userId);
        return map;
    }

    @RequestMapping("getByWxId")
    public Map getKeepDataByWxIdPart(String wxId, Integer keepNum, Integer allShow) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        int pageSize = 10;
        List<KeepData> keepDataList = keepDataService.getKeepDataListByWxId(wxId, keepNum, pageSize + 1);
        if (keepDataList != null) {
            map.put("fail", 1);
            if (keepDataList.size() == pageSize + 1) {
                map.put("lastOne", -1);
                keepDataList.remove(keepDataList.size() - 1);
            } else {
                map.put("lastOne", 1);
            }
            map.put("getData", keepDataList.size());
            map.put("keepData", keepDataList);
        }else {
            map.put("fail", 1);
            map.put("lastOne", 1);
            map.put("getData", 0);
            map.put("keepData", keepDataList);
        }
        return map;
    }
}
