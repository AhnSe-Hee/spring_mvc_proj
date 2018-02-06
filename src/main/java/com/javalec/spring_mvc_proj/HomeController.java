package com.javalec.spring_mvc_proj;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javalec.member.Member;

/**
 * Handles requests for the application home page.
 */

//@RequestMapping("/board")
//hierarchy 구조인 경우, 
//클래스에 /board를  @RequestMapping하고
//메소드에 /view를 @RequestMapping하여 조합된 요청 경로를 만들수 있음(line 19, 47)
@Controller //Controller임을 명시 (servlet-context.xml에도 지정)
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//요청 경로
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//"http://localhost:8181/spring_mvc_proj/"로 get요청을 한 경우
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home"; //view page(.jsp) 명으로 servlet-context.xml에서 prefix와 suffix 사이 값임
	}
	
	//http://localhost:8181/spring_mvc_proj/board/view
	//@RequestMapping("/view") 
	@RequestMapping("/board/view") 
	public String view() {
		return "board/view";
	}
	
	//http://localhost:8181/spring_mvc_proj/board/content
	@RequestMapping("/board/content")
	public String content(Model model) { //Model객체를 파라미터로 받음
		model.addAttribute("id", 30); //Model객체에 데이터를 담음
		
		return "board/content"; //spring container가 model내 데이터를 jsp에서 쓸수있게 처리함
	}
	
	//http://localhost:8181/spring_mvc_proj/board/reply
	@RequestMapping("/board/reply")
	public ModelAndView Reply() {
		ModelAndView mv = new ModelAndView(); //Model객체를 사용하는 대신에 ModelAndView 객체 생성
		mv.addObject("id", 30); //Model 객체에 데이터 저장
		mv.setViewName("board/reply"); //View 명 설정
		
		return mv;
	}
	
	//HttpServletRequest 사용하여 URL내 parameter 값을 받아서 jsp에 출력 예제 (URL내 id, pw없어도 non-error)
	//http://localhost:8181/spring_mvc_proj/board/confirmId?id=ahnsehee&pw=12345
	@RequestMapping("/board/confirmId")
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
		model.addAttribute("id", id); //Model 객체에 데이터 저장
		model.addAttribute("pw", pw); //Model 객체에 데이터 저장
		
		return "/board/confirmId";
	}
		
	//@RequestParam 사용하여 form 값을 받아서 jsp에 출력 예제 (URL내 id, pw없으면 400 Bad Request error)
	//http://localhost:8181/spring_mvc_proj/board/confirmIdWithForm?id=ahnsehee&pw=12345
	@RequestMapping("/board/confirmIdWithForm")
	public String confirmIdWithForm(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		model.addAttribute("id", id); //Model 객체에 데이터 저장
		model.addAttribute("pw", pw); //Model 객체에 데이터 저장
		
		return "/board/confirmId";
	}
	
	//URL내 parameter 값을 받아서 jsp에 출력 예제 (URL내 id, pw없어도 non-error) : 메소드 매개변수가 지저분함
//	//http://localhost:8181/spring_mvc_proj/member/joinData?id=ahnsehee&pw=12345
//	@RequestMapping("/member/joinData")
//	public String joinData(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
//		Member member = new Member();
//		member.setId(id);
//		member.setPw(pw);
//		model.addAttribute("member", member); //Model객체에 member객체 추가
//		
//		return "member/joinData";
//	}
	
	//URL내 parameter 값을 받아서 jsp에 출력 예제 (URL내 id, pw없어도 non-error) : 메소드 매개변수가 간략함
	//데이터(커맨드) 객체를 사용하여 데이터가 많은 경우 코드양을 줄일 수 있음
	//그러나 URL에 Member객체 내 변수명과 일치하게 parameter를 설정해야 한다
	//http://localhost:8181/spring_mvc_proj/member/joinData?id=ahnsehee&pw=12345
	@RequestMapping("/member/joinData")
	public String joinData(Member member) {
		//커맨드 객체 이름이 member이기에 .jsp에서는 ${member.id}로 참조함
		//함수의 매개변수를 @ModelAttribute("mem") Member member 로 설정하면 .jsp에서는 ${mem.id}로 참조함
		return "member/joinData";
	}
	
	//@PathVariable 사용해서 URL내 parameter 값을 받아서 jsp에 출력 예제
	//http://localhost:8181/spring_mvc_proj/member/joinDataWithPathVariable/{ahnsehee}/{12345}
	@RequestMapping("/member/joinDataWithPathVariable/{id}/{pw}")
	public String joinDataWithPathVariable(@PathVariable String id, @PathVariable String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "member/joinDataWithPathVariable";
	}

	//post로 id받고 get으로 id 출력 예제
	//http://localhost:8181/spring_mvc_proj/index
	@RequestMapping("/index")
	public String goIndex() {
		return "index";
	}

	//post로 id받고 get으로 id 출력 예제
	//http://localhost:8181/spring_mvc_proj/student
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public ModelAndView goStudent(HttpServletRequest httpServletRequest) {
		System.out.println(">> RequestMethod.POST");
		
		String id = httpServletRequest.getParameter("id");
//		//parameter에  Model model이 있는 경우 (method return type이 String)
//		model.addAttribute("studentId", id);
//		return "student/studentId";
		
		//parameter에  Model model이 없는 경우 (method return type이 ModelAndView)
		ModelAndView mv = new ModelAndView();
		mv.setViewName("student/studentId");
		mv.addObject("studentId", id);
		return mv;
	}
	
	//get으로 id 출력 예제
	//http://localhost:8181/spring_mvc_proj/student?id=ahnsehee
	@RequestMapping(method = RequestMethod.GET, value = "/student")
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {	
		System.out.println(">> RequestMethod.GET");
		
		String id = httpServletRequest.getParameter("id");
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
}
