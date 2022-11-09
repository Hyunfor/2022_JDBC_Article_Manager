package com.KoreaIT.example.JAM.dao;

import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberDao {

	public MemberDao() {

	}

	public int doJoin(String loginId, String loginPw, String name, String address, String email) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);
		sql.append(", address = ?", address);
		sql.append(", email = ?", email);

		return DBUtil.insert(Container.conn, sql);
	}

	public boolean isLoginIdDup(String loginId) {
		// 아이디 중복체크 쿼리
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(loginId) > 0");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		return DBUtil.selectRowBooleanValue(Container.conn, sql);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

		if (memberMap.isEmpty()) {
			return null;
		}

		return new Member(memberMap);
	}

	public int profileModify(int id, String loginPw, String name, String address, String email) {
		SecSql sql = new SecSql();

		sql.append("UPDATE member");
		sql.append("SET updateDate = NOW()");
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);
		sql.append(", address = ?", address);
		sql.append(", email = ?", email);

		return DBUtil.update(Container.conn, sql);
	}

	public Member getMemberById(int id) { // sql 멤버정보 수정에 맡게 변경해야함.
		SecSql sql = new SecSql();

		sql.append("SELECT A.*, M.name AS writerName");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ?", id);
		// member 하나를 가져와서 진행
		Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

		if (memberMap.isEmpty()) {
			System.out.printf("%d번 회원은 존재하지 않습니다\n", id);
			return null;
		}
		return new Member(memberMap);
	}

}
