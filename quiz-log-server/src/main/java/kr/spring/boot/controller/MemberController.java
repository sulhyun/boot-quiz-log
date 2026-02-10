package kr.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.boot.model.dto.SignupDTO;
import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/signup")
	public String signup() {
		System.out.println("Signup: GET");
		return "member/signup";
	} // 회원가입 페이지
	
	@ResponseBody
	@GetMapping("/checkId")
	public ResponseEntity<Boolean> checkId(@RequestParam String mb_id) {
		System.out.println("CheckId: GET (Axios)");
		MemberVO user = memberService.getMember(mb_id);
		boolean res = (user == null) ? true : false;
		return ResponseEntity.ok(res);
	} // 아이디 중복검사
	
	@PostMapping("/signup")
	public String signupPost(Model model, SignupDTO user) {
		System.out.println("Signup: POST (Form)");
		boolean res = memberService.signup(user);
		model.addAttribute("msg", res ? "회원가입에 성공하셨습니다." : "회원가입에 실패하셨습니다.");
		model.addAttribute("url", "/");
		return "util/msg";
	} // 회원가입
	
	@GetMapping("/signin")
	public String signin() {
		System.out.println("Signin: GET");
		return "/member/signin";
	} // 로그인 페이지
}
