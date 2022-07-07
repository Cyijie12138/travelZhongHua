package service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TrailMapper;
import pojo.Trail;

@Service
public class TrailService {
	@Autowired
	TrailMapper trailMapper;

	public List<Trail> selectAll(Trail trail) {
		// TODO Auto-generated method stub
		List<Trail> trail_list = trailMapper.selectAll(trail);
		return trail_list;
	}

	public int addTrail(Trail trail) {
		// TODO Auto-generated method stub
		if(null==trail.getBegintime()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			trail.setBegintime(timestamp);
		}
		int result = trailMapper.insert(trail); 
		if(result==0) {
			return result;
		}else {
			List<Trail> trail_list = trailMapper.selectAll(trail);
			return trail_list.get(0).getId();
		}
		
	}

	public Trail selectById(int result) {
		// TODO Auto-generated method stub
		Trail result1 = trailMapper.selectByPrimaryKey(result);
		return result1;
	}

	public int updateTrail(Trail trail) {
		// TODO Auto-generated method stub
		if(null==trail.getEndtime()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			trail.setEndtime(timestamp);
		}
		int result = trailMapper.updateByPrimaryKeySelective(trail);
		return result;
	}

	public List<Map> getDetailByUid(Integer uid) {
		// TODO Auto-generated method stub
		List<Map> result = trailMapper.getDetailByUid(uid);
		return result;
	}
}
