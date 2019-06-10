package com.wyb.rec.domain;

import java.sql.Date;

/**
 * 用户的实体
 * @author wyb
 *
 */
public class User {

	private Integer idUser;//用户id
	private String userName;//用户名
	private String userEmail;//用户邮箱
	private String userPass;//用户密码
	private Date registerDate;//用户注册时间
	private Integer userState;
	//get,set方法
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
	}
	
	
	
}
