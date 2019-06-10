package com.wyb.rec.domain;
/**
 * 该类的主要功能是用于查询与用户有关的标签行为
 * @author wyb
 *
 */
public class UserAndSlType {
	private Integer iduserandsltype;
	private Integer idUser;
	private Integer idtype;
	private Integer times;
	public Integer getIduserandsltype() {
		return iduserandsltype;
	}
	public void setIduserandsltype(Integer iduserandsltype) {
		this.iduserandsltype = iduserandsltype;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	
	//get set
	
}
