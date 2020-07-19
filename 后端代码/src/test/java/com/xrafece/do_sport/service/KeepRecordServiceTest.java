package com.xrafece.do_sport.service;

import com.xrafece.do_sport.model.FitnessRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xrafece
 */
@SpringBootTest
class KeepRecordServiceTest {

    @Autowired
    private KeepRecordService keepRecordService;

    @Test
    void allKeepRecordList() {
        List<FitnessRecord> fitnessRecords = keepRecordService.allKeepRecordList();
        for (FitnessRecord fitnessRecord : fitnessRecords) {
            System.out.println(fitnessRecord);
        }
    }
}