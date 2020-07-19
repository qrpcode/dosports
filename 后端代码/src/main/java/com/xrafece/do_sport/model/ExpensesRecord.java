package com.xrafece.do_sport.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Xrafece
 */
@Data
@NoArgsConstructor
public class ExpensesRecord {
    private Integer id;
    private String wx_id;
    private Date pay_time;
    private Double pay_money;
    private String xddpay_order;
    private String pay_qr;
    private Integer pay_return;
    private String card_name;
}
