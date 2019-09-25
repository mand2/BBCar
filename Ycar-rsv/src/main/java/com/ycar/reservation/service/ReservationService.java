package com.ycar.reservation.service;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.ycar.reservation.dao.ReservationDao;
import com.ycar.reservation.domain.Reservation;

@Service("rsvService")
public class ReservationService {

	private ReservationDao dao;
	
	@Inject
	private SqlSessionTemplate template;
	
	public int reserve(Reservation rsv) {
		
		dao = template.getMapper(ReservationDao.class);
		
		int result = dao.reserve(rsv);
		
		return result;
	}
	
	public List<Reservation> getRsvList(int p_idx){
		
		dao = template.getMapper(ReservationDao.class);
		
		List<Reservation> list = dao.selectByP_idx(p_idx);
		
		return list;
	}
	
	public int delete(int p_idx, int r_idx) {
		
		dao = template.getMapper(ReservationDao.class);
		
		int result = 0;
		
//		탑승자 idx값 대조/ 비밀번호 확인?
//		if문으로 idx확인해야함
		
		return dao.delete(r_idx);
	}
}
