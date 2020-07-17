package com.xrafece.do_sport.service;

import com.xrafece.do_sport.model.ExpensesRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xrafece
 */
@SpringBootTest
class PayRecordServiceTest {

    @Autowired
    private PayRecordService payRecordService;

    @Test
    void allPayRecordList() {
        for (ExpensesRecord record : payRecordService.allPayRecordList()) {
            System.out.println(record);
        }
    }

    @Test
    void addPayRecord() {
    }

    @Test
    void deletePayRecord() {
    }

    @Test
    void updatePayRecord() {
    }

    @Test
    void monthPayMoney() {
    }
}