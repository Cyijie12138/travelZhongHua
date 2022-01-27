package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ViewMapper;
import pojo.View;

@Service
public class ViewService {
	@Autowired
	ViewMapper viewMapper;

	public List<View> selectAll(View view) {
		// TODO Auto-generated method stub
		List<View> result = viewMapper.selectAll(view);
		return result;
	}

	public List<View> selectNearest(View view) {
		// TODO Auto-generated method stub
		List<View> result = viewMapper.selectNearest(view);
		return result;
	}

	public int addView(View view) {
		// TODO Auto-generated method stub
		int result = viewMapper.insert(view);
		return result;
	}
}
