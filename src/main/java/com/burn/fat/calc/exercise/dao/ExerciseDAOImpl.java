package com.burn.fat.calc.exercise.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.calc.exercise.model.ExerciseBean;


@Repository
public class ExerciseDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*�Ÿ�� ��������*/
	public List<ExerciseBean> bringtype() throws Exception{
		List<ExerciseBean> list = sqlSession.selectList("bring_type");
		return list;
	}
	
	/*��̸� ��������*/
	public List<ExerciseBean> bringname(String name) throws Exception{
		List<ExerciseBean> list = sqlSession.selectList("bring_name",name);
		return list;
	}

	/*�ð�����ϱ�*/
	public List<ExerciseBean> bringtime(Map map) {
		List<ExerciseBean> list = sqlSession.selectList("bring_time",map);
		return list;
	}
	

}











