package com.KoreaIT.example.JAM;

import java.time.LocalDateTime;
import java.util.Map;

public class Member extends Object { // Object - 모든 class의 최상위 class
	public int id;
	public int memberId;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public String loginId;
	public String loginPw;
	public String name;
	public String address;
	public String email;

	public Member(int id, LocalDateTime regDate, LocalDateTime updateDate, String loginId, String loginPw,
			String name, String address, String email) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.address = address;
		this.email = email;
	}

	public Member(Map<String, Object> MemberMap) {
		this.id = (int) MemberMap.get("id"); // 형변환 해줘야함.
		this.regDate = (LocalDateTime) MemberMap.get("regDate");
		this.updateDate = (LocalDateTime) MemberMap.get("updateDate");
		this.loginId = (String) MemberMap.get("loginId");
		this.loginPw = (String) MemberMap.get("loginPw");
		this.name = (String) MemberMap.get("name");
		this.address = (String)MemberMap.get("address");
		this.email = (String)MemberMap.get("email");
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", loginId=" + loginId
				+ ", loginPw=" + loginPw + ", name=" + name + ", address=" + address + ", email=" + email + "]";
	}



}
