package kr.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.spring.boot.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/community/list")
	public String CommunityList(Model model) {
		System.out.println("/community/list: GET");
		
		return "community/list";
	}
}
