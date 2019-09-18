package com.ycar.driver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.driver.dao.DriverDao;
import com.ycar.driver.model.Driver;
import com.ycar.driver.model.DriverInfo;
import com.ycar.driver.model.EditDriverMyInfo;
import com.ycar.driver.model.Route;

@Service(value = "mypageService")
public class MyPageService {
	
	@Autowired
	private SqlSessionTemplate template;
	private DriverDao dao;
	

	//mypage에 개인정보 및 즐찾경로 모두 불러오기
	public Map<String, Object> showMyPage(int idx){
		dao = template.getMapper(DriverDao.class);
		Driver driver = dao.selectByIdx(idx);
		List<Route> myrouteList = dao.selectRoute(idx);

		System.out.println("------ mypage service-----------");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myinfo", driver);
		map.put("myroute", myrouteList);
		
		System.out.println("route 들어가는가?"+map.get("myroute"));
		
		return map;
	}
	
	//수정 (운전옵션 혹은 개인이메일, 본인 차 관련)
	public int editMyInfo(int idx, EditDriverMyInfo driver) {
		int result = 0;
		
		dao = template.getMapper(DriverDao.class);
		Map<String, Object> params = new HashMap<String, Object>();
			params.put("d_idx", idx);
			params.put("email", driver.getEmail());
			params.put("d_option", driver.getD_option());
			
			params.put("cartype", driver.getCartype());
			params.put("carnum", driver.getCarnum());
		
		result = dao.updateMyInfo(params);
		
		return result;
	}
	
	/*-----------------------------------*/
//			  아래는 안 쓸 수도...	
	/*-----------------------------------*/
	
	//mypage에 내 정보 불러오기
	public Driver showMyInfo(int idx) {
		dao = template.getMapper(DriverDao.class);
		Driver driver = dao.selectByIdx(idx);
		return driver;
	}
	
	
	//mypage에 내 즐겨찾는경로 불러오기
	public List<Route> showMyRoute(int idx) {
		dao = template.getMapper(DriverDao.class);
//		Route route = dao.selectRoute(idx);
		return dao.selectRoute(idx);
	}
	

}
