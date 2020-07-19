package com.xrafece.do_sport.mapper;

import com.xrafece.do_sport.model.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xrafece
 */
@Repository
public interface MemberMapper {
    List<Member> allMemberList();

    int addMember(Member member);

    int deleteMember(Member member);

    int updateMember(Member member);

    int allMemberNum();

    int memberNumByGender(int gender);

    List<Member> memberList(@Param("startIndex") int startId, @Param("pageSize") int pageSize);

    Member lastMember();

    int deleteMemberNById(int id);

    Member getMemberById(int id);

    Member getMemberByWxId(String wxId);
}
