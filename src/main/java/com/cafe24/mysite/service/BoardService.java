package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public boolean write(BoardVo boardVo, Object object) {
		UserVo authUser = (UserVo) object;
		boardVo.setUserNo(authUser.getNo());
		return boardDao.write(boardVo);
	}

	public List<BoardVo> getList() {
		return boardDao.getList();
	}

}
