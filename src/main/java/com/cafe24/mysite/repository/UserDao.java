package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(Long no) throws UserDaoException{
		UserVo userVo = sqlSession.selectOne("getByNo", no);
		return userVo;
	}

	public UserVo get(UserVo userVo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", userVo.getEmail());
		map.put("password", userVo.getPassword());
		UserVo userVo2 = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo2;
	}	
	
	public Boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);
		
		return 1 == count;
	}	
	
	
	public Boolean update(UserVo userVo) {
		int count = sqlSession.update("update", userVo);
		return 1==count;
	}

	public Object getUpdtaeInfo(Long no) {
		return null;
	}

}
