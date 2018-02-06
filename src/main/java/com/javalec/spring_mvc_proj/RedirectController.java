package com.javalec.spring_mvc_proj;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

	//redirect:Ű���� ���� (ȸ�� ��й�ȣ ������ �ַ� ���)
	//http://localhost:8181/spring_mvc_proj/studentConfirm?id=ahnsehee > @RequestMapping("/studentOk")�� �����̷�Ʈ
	//http://localhost:8181/spring_mvc_proj/studentConfirm?id=ahnsewon > @RequestMapping("/studentNg")�� �����̷�Ʈ
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model){
		
		String id = httpServletRequest.getParameter("id");
		if(id.equals("ahnsehee")) { //id�� ahnsehee�� ������ @RequestMapping("/studentOk")�� �����̷�Ʈ��
			return "redirect:studentOk";
		}
		
		return "redirect:studentNg"; //id�� abc�� �ƴϸ� @RequestMapping("/studentNg")�� �����̷�Ʈ��
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(Model model){
		
		return "student/studentOk";
	}
	
	
	@RequestMapping("/studentNg")
	public String studentNg(Model model){
		
		return "student/studentNg";
	}
	
	//URL�ּҷ� �����̷�Ʈ
	//http://localhost:8181/spring_mvc_proj/studentURL1 > //webapp/studentURL1.jsp�� �����̷�Ʈ
	@RequestMapping("/studentURL1")
	public String studentURL1(Model model) {
		System.out.println(">> /studentURL1");
		return "redirect:http://localhost:8181/spring_mvc_proj/studentURL1.jsp"; //webapp/studentURL1.jsp
	}
	
	//http://localhost:8181/spring_mvc_proj/studentURL2 > @RequestMapping("/student/studentURL2")�� �����̷�Ʈ
	@RequestMapping("/studentURL2")
	public String studentURL2(Model model) {
		System.out.println(">> /studentURL2");
		
		return "redirect:student/studentURL2"; //webapp/studentURL2.jsp
	}
	
	//student/studentURL2 > //webapp/studentURL2.jsp�� �����̷�Ʈ
	@RequestMapping("/student/studentURL2")
	public String studentURL22(Model model) {
		System.out.println(">> /student/studentURL2");
		
		return "redirect:http://localhost:8181/spring_mvc_proj/studentURL2.jsp"; //webapp/studentURL2.jsp
	}
	
	
}
