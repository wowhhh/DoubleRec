package com.wyb.rec.web.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.SongListService;
import com.wyb.rec.utils.CollectFlag;
import com.wyb.rec.utils.PageBean;
/**
 * 处理歌单的action,收藏
 * @author wyb
 *
 */
public class SongListAction extends ActionSupport implements ModelDriven<SongList> {

	private String  ListId;//用于接收点击后传入的歌单id
	//注入service
	private SongListService songListService;
	
	private int page;
	
	private InputStream inputStream;
	
	//
	
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setSongListService(SongListService songListService) {
		this.songListService = songListService;
	}

	public void setListId(String listId) {
		ListId = listId;
	}

	@Override
	public SongList getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 根据用户点击要显示的歌单，对其进行显示
	 */
	public String detail()//存在问题，歌单内容显示有问题
	{
		//1:值得注意的是，除了获取歌曲的数据外，还需要获取此ListId歌单的数据以及类型
		List<SongList> songLists= songListService.findOneSongListByListId(ListId);
		ActionContext.getContext().getValueStack().set("SongListByListId", songLists.get(0));
		//接受SongList的ListId，只有在界面按照类里面的格式才可以加载
		//System.out.println(songList.getListId());
		//2:调用service，其实不用查询歌单的信息了吧，直接查询多对多的歌曲
		PageBean<Song> pageBeanSong= songListService.findDetailByListId(ListId,page);//根据歌单的id进行查询，带有分页，返回pagebean
		ActionContext.getContext().getValueStack().set("pageBeanSong", pageBeanSong);
		
		//3:增加操作，用于提取并显示此歌单的标签，多对多的查询，传入ListId
		List<type> SongList_type=songListService.findSongListTypeByListId(ListId);
		//查询成功。将SongList_type 存入值栈中
		ActionContext.getContext().getValueStack().set("SongListType", SongList_type);
		
		//4:增加操作，用于显示第一个标签同类的三个歌曲
		String type_first=SongList_type.get(0).getTypeName();
		int type_id=SongList_type.get(0).getIdtype();
		//根据type的id，再联合两个表查询一次，查询出六个吧，显示五个 ，取出重复的,查询出type,listId用来去除当前歌单
		List<SongList> RecSongList= songListService.findRecSongListByType(type_id,ListId);
		//传入值栈
		ActionContext.getContext().getValueStack().set("RecSongList", RecSongList);
		//5:增加操作，用于判断此歌单是否被用户收藏，要先判断是否有用户登录
		//获取session,判断是否有用户登录，
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user !=null)//仅仅在用户登录的情况下执行此操作
		{
			int userId=user.getIdUser();
		    List<CollectSongList> list= songListService.isCollectThisSongList(userId,ListId);
		    if(list.size()>0)//如果此用户已经对这个歌单进行了改变
		    {
		    	//考虑了一下，还是保存查询到的结果到session吧，然后进行判断
		    	ServletActionContext.getRequest().getSession().setAttribute("iscollectsonglist",list.get(0));
		    }
		}
		
		return "songlist_detail";
	}
	
	public String getListId() {
		return ListId;
	}

	/************
	 * 用于添加收藏和取消收藏，用户是否登录的行为已经在js中进行了判断
	 * 不需要加入是否收藏成功和取消收藏成功的判断，因为已经确保了显示收藏的时候数据库中的确不存在此收藏信息
	 * 点击取消收藏的时候，数据库中的确已经添加了此收藏信息
	 * @throws UnsupportedEncodingException 
	 * ****************/
	public String canclecollect2() throws UnsupportedEncodingException
	{	
		//从session中获取user
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		if(user!=null)//如果已经登录
		{
			int userId=user.getIdUser();
			//目前有了用户的id以及歌单的id，所以这个时候就可以调用service进行删除了
			boolean cancle=  songListService.cancleUserCollectSongList(userId,ListId);
			//保存完成
			//获得response对象，向页面输出
				//HttpServletResponse response= ServletActionContext.getResponse();
			 //response.setContentType("text/html;charset=UTF-8");//设置编码
				
				//进行判断，如果收藏成功，这里就直接显示取消收藏
				
				//	response.getWriter().println("<font color='red'>取消收藏</font>");
			 String str = "收藏";  
		        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));

		        //还有一点是，取消iscollectsonglist，因为取消收藏之后，session如果不清楚那显示不会改变
		       ServletActionContext.getRequest().getSession().setAttribute("iscollectsonglist", null); 
		        return SUCCESS;  
				
			//	return NONE;
			 
			 //由于下面没办法得到ListId，那就在这里执行插入收藏信息之后的刷新吧
			 /*CollectFlag collectFlag=new CollectFlag();
				collectFlag.setListId(ListId.toString());//null
				collectFlag.setUserId(user.getIdUser());
				
				ServletActionContext.getRequest().getSession()
				.setAttribute("CollectFlag", collectFlag);//将user存入session中*/
				//return "Getdetail";
		}
		
		return SUCCESS;
	}
	
	//记录用户的收藏，需要获取的参数是用户的id（从session中拿），歌单的id，页面传入
	public String collect2() throws IOException
	{
			//从session中获取user
			User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
			
			if(user!=null)//如果已经登录
			{
				CollectSongList collectSongList=new CollectSongList();
				int userId=user.getIdUser();
				//目前有了用户的id以及歌单的id，所以这个时候就可以调用service进行保存了
				 songListService.saveUserCollectSongList(userId,ListId);
				//保存完成
				//获得response对象，向页面输出
					//HttpServletResponse response= ServletActionContext.getResponse();
				 //response.setContentType("text/html;charset=UTF-8");//设置编码
					
					//进行判断，如果收藏成功，这里就直接显示取消收藏
					
					//	response.getWriter().println("<font color='red'>取消收藏</font>");
				 	String str = "取消收藏";  
			        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			        //添加session，用于加入收藏后的显示更改
			        
			        List<CollectSongList> collectSongLists = songListService.findCollectedByUserid(user.getIdUser());
			      //用户收藏的音乐信息保存到值栈中
					 ServletActionContext.getRequest().getSession().setAttribute("collectedSongLists", collectSongLists);
			        return SUCCESS;  
					
				//	return NONE;
				 
				 //由于下面没办法得到ListId，那就在这里执行插入收藏信息之后的刷新吧
				 /*CollectFlag collectFlag=new CollectFlag();
					collectFlag.setListId(ListId.toString());//null
					collectFlag.setUserId(user.getIdUser());
					
					ServletActionContext.getRequest().getSession()
					.setAttribute("CollectFlag", collectFlag);//将user存入session中*/
					//return "Getdetail";
			}
			
			return SUCCESS;
		}
	
	//查询用户收藏过的歌单，获取用户id，然后
	public String UserCollected()
	{
		//可以获取到用户id和歌单的长id
		//从session中获取user
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
			List<CollectSongList> collectSongLists = songListService.findCollectedByUserid(user.getIdUser());
			//先保存到值栈，调试一手
			//ActionContext.getContext().getValueStack().set("collectSongLists",collectSongLists);
			//根据获取到的id，查询整个SongList的信息
			List<SongList> songLists= songListService.findCollectedSongListByListIds(collectSongLists);
			//保存值栈，用于显示
			ActionContext.getContext().getValueStack().set("collectedSonglists",songLists );
		
			//用户收藏的音乐信息保存到值栈中
			 ServletActionContext.getRequest().getSession().setAttribute("collectedSongLists", collectSongLists);
		        
		}
		return "GetCollect";
	}
		
		/***********以下函数没用到，弃用*************/
	
	//记录用户的收藏，需要获取的参数是用户的id（从session中拿），歌单的id，页面传入
	public String collect() throws IOException
	{
		//从session中获取user
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		if(user!=null)//如果已经登录
		{
			int userId=user.getIdUser();
			//目前有了用户的id以及歌单的id，所以这个时候就可以调用service进行保存了
			 songListService.saveUserCollectSongList(userId,ListId);
			//保存完成
			//获得response对象，向页面输出
				HttpServletResponse response= ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");//设置编码
				
				//进行判断，如果收藏成功，这里就直接显示取消收藏
				
			response.getWriter().println("<font color='red'>取消收藏</font>");
					
				return NONE;
			 
			 //由于下面没办法得到ListId，那就在这里执行插入收藏信息之后的刷新吧
			 /*CollectFlag collectFlag=new CollectFlag();
				collectFlag.setListId(ListId.toString());//null
				collectFlag.setUserId(user.getIdUser());
				
				ServletActionContext.getRequest().getSession()
				.setAttribute("CollectFlag", collectFlag);//将user存入session中*/
				//return "Getdetail";
		}
		
		return NONE;//跳转到下面的action
	}
	
	//将“收藏图标”改为“已收藏”，之所以单独列出来是因为当用户在已登录状态点击进去的时候，还需要判断此歌单是否收藏
	public String Collected()
	{
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//
		CollectFlag collectFlag=new CollectFlag();
		collectFlag.setListId(ListId);//null
		collectFlag.setUserId(user.getIdUser());
		
		ServletActionContext.getRequest().getSession()
		.setAttribute("CollectFlag", collectFlag);//将user存入session中
		return "songlist_detail";
	}
	
	
	
	//查询歌单是否已经收藏。
	public String isCollect()
	{
		return null;
	}
}
