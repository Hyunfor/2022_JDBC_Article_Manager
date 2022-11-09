package com.KoreaIT.example.JAM.service;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
	}

	public int doJoin(String loginId, String loginPw, String name, String address, String email) {
		return memberDao.doJoin(loginId, loginPw, name, address, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public int profileModify(int id, String loginPw, String name, String address, String email) {
		return memberDao.profileModify(id, loginPw, name, address, email);
		
	}

	

}
