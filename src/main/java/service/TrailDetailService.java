package service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TrailDetailMapper;
import pojo.TrailDetail;

@Service
public class TrailDetailService {
	@Autowired
	TrailDetailMapper trailDetailMapper;

	public List<TrailDetail> selectAll(TrailDetail trailDetail) {
		// TODO Auto-generated method stub
		List<TrailDetail> result = trailDetailMapper.selectAll(trailDetail);
		return result;
	}

	public int addTrailDetail(TrailDetail trailDetail) {
		// TODO Auto-generated method stub
		if(null==trailDetail.getTrTime()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			trailDetail.setTrTime(timestamp);
		}
		int result = trailDetailMapper.insert(trailDetail);
		return result;
	}
}
