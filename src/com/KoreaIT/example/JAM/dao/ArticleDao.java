package com.KoreaIT.example.JAM.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class ArticleDao {
	
	private Connection conn;
	
	public ArticleDao() {
		
	}
	
	public int doWrite(String title, String body) {
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ? ", title); 
		sql.append(", body = ? ", body);
		
		return DBUtil.insert(conn, sql);
	}
	
	public int doModify(int id, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append("WHERE id = ?", id);

		return DBUtil.update(conn, sql);
	}

	public void doDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		DBUtil.delete(conn, sql);
	}

	public boolean isArticeExists(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(id) > 0");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		return DBUtil.selectRowBooleanValue(conn, sql);

	}
	
	public Article getArticle(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		// article 하나를 가져와서 진행
		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);
		
		if (articleMap.isEmpty()) {
			System.out.printf("%d번 게시글은 존재하지 않습니다\n", id);
			return null;
		}
		return new Article(articleMap);
	}
	
	public List<Article> getArticles() {
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append(" FROM article");
		sql.append(" ORDER BY id DESC;"); 
		// article 여러개를 가져와서 진행
		List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);
		//      key타입,  value 타입 
		// List에 비해서 Map이 자료를 가져오는 속도가 훨씬 빠름
		
		List<Article> articles = new ArrayList<>(); // 넘겨받은 자료로 여기서 객체를 생성
		
		for(Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap)); // Map 데이터 안에 있는   key와  value를 article로 조립
		}
		return articles;
	}
}
