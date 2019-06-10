package com.wyb.rec.utils;
/**
 * 协助完成歌单由收藏图标变为已收藏图标
 * @author wyb
 *
 */
public class CollectFlag {
	int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	String ListId;

	public String getListId() {
		return ListId;
	}

	public void setListId(String listId) {
		ListId = listId;
	}
	
}
