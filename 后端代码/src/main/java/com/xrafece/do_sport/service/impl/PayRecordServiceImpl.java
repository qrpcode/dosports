package com.xrafece.do_sport.service.impl;

import com.xrafece.do_sport.mapper.PayRecordMapper;
import com.xrafece.do_sport.model.ExpensesRecord;
import com.xrafece.do_sport.service.PayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xrafece
 */
@Service
public class PayRecordServiceImpl implements PayRecordService {
    @Autowired
    private PayRecordMapper payRecordMapper;

    @Override
    public List<ExpensesRecord> allPayRecordList() {
        return payRecordMapper.allPayRecordList();
    }

    @Override
    public int addPayRecord(ExpensesRecord record) {
        return payRecordMapper.addPayRecord(record);
    }

    @Override
    public int deletePayRecord(ExpensesRecord record) {
        return payRecordMapper.deletePayRecord(record);
    }

    @Override
    public int updatePayRecord(ExpensesRecord record) {
        return payRecordMapper.updatePayRecord(record);
    }

    @Override
    public int monthPayMoney() {
        return payRecordMapper.monthPayMoney();
    }
}
