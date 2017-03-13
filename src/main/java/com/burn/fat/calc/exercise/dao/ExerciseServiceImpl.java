package com.burn.fat.calc.exercise.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.calc.exercise.model.ExerciseBean;

@Service("ExerciseService")
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
	private ExerciseDAOImpl exercise;

    @Override
    public List<ExerciseBean> bringtype() throws Exception {
    	return exercise.bringtype();
    };
    
    @Override
    public List<ExerciseBean> bringname(String name) throws Exception {
    	return exercise.bringname(name);
    };
	
    @Override
    public List<ExerciseBean> bringtime(Map map) throws Exception{
    	return exercise.bringtime(map);
    }
    
 /*   @Override
    public List<ExerciseBean> bringtime_p(String name) throws Exception{
    	return exerbean.bringtime_p(name);
    }*/
}
