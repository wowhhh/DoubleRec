package com.wyb.rec.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 用于保存歌单类型的类
 * @author wyb
 *
 */
public class type {
	public Integer idtype;
	public String typeName;
	
	//表示type与genres的多对多关系
	private Set<SongList> songLists=new HashSet<SongList>();
	
	
	
	
	public Set<SongList> getSongLists() {
		return songLists;
	}
	public void setSongLists(Set<SongList> songLists) {
		this.songLists = songLists;
	}
	//get set
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "type [idtype=" + idtype + ", typeName=" + typeName + "]";
	}
	
	//toString
	
}
