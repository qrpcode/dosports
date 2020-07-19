package com.xrafece.do_sport.mapper;

import com.xrafece.do_sport.dto.PayData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xrafece
 */
@Repository
public interface PayDataMapper {
    List<PayData> allPayDataList();

    List<PayData> payDataList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    PayData lastPayData();

    List<PayData> getPayDataById(int id);

    List<PayData> getPayDataByWxId(@Param("wxId") String wxId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    int addPayData(PayData Data);

    int deletePayData(PayData Data);

    int updatePayData(PayData Data);
}
