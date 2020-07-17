package com.xrafece.do_sport.service.impl;

import com.xrafece.do_sport.mapper.KeepRecordMapper;
import com.xrafece.do_sport.model.FitnessRecord;
import com.xrafece.do_sport.service.KeepRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xrafece
 */
@Service
public class KeepRecordServiceImpl implements KeepRecordService {
    @Autowired
    private KeepRecordMapper keepRecordMapper;

    @Override
    public List<FitnessRecord> allKeepRecordList() {
        return keepRecordMapper.allKeepRecordList();
    }

    @Override
    public int addKeepRecord(FitnessRecord record) {
        return keepRecordMapper.addKeepRecord(record);
    }

    @Override
    public int deleteKeepRecord(FitnessRecord record) {
        return keepRecordMapper.deleteKeepRecord(record);
    }

    @Override
    public int updateKeepRecord(FitnessRecord record) {
        return keepRecordMapper.updateKeepRecord(record);
    }

    @Override
    public int todayKeepRecord() {
        return keepRecordMapper.todayKeepRecord();
    }

    @Override
    public int monthKeepRecord() {
        return keepRecordMapper.monthKeepRecord();
    }
}
