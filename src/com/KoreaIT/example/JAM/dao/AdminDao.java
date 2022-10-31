package com.KoreaIT.example.JAM.dao;

import com.KoreaIT.example.JAM.dto.AdminDto;
import com.KoreaIT.example.JAM.dto.MemberDto;

public class AdminDao {
	boolean loginCheck(AdminDTO dto) throws Exception;    //로그인을 체크하는 메소드
	 
	 
    void admin_member_forced_evictionCheck(MemberDTO dto) throws Exception;    //회원 강제탈퇴 관련 메소드
}
