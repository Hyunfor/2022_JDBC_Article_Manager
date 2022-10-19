package com.KoreaIT.example.JAM.container;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.controller.ArticleController;
import com.KoreaIT.example.JAM.controller.MemberController;
import com.KoreaIT.example.JAM.dao.ArticleDao;
import com.KoreaIT.example.JAM.dao.MemberDao;
import com.KoreaIT.example.JAM.service.ArticleService;
import com.KoreaIT.example.JAM.service.MemberService;
import com.KoreaIT.example.JAM.session.Session;

public class Container { // 프로그램 실행 후 끄기전까지 오랫동안 데이터를 담아둘 컨테이너
	public static ArticleController articleController;
	public static MemberController memberController;

	public static ArticleService articleService;
	public static MemberService memberService;

	public static ArticleDao articleDao;
	public static MemberDao memberDao;

	public static Session session;
	public static Scanner sc;
	
	public static Connection conn;

	public static void init() {
		session = new Session();

		articleDao = new ArticleDao();
		memberDao = new MemberDao();

		articleService = new ArticleService();
		memberService = new MemberService();

		articleController = new ArticleController();
		memberController = new MemberController();
		
	}
	
}
