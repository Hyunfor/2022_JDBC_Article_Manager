package com.KoreaIT.example.JAM.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int doWrite(int memberId, String title, String body) {
		return articleDao.doWrite(memberId, title, body);
	}

	public int doModify(int id, String title, String body) {
		return articleDao.doModify(id, title, body);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public boolean isArticleExists(int id) {
		return articleDao.isArticeExists(id);
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);

	}

	public List<Article> getForPrintArticles(int page, int itemsInAPage, String searchKeyword) {
		int limitFrom = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
<<<<<<< HEAD

		Map<String, Object> args = new HashMap<>();
		args.put("searchKeyword", searchKeyword);
		args.put("limitFrom", limitFrom);
		args.put("limitTake", limitTake);

		return articleDao.getForPrintArticles(args);
	}
=======
		
		Map<String, Object> args = new HashMap<>(); // 실질적으로 사용할것은 Hash, Map은 담아두는 용도
		args.put("searchKeyword", searchKeyword); // 변수명과 키 이름이 동일한게 편함 , 중복은 불가
		args.put("limitFrom", limitFrom);
		args.put("limitTake", limitTake);
 	
		return articleDao.getForPrintArticles(args);
	}
	
	

>>>>>>> 57db209be40c2e25311e74d72bb11adf77f0e02d

}
