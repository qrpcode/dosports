package com.xrafece.do_sport.mapper;

import com.xrafece.do_sport.dto.KeepData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xrafece
 */
@Repository
public interface KeepDataMapper {
    List<KeepData> getKeepDataById(@Param("id") int id);

    List<KeepData> getKeepDataListById(@Param("id") int id, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    List<KeepData> getKeepDataListByWxId(@Param("wxId") String wxId, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
