package com.javalec.spring_mvc_proj;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Student.class.isAssignableFrom(arg0); 
		//런타임에 동적으로 해당 class가 다른 인터페이스를 구현/상속받았는지 체크
		//cf) instanceof는 컴파일 시 해당 class가 다른 인터페이스를 구현/상속받았는지 체크
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println(">> validate()");
		Student student = (Student)obj;
		
		String studentName = student.getName();
		if(studentName == null || studentName.trim().isEmpty()) {
			System.out.println(">> studentName is null or empty");
			errors.rejectValue("name", "trouble"); //필드(name)에 대한 에러 코드(trouble)를 추가
		}
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "trouble");
		//값이 null/ 공백문자이거나 길이가 0으로 구성된 경우
		
		int studentId = student.getId();
		if(studentId == 0) {
			System.out.println(">> studentId is 0");
			errors.rejectValue("id", "trouble");
		}
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "trouble");
		//값이 null/ 공백문자이거나 길이가 0으로 구성된 경우
	}
	
}
