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

import pojo.Comment;
import pojo.RequestResult;
import pojo.RequestResultSim;
import service.CommentService;


@Controller
public class CommentRest {
	
	@Autowired
	CommentService commentService;
	@Autowired
	RequestResult<Comment> result;
	@Autowired
	RequestResultSim<Comment> result_sim;
	
	@RequestMapping(path = "/rest/comment/selectAll", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<Comment> selectAll(@RequestBody Comment comment){
		System.out.println("调用了获取评论接口");
		List<Comment> comment_list = commentService.selectAll(comment);
		System.out.println(comment_list);
		result.setCode(200);
		result.setMessage("成功获取评论");
		result.setSuccess(true);
		result.setData(comment_list);
		System.out.println(result);
		return result;
	}
	
	
	@RequestMapping(path = "/rest/comment/addComment", method=RequestMethod.POST)
	@ResponseBody
	public RequestResultSim<Comment> addComment(@RequestBody Comment comment){
		System.out.println("进入到添加评论接口");
		int result = commentService.addComment(comment);
		if(result>=1) {
			System.out.println("添加成功");
			result_sim.setCode(200);
			result_sim.setMessage("评论成功");
			result_sim.setSuccess(true);
			result_sim.setData(comment);
			return result_sim;
		}else {
			System.out.println("添加失败");
			result_sim.setCode(201);
			result_sim.setMessage("评论失败");
			result_sim.setSuccess(false);
			result_sim.setData(null);
			return result_sim;
		}
		
		
	}
	
	@RequestMapping(path = "/rest/comment/getCommentAndUser", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<Map> getCommentAndUser(@RequestParam Integer tid,@RequestParam Integer size){
		RequestResult<Map> result = new RequestResult<Map>();
		System.out.println("进入到获取评论及用户接口");
		List<Map> comment_list = commentService.getCommentAndUser(tid,size);
		System.out.println(comment_list);
		result.setCode(200);
		result.setMessage("成功获取评论");
		result.setSuccess(true);
		result.setMapData(comment_list);
		System.out.println(result);
		return result;
		
		
	}
}
