package com.xrafece.do_sport.service;

import com.xrafece.do_sport.dto.PayData;

import java.util.List;

/**
 * @author Xrafece
 */
public interface PayDataService {
    List<PayData> allPayDataList();

    List<PayData> payDataList(int startIndex, int pageSize);

    PayData lastPayData();

    List<PayData> getPayDataById(int id);

    List<PayData> getPayDataByWxId(String wxId, int startIndex, int pageSize);
}
