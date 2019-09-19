package com.ycar.driver.client.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ycar.driver.client.domain.DriverInfo;

@RestController
@RequestMapping("/join")
public class JoinController {

	
	@PostMapping
	public ResponseEntity<String> join(@RequestBody DriverInfo driverInfo){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<DriverInfo> entity = new HttpEntity<DriverInfo>(driverInfo, headers);
		
		RestTemplate template = new RestTemplate();
//		int result = template.postForObject("http://localhost:8080/driver/join",	driverInfo, Integer.class);
//		System.out.println("잘들어갔니 4 ====" + result);
//		return new ResponseEntity<String>(result>0 ? "success":"fail",HttpStatus.OK);
		return template.exchange("http://localhost:8080/driver/join", HttpMethod.POST, entity, String.class);
	}
}
