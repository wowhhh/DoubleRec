package com.wyb.rec.domain;

import java.sql.Date;

/**
 * �û���ʵ��
 * @author wyb
 *
 */
public class User {

	private Integer idUser;//�û�id
	private String userName;//�û���
	private String userEmail;//�û�����
	private String userPass;//�û�����
	private Date registerDate;//�û�ע��ʱ��
	private Integer userState;
	//get,set����
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
