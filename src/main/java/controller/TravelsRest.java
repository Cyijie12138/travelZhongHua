package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.RequestResult;
import pojo.RequestResultSim;
import pojo.Travels;
import service.TravelsService;

@Controller
public class TravelsRest {

	@Autowired
	TravelsService travelsService;
	@Autowired
	RequestResult<Travels> result;
	@Autowired
	RequestResultSim<Travels> result_sim;
	
	@RequestMapping(path = "/rest/travels/selectAll", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<Travels> selectAll(@RequestBody Travels travels){
		System.out.println("调用了查询游记接口");
		List<Travels> travels_list = travelsService.selectAll(travels);
		result.setCode(200);
		result.setMessage("成功获取游记");
		result.setSuccess(true);
		result.setData(travels_list);
		System.out.println(result);
		return result;
	}
	
	
	@RequestMapping(path = "/rest/travels/addTravels", method=RequestMethod.POST)
	@ResponseBody
	public RequestResultSim<Travels> addTravels(@RequestBody Travels travels){
		System.out.println("进入到添加游记接口");
		int result = travelsService.addTravels(travels);
		if(result>=1) {
			System.out.println("添加成功");
			result_sim.setCode(200);
			result_sim.setMessage("游记发布成功");
			result_sim.setSuccess(true);
			result_sim.setData(travels);
			return result_sim;
		}else {
			System.out.println("添加失败");
			result_sim.setCode(201);
			result_sim.setMessage("游记发布失败");
			result_sim.setSuccess(false);
			result_sim.setData(null);
			return result_sim;
		}
		
		
	}
	
	//获取今日优质游记
	@RequestMapping(path = "/rest/travels/selectTravelsBySort", method=RequestMethod.GET)
	@ResponseBody
	public RequestResult<Travels> selectTravelsBySort(){
		System.out.println("调用了获取今日优质游记接口");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.now();
		String nowDays = date.format(formatter);
		List<Travels> travels_list = travelsService.selectTravelsBySort(nowDays);
		if(null==travels_list&&0==travels_list.size()) {
			result.setCode(201);
			result.setMessage("今日无游记");
			result.setSuccess(true);
			result.setData(null);
			System.out.println(result);
			return result;
		}else {
			result.setCode(200);
			result.setMessage("成功获取今日优质游记");
			result.setSuccess(true);
			result.setData(travels_list);
			System.out.println(result);
			return result;
		}
		
	}
}
