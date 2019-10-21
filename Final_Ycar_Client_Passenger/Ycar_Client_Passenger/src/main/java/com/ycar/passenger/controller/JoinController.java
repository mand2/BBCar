package com.ycar.passenger.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ycar.passenger.domain.JoinInfo;
import com.ycar.passenger.domain.LoginInfo;

@Controller
@RequestMapping("/join")
public class JoinController {

	// 회원가입 페이지 로딩
	@GetMapping
	public String page() {
		return "join";
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<String> join(@RequestBody JoinInfo joinInfo) {
		System.out.println("client controller --- JoinInfo ::: " + joinInfo);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<JoinInfo> entity = new HttpEntity<JoinInfo>(joinInfo, headers);

		RestTemplate template = new RestTemplate();
		return template.exchange("http://13.125.252.85:8080/server/members/join", HttpMethod.POST, entity, String.class);
	}

}
