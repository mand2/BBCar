package com.ycar.passenger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ycar.passenger.domain.ChangeInfo;

@CrossOrigin
@Controller
@RequestMapping("/mypage")
public class MypageController {

	// 마이페이지 로딩
	@GetMapping
	public String page() {
		return "mypage";
	}

	// 내 정보 업로드
	@PutMapping
	@ResponseBody
	public int changeInfo(@RequestBody ChangeInfo changeInfo) {

		RestTemplate rt = new RestTemplate();

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", changeInfo.getId());
		map.put("pw1", changeInfo.getPw1());
		map.put("pw2", changeInfo.getPw2());

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(map, headers);

		int result = rt.exchange("http://13.125.252.85:8080/server/members/mypage", HttpMethod.PUT, entity, Integer.class)
				.getBody();
		System.out.println(result);
		// [수정 추가예정 내용: email수정] result = 1일때, session에 업데이트된 정보로 다시 저장

		return result;
	}

	// 회원탈퇴 -> verify = D로 바꿈
	@RequestMapping(value = "/deleteMem/{idx}", method = RequestMethod.PUT)
	@ResponseBody
	public String deleteMem(@PathVariable("idx") String idx, HttpServletRequest request) {
		System.out.println("deletemem01 "+idx);
		RestTemplate rt = new RestTemplate();

		Map<String, String> map = new HashMap<String, String>();
		map.put("idx", idx);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(map, headers);

		int result = rt.exchange("http://13.125.252.85:8080/server/members/mypage/deleteMem", HttpMethod.PUT, entity,
				Integer.class).getBody();
		System.out.println("deletemem04 "+result);
		if(result>0) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			return "success";
		}

		return "fail";

	}
}
