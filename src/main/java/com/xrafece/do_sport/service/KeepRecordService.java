package com.xrafece.do_sport.service;

import com.xrafece.do_sport.model.FitnessRecord;

import java.util.List;

/**
 * @author Xrafece
 */
public interface KeepRecordService {
    List<FitnessRecord> allKeepRecordList();

    int addKeepRecord(FitnessRecord record);

    int deleteKeepRecord(FitnessRecord record);

    int updateKeepRecord(FitnessRecord record);

    int todayKeepRecord();

    int monthKeepRecord();
}
