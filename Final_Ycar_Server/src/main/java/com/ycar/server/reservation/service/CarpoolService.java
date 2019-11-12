package com.ycar.server.reservation.service;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.ycar.server.reservation.dao.ReservationDao;
import com.ycar.server.reservation.domain.Carpool;

@Service("carpoolService")
public class CarpoolService {
	
	private ReservationDao dao;
	
	@Inject
	private SqlSessionTemplate template;
	
	//전체카풀 출력하기~ 
	public List<Carpool> getAllList(){
		
		dao = template.getMapper(ReservationDao.class);
		
		List<Carpool> list = dao.allCarpoolList();
		
		return list;
	}
	
//	//검색
	public List<Carpool> getSearchList(String date, String time, String startlat, String startlon, String endlat, String endlon) {
		
		dao =template.getMapper(ReservationDao.class);
		
		List<Carpool> list = dao.searchCarpoolList(date, time, startlat, startlon, endlat, endlon);
		
		System.out.println("서비스"+ date + time + "시작위도" + startlat + "시작경도" + startlon + "종료위도" + endlat + "종료경도" + endlon);
		
		return list;
	}

	
	//rsv할 카풀 선택! 
	public Carpool selectCarpool(int dr_idx) {
		
		dao = template.getMapper(ReservationDao.class);
		
		Carpool carpool = dao.selectByDr_idx(dr_idx);
		
		return carpool;
	}


}