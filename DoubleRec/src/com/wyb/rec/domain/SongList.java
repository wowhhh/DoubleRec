package com.wyb.rec.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * �赥�࣬������Ǹ赥����Ϣ
 * �赥�͸赥�����Ƕ�Զ�ģ��赥�͸���Ҳ�Ƕ�Զ��
 * ���ݿ��б�����songlist
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
	
	//��ʾ�赥�͸����Ķ�Զ��ϵ
	private Set<Song> songs=new HashSet<Song>();
	
	public Set<Song> getSongs() {
		return songs;
	}
	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	//��ʾ�赥��赥���͵Ķ�Զ��ϵ,���õ������͵�һ������
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
