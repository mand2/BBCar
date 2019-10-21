package com.ycar.boot.passenger.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.boot.passenger.dao.PassengerDaoImpl;
import com.ycar.boot.passenger.domain.DChattingDomain;
import com.ycar.boot.passenger.domain.MemoDomain;
import com.ycar.boot.passenger.domain.PChattingDomain;
import com.ycar.boot.passenger.entity.MemoEntity;
import com.ycar.boot.passenger.entity.PRouteEntity;
import com.ycar.boot.passenger.entity.PassengerEntity;
import com.ycar.boot.passenger.repository.MemoRepository;
import com.ycar.boot.passenger.service.ChattingService;
import com.ycar.boot.passenger.service.MemoService;


@RestController
@Controller
@CrossOrigin
public class MypageController {

	// -- 채팅 기능 --

	@Autowired
	private ChattingService chattingService;

	// [채팅] 탑승자가 예약한 카풀 리스트 출력
	@RequestMapping("/PrsvList/{idx}")
	public List<PChattingDomain> PrsvList(@PathVariable("idx") int p_idx) {

		System.out.println("예약 리스트01");

		List<PChattingDomain> list = chattingService.PrsvList(p_idx);

		for (PChattingDomain chattingDomain : list) {
			System.out.println(chattingDomain);
		}

		return list;
	}

	// [채팅] 드라이버가 등록한 카풀을 예약한 리스트 출력
	@RequestMapping("/DrsvList/{idx}")
	public List<DChattingDomain> DrsvList(@PathVariable("idx") int d_idx) {

		System.out.println("예약 리스트02");

		List<DChattingDomain> list = chattingService.DrsvList(d_idx);

		return list;
	}

	// -- 탑승자 메모 기능 --
	
	@Autowired
	private MemoService memoService;
	
	// [메모] 등록된 카풀 리스트 출력 : 예약이 아직 되지 않은 카풀 등록 리스트
	// [메모] 메모 출력
	@RequestMapping("/cpList/{idx}")
	public List<MemoDomain> cpList(@PathVariable("idx") int idx) {

		System.out.println("탑승자 메모 01");
		
		// r_comfirm에 따라 카풀 예약 상태가 다름 : 
		// rsv_list가 없거나  r_confirm = null => '지금 예약이 가능합니다!'
		// Y => 예약 불가
		// B => 예약 임박
		List<MemoDomain> cpList = memoService.cpList(idx);

		return cpList;

	}

	@Autowired
	private MemoRepository mmRepo;

	// [메모] 메모 작성 : 카풀 선택 -> pIdx = 회원번호 / dr_idx = 카풀등록번호
	@PostMapping("/writeMemo/{pIdx}/{dr_idx}")
	public String writeMemo(@PathVariable("pIdx") int pIdx, @PathVariable("dr_idx") int dr_idx,
			@RequestParam("memo") String memo) {

		MemoEntity result = mmRepo.save(new MemoEntity(pIdx, dr_idx, memo));

		return result != null ? "success" : "fail";
	}
	
	// [메모] 메모 수정
	@PutMapping("/writeMemo/{idx}")
	public String editMemo(@PathVariable("idx") int idx, @RequestParam("context") String context) {
				
		int result = mmRepo.updateContext(idx, context);
				
		return result>0?"success":"fail";	
	}

	// [메모] 메모 삭제
	@DeleteMapping("/writeMemo/{idx}")
	public String delMemo(@PathVariable("idx") int idx) {
		
		mmRepo.deleteById(idx);
				
		return "삭제완료";	
	}

	// --- 탑승자 개인 정보 ---

	@PersistenceContext
	EntityManager entityManager;

	private PassengerDaoImpl dao;

	@RequestMapping("/findEnv/{idx}")
	public PassengerEntity findEnvByIdx(@PathVariable("idx") long idx) {

		this.dao = new PassengerDaoImpl(entityManager);

		PassengerEntity entity = dao.findEnvByIdx(idx);
		System.out.println(entity);

		return entity;
	}

	@RequestMapping("/findRoute/{idx}")
	public List<PRouteEntity> findRouteByIdx(@PathVariable("idx") int idx) {

		System.out.println(idx);

		this.dao = new PassengerDaoImpl(entityManager);

		List<PRouteEntity> entity = dao.findRouteByIdx(idx);
		System.out.println(entity);

		return entity;
	}

}
