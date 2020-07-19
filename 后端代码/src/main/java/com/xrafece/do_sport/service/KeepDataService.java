package com.xrafece.do_sport.service;

import com.xrafece.do_sport.dto.KeepData;

import java.util.List;

/**
 * @author Xrafece
 */
public interface KeepDataService {
    List<KeepData> getKeepDataById(int id);

    List<KeepData> getKeepDataListById(int id, int startIndex, int pageSize);

    List<KeepData> getKeepDataListByWxId(String wxId, int startIndex, int pageSize);
}
