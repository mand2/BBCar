package com.ycar.reservation.dao;

import java.util.List;
import java.util.Map;

import com.ycar.reservation.domain.Carpool;
import com.ycar.reservation.domain.MyCarpool;
import com.ycar.reservation.domain.Reservation;
import com.ycar.reservation.domain.SearchCarpool;

public interface ReservationDao {

	// 전체 카풀 리스트 출력
	public List<Carpool> allCarpoolList();

	//카풀 검색
	public List<Carpool> searchCarpoolList(SearchCarpool search);
	
	//예약할 카풀 선택
	public Carpool selectByDr_idx(int dr_idx);

	//예약등록
	public int reserve(Reservation rsv);
	
	//회원별 예약 리스트 출력 <- 여기서 또 쪼개서 나누기 예약된것 안된것 과거미래 등등
	public List<Reservation> selectByP_idx(int p_idx);
	
	//확정된 예약만 p_idx 별로 보여주기
	public List<MyCarpool> confirmListByP_idx(int p_idx);
	
	//대기예약만~~ 
	public List<MyCarpool> waitingListByP_idx(int p_idx);
	
	//거절예약만 
//	public List<MyCarpool> refusedListByP_idx(int p_idx);
	
	//예약취소
	public int delete(int r_idx);
}
