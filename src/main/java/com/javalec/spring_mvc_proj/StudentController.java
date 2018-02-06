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
	
	//������ ������ ���� Validatior�������̽��� ������ Ŭ������ validate()�޼ҵ带 ȣ����
//	@RequestMapping("/student/create")
//	public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
//		//BindingResult : validator�� ��ȿ�� �˻縦 �� ����� ����� ��ü
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
	
	//������ ������ ���� �޼ҵ峻���� validate()�� ���� ȣ������ �ʰ� annotation ����ؼ� ������ �����ӿ�ũ���� ȣ����
	@RequestMapping("/student/create")
	public String studentCreate(@ModelAttribute("student") @Valid Student student, BindingResult result) {
		//BindingResult : validator�� ��ȿ�� �˻縦 �� ����� ����� ��ü
		String page = "createDonePage";
		
		if(result.hasErrors()) { //BindingResult��ü�� ���� �������¿� ���� jsp�� �б��
			page = "createPage";
		}
		
		return page;
	}
	
	@InitBinder //@Valid�� �Ű������� �޾ұ⿡ ������ �����ӿ�ũ���� �ڵ� ȣ����
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new StudentValidator()); //StudentValidator�� validate()�޽��尡 �ڵ� ȣ��
	}
}
