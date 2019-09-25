package com.ycar.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.reservation.domain.Carpool;
import com.ycar.reservation.domain.MyCarpool;
import com.ycar.reservation.domain.Reservation;
import com.ycar.reservation.service.CarpoolService;
import com.ycar.reservation.service.MyCarpoolListService;
import com.ycar.reservation.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private CarpoolService carpoolService;
	
	@Autowired
	private ReservationService rsvService;
	
	@Autowired
	private MyCarpoolListService myCarpoolService;
	
	//전체 카풀 리스트
	@CrossOrigin
	@GetMapping("/carpool")
	public List<Carpool> getAllList(){
		
		List<Carpool> list = carpoolService.getAllList();
		
		return list;
	}
	
	//카풀 검색 리스트 - 부트스트랩이랑 충돌남 클래스 아이디 값 다 빼고 기본 폼으로 다시 만들기
	
	

	//예약할 카풀 선택해서 정보 부르자 
	@CrossOrigin
	@GetMapping("/carpool/{dr_idx}")
	public Carpool selectCarpool(@PathVariable("dr_idx") int idx) {
		
		return carpoolService.selectCarpool(idx);
	}
	
	//불러와서 rsv테이블에 insert
	@CrossOrigin
	@PostMapping("/reserve/{p_idx}")
	public String insert(Reservation rsv, @PathVariable("p_idx") int idx) {
		
		int result = rsvService.reserve(rsv);
		
		return result > 0 ? "success" : "fail";		
	}
	
	//회원별 예약 리스트 대기예약과 확정/거절예약으로 리스트 분리
	@CrossOrigin
	@GetMapping("/reserve/{p_idx}")
	public List<Reservation> selectByPidx(@PathVariable("p_idx") int idx) {

		List<Reservation> list = rsvService.getRsvList(idx);

		return list;
	}
	
	//나의카풀 페이지에 출력될 예약 확정 리스트
	@CrossOrigin
	@GetMapping("/mycarpool/{p_idx}")
	public List<MyCarpool> getConfirmList(@PathVariable("p_idx") int idx){
		
		List<MyCarpool> list = myCarpoolService.getConfirmList(idx);
		
		return list;
	}
	
	// 나의카풀 페이지에 출력될 예약 대기 리스트
	@CrossOrigin
	@GetMapping("/waitcarpool/{p_idx}")
	public List<MyCarpool> getWaitingList(@PathVariable("p_idx") int idx) {

		List<MyCarpool> list = myCarpoolService.getWaitingList(idx);

		return list;
	}
	
	//예약삭제
	@CrossOrigin
	@DeleteMapping("/mycarpool/{p_idx}/{r_idx}")
	public String delete(@PathVariable("p_idx") int p_idx, @PathVariable("r_idx") int r_idx) {
		
		int result = rsvService.delete(p_idx, r_idx);
		
		return result > 0 ?"success":"fail";
	}
}
