package com.KoreaIT.example.JAM.service;

import com.KoreaIT.example.JAM.dto.AdminDto;
import com.KoreaIT.example.JAM.dto.MemberDto;

public class AdminService {
	 boolean loginCheck(AdminDto dto, HttpSession session) throws Exception;    //관리자 로그인을 체크하는 메소드
	 
	    
	 void admin_member_forced_evictionCheck(MemberDto dto) throws Exception; //강제탈퇴 시킬때 해당 회원이 있는지 체크하는 메소드
}
