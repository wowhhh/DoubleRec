package com.wyb.rec.domain;
import java.util.HashSet;
import java.util.Set;
/**
 * �����࣬������Ҫ��赥����ƥ�䣬���
 * @author wyb
 *
 */

public class Song {
	private Integer idsong;
	private String SongId;
	private String SongName;
	private String SongTime;
	private String SongSinger;
	private String SongUrl;
	private String SongPic;
	private String SongLrc;
	private Integer Songtimes;
	public Integer getSongtimes() {
		return Songtimes;
	}

	public void setSongtimes(Integer songtimes) {
		Songtimes = songtimes;
	}

	//��赥�Ķ�Զ࣬һ���Ǹ��ݸ赥�������
	private Set<SongList> songLists=new HashSet<SongList>();

	
	//getset
	public Integer getIdsong() {
		return idsong;
	}

	public void setIdsong(Integer idsong) {
		this.idsong = idsong;
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

	public Set<SongList> getSongLists() {
		return songLists;
	}

	public void setSongLists(Set<SongList> songLists) {
		this.songLists = songLists;
	}
	

	
}
