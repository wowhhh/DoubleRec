package com.wyb.rec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.domain.Song;
import com.wyb.rec.service.SearchService;
import com.wyb.rec.utils.JSON.GetAndAnalyseJSON;

/**
 * 用于歌曲搜索
 * @author wyb
 *
 */
public class SearchAction extends ActionSupport {
	private String message;//接受用户要搜索的内容

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    private SearchService searchService;	//注入service
	
	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	//默认执行的方法，搜索的实现是调用接口，解析json文件，然后进行保存，搜索出来的歌曲可以进行直接保存
	//搜索歌单暂时不做，仅考虑歌曲
	public String execute()
	{
		if(message!=null)
		{
		//调用util中写好的GetAndAnalyseJSON
		GetAndAnalyseJSON getAndAnalyseJSON=new GetAndAnalyseJSON();
		//先判断message是不是为空。这个可以在js中进行判断吧，还是不在js中加入判断了
		List<Song> songs= getAndAnalyseJSON.GetSearchSongs(message);//获取歌曲的集合
		
		//存入值栈进行显示，
		ActionContext.getContext().getValueStack().set("searchsongs", songs);
		//保存到数据库中，调用service
		if(songs!=null)
		{
		searchService.saveSearchSongs(songs);
		}
		return "song";
		}
		return "song";
	}
}
