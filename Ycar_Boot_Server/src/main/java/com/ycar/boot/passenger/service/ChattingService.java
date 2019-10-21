package com.ycar.boot.passenger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.boot.passenger.domain.DChattingDomain;
import com.ycar.boot.passenger.domain.PChattingDomain;
import com.ycar.boot.passenger.entity.DCarpoolEntity;
import com.ycar.boot.passenger.entity.DriverEntity;
import com.ycar.boot.passenger.entity.PassengerEntity;
import com.ycar.boot.passenger.entity.RsvEntity;
import com.ycar.boot.passenger.repository.CarPoolRepository;
import com.ycar.boot.passenger.repository.DriverRepository;
import com.ycar.boot.passenger.repository.PassengerRepository;
import com.ycar.boot.passenger.repository.RsvRepository;


@Service("chattingService")
public class ChattingService {

	@Autowired
	private RsvRepository rsvRepo;
	@Autowired
	private DriverRepository dRepo;
	@Autowired
	private CarPoolRepository dCPRepo;
	@Autowired
	private PassengerRepository pRepo;

	public List<PChattingDomain> PrsvList(int p_idx) {

		List<RsvEntity> list = rsvRepo.findByPIdx(p_idx);
		List<PChattingDomain> cdList = new ArrayList<PChattingDomain>();

		for (int i = 0; i < list.size(); i++) {

			RsvEntity rsv = list.get(i);
			DCarpoolEntity dcp = list.get(i).getDcp();

			// 운전자 nickname 가져오기
			String dNick = getDriverNick(dcp.getdIdx());

			// 카풀 정보 넣기
			cdList.add(new PChattingDomain(rsv.getDr_idx(), rsv.getR_confirm(), dcp.getdIdx(), dcp.getD_date(),
					dcp.getD_starttime(), dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(),
					dcp.getD_commute(), dcp.getD_fee(), dcp.getD_distance(), dNick));

		}

		return cdList;
	}

	public List<DChattingDomain> DrsvList(int d_idx) {

		List<DCarpoolEntity> list = dCPRepo.findByDIdx(d_idx);
		List<DChattingDomain> cdList = new ArrayList<DChattingDomain>();

		for (int i = 0; i < list.size(); i++) {
			
			DCarpoolEntity dcp = list.get(i);
			
			if (dcp.getRsvlist() != null) {
				// 예약 신청이 있는 카풀

				for (int j = 0; j < dcp.getRsvlist().size(); j++) {
					
					RsvEntity rsv = dcp.getRsvlist().get(j);
					
					// 탑승자 nickname 가져오기
					String pNick = getPassengerNick(rsv.getpIdx());
					
					// 카풀 정보 넣기
					cdList.add(new DChattingDomain(dcp.getDr_idx(), rsv.getR_confirm(), rsv.getpIdx(), rsv.getrDate(),
							dcp.getD_starttime(), dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(),
							dcp.getD_commute(), dcp.getD_fee(), dcp.getD_distance(), pNick));
				}

			}
		}

		return cdList;
	}

	private String getDriverNick(int d_idx) {

		DriverEntity entity = dRepo.getOne(d_idx);
		String dNick = entity.getNickname();

		return dNick;
	}
	
	private String getPassengerNick(int p_idx) {

		PassengerEntity entity = pRepo.getOne(p_idx);
		String pNick = entity.getNickname();

		return pNick;
	}

}
