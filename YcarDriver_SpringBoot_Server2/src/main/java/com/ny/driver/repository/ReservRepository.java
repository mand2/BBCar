package com.ny.driver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ny.driver.entity.ReservationEntity;


public interface ReservRepository extends JpaRepository<ReservationEntity, Long> {
	
	@Query(value = "SELECT "
			+ "c.dr_idx"
			+ " , r.r_idx"
			+ " , r.p_idx"
			+ " , c.d_date"
			+ " , c.d_startpoint"
			+ " , c.d_endpoint"
			+ " , r.r_confirm"
			+ " , d.d_idx"
			+ " from RESERVATION r" 
			+ " left join D_CARPOOL c"
			+ " on c.dr_idx = r.dr_idx"
			+ " inner join DRIVER d"
			+ " on d.d_idx = c.d_idx"
			+ " where r.r_confirm is not null" 
			+ " and d.d_idx = ?1"
			+ " order by r.dr_idx , r.r_idx"
			,nativeQuery = true)
	public List<ReservationEntity> getlists(String d_idx);
	
	
}
