package com.javalec.spring_mvc_proj;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

	//redirect:키워드 예제 (회원 비밀번호 인증에 주로 사용)
	//http://localhost:8181/spring_mvc_proj/studentConfirm?id=ahnsehee > @RequestMapping("/studentOk")로 리다이렉트
	//http://localhost:8181/spring_mvc_proj/studentConfirm?id=ahnsewon > @RequestMapping("/studentNg")로 리다이렉트
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model){
		
		String id = httpServletRequest.getParameter("id");
		if(id.equals("ahnsehee")) { //id가 ahnsehee가 맞으면 @RequestMapping("/studentOk")로 리다이렉트됨
			return "redirect:studentOk";
		}
		
		return "redirect:studentNg"; //id가 abc가 아니면 @RequestMapping("/studentNg")로 리다이렉트됨
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(Model model){
		
		return "student/studentOk";
	}
	
	
	@RequestMapping("/studentNg")
	public String studentNg(Model model){
		
		return "student/studentNg";
	}
	
	//URL주소로 리다이렉트
	//http://localhost:8181/spring_mvc_proj/studentURL1 > //webapp/studentURL1.jsp로 리다이렉트
	@RequestMapping("/studentURL1")
	public String studentURL1(Model model) {
		System.out.println(">> /studentURL1");
		return "redirect:http://localhost:8181/spring_mvc_proj/studentURL1.jsp"; //webapp/studentURL1.jsp
	}
	
	//http://localhost:8181/spring_mvc_proj/studentURL2 > @RequestMapping("/student/studentURL2")로 리다이렉트
	@RequestMapping("/studentURL2")
	public String studentURL2(Model model) {
		System.out.println(">> /studentURL2");
		
		return "redirect:student/studentURL2"; //webapp/studentURL2.jsp
	}
	
	//student/studentURL2 > //webapp/studentURL2.jsp로 리다이렉트
	@RequestMapping("/student/studentURL2")
	public String studentURL22(Model model) {
		System.out.println(">> /student/studentURL2");
		
		return "redirect:http://localhost:8181/spring_mvc_proj/studentURL2.jsp"; //webapp/studentURL2.jsp
	}
	
	
}
