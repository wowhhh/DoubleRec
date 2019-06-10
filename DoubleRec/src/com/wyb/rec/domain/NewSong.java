package com.wyb.rec.domain;
/**
 * 用于显示首页的新音乐
 * @author wyb
 *
 */
public class NewSong {
	public Integer idnewsong;

	public String SongId;
	public String SongName;
	public String SongTime;
	public String SongSinger;
	public String SongUrl;
	public String SongPic;
	public String SongLrc;
	//tostring
	@Override
	public String toString() {
		return "NewSong [idnewsong=" + idnewsong + ", SongId=" + SongId + ", SongName=" + SongName + ", SongTime="
				+ SongTime + ", SongSinger=" + SongSinger + ", SongUrl=" + SongUrl + ", SongPic=" + SongPic
				+ ", SongLrc=" + SongLrc + "]";
	}
	//get set
	public Integer getIdnewsong() {
		return idnewsong;
	}

	public void setIdnewsong(Integer idnewsong) {
		this.idnewsong = idnewsong;
	}
	public String getSongId() {
		return SongId;
	}
	public void setSongId(String songId) {
		SongId = songId;
	}
	public String getSongName() {
		return SongName;
	}
	public void setSongName(String songName) {
		SongName = songName;
	}
	public String getSongTime() {
		return SongTime;
	}
	public void setSongTime(String songTime) {
		SongTime = songTime;
	}
	public String getSongSinger() {
		return SongSinger;
	}
	public void setSongSinger(String songSinger) {
		SongSinger = songSinger;
	}
	public String getSongUrl() {
		return SongUrl;
	}
	public void setSongUrl(String songUrl) {
		SongUrl = songUrl;
	}
	public String getSongPic() {
		return SongPic;
	}
	public void setSongPic(String songPic) {
		SongPic = songPic;
	}
	public String getSongLrc() {
		return SongLrc;
	}
	public void setSongLrc(String songLrc) {
		SongLrc = songLrc;
	}

}
