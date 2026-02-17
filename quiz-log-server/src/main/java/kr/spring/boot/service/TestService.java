package kr.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kr.spring.boot.model.dto.TestDTO;

@Service
public class TestService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public TestDTO getData() {
		String url = "http://localhost:8081/api/test";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("HEADER : " + headers);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		System.out.println("ENTITY : " + entity);
		
		ResponseEntity<TestDTO> res = restTemplate.exchange(url, HttpMethod.GET, entity, TestDTO.class);
		System.out.println("RESPONSE : " + res);
		System.out.println("REPONSE.GETBODY : " + res.getBody());
		
		return res.getBody();
	}
}
