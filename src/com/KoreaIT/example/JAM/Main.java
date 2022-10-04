package com.KoreaIT.example.JAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 실행 ==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.printf("명령어 입력 ) ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
		}
		
		System.out.println("== 프로그램 종료 ==");
		sc.close();
		
	}
}
