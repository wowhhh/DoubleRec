package com.wyb.rec.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.aspectj.internal.lang.reflect.PointcutBasedPerClauseImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.domain.DiscoverSong;
import com.wyb.rec.domain.NewSong;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.TopSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.service.SongService;

/**
 * 用于首页访问的Action,不需要接受数据，就不用实现模型驱动了
 * @author wyb
 *
 */
public class IndexAction extends ActionSupport {

	//注入音乐的service ,用于首页音乐数据的加载
	private SongService songService;
	//set方法
	public void setSongService(SongService songService) {
		this.songService = songService;
	}

	//执行的访问首页的方法
	/**
	 * 流程，按照次数进行查询，保存值栈加载即可
	 */
	public String execute()
	{
		//查询discoverSong
		List<Song> dSList= songService.findDiscoverSong();
		//将list集合保存到值栈中
		ActionContext.getContext().getValueStack().set("dSList", dSList);
		
		//查询newSong
		List<NewSong> nSList=songService.findNewSong();
		//将list集合保存在值栈中
		ActionContext.getContext().getValueStack().set("nSList", nSList);
		
		//查询topSong
		List<TopSong> tSList=songService.findTopSong();
		ActionContext.getContext().getValueStack().set("tSList", tSList);
		ServletActionContext.getRequest().getSession().setAttribute("nowplay", tSList);
		//判断用户是否存在，如果有用户的话，收藏音乐的session就不用置为空
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null)
		{
		ServletActionContext.getRequest().getSession()
		.setAttribute("collectSongs", null);
		}
		
		return "index";
	}
	
	
}
