package com.wyb.rec.utils;

import java.util.List;



/**
 * 分页类的封装
 * @author wyb
 *
 */
public class PageBean<T>{
	private int page;//当前页数
	private int totalCount;//总记录数
	private int totalPage;//总页数
	private int limit;//每一页显示的记录数
	private List<T> list;//每一页歌单的集合，用范型，因为后面还有音乐的分页
	private String typeName;//歌单类型的名称
	//get set
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
