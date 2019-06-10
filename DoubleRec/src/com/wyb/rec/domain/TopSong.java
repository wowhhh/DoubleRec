package com.wyb.rec.domain;
/**
 * topSong区的实体类
 * @author wyb
 *
 */
public class TopSong {
	public Integer idtopsong;

	public String SongId;
	public String SongName;
	public String SongTime;
	public String SongSinger;
	public String SongUrl;
	public String SongPic;
	public String SongLrc;
	
	//toString
	@Override
	public String toString() {
		return "TopSong [idtopsong=" + idtopsong + ", SongId=" + SongId + ", SongName=" + SongName + ", SongTime="
				+ SongTime + ", SongSinger=" + SongSinger + ", SongUrl=" + SongUrl + ", SongPic=" + SongPic
				+ ", SongLrc=" + SongLrc + "]";
	}
	
	//get set
	public Integer getidtopsong() {
		return idtopsong;
	}
	public void setidtopsong(Integer idtopsong) {
		this.idtopsong = idtopsong;
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
