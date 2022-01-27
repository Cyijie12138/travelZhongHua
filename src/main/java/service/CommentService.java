package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CommentMapper;
import pojo.Comment;

@Service
public class CommentService {
	@Autowired
	CommentMapper commentMapper;

	public List<Comment> selectAll(Comment comment) {
		// TODO Auto-generated method stub
		List<Comment> result = commentMapper.selectAll(comment);
		return result;
	}

	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		int result = commentMapper.insert(comment);
		return result;
	}
	
	
	
}
