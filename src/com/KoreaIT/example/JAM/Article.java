package com.KoreaIT.example.JAM;

import java.time.LocalDateTime;
import java.util.Map;

public class Article extends Object { // Object - 모든 class의 최상위 class
	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public int memberId;
	public String title;
	public String body;
	public String writerName;
	public int hit;
	public int rec;

	public Article(Map<String, Object> articleMap) { // 압축 풀기
		this.id = (int) articleMap.get("id"); // 형변환 해줘야함.
		this.regDate = (LocalDateTime) articleMap.get("regDate");
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.memberId = (int) articleMap.get("memberId");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.writerName = (String) articleMap.get("writerName");
		this.hit = (int) articleMap.get("hit");
		this.rec = (int) articleMap.get("rec");

	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", memberId=" + memberId
				+ ", title=" + title + ", body=" + body + ", writerName=" + writerName + ", hit=" + hit + ", rec=" + rec
				+ "]";
	}

}
