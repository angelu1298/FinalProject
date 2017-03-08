package com.burn.fat.member.join.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.join.dao.JoinService;
import com.burn.fat.member.model.MemberBean;
import com.oreilly.servlet.MultipartRequest;

@Controller("Joinaction")
public class JoinAction {
	
	@Autowired
	private JoinService service;
	private String saveFolder = "C:/Users/angel/git/FinalProject/src/main/webapp/resources/upload";

	
	//회원가입 1단계로 이동(가입동의)
	@RequestMapping(value="/Join.brn")
	public String join(){
		return "html_membership/join1";
	}
	//회원가입페이지로 이동
	@RequestMapping(value="/JoinGetInfo.brn", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, HttpServletResponse response ){
		if(request.getParameter("checkall")==null)
			return "html_membership/join1";
		return "html_membership/join2";
	}
	//워너비 사진 제외 회원가입완료
	@RequestMapping(value="/JoinSubmit.brn", method=RequestMethod.POST)
	public String joinSubmit(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="inputid")String id
			,@RequestParam(value="inputpw")String pw, @RequestParam(value="inputname")String name, @RequestParam(value="gender")int gender
			,@RequestParam(value="birthyear")int year, @RequestParam(value="birthmonth")int month,@RequestParam(value="birthday")int day
			,@RequestParam(value="mobileNo1")String hp1
			,@RequestParam(value="middleph")String hp2, @RequestParam(value="lastph")String hp3,@RequestParam(value="zipcode")int zc
			,@RequestParam(value="address")String addr, @RequestParam(value="mem_tel1")String tel1, @RequestParam(value="mem_tel2")String tel2
			,@RequestParam(value="mem_tel3")String tel3
			,@RequestParam(value="detailaddr")String detailaddr, @RequestParam(value="email")String email, @RequestParam(value="domain")String domain
			,@RequestParam(value="height")double height, @RequestParam(value="weight")double weight) throws Exception{
		MemberBean member = new MemberBean();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(year+"-"+month+"-"+day);
		Date sqldate = new Date(date.getTime());
		String tel=tel1+tel2+tel3;
		HttpSession session = request.getSession();
		session.setAttribute("mem_id", id);
		
		member.setMem_id(id);	member.setMem_sx(gender);	member.setMem_bd(sqldate);
		member.setMem_pw(pw);	member.setMem_hp(hp1+hp2+hp3); 	member.setMem_zc(zc);	member.setMem_add1(addr);
		member.setMem_nm(name);	member.setMem_add2(detailaddr);		member.setMem_ma(email+"@"+domain);	member.setMem_h(height);
		member.setMem_w(weight); member.setMem_tel(tel);
		int mem_no = service.joinMember(member);
		session.setAttribute("mem_no", mem_no);
		
		return "html_membership/join3";
	}
	@RequestMapping(value="/PutThum.brn")
	public String seeThum(){
		return "html_membership/join3";
	}
	//워너비사진 완료
	@RequestMapping(value="/Thumbnail.brn", method=RequestMethod.POST)
	public ModelAndView thumbnailReturn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		int fileSize = 5 * 1024 * 1024; // 이진파일 최대 업로드 크기
		String fileDBName = "";
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		File thumbnail = multi.getFile("wannabe");
		String filename = thumbnail.getName();

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
		int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
		int date = c.get(Calendar.DATE); // 오늘 일 구합니다.

		String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
		// upload폴더 아래에 파일 올린 날짜로 폴더 생성합니다.
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdir();// 새로운 폴더를 생성
		}
		// 난수를 구합니다.
		Random r = new Random();
		int random = r.nextInt(100000000);

		/**** 확장자 구하기 시작 ****/
		int index = 0;
		String fileExtension = "";
		String refileName = "";

		index = filename.lastIndexOf(".");
		// 문자열에서 특정 문자열의 위치 값(index)를 반환한다.
		// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		// lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
		// (파일명에 점에 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		fileExtension = filename.substring(index + 1);
		/**** 확장자 구하기 끝 ***/
		// 새로운 파일명을 저장
		refileName = "wannabe" + year + month + date + random + "." + fileExtension;
		// 오라클 디비에 저장될 레코드 값
		fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		// 파일명 변경합니다.
		thumbnail.renameTo(new File(homedir + "/" + refileName));
		
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("mem_wb", fileDBName);
		map.put("mem_id", (String)session.getAttribute("mem_id") );
		service.insertWannabe(map);
		
		ModelAndView model = new ModelAndView("html_membership/join3");
		model.addObject("wannabe",fileDBName);
		
		 
		return model;
	} 
	
	//가입 완료 메시지
	@RequestMapping(value="/JoinComplete.brn")
	public String joinComplete(){
		return "html_membership/joinComplete";
	}
	
	@RequestMapping(value="/FindZipCode.brn")
	public String findZipCode(){
		return "html_membership/zipcode";
	}
	
}
