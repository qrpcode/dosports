package com.xrafece.do_sport.service.impl;

import com.xrafece.do_sport.dto.KeepData;
import com.xrafece.do_sport.mapper.KeepDataMapper;
import com.xrafece.do_sport.service.KeepDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xrafece
 */
@Service
public class KeepDataServiceImpl implements KeepDataService {
    @Autowired
    private KeepDataMapper keepDataMapper;

    @Override
    public List<KeepData> getKeepDataById(int id) {
        return keepDataMapper.getKeepDataById(id);
    }

    @Override
    public List<KeepData> getKeepDataListById(int id, int startIndex, int pageSize) {
        return keepDataMapper.getKeepDataListById(id, startIndex, pageSize);
    }

    @Override
    public List<KeepData> getKeepDataListByWxId(String wxId, int startIndex, int pageSize) {
        return keepDataMapper.getKeepDataListByWxId(wxId, startIndex, pageSize);
    }
}
