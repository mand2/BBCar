package com.ycar.passenger.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ycar.passenger.entity.MemoEntity;

public interface MemoRepository extends JpaRepository<MemoEntity, Integer> {
	
	// 카풀 선택하여 메모 작성 : pIdx = 회원번호 / cIdx = 카풀등록번호
//	@Query("insert into MemoEntity (p_idx, dr_idx, context) values (:pIdx, :dIdx, :memo)")
//	public int writeMemo(@Param("pIdx")long pIdx, @Param("dIdx")long dIdx, @Param("memo")String memo);
//		 SpEL문
//		 @Query("insert into MemoEntity values p_idx = :#{#memo.pIdx}, dr_idx = :#{#memo.dIdx}, context = :#{#memo.context}")
//		 public int writeMemo(@Param("memo") Memo memo);
	
	// 작성한 메모에 대한 카풀이 예약 되었는지 확인 : Y 예약됨 / B 예약 대기
	
	// 메모 수정
	// UPDATE `ycar`.`P_MEMO` SET `context` = 'hihi' WHERE m_idx = ?;
	@Modifying
	@Transactional
	@Query(value = "update MemoEntity set context = ?2 where m_idx = ?1")
	//public int updateContext(@Param("m_idx")int m_idx, @Param("context") String context);
	public int updateContext(int m_idx, String context);
		
	// 메모 불러오기
	//SELECT * FROM ycar.P_MEMO where p_idx = 10 and dr_idx = 26;
	@Query(value = "select m from MemoEntity m where p_idx = ?1 and dr_idx =?2")
	public List<MemoEntity> findByDrIdxPIdx(int p_idx, int dr_idx);
}
