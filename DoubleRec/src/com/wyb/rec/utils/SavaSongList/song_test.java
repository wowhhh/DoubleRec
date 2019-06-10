package com.wyb.rec.utils.SavaSongList;
/**
 * 用于一首歌曲信息的保存
 * @author wyb
 *
 */
public class song_test {
	private String SongId;
	private String SongName;
	private String SongTime;
	private String SongSinger;
	private String SongUrl;
	private String SongPic;
	private String SongLrc;
	
	
	
	@Override
	public String toString() {
		return "song [SongId=" + SongId + ", SongName=" + SongName + ", SongTime=" + SongTime + ", SongSinger="
				+ SongSinger + ", SongUrl=" + SongUrl + ", SongPic=" + SongPic + ", SongLrc=" + SongLrc + "]";
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
