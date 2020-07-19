package com.xrafece.do_sport.mapper;

import com.xrafece.do_sport.model.FitnessRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xrafece
 */
@Repository
public interface KeepRecordMapper {
    List<FitnessRecord> allKeepRecordList();

    int addKeepRecord(FitnessRecord record);

    int deleteKeepRecord(FitnessRecord record);

    int updateKeepRecord(FitnessRecord record);

    int todayKeepRecord();

    int monthKeepRecord();
}
