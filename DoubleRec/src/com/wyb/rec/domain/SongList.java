package com.wyb.rec.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 歌单类，保存的是歌单的信息
 * 歌单和歌单类型是多对多的，歌单和歌曲也是多对多的
 * 数据库中表名是songlist
 * @author wyb
 *
 */
public class SongList {
	private Integer id;
	private String ListId;
	private String ListTitle;
	private String ListAuthor;
	private String ListDesc;
	private Integer ListSongNum;
	private String ListLogo;
	
	//表示歌单和歌曲的多对多关系
	private Set<Song> songs=new HashSet<Song>();
	
	public Set<Song> getSongs() {
		return songs;
	}
	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	//表示歌单与歌单类型的多对多关系,放置的是类型的一个集合
	private Set<type> types=new HashSet<type>();
	
	public Set<type> getTypes() {
		return types;
	}
	public void setTypes(Set<type> types) {
		this.types = types;
	}
	//get set
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getListId() {
		return ListId;
	}
	public void setListId(String listId) {
		ListId = listId;
	}
	public String getListTitle() {
		return ListTitle;
	}
	public void setListTitle(String listTitle) {
		ListTitle = listTitle;
	}
	public String getListAuthor() {
		return ListAuthor;
	}
	public void setListAuthor(String listAuthor) {
		ListAuthor = listAuthor;
	}
	public String getListDesc() {
		return ListDesc;
	}
	public void setListDesc(String listDesc) {
		ListDesc = listDesc;
	}
	public Integer getListSongNum() {
		return ListSongNum;
	}
	public void setListSongNum(Integer listSongNum) {
		ListSongNum = listSongNum;
	}
	public String getListLogo() {
		return ListLogo;
	}
	public void setListLogo(String listLogo) {
		ListLogo = listLogo;
	}
	
	
}
