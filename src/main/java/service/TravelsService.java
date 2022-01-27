package service;

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
		int result = travelsMapper.insert(travels);
		return result;
	}
}
