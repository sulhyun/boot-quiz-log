package kr.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.boot.model.dto.TestDTO;
import kr.spring.boot.service.TestService;

@RestController
public class TestController {
	
	// 요청을 주는 서버
	
	@Autowired
	TestService testService;
	
	@GetMapping("/api/civar")
	public TestDTO test() {
		return testService.getData();
	}
}
