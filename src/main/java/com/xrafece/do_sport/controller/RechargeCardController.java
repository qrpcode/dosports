package com.xrafece.do_sport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrafece.do_sport.model.ExpensesRecord;
import com.xrafece.do_sport.model.Member;
import com.xrafece.do_sport.model.RechargeCard;
import com.xrafece.do_sport.service.MemberService;
import com.xrafece.do_sport.service.PayRecordService;
import com.xrafece.do_sport.service.RechargeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Xrafece
 */
@RestController
@RequestMapping("card")
public class RechargeCardController {
    @Autowired
    private RechargeCardService rechargeCardService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PayRecordService payRecordService;

    @RequestMapping("list")
    public Map allRechargeCardList(){
        List<RechargeCard> cardList = rechargeCardService.allRechargeCardList();
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (cardList != null){
            map.put("fail", 1);
            map.put("cardNum", cardList.size());
            map.put("card", cardList);
        }
        return map;
    }

    @RequestMapping("submit")
    public Map submitRechargeCardList(String token, String cardList) {
        Map<Object, Object> map = new HashMap<>();
        map.put("fail", -1);
        if (token != null) {
            //解析cardList为列表
            ObjectMapper objectMapper = new ObjectMapper();
            List list = objectMapper.convertValue(cardList, List.class);
            for (Object o : list) {
                System.out.println(list);
            }
            map.put("fail", 1);
        }
        return map;
    }

    @RequestMapping("recharge")
    public Map rechargeForCard(String token, String wxid, RechargeCard card, Date rechargeTime) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("fail", -1);

        // 事务操作
        // 1.修改会员到期时间
        Integer addCardExpireDay = card.getCard_expireDay();
        Member member = memberService.getMemberByWxId(wxid);
        Date card_expireTime = member.getCard_expireTime();
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(card_expireTime);
        calendar.add(Calendar.DATE, addCardExpireDay);
        Date time = calendar.getTime();
        member.setCard_expireTime(time);
        memberService.updateMember(member);
        // 2.添加消费记录
        ExpensesRecord record = new ExpensesRecord();
        record.setCard_name(card.getCard_name());
        record.setPay_money(card.getCard_price());
        record.setPay_time(rechargeTime);
        record.setWx_id(wxid);
        payRecordService.addPayRecord(record);
        map.put("fail", 1);
        return map;
    }
}
