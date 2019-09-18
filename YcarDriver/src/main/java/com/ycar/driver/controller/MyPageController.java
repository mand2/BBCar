package com.ycar.driver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Map<String, Object>> getMyPage(@PathVariable("idx")int idx){
		Map<String, Object> map = new HashMap<String, Object>();
		map = mypageService.showMyPage(idx);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	//수정 (운전옵션 혹은 개인이메일)
	@CrossOrigin
	@PutMapping(value = "/{idx}")
	public ResponseEntity<String> editMyInfo(@PathVariable("idx")int idx, @RequestBody EditDriverMyInfo driver){
		return new ResponseEntity<String>(mypageService.editMyInfo(idx, driver)>0?"success":"fail", HttpStatus.OK);
	}
	
	/*-----------------------------------*/
//			  아래는 안 쓸 수도...	
	/*-----------------------------------*/
	
	//DRIVER 테이블만
	@CrossOrigin
	@GetMapping(value = "info/{idx}")
	public ResponseEntity<Driver> getMyInfo(@PathVariable("idx")int idx){
		Driver driver = mypageService.showMyInfo(idx);
		return new ResponseEntity<Driver>(driver, HttpStatus.OK);
	}
	
	//D_ROUTE 테이블만
//	@CrossOrigin
//	@GetMapping(value = "route/{idx}")
//	public ResponseEntity<Route> getMyRoute(@PathVariable("idx") int idx){
//		Route route = mypageService.showMyRoute(idx);
//		return new ResponseEntity<Route>(route, HttpStatus.OK);
//	}
	
	
}
