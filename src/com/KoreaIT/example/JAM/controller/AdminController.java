package com.KoreaIT.example.JAM.controller;

import com.KoreaIT.example.JAM.dto.AdminDto;
import com.KoreaIT.example.JAM.service.AdminService;

public class AdminController {
	 
	AdminService adminservice;
	  
	 public ModelAndView admin_login(String admin_id, String admin_pass, HttpSession session) {
	        
	        //로그인 체크도 같이 함
	        //dto에 값들을 넣기 위해 객체를 생성한다.
	        AdminDto dto = new AdminDto();
	        
	        
	        dto.setAdmin_id(admin_id);
	        dto.setAdmin_pass(admin_pass);
	        
	        //로그인 체크를 하기위한 메소드, 로그인 체크후 결과를 result 변수에 넣는다.
	        boolean result = adminservice.loginCheck(dto, session);
	        ModelAndView mav = new ModelAndView();
	        
	        
	        if(result)    {//로그인이 성공했을시 출력되는 구문
	            mav.setViewName("home");    //로그인이 성공했을시 이동하게되는 뷰의 이름
	            mav.addObject("admin_id", session.getAttribute(admin_id));
	            
	            }else if(session.getAttribute(admin_id) == null) {    //로그인 실패 했을시 출력
	                
	                //뷰에 전달할 값
	                mav.addObject("message", "관리자의 아이디 혹은 비밀번호가 일치하지 않습니다.");
	            
	            }
	        
	                return mav;
	        }
}
