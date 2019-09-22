package com.ycar.driver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.driver.model.LoginDriverSearch;
import com.ycar.driver.service.LoginService;

/*-------------------
 * 파일이름: LoginController.java
 * 파일설명: SERVER side - 로그인 controller 
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
	
	@Autowired
	private LoginService loginService;
	
	//로그인
	@PostMapping
	@CrossOrigin
	public Map<String, Object> login(@RequestBody LoginDriverSearch search){
		String id = search.getId();
		String pw = search.getPw();
		
		Map<String, Object> maps = loginService.loginDriverSimple(id, pw);
		
		System.out.println("SERVER 확인::: ID " + id + " PW " + pw + " MAPS " + maps);
		
		return maps;
	}
	
	//아이디찾기
	@CrossOrigin
	@PostMapping("/findID")
	public String findID(@RequestBody LoginDriverSearch search) {
		
		String name = search.getName();
		String email = search.getEmail();
		
		return loginService.findID(name, email);
	}
	
	
	//패스워드 찾기
	@CrossOrigin
	@PostMapping("/findPW")
	public String findPW(@RequestBody LoginDriverSearch search) {
		
		String id = search.getId();
		String email = search.getEmail();
		
		return loginService.findPW(id, email);
	}
	
}
