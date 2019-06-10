package com.wyb.rec.domain;
/**
 * 用户推荐的歌曲类
 * @author wyb
 *
 */
public class Rec {
	private Integer idRec;
	private Integer userId;
	private Integer songId;
	private String Date;
	
	
	public Integer getIdRec() {
		return idRec;
	}
	public void setIdRec(Integer idRec) {
		this.idRec = idRec;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSongId() {
		return songId;
	}
	public void setSongId(Integer songId) {
		this.songId = songId;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
}
