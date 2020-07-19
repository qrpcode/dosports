package com.xrafece.do_sport.service.impl;

import com.xrafece.do_sport.mapper.RechargeCardMapper;
import com.xrafece.do_sport.model.RechargeCard;
import com.xrafece.do_sport.service.RechargeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xrafece
 */
@Service
public class RechargeCardServiceImpl implements RechargeCardService {
    @Autowired
    @Qualifier("rechargeCardMapper")
    private RechargeCardMapper rechargeCardMapper;

    @Override
    public List<RechargeCard> allRechargeCardList() {
        return rechargeCardMapper.allRechargeCardList();
    }

    @Override
    public int addRechargeCard(RechargeCard card) {
        return rechargeCardMapper.addRechargeCard(card);
    }

    @Override
    public int deleteRechargeCard(RechargeCard card) {
        return rechargeCardMapper.deleteRechargeCard(card);
    }

    @Override
    public int updateRechargeCard(RechargeCard card) {
        return rechargeCardMapper.updateRechargeCard(card);
    }

    @Override
    public int deleteAll() {
        return rechargeCardMapper.deleteAll();
    }
}
