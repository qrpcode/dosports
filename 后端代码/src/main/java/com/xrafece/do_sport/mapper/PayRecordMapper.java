package com.xrafece.do_sport.mapper;

import com.xrafece.do_sport.model.ExpensesRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xrafece
 */
@Repository
public interface PayRecordMapper {
    List<ExpensesRecord> allPayRecordList();

    int addPayRecord(ExpensesRecord record);

    int deletePayRecord(ExpensesRecord record);

    int updatePayRecord(ExpensesRecord record);

    int monthPayMoney();
}
