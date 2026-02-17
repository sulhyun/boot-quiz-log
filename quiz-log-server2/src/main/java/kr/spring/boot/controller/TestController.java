package kr.spring.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	// 요청을 받는 서버
	
	@GetMapping("/api/test")
	public Map<String, Object> getTest() {
		Map<String, Object> data = new HashMap<>();
		data.put("status", "sunny");
		data.put("tmp", 25);
		System.out.println("DATA : " + data);
		
		return data;
	}
}
