package com.ycar.driver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.driver.model.Driver;
import com.ycar.driver.model.EditDriverMyInfo;
import com.ycar.driver.model.Route;
import com.ycar.driver.service.MyPageService;

@RestController
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private MyPageService mypageService;
	
	
	@CrossOrigin
	@GetMapping(value = "/{idx}")
	public Map<String, Object> getMyPage(@PathVariable("idx")int idx){
		
		System.out.println("서버:: idx " + idx);
		Map<String, Object> map = new HashMap<String, Object>();
		map = mypageService.showMyPage(idx);
		
		System.out.println("서버:: map " + map);
		
		return map;
	}
	
	
	//수정 (운전옵션 혹은 개인이메일)
	@CrossOrigin
	@PutMapping(value = "/{idx}")
	public int editMyInfo(@PathVariable("idx")int idx, @RequestBody EditDriverMyInfo driver){
		return mypageService.editMyInfo(idx, driver);
	}
	
	
	
}
