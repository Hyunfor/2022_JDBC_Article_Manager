package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberController extends Controller {

	private Connection conn;
	
	public MemberController(Connection conn, Scanner sc) {
		super(sc); // this는 부모에게 상속받더라도 받아오는 변수 이름이 같을 시에는 가까운 것을 바라봄.
					// super는 부모에게 상속 받는 상황일 시 변수의 이름이 같을 시 부모의 것을 가져옴.
		this.conn = conn;
	}
	public void doJoin(String cmd) {
		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;
		System.out.println("== 회원가입 ==");

		while(true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();

			if(loginId.length() == 0) { 
				System.out.println("아이디를 입력해주세요");
				continue;
			}
			
			// 아이디 중복체크 쿼리
			SecSql sql = new SecSql();
			
			sql.append("SELECT COUNT(loginId) > 0");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?", loginId);
			
			boolean isLoginIdDup =  DBUtil.selectRowBooleanValue(conn, sql);
			
			if(isLoginIdDup) { // 중복된 아이디가 있는 경우
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			
			break;
		}
		while(true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if(loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}

			boolean loginPwCheck = true;

			while(true) {
				System.out.printf("비밀번호 확인 : ");
				loginPwChk = sc.nextLine().trim();

				if(loginPwChk.length() == 0) {
					System.out.println("비밀번호 확인을 입력해주세요");
					continue;
				}

				if(loginPw.equals(loginPwChk) == false) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
					loginPwCheck = false;	
				}
				break;
			}
			if(loginPwCheck) {
				break;
			}
		}

		while(true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if(name.length() == 0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;
		}
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);
		
		DBUtil.insert(conn, sql);

		System.out.printf("%s 회원님, 가입 되었습니다\n", name);

	}

}
