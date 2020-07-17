package com.xrafece.do_sport.service.impl;

import com.xrafece.do_sport.mapper.MemberMapper;
import com.xrafece.do_sport.model.Member;
import com.xrafece.do_sport.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xrafece
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public void setMemberMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public List<Member> allMemberList() {
        return memberMapper.allMemberList();
    }

    @Override
    public int addMember(Member member) {
        return memberMapper.addMember(member);
    }

    @Override
    public int deleteMember(Member member) {
        return memberMapper.deleteMember(member);
    }

    @Override
    public int updateMember(Member member) {
        return memberMapper.updateMember(member);
    }

    @Override
    public int allMemberNum() {
        return memberMapper.allMemberNum();
    }

    @Override
    public int memberNumByGender(int gender) {
        return memberMapper.memberNumByGender(gender);
    }

    @Override
    public List<Member> memberList(int startId, int pageSize) {
        return memberMapper.memberList(startId, pageSize);
    }

    @Override
    public Member lastMember() {
        return memberMapper.lastMember();
    }

    @Override
    public boolean deleteMemberNById(int id) {
        return memberMapper.deleteMemberNById(id) != 0;
    }

    @Override
    public Member getMemberById(int id) {
        return memberMapper.getMemberById(id);
    }

    @Override
    public Member getMemberByWxId(String wxId) {
        return memberMapper.getMemberByWxId(wxId);
    }
}
