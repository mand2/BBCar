package com.ycar.driver.client.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ycar.driver.client.domain.EditDriverMyInfo;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

//		@GetMapping(value = "/{idx}")
//		public ResponseEntity<Map<String, Object>> getMyPage(@PathVariable("idx")int idx){
//			RestTemplate template = new RestTemplate();
//			Map<String, Object> map = template.getForObject("http://localhost:8080/driver/mypage/{idx}", Map.class);
////			Map<String, Object> map = template.postForObject("http://localhost:8080/driver/mypage", idx, Map.class);
//			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//
////			template.exe
////			return template.exchange("http://localhost:8080/driver/mypage/{idx}", HttpMethod.GET, new HttpEntity<>(idx), Map<String,Object>.class, idx);
//		}
		@GetMapping(value = "/{idx}")
		@ResponseBody
		public ResponseEntity<String> getMyPage(@PathVariable("idx")int idx){
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			System.out.println("GET client:: idx " + idx);
			System.out.println("GET client:: entity " + entity);
			
			
			RestTemplate template = new RestTemplate();
			
			return
			template.exchange("http://localhost:8080/driver/mypage/"+idx, HttpMethod.GET, entity, String.class, idx);
		}
		
		@PutMapping(value = "/{idx}")
		public ResponseEntity<String> editMyInfo(@PathVariable("idx")int idx, @RequestBody EditDriverMyInfo driver){
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<EditDriverMyInfo> entity = new HttpEntity<EditDriverMyInfo>(driver, headers);
			
			System.out.println("PUT client:: idx " + idx);
			System.out.println("PUT client:: entity " + entity);
			
			RestTemplate template = new RestTemplate();
			
			return template.exchange("http://localhost:8080/driver/mypage/"+idx, HttpMethod.PUT, entity, String.class, idx);
		}
		
}
