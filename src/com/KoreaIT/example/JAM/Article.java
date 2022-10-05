package com.KoreaIT.example.JAM;

public class Article extends Object{ // Object - 모든 class의 최상위 class
	
	int id;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + "]";
	}
	
	
}
