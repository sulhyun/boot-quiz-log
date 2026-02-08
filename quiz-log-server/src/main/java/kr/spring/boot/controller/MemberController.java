package kr.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.boot.model.vo.MemberVO;
import kr.spring.boot.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/signup")
	public String signup() {
		System.out.println("화원가입 페이지 진입");
		return "member/signup";
	} // 회원 가입 페이지
	
	@ResponseBody
	@GetMapping("/checkId")
	public ResponseEntity<Boolean> checkId(@RequestParam String mb_id) {
		MemberVO user = memberService.getMember(mb_id);
		boolean res = (user == null) ? true : false;
		return ResponseEntity.ok(res);
	} // 아이디 중복검사
}
