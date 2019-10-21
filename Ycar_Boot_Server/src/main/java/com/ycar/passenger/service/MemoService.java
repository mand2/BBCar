package com.ycar.passenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.passenger.domain.MemoDomain;
import com.ycar.passenger.domain.PerMemoDomain;
import com.ycar.passenger.entity.DCarpoolEntity;
import com.ycar.passenger.entity.MemoEntity;
import com.ycar.passenger.entity.RsvEntity;
import com.ycar.passenger.repository.CarPoolRepository;
import com.ycar.passenger.repository.MemoRepository;

@Service("memoService")
public class MemoService {

	@Autowired
	private CarPoolRepository cpRepo;
	@Autowired
	private MemoRepository mmRepo;

	public List<MemoDomain> cpList(int idx) {

		List<DCarpoolEntity> list = cpRepo.findAll();
		List<MemoDomain> cpList = new ArrayList<MemoDomain>();

		for (int i = 0; i < list.size(); i++) {

			DCarpoolEntity dcp = list.get(i);

			// r_comfirm에 따라 카풀 예약 상태가 다름 :
			// rsv_list가 없거나 r_confirm = null => '지금 예약이 가능합니다!'
			// Y => 예약 불가
			// B => 예약 임박
			// 이에 따라 나눠서 표시해주기::

			// 우선순위 1. rsv_list가 없거나
			if (dcp.getRsvlist().size() < 1) {

				List<MemoEntity> memoList = mmRepo.findByDrIdxPIdx(idx, dcp.getDr_idx());

				// 1) 해당 카풀에 작성한 메모가 있을 때
				if (memoList.size() > 0) {

					ArrayList<PerMemoDomain> memo = new ArrayList<PerMemoDomain>();

					for (int k = 0; k < memoList.size(); k++) {
						PerMemoDomain pmd = new PerMemoDomain(memoList.get(k).getM_idx(), memoList.get(k).getContext());
						memo.add(pmd);
					}

					cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(),
							dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(),
							dcp.getD_fee(), dcp.getD_distance(), null, memo));
					
					continue;

				}

				// 2) 해당 카풀에 작성한 메모가 없을 때
				cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(), dcp.getD_endtime(),
						dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(), dcp.getD_fee(),
						dcp.getD_distance(), null));
				
				continue;

			} else {

				List<RsvEntity> rsvList = dcp.getRsvlist();
				ArrayList<String> r_con = new ArrayList<String>();

				for (int j = 0; j < rsvList.size(); j++) {

					r_con.add(rsvList.get(j).getR_confirm());

				}

				// 우선순위 2. Y => 예약 불가
				if (r_con.contains("Y")) {

					List<MemoEntity> memoList = mmRepo.findByDrIdxPIdx(idx, dcp.getDr_idx());

					// 1) 해당 카풀에 작성한 메모가 있을 때
					if (memoList.size() > 0) {

						ArrayList<PerMemoDomain> memo = new ArrayList<PerMemoDomain>();

						for (int k = 0; k < memoList.size(); k++) {
							PerMemoDomain pmd = new PerMemoDomain(memoList.get(k).getM_idx(),
									memoList.get(k).getContext());
							memo.add(pmd);
						}

						cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(),
								dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(),
								dcp.getD_fee(), dcp.getD_distance(), null, memo));
						
						continue;

					}

					// 2) 해당 카풀에 작성한 메모가 없을 때
					cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(),
							dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(),
							dcp.getD_fee(), dcp.getD_distance(), "Y"));
					
					continue;

				}

				// 우선순위 3. B => 예약 임박
				else if (r_con.contains("B")) {

					List<MemoEntity> memoList = mmRepo.findByDrIdxPIdx(idx, dcp.getDr_idx());

					// 1) 해당 카풀에 작성한 메모가 있을 때
					if (memoList.size() > 0) {

						ArrayList<PerMemoDomain> memo = new ArrayList<PerMemoDomain>();

						for (int k = 0; k < memoList.size(); k++) {
							PerMemoDomain pmd = new PerMemoDomain(memoList.get(k).getM_idx(),
									memoList.get(k).getContext());
							memo.add(pmd);
						}

						cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(),
								dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(),
								dcp.getD_fee(), dcp.getD_distance(), null, memo));
						
						continue;
					}

					// 2) 해당 카풀에 작성한 메모가 없을 때
					cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(),
							dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(),
							dcp.getD_fee(), dcp.getD_distance(), "B"));
					
					continue;
				}

				// 우선순위 4. r_confirm = null
				else if (r_con.contains(null)) {

					List<MemoEntity> memoList = mmRepo.findByDrIdxPIdx(idx, dcp.getDr_idx());

					// 1) 해당 카풀에 작성한 메모가 있을 때
					if (memoList.size() > 0) {

						ArrayList<PerMemoDomain> memo = new ArrayList<PerMemoDomain>();

						for (int k = 0; k < memoList.size(); k++) {
							PerMemoDomain pmd = new PerMemoDomain(memoList.get(k).getM_idx(),
									memoList.get(k).getContext());
							memo.add(pmd);
						}

						cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(),
								dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(),
								dcp.getD_fee(), dcp.getD_distance(), null, memo));
						
						continue;
					}

					// 2) 해당 카풀에 작성한 메모가 없을 때
					cpList.add(new MemoDomain(dcp.getDr_idx(), dcp.getD_date(), dcp.getD_starttime(),
							dcp.getD_endtime(), dcp.getD_startpoint(), dcp.getD_endpoint(), dcp.getD_commute(),
							dcp.getD_fee(), dcp.getD_distance(), null));
					
					continue;
				}

			}
		}

		return cpList;

	}
}