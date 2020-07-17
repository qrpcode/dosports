package com.xrafece.do_sport.mapper;

import com.xrafece.do_sport.model.RechargeCard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xrafece
 */
@Repository
public interface RechargeCardMapper {
    List<RechargeCard> allRechargeCardList();

    int addRechargeCard(RechargeCard card);

    int deleteRechargeCard(RechargeCard card);

    int updateRechargeCard(RechargeCard card);

    int deleteAll();
}
