package com.wyb.rec.domain;

public class CollectSong {
	private Integer idusercollectsong;
	private Integer idUser;
	private String SongId;
	public Integer getIdusercollectsong() {
		return idusercollectsong;
	}
	public void setIdusercollectsong(Integer idusercollectsong) {
		this.idusercollectsong = idusercollectsong;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getSongId() {
		return SongId;
	}
	public void setSongId(String songId) {
		SongId = songId;
	}

	
}
