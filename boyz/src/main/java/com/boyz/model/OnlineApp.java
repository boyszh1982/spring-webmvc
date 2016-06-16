package com.boyz.model;

public class OnlineApp {

	public String getOpDay() {
		return opDay;
	}
	public void setOpDay(String opDay) {
		this.opDay = opDay;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public String getMenuCycle() {
		return menuCycle;
	}
	public void setMenuCycle(String menuCycle) {
		this.menuCycle = menuCycle;
	}
	public String getMenuMemo() {
		return menuMemo;
	}
	public void setMenuMemo(String menuMemo) {
		this.menuMemo = menuMemo;
	}
	public String getMenuOwner() {
		return menuOwner;
	}
	public void setMenuOwner(String menuOwner) {
		this.menuOwner = menuOwner;
	}
	public String getMenuOrg() {
		return menuOrg;
	}
	public void setMenuOrg(String menuOrg) {
		this.menuOrg = menuOrg;
	}
	public String getRecordInsertTime() {
		return recordInsertTime;
	}
	public void setRecordInsertTime(String recordInsertTime) {
		this.recordInsertTime = recordInsertTime;
	}
	private String opDay ;
	private String idx ;
	private String menuId ;
	private String menuTitle ;
	private String menuPath ;
	private String menuCycle ;
	private String menuMemo ;
	private String menuOwner ;
	private String menuOrg ;
	private String recordInsertTime ;
	
	public boolean isMarked() {
		return marked;
	}
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	private boolean marked ;
	private String markedBy ;

	public String getMarkedBy() {
		return markedBy;
	}
	public void setMarkedBy(String markedBy) {
		this.markedBy = markedBy;
	}
}
