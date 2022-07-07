package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.RequestResult;
import pojo.RequestResultSim;
import pojo.Trail;
import pojo.TrailDetail;
import service.TrailDetailService;

@Controller
public class TrailDetailRest {
	@Autowired
	TrailDetailService trailDetailService;
	@Autowired
	RequestResult<TrailDetail> result;
	@Autowired
	RequestResultSim<TrailDetail> result_sim;
	
	@RequestMapping(path = "/rest/trailDetail/selectAll", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<TrailDetail> selectAll(@RequestBody TrailDetail trailDetail){
		System.out.println("调用了根据轨迹ID查询详细轨迹信息接口");
		List<TrailDetail> trailDetail_list = trailDetailService.selectAll(trailDetail);
		result.setCode(200);
		result.setMessage("成功获取用户轨迹详细信息");
		result.setSuccess(true);
		result.setData(trailDetail_list);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(path = "/rest/trailDetail/addTrailDetail", method=RequestMethod.POST)
	@ResponseBody
	public RequestResultSim<TrailDetail> addTrailDetail(@RequestBody TrailDetail trailDetail){
		System.out.println("进入到添加轨迹详情接口");
		int result = trailDetailService.addTrailDetail(trailDetail);
		if(result>=1) {
			System.out.println("添加成功");
			result_sim.setCode(200);
			result_sim.setMessage("轨迹详情添加成功");
			result_sim.setSuccess(true);
			result_sim.setData(trailDetail);
			return result_sim;
		}else {
			System.out.println("添加失败");
			result_sim.setCode(201);
			result_sim.setMessage("轨迹详情添加失败");
			result_sim.setSuccess(false);
			result_sim.setData(null);
			return result_sim;
		}
		
		
	}
}
