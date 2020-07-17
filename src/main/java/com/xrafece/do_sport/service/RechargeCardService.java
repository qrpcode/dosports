package com.xrafece.do_sport.service;

import com.xrafece.do_sport.model.RechargeCard;

import java.util.List;

/**
 * @author Xrafece
 */
public interface RechargeCardService {
    List<RechargeCard> allRechargeCardList();

    int addRechargeCard(RechargeCard card);

    int deleteRechargeCard(RechargeCard card);

    int updateRechargeCard(RechargeCard card);

    int deleteAll();
}
