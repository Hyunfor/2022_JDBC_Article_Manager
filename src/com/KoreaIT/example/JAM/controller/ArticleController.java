package com.KoreaIT.example.JAM.controller;

import java.util.List;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.service.ArticleService;

public class ArticleController extends Controller {

	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void doWrite(String cmd) {

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		System.out.println("== 게시물 작성 ==");

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int memberId = Container.session.loginedMemberId;

		int id = articleService.doWrite(memberId, title, body);

		System.out.printf("%d번 글이 생성 되었습니다\n", id);

	}
<<<<<<< HEAD

	public void showList(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int page = 1;
		String searchKeyword = "";

		if(cmdBits.length >= 3) {
			page = Integer.parseInt(cmdBits[2]);
		}

		if(cmdBits.length >= 4) {
			searchKeyword = cmdBits[3];
		}

		int itemsInAPage = 10;

		List<Article> articles = articleService.getForPrintArticles(page, itemsInAPage, searchKeyword);

=======
	
	public void showList(String cmd) { 
		
		String[] cmdBits = cmd.split(" ");
		
		int page = 1;
		String searchKeyword = "";
		
		if(cmdBits.length >= 3) {
			page = Integer.parseInt(cmdBits[2]);
		}
		
		if(cmdBits.length >= 4) { // 검색어 까지 입력이 된 경우
			searchKeyword = cmdBits[3];
		}
		
		int itemsInAPage = 10; // 한페이지에서 보이는 게시글 수 
		
		List<Article> articles = articleService.getForPrintArticles(page, itemsInAPage, searchKeyword); 
		
>>>>>>> 57db209be40c2e25311e74d72bb11adf77f0e02d
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}

		System.out.println("== 게시물 리스트 ==");

		System.out.println("번호	|	제목	|	작성자	|	조회수	|	작성일");

		for (Article article : articles) {
			System.out.printf("%d	|	%s	|	%s	|	%d	|	%s\n", article.id, article.title, article.writerName,
					article.hit, article.updateDate);
		}

	}

	public void showDetail(String cmd) {

		int id = Integer.parseInt(cmd.split(" ")[2]);

		articleService.increaseHit(id);

		Article article = articleService.getArticle(id);

		if (article == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;

		}

		System.out.printf("== %d번 게시물 상세보기 ==\n", id);
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("작성자 : %s\n", article.writerName);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("조회수 : %s\n", article.hit);

	}

	public void doModify(String cmd) {

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(id);

		if (article == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다\n", id);
			return;
		}

		if (article.memberId != Container.session.loginedMemberId) {
			System.out.println("해당 게시글에 대한 권한이 없습니다.");
			return;
		}

		System.out.printf("== %d번 게시물 수정 ==\n", id);

		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();

		articleService.doModify(id, title, body);

		System.out.printf("%d번 글이 수정 되었습니다\n", id);

	}

	public void doDelete(String cmd) {

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(id); // modify와 기능 동일

		if (article == null) { // modify와 기능 동일
			System.out.printf("%d번 게시글은 존재하지 않습니다\n", id);
			return;
		}

		if (article.memberId != Container.session.loginedMemberId) { // modify와 기능 동일
			System.out.println("해당 게시글에 대한 권한이 없습니다.");
			return;
		}

		System.out.printf("== %d번 게시물 삭제 ==\n", id);

		articleService.doDelete(id);

		System.out.printf("%d번 글이 삭제 되었습니다\n", id);

	}

}
