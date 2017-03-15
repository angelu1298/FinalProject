package com.burn.fat.main.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.main.dao.MainServiceImpl;

import com.burn.fat.board.gboard.dao.GboardService;
import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.gboard.model.GcommBean;

import com.burn.fat.board.fboard.dao.FboardService;
import com.burn.fat.board.fboard.model.FboardBean;

import com.burn.fat.board.sboard.dao.SboardService;
import com.burn.fat.board.sboard.model.SboardBean;

import com.burn.fat.board.oboard.dao.OBoardService;
import com.burn.fat.board.oboard.model.ObsBean;

import com.burn.fat.board.eboard.dao.EboardService;
import com.burn.fat.board.eboard.model.EboardBean;

import com.oreilly.servlet.MultipartRequest;// cos.jar 파일을 dependency에 추가해준다.
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MainAction {
 
	@Autowired
	private MainServiceImpl mainService;   
	
	/* 자료실 목록 */
	@RequestMapping(value="/Main.brn")
	public ModelAndView gbbs_gall(HttpServletRequest request, HttpServletResponse response) throws Exception{


		List<GbbsBean> glist = new ArrayList<GbbsBean>();
		glist = mainService.getGList(); 
		
		List<FboardBean> flist = new ArrayList<FboardBean>();
		flist = mainService.getFList(); 
		
		List<SboardBean> slist = new ArrayList<SboardBean>();
		slist = mainService.getSList(); 
		
		List<ObsBean> olist = new ArrayList<ObsBean>();
		olist = mainService.getOList(); 
		
		List<EboardBean> elist = new ArrayList<EboardBean>();
		elist = mainService.getEList(); 
		
		ModelAndView model = new ModelAndView("main/mainPage");

		model.addObject("glist", glist);
		model.addObject("olist", olist);
		model.addObject("flist", flist);
		model.addObject("slist", slist);
		model.addObject("elist", elist);
		
		return model;	
	
	}
 
}