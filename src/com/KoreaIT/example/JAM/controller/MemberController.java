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
		String address = null;
		String email = null;
		System.out.println("== 회원가입 ==");

		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup) { // 중복된 아이디가 있는 경우
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}

			break;
		}
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}

			boolean loginPwCheck = true;

			while (true) {
				System.out.printf("비밀번호 확인 : ");
				loginPwChk = sc.nextLine().trim();

				if (loginPwChk.length() == 0) {
					System.out.println("비밀번호 확인을 입력해주세요");
					continue;
				}

				if (loginPw.equals(loginPwChk) == false) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
					loginPwCheck = false;
				}
				break;
			}
			if (loginPwCheck) {
				break;
			}
		}

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.printf("주소 : ");
			address = sc.nextLine().trim();

			if (address.length() == 0) {
				System.out.println("주소를 입력해주세요");
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.printf("이메일 : ");
			email = sc.nextLine().trim();

			if (email.length() == 0) {
				System.out.println("이메일을 입력해주세요");
				continue;
			}
			break;
		}

		memberService.doJoin(loginId, loginPw, name, address, email);

		System.out.printf("%s 회원님, 가입 되었습니다\n", name);

	}

	public void doLogin(String cmd) {
		String loginId = null;
		String loginPw = null;
		System.out.println("== 로그인 ==");

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup == false) { // 아이디가 없는경우
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

		while (true) {
			if (tryCount >= tryMaxCount) { // 너무 많이 틀릴시
				System.out.println("비밀번호를 확인하고 다시 시도해주세요.");
				break;
			}

			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				tryCount++;
				System.out.println("비밀번호 확인을 입력해주세요");
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) { // 비밀번호 비교 후 일치 or 불일치시
				tryCount++;
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			System.out.printf("%s님 환영합니다\n", member.name);

			Container.session.login(member);

			break;
		}
		

	}

	public void showProfile(String cmd) {
		if (Container.session.loginedMemberId == -1) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}
		
		System.out.println("아이디 : " + Container.session.loginedMember.loginId);
		System.out.println("가입일 : " + Container.session.loginedMember.regDate);
		System.out.println("이름 : " + Container.session.loginedMember.name);
		System.out.println("주소 : " + Container.session.loginedMember.address);

	}
	
	public void profileModify(String cmd) {

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Member member = memberService.getMemberById(id);

		if (member == null) {
			System.out.printf("%d번 회원은 존재하지 않습니다\n", id);
			return;
		}

		if (member.memberId != Container.session.loginedMemberId) {
			System.out.println("해당 계정에 대한 권한이 없습니다.");
			return;
		}
		String loginPw = null;
		System.out.printf("== %d번 프로필 수정 ==\n", id);

		System.out.printf("새 비밀번호 : ");
		loginPw = sc.nextLine();
		System.out.printf("이름 변경 : ");
		String name = sc.nextLine();
		System.out.printf("새 주소 : ");
		String address = sc.nextLine();
		System.out.printf("새 이메일 : ");
		String email = sc.nextLine();

		memberService.profileModify(id, loginPw, name, address, email);

		System.out.printf("%d번 회원님 정보가 변경 되었습니다\n", id);

	}

	public void doLogout(String cmd) {
		Container.session.logout();

		System.out.println("로그아웃 되었습니다.");

	}


}
