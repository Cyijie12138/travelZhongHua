package service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TravelsMapper;
import pojo.Travels;

@Service
public class TravelsService {
	@Autowired
	TravelsMapper travelsMapper;

	public List<Travels> selectAll(Travels travels) {
		// TODO Auto-generated method stub
		List<Travels> result = travelsMapper.selecAll(travels);
		return result;
	}

	public int addTravels(Travels travels) {
		// TODO Auto-generated method stub
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		travels.setTtime(timestamp);
		int result = travelsMapper.insert(travels);
		return result;
	}

	public List<Travels> selectTravelsBySort(String nowDays) {
		// TODO Auto-generated method stub
		List<Travels> result = travelsMapper.selectTravelsBySort(nowDays);
		return result;
	}
}
