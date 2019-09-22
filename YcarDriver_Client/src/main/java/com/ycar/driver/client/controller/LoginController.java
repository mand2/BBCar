package com.ycar.driver.client.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ycar.driver.client.domain.DriverInfo;
import com.ycar.driver.client.domain.LoginDriverInfo;
import com.ycar.driver.client.domain.LoginDriverSearch;
import com.ycar.driver.client.domain.Route;

/*-------------------
 * 파일이름: LoginController.java
 * 파일설명: CLIENT side - 로그인 controller 
 * 작성자: 김나연
 * 버전: 1.0.1
 * 생성일자: 2019-09-20 오전 10시 09분
 * 최종수정일자: 2019-09-20 오전 10시 09분
 * 최종수정자: 김나연
 * 최종수정내용: 최초 작성
 * 
 * @login : 로그인(간편가입자 로그인 처리)
 * -------------------*/

@RestController
@RequestMapping("/login")
public class LoginController {
	
	//로그인
	@PostMapping
	@ResponseBody
	public int login(@RequestBody LoginDriverSearch search, HttpServletRequest request){
		RestTemplate template = new RestTemplate();
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps = template.postForObject("http://localhost:8080/driver/login", search, Map.class);
		
		int msg = (int) maps.get("msg");
		
		//pw 맞은 애들 세션처리
		if(msg == 4 || msg ==3) {
			System.out.println("세션처리시작");
			
			HttpSession session = request.getSession(false); 
			LinkedHashMap<String, Object> loginMap = (LinkedHashMap<String, Object>) maps.get("loginInfo");

			LoginDriverInfo loginInfo = new LoginDriverInfo(
										(int)loginMap.get("d_idx"),
										(String) loginMap.get("id"),
										(String) loginMap.get("name"),
										(String) loginMap.get("nickname")
									);
			
			session.setAttribute("loginInfo", loginInfo);
			System.out.println("세션처리완료?  - 기본정보 -  " + loginInfo);
			
		}
		
		return msg;
	}
	
	@PostMapping("/findID")
	public ResponseEntity<String> findID(@RequestBody LoginDriverSearch search){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<LoginDriverSearch> entity = new HttpEntity<LoginDriverSearch>(search, headers);
		
		System.out.println("client ::: entity  "+entity);
		
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> result= 
				template.exchange("http://localhost:8080/driver/login/findID", HttpMethod.POST, entity, String.class);
		
		
//		테스트용
//		System.out.println("client ::: result? " + result);
//		System.out.println("client ::: result body ? " + result.getBody());
//		System.out.println("client ::: result status? " + result.getStatusCode());
		
		
		return result;
	}
	
	@PostMapping("/findPW")
	public ResponseEntity<String> findPW(@RequestBody LoginDriverSearch search){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<LoginDriverSearch> entity = new HttpEntity<LoginDriverSearch>(search, headers);
		
		RestTemplate template = new RestTemplate();
		
		return template.exchange("http://localhost:8080/driver/login/findPW", HttpMethod.POST, entity, String.class);
	}
	
}
