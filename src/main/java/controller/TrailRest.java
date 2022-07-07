package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.RequestResult;
import pojo.RequestResultSim;
import pojo.Trail;
import pojo.Travels;
import service.TrailService;

@Controller
public class TrailRest {
	@Autowired
	TrailService trailService;
	@Autowired
	RequestResult<Trail> result;
	@Autowired
	RequestResultSim<Trail> result_sim;
	
	@RequestMapping(path = "/rest/trail/selectAll", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<Trail> selectAll(@RequestBody Trail trail){
		System.out.println("调用了根据用户id查询轨迹接口");
		List<Trail> trail_list = trailService.selectAll(trail);
		result.setCode(200);
		result.setMessage("成功获取用户轨迹");
		result.setSuccess(true);
		result.setData(trail_list);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(path = "/rest/trail/addTrail", method=RequestMethod.POST)
	@ResponseBody
	public RequestResultSim<Trail> addTrail(@RequestBody Trail trail){
		System.out.println("进入到添加轨迹接口");
		int result = trailService.addTrail(trail);
		if(result>=1) {
			Trail trail1 = trailService.selectById(result);
			System.out.println("添加成功");
			result_sim.setCode(200);
			result_sim.setMessage("轨迹添加成功");
			result_sim.setSuccess(true);
			result_sim.setData(trail1);
			return result_sim;
		}else {
			System.out.println("添加失败");
			result_sim.setCode(201);
			result_sim.setMessage("轨迹添加失败");
			result_sim.setSuccess(false);
			result_sim.setData(null);
			return result_sim;
		}
		
		
	}
	
	@RequestMapping(path = "/rest/trail/updateTrail", method=RequestMethod.POST)
	@ResponseBody
	public RequestResultSim<Trail> updateTrail(@RequestBody Trail trail){
		System.out.println("进入到修改轨迹接口");
		int result = trailService.updateTrail(trail);
		if(result>=1) {
			Trail trail1 = trailService.selectById(trail.getId());
			System.out.println("修改成功");
			result_sim.setCode(200);
			result_sim.setMessage("轨迹修改成功");
			result_sim.setSuccess(true);
			result_sim.setData(trail1);
			return result_sim;
		}else {
			System.out.println("修改失败");
			result_sim.setCode(201);
			result_sim.setMessage("轨迹修改失败");
			result_sim.setSuccess(false);
			result_sim.setData(null);
			return result_sim;
		}
		
		
	}
	
	@RequestMapping(path = "/rest/trail/getDetailByUid", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<Map> getDetailByUid(@RequestParam Integer uid){
		RequestResult<Map> result = new RequestResult<Map>();
		System.out.println("进入到获取轨迹统计数据接口");
		List<Map> trail_detail_list = trailService.getDetailByUid(uid);
		System.out.println(trail_detail_list);
		result.setCode(200);
		result.setMessage("成功获取统计信息");
		result.setSuccess(true);
		result.setMapData(trail_detail_list);
		System.out.println(result);
		return result;
		
		
	}
}
