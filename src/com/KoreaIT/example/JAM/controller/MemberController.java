package com.KoreaIT.example.JAM.controller;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.service.MemberService;

public class MemberController extends Controller {
	
	private MemberService memberService;
	
	public MemberController() {
		this.memberService = Container.memberService;
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
			
			boolean isLoginIdDup =  memberService.isLoginIdDup(loginId); 
			
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
		
		memberService.doJoin(loginId, loginPw, name);

		System.out.printf("%s 회원님, 가입 되었습니다\n", name);

	}
	
	public void doLogin(String cmd) {
		String loginId = null;
		String loginPw = null;
		System.out.println("== 로그인 ==");
		
		while(true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}
			
			boolean isLoginIdDup =  memberService.isLoginIdDup(loginId); 
			
			if(isLoginIdDup == false) { // 아이디가 없는경우
				System.out.printf("%s은(는) 존재하지 않는 아이디입니다.\n", loginId);
				continue;
			}	
			break;	
		}
		
		Member member = memberService.getMemberByLoginId(loginId); // 입력받은 로그인 아이디
//		System.out.println(member.id);
//		System.out.println(member.regDate);
//		System.out.println(member.updateDate);
//		System.out.println(member.loginId);
//		System.out.println(member.loginPw);
		
		int tryCount = 0; // 횟수 제한
		int tryMaxCount = 3;
		
		while(true) {
			if(tryCount >= tryMaxCount) { // 너무 많이 틀릴시
				System.out.println("비밀번호를 확인하고 다시 시도해주세요.");
				break;
			}
			
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if(loginPw.length() == 0) {
				tryCount++;
				System.out.println("비밀번호 확인을 입력해주세요");
				continue;
			}
			
			if(member.loginPw.equals(loginPw) == false) { // 비밀번호 비교 후 일치 or 불일치시
				tryCount++;
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}	
			System.out.printf("%s님 환영합니다\n", member.name);
			
			Container.session.loginedMemberId = member.id;
			Container.session.loginedMember = member;
			
			break;
		}
		
	}
	public void showProfile(String cmd) {
		if (Container.session.loginedMemberId == -1) {
			System.out.println("로그인 상태가 아닙니다.");
		} else {
			System.out.println(Container.session.loginedMember.name);
		}
	}
	
}
