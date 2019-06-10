package com.wyb.rec.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.User;
import com.wyb.rec.service.SongService;
import com.wyb.rec.utils.userbehaviour.SongBehavior;

public class SongAction extends ActionSupport {
	
	private String SongId;//用于接受用户要收藏的歌曲的id
	private SongService songService;//注入songservice
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}


	//set方法
	public void setSongService(SongService songService) {
		this.songService = songService;
	}
	
	public String getSongId() {
		return SongId;
	}

	public void setSongId(String songId) {
		SongId = songId;
	}
	
	//用户对歌曲，收藏，取消收藏，添加到自己创建的播放列表
	
	//用户对歌曲添加收藏，不跳转页面
	  public String  collect() throws UnsupportedEncodingException
	  {
		//从session中获取user
			User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
			if(user!=null)
			{
				int idUser=user.getIdUser();
				//现在有了idUser和SongId 需要做的就是插入到数据库中
 				songService.saveUserCollectSongId(idUser,SongId);
				String str = "取消收藏";  
		        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		        //以上步骤已经做到收藏信息的插入，现在要做的是收藏歌曲的显示，收藏歌曲显示的这个操作每个歌曲显示界面都应该进行
		        //先查询，然后在每个歌曲显示界面判断当前id是否在查询到的收藏列表中。
		        //添加session，用于加入收藏后的显示更改
		      //以上做到了数据的删除，现在重新查询收藏信息，然后重新设置session
		        List<CollectSong> collectSongs= songService.findUserCollectSong(idUser);
		        //保存到session
		        ServletActionContext.getRequest().getSession().setAttribute("collectSongs", collectSongs);
		        
		        //用户行为评分记录
		        SongBehavior songBehavior=new SongBehavior();
		        songBehavior.AddScore(user.getUserName(), SongId,1);
		        return SUCCESS;  
				
			}
		  return SUCCESS;
	  }
	  
	  //用户取消收藏
	  public String  canclecollect() throws UnsupportedEncodingException
	  {
		//从session中获取user
			User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
			if(user!=null)
			{
				int idUser=user.getIdUser();
				//现在有了idUser和SongId 需要做的就是从数据库中删除数据
 				songService.delUserCollectSongId(idUser,SongId);
				String str = "收藏";  
		        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		        //以上做到了数据的删除，现在重新查询收藏信息，然后重新设置session
		        List<CollectSong> collectSongs= songService.findUserCollectSong(idUser);
		        //保存到session
		        ServletActionContext.getRequest().getSession().setAttribute("collectSongs", collectSongs);  //用户行为评分记录
		        SongBehavior songBehavior=new SongBehavior();
		        songBehavior.DelScore(user.getUserName(), SongId);
		        return SUCCESS;  
				
			}
		  return SUCCESS;
	  }
	  //查询用户收藏的所有歌曲
	  public String UserCollected()
	  {
		  //从session中获取user
		  User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		  if(user!=null)
		  {
			  List<CollectSong> collectSongs = songService.findCollectedSongByUserid(user.getIdUser());
				//先保存到值栈，调试一手
				//ActionContext.getContext().getValueStack().set("collectSongLists",collectSongLists);
				//根据获取到的id，查询整个Song的信息
				List<Song> songs= songService.findCollectedSongBySongIds(collectSongs);
				//保存值栈，用于显示
				ActionContext.getContext().getValueStack().set("collectedSongs",songs );
				return "collectedSongs";
		  }
		  return "collectedSongs";
	  }
	  //用户点击播放之后的行为信息
	  public String play() throws UnsupportedEncodingException
	  {
		  System.out.println("diiddi");
		  //获取用户名
		  User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		  if(user!=null)
		  {
			SongBehavior songBehavior=new SongBehavior();
			//获取SongId
			  songBehavior.AddScore(user.getUserName(), SongId,0);
			  //更新Song中的Songtimes
			  //更新评分矩阵
			  //用户播放历史
		  }
		  songService.updateSongTimes(SongId);
		  String str = "ok";  
		  inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		  return SUCCESS; 
	  }

}
