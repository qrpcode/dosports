package com.xrafece.do_sport.service;

import com.xrafece.do_sport.model.Member;

import java.util.List;

/**
 * @author Xrafece
 */
public interface MemberService {
    List<Member> allMemberList();

    int addMember(Member member);

    int deleteMember(Member member);

    int updateMember(Member member);

    int allMemberNum();

    int memberNumByGender(int gender);

    List<Member> memberList(int startId, int pageSize);

    Member lastMember();

    boolean deleteMemberNById(int id);

    Member getMemberById(int id);

    Member getMemberByWxId(String wxId);
}
