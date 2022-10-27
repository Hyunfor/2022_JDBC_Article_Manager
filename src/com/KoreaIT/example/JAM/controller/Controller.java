package com.KoreaIT.example.JAM.controller;

import java.util.Scanner;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;

public class Controller {
	public Scanner sc;

	public Controller() {
		this.sc = Container.sc;
	}

	public static Member loginedMember; // Article과 Member공유재로 만들어두기

	public static boolean isLogined() {

		return loginedMember != null;
	}

}
