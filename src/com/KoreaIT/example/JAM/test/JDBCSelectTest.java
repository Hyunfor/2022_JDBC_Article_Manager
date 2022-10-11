package com.KoreaIT.example.JAM.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.example.JAM.Article;

public class JDBCSelectTest {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// List로 압축
		List<Article> articles = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/jdbc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

			conn = DriverManager.getConnection(url, "root", "");

			String sql = "SELECT *";
			sql += " FROM article";
			sql += " ORDER BY id DESC;";

			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) { // get로 압축 풀기
//				int id = rs.getInt("id");
//				String regDate = rs.getString("regDate");
//				String updateDate = rs.getString("updateDate");
//				String title = rs.getString("title");
//				String body = rs.getString("body");
				
				// 데이터 들어있는지 확인
//				System.out.println(id);
//				System.out.println(regDate);
//				System.out.println(updateDate);
//				System.out.println(title);
//				System.out.println(body);

				// 압축 풀은 것을 다시 조립
//				Article article = new Article(id, regDate, updateDate, title, body);
//				articles.add(article);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("결과 : " + articles);
	}
}