package com.xrafece.do_sport.service;

import com.xrafece.do_sport.model.ExpensesRecord;

import java.util.List;

/**
 * @author Xrafece
 */
public interface PayRecordService {
    List<ExpensesRecord> allPayRecordList();

    int addPayRecord(ExpensesRecord record);

    int deletePayRecord(ExpensesRecord record);

    int updatePayRecord(ExpensesRecord record);

    int monthPayMoney();
}
