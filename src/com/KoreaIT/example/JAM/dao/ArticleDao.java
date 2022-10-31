package com.KoreaIT.example.JAM.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class ArticleDao {

	public ArticleDao() {

	}

	public int doWrite(int memberId, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", memberId = ? ", memberId);
		sql.append(", title = ? ", title);
		sql.append(", body = ? ", body);
		sql.append(", hit = ? ", 0);

		return DBUtil.insert(Container.conn, sql);
	}

	public int doModify(int id, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append("WHERE id = ?", id);

		return DBUtil.update(Container.conn, sql);
	}

	public void doDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		DBUtil.delete(Container.conn, sql);
	}

	public boolean isArticeExists(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(id) > 0");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		return DBUtil.selectRowBooleanValue(Container.conn, sql);

	}

	public Article getArticle(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT A.*, M.name AS writerName");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ?", id);
		// article 하나를 가져와서 진행
		Map<String, Object> articleMap = DBUtil.selectRow(Container.conn, sql);

		if (articleMap.isEmpty()) {
			System.out.printf("%d번 게시글은 존재하지 않습니다\n", id);
			return null;
		}
		return new Article(articleMap);
	}

	public List<Article> getArticles() {
		SecSql sql = new SecSql();

		sql.append("SELECT A.*, M.name AS writerName");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY id DESC;");
		// article 여러개를 가져와서 진행
		List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);
		// key타입, value 타입
		// List에 비해서 Map이 자료를 가져오는 속도가 훨씬 빠름

		List<Article> articles = new ArrayList<>(); // 넘겨받은 자료로 여기서 객체를 생성

		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap)); // Map 데이터 안에 있는 key와 value를 article로 조립
		}
		return articles;
	}

	public void increaseHit(int id) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET hit = hit + 1");
		sql.append("WHERE id = ?", id);

		DBUtil.update(Container.conn, sql);

	}

	public List<Article> getForPrintArticles(Map<String, Object> args) {
		SecSql sql = new SecSql();

		String searchKeyword = "";
		int limitFrom = -1;
		int limtiTake = -1;
		
		if(args.containsKey("searchKeyword")) { // map은 get(key)값으로 꺼내옴. key 중복 되면 안됨
			searchKeyword = (String) args.get("searchKeyword"); // 기존형태로 가져와야함
		}
		
		if(args.containsKey("limitFrom")) { 
			limitFrom = (int) args.get("limitFrom"); 
		}
		
		if(args.containsKey("limitTake")) { 
			limtiTake = (int) args.get("limitTake"); 
		}
		
		sql.append("SELECT *");
		sql.append("FROM (");
		sql.append("SELECT a.*, m.name AS writerName");
		sql.append("FROM article AS a");
		sql.append("INNER JOIN `member` AS m");
		sql.append("ON a.memberId = m.id");
		sql.append("ORDER BY a.id DESC");
		if(limitFrom != -1) {
			sql.append("LIMIT ?, ?", limitFrom, limtiTake);

		}
		sql.append(") A");
		if(searchKeyword.length() > 0) {
			sql.append("WHERE title LIKE CONCAT('%', ?, '%')", searchKeyword);
		}


		List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}
	
}
