package com.javalec.spring_mvc_proj;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
public class StudentController {

	//http://localhost:8181/spring_mvc_proj/studentForm
	@RequestMapping("/studentForm")
	public String studentForm() {
		return "createPage";
	}
	
	//데이터 검증을 위해 Validatior인터페이스를 구현한 클래스의 validate()메소드를 호출함
//	@RequestMapping("/student/create")
//	public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
//		//BindingResult : validator가 유효성 검사를 한 결과가 담겨질 객체
//		String page = "createDonePage";
//		
//		StudentValidator validator = new StudentValidator();
//		validator.validate(student, result);
//		if(result.hasErrors()) {
//			System.out.println(">> has Errors");
//			page = "createPage";
//		}else {
//			System.out.println(">> has not Errors");
//		}
//		return page;
//	}
	
	//데이터 검증을 위해 메소드내에서 validate()를 직접 호출하지 않고 annotation 사용해서 스프링 프레임워크에서 호출함
	@RequestMapping("/student/create")
	public String studentCreate(@ModelAttribute("student") @Valid Student student, BindingResult result) {
		//BindingResult : validator가 유효성 검사를 한 결과가 담겨질 객체
		String page = "createDonePage";
		
		if(result.hasErrors()) { //BindingResult객체에 의해 에러상태에 따라 jsp가 분기됨
			page = "createPage";
		}
		
		return page;
	}
	
	@InitBinder //@Valid로 매개변수를 받았기에 스프링 프레임워크에서 자동 호출함
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new StudentValidator()); //StudentValidator의 validate()메스드가 자동 호출
	}
}
