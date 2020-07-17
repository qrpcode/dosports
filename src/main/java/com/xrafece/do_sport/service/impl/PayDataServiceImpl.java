package com.xrafece.do_sport.service.impl;

import com.xrafece.do_sport.dto.PayData;
import com.xrafece.do_sport.mapper.PayDataMapper;
import com.xrafece.do_sport.service.PayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xrafece
 */
@Service
public class PayDataServiceImpl implements PayDataService {
    @Autowired
    private PayDataMapper payDataMapper;

    @Override
    public List<PayData> allPayDataList() {
        return payDataMapper.allPayDataList();
    }

    @Override
    public List<PayData> payDataList(int startIndex, int pageSize) {
        return payDataMapper.payDataList(startIndex, pageSize);
    }

    @Override
    public PayData lastPayData() {
        return payDataMapper.lastPayData();
    }

    @Override
    public List<PayData> getPayDataById(int id) {
        return payDataMapper.getPayDataById(id);
    }

    @Override
    public List<PayData> getPayDataByWxId(String wxId, int startIndex, int pageSize) {
        return payDataMapper.getPayDataByWxId(wxId, startIndex, pageSize);
    }
}
