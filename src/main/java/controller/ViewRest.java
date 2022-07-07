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
import pojo.View;
import service.ViewService;

@Controller
public class ViewRest {

	@Autowired
	ViewService viewService;
	@Autowired
	RequestResult<View> result;
	@Autowired
	RequestResultSim<View> result_sim;
	
	@RequestMapping(path = "/rest/view/selectAll", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<View> selectAll(@RequestBody View  view) {
		System.out.println("调用了查询景点接口");
		List<View> view_list = viewService.selectAll(view);
		result.setCode(200);
		result.setMessage("成功获取景点信息");
		result.setSuccess(true);
		result.setData(view_list);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(path = "/rest/view/selectNearest", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<View> selectNearest(@RequestBody View  view){
		System.out.println("调用了查询当前最近景点接口");
		List<View> view_list = viewService.selectNearest(view);
		result.setCode(200);
		result.setMessage("成功获取最近景点信息");
		result.setSuccess(true);
		result.setData(view_list);
		System.out.println(result);
		return result;
		
	}
	
	@RequestMapping(path = "/rest/view/addView", method=RequestMethod.POST)
	@ResponseBody
	public RequestResultSim<View> addView(@RequestBody View  view){
		System.out.println("调用了添加景点接口");
		int result = viewService.addView(view);
		if(result>=1) {
			result_sim.setCode(200);
			result_sim.setMessage("添加成功");
			result_sim.setSuccess(true);
			result_sim.setData(view);
			System.out.println(result_sim);
			return result_sim;
		}else {
			result_sim.setCode(201);
			result_sim.setMessage("添加失败");
			result_sim.setSuccess(false);
			result_sim.setData(null);
			return result_sim;
		}
		
		
	}
}
