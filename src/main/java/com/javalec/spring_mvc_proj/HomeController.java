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
//hierarchy ������ ���, 
//Ŭ������ /board��  @RequestMapping�ϰ�
//�޼ҵ忡 /view�� @RequestMapping�Ͽ� ���յ� ��û ��θ� ����� ����(line 19, 47)
@Controller //Controller���� ��� (servlet-context.xml���� ����)
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//��û ���
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//"http://localhost:8181/spring_mvc_proj/"�� get��û�� �� ���
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home"; //view page(.jsp) ������ servlet-context.xml���� prefix�� suffix ���� ����
	}
	
	//http://localhost:8181/spring_mvc_proj/board/view
	//@RequestMapping("/view") 
	@RequestMapping("/board/view") 
	public String view() {
		return "board/view";
	}
	
	//http://localhost:8181/spring_mvc_proj/board/content
	@RequestMapping("/board/content")
	public String content(Model model) { //Model��ü�� �Ķ���ͷ� ����
		model.addAttribute("id", 30); //Model��ü�� �����͸� ����
		
		return "board/content"; //spring container�� model�� �����͸� jsp���� �����ְ� ó����
	}
	
	//http://localhost:8181/spring_mvc_proj/board/reply
	@RequestMapping("/board/reply")
	public ModelAndView Reply() {
		ModelAndView mv = new ModelAndView(); //Model��ü�� ����ϴ� ��ſ� ModelAndView ��ü ����
		mv.addObject("id", 30); //Model ��ü�� ������ ����
		mv.setViewName("board/reply"); //View �� ����
		
		return mv;
	}
	
	//HttpServletRequest ����Ͽ� URL�� parameter ���� �޾Ƽ� jsp�� ��� ���� (URL�� id, pw��� non-error)
	//http://localhost:8181/spring_mvc_proj/board/confirmId?id=ahnsehee&pw=12345
	@RequestMapping("/board/confirmId")
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
		model.addAttribute("id", id); //Model ��ü�� ������ ����
		model.addAttribute("pw", pw); //Model ��ü�� ������ ����
		
		return "/board/confirmId";
	}
		
	//@RequestParam ����Ͽ� form ���� �޾Ƽ� jsp�� ��� ���� (URL�� id, pw������ 400 Bad Request error)
	//http://localhost:8181/spring_mvc_proj/board/confirmIdWithForm?id=ahnsehee&pw=12345
	@RequestMapping("/board/confirmIdWithForm")
	public String confirmIdWithForm(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		model.addAttribute("id", id); //Model ��ü�� ������ ����
		model.addAttribute("pw", pw); //Model ��ü�� ������ ����
		
		return "/board/confirmId";
	}
	
	//URL�� parameter ���� �޾Ƽ� jsp�� ��� ���� (URL�� id, pw��� non-error) : �޼ҵ� �Ű������� ��������
//	//http://localhost:8181/spring_mvc_proj/member/joinData?id=ahnsehee&pw=12345
//	@RequestMapping("/member/joinData")
//	public String joinData(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
//		Member member = new Member();
//		member.setId(id);
//		member.setPw(pw);
//		model.addAttribute("member", member); //Model��ü�� member��ü �߰�
//		
//		return "member/joinData";
//	}
	
	//URL�� parameter ���� �޾Ƽ� jsp�� ��� ���� (URL�� id, pw��� non-error) : �޼ҵ� �Ű������� ������
	//������(Ŀ�ǵ�) ��ü�� ����Ͽ� �����Ͱ� ���� ��� �ڵ���� ���� �� ����
	//�׷��� URL�� Member��ü �� ������� ��ġ�ϰ� parameter�� �����ؾ� �Ѵ�
	//http://localhost:8181/spring_mvc_proj/member/joinData?id=ahnsehee&pw=12345
	@RequestMapping("/member/joinData")
	public String joinData(Member member) {
		//Ŀ�ǵ� ��ü �̸��� member�̱⿡ .jsp������ ${member.id}�� ������
		//�Լ��� �Ű������� @ModelAttribute("mem") Member member �� �����ϸ� .jsp������ ${mem.id}�� ������
		return "member/joinData";
	}
	
	//@PathVariable ����ؼ� URL�� parameter ���� �޾Ƽ� jsp�� ��� ����
	//http://localhost:8181/spring_mvc_proj/member/joinDataWithPathVariable/{ahnsehee}/{12345}
	@RequestMapping("/member/joinDataWithPathVariable/{id}/{pw}")
	public String joinDataWithPathVariable(@PathVariable String id, @PathVariable String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "member/joinDataWithPathVariable";
	}

	//post�� id�ް� get���� id ��� ����
	//http://localhost:8181/spring_mvc_proj/index
	@RequestMapping("/index")
	public String goIndex() {
		return "index";
	}

	//post�� id�ް� get���� id ��� ����
	//http://localhost:8181/spring_mvc_proj/student
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public ModelAndView goStudent(HttpServletRequest httpServletRequest) {
		System.out.println(">> RequestMethod.POST");
		
		String id = httpServletRequest.getParameter("id");
//		//parameter��  Model model�� �ִ� ��� (method return type�� String)
//		model.addAttribute("studentId", id);
//		return "student/studentId";
		
		//parameter��  Model model�� ���� ��� (method return type�� ModelAndView)
		ModelAndView mv = new ModelAndView();
		mv.setViewName("student/studentId");
		mv.addObject("studentId", id);
		return mv;
	}
	
	//get���� id ��� ����
	//http://localhost:8181/spring_mvc_proj/student?id=ahnsehee
	@RequestMapping(method = RequestMethod.GET, value = "/student")
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {	
		System.out.println(">> RequestMethod.GET");
		
		String id = httpServletRequest.getParameter("id");
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
}
