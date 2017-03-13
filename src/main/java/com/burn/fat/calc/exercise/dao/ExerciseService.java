package com.burn.fat.calc.exercise.dao;

import java.util.List;
import java.util.Map;

import com.burn.fat.calc.exercise.model.ExerciseBean;


public interface ExerciseService {

	public List<ExerciseBean> bringtype() throws Exception;
	
	public List<ExerciseBean> bringname(String name) throws Exception;

	public List<ExerciseBean> bringtime(Map map) throws Exception;

	//public List<ExerciseBean> bringtime_m(String name) throws Exception;
	
	//public List<ExerciseBean> bringtime_p(String name) throws Exception;
	
	
}
