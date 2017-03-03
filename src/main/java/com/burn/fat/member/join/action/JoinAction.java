package com.burn.fat.member.join.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("Joinaction")
public class JoinAction {
	
	@RequestMapping("/Join.brn")
	public String join(){
		return "html_membership/join1";
	}
	@RequestMapping("/Modify.brn")
	public String modify(){
		return "html_membership/join2";
	}
	
}
