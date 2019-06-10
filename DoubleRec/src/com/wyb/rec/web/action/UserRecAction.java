package com.wyb.rec.web.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.CollaborationFiltering.CFaction;
import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.UserRecService;

public class UserRecAction extends ActionSupport {
	
	//注入service
	private UserRecService userRecService;
	//提供set方法
	
	public void setUserRecService(UserRecService userRecService) {
		this.userRecService = userRecService;
	}
	
	//默认执行的方法，进行推荐，当然我不会让用户一点击就执行推荐，这样系统的计算量太大了，先一天做一次推荐吧
	//默认执行的方法就进行页面的调用与显示
	
	public String execute()
	{
		//1：首先是进行session的判断，如果session 中并不存在用户，那就提醒用户登录
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		//2：判断user是否为空，如果是空，就不做推荐，提示那啥用户要登录
		
		if(user !=null)//如果用户不为空
		{
		//判断数据库中是否有数据，也就是所有用户的今日推荐是否已经计算完成
		boolean isRecDone=userRecService.findRecTodayDone();
		//如果有，则查询数据并保存到值栈
		if(isRecDone)//今日的计算已经完毕
		{
			//进行查询该用户的推荐，用户如果登录，是保存到了session中的
			List<Rec> list= userRecService.findRecSongByUserId(user.getIdUser());
			//此集合目前是只有歌曲的id，还需要根据歌曲id，查询歌曲的全部信息。先将这些id进行显示吧
			ActionContext.getContext().getValueStack().set("IdSong", list);
			
			List<Song> songs=userRecService.findSongBySongId(list);//在service中进行主要操作
			ActionContext.getContext().getValueStack().set("songs", songs);
			
			return "recsong";
		}
		//如果没有，则跳转到GetRecAction 中
		//return "GetRec";
		}
		return "recsong";//用户没登录
	}
	
	//歌单推荐方法：
	public String SongListRec()
	{
		//1：首先是进行session的判断，如果session 中并不存在用户，那就提醒用户登录
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
				
		//2：判断user是否为空，如果是空，就不做推荐，提示那啥用户要登录
				
		if(user !=null)//如果用户不为空
		{
			//调用计算推荐，推荐结果保存值栈emmmmm，其实推荐的是歌曲的类型，先推荐出类型
			List<RecSongList> typeList=userRecService.findRecTypeByUserId(user.getIdUser());
			ActionContext.getContext().getValueStack().set("IdType", typeList);
			//根据歌单类型的id 去查询歌单类型的名字
			List<type> types=userRecService.findTypeByTypeId(typeList); 
			ActionContext.getContext().getValueStack().set("types", types);
			
			//查询每个类型下的歌单进行推荐
			List<SongList> songLists=userRecService.findRecSongListByType(types,user.getUserName());
			//返回的歌单可能还有重复的。。。。循环去除吧。即删除线性表中的重复数据
			List<SongList> songLists2=new LinkedList<SongList>();
			
			for(int i=0;i<songLists.size();i++)
			{
				int flag=1;
				for(int j=i+1;j<songLists.size();j++)
				{
					if(songLists.get(i) == songLists.get(j))
					{
						flag=0;//有重复的
					}
					
				}
				if(flag==1)
				{
					songLists2.add(songLists.get(i));
				}
			}
			//保存值栈，显示歌单
			ActionContext.getContext().getValueStack().set("RecSongList", songLists2);
			return "recType";
		}
		return "recType";
	}

	//获取推荐数据，推荐数据应该是计算过了的，应该设置个变量，如果是第一次就进行计算，反之就直接取
	public String GetRec()
	{
		CFaction cFaction=new CFaction();
		Map<Integer,Integer[]> user2songRecMatrix=new HashMap<Integer, Integer[]>();
		user2songRecMatrix=cFaction.run();//接受返回值
		
		return null;
	}
}
