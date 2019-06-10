package com.wyb.rec.utils.SavaSongList;
/**
 * 歌单的信息，歌单包括歌单的唯一标识，标题，作者，描述，歌曲数目，歌单的logo
 * @author wyb
 *
 */
public class songlist_test {
	
	private String ListId;
	private String ListTitle;
	private String ListAuthor;
	private String ListDesc;
	private int ListSongNum;
	private String ListLogo;
	
	
	
	
	@Override
	public String toString() {
		return "songList [ListId=" + ListId + ", ListTitle=" + ListTitle + ", ListAuthor=" + ListAuthor + ", ListDesc="
				+ ListDesc + ", ListSongNum=" + ListSongNum + ", ListLogo=" + ListLogo + "]";
	}
	//get,set
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
	public int getListSongNum() {
		return ListSongNum;
	}
	public void setListSongNum(int listSongNum) {
		ListSongNum = listSongNum;
	}
	public String getListLogo() {
		return ListLogo;
	}
	public void setListLogo(String listLogo) {
		ListLogo = listLogo;
	}
	
	
}
