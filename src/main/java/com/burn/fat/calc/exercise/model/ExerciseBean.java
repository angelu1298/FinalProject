package com.burn.fat.calc.exercise.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ExerciseBean {

	private String erc_ty;
	private String erc_nm;
	private int erc_sx;
	private int erc_minw;
	private int erc_maxw;
	private int erc_ten;
	private int erc_hun;
	
	private int exe_kcal;
	
	
	public int getExe_kcal() {
		return exe_kcal;
	}
	public void setExe_kcal(int exe_kcal) {
		this.exe_kcal = exe_kcal;
	}
	public String getErc_ty() {
		return erc_ty;
	}
	public void setErc_ty(String erc_ty) {
		this.erc_ty = erc_ty;
	}
	public String getErc_nm() {
		return erc_nm;
	}
	public void setErc_nm(String erc_nm) {
		this.erc_nm = erc_nm;
	}
	public int getErc_sx() {
		return erc_sx;
	}
	public void setErc_sx(int erc_sx) {
		this.erc_sx = erc_sx;
	}
	public int getErc_minw() {
		return erc_minw;
	}
	public void setErc_minw(int erc_minw) {
		this.erc_minw = erc_minw;
	}
	public int getErc_maxw() {
		return erc_maxw;
	}
	public void setErc_maxw(int erc_maxw) {
		this.erc_maxw = erc_maxw;
	}
	public int getErc_ten() {
		return erc_ten;
	}
	public void setErc_ten(int erc_ten) {
		this.erc_ten = erc_ten;
	}
	public int getErc_hun() {
		return erc_hun;
	}
	public void setErc_hun(int erc_hun) {
		this.erc_hun = erc_hun;
	}

	
	
}
