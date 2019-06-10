package com.wyb.rec.web.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wyb.rec.CollaborationFiltering.CFaction;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.UserService;
import com.wyb.rec.utils.userandsltype;
import com.wyb.rec.utils.SavaSongList.GetConn;

/**
 * 用户管理的Action
 * @author wyb
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	//模型驱动使用的对象
	private User user=new User();
	//private String selected[];
	private String[] selected;
	private InputStream inputStream;
	
	/*public void setSelected(String[] selected) {
		this.selected = selected;
	}
	public String[] getSelected() {
		return selected;
	}*/

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//注入Service
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册的一种方法，直接登录
	 */
	public String regist(){
		boolean flag= userService.regist(user);	
		
		if(flag)
		{
		//登录成功,将用户信息存入session，再完成页面的跳转 
		ServletActionContext.getRequest().getSession()
			.setAttribute("existUser", user);//将user存入session中
		//页面跳转，首页
		//除了进行页面的跳转，还要查询用户的收藏记录，然后保存到session 中，后面用户再添加收藏的话就再追加
		List<CollectSong> collectSongs= userService.findUserCollectSongByUserId(user.getIdUser());
		//将collectSongs 保存到session
		//ServletActionContext.getRequest().getSession()
		//.setAttribute("collectSongs", collectSongs);
		ActionContext.getContext().getSession().put("collectSongs", collectSongs);
		return "registSuccess";
		}
		else
		{
			return "signup";
		}
	}
	
	/**
	 * 保存用户口味的方法，先判断是否登录（js中判断）,显示数据库中已经记录的用户有过行为的口味
	 * @throws UnsupportedEncodingException 
	 */
	public String taste() throws UnsupportedEncodingException
	{
		//目前selected数组里面已经保存了用户提交的内容
		//开始循环单个处理每一个内容
		//1：首先是进行session的判断，如果session 中并不存在用户，那就提醒用户登录
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
		int index=0;
		
		while(index<selected.length)
		{
			//根据标签内容，去dao中查询id,然后结合此用户，保存到用户次数表中
			userService.SavaUserTaste(selected[index],user.getIdUser());
			index++;
		}
		System.out.println(selected);
		}
		
        //还有一点是，取消iscollectsonglist，因为取消收藏之后，session如果不清楚那显示不会改变
		return "taste";
	}
	
	/**
	 * 访问用户口味页面的方法
	 */
	public String gettaste()
	{
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
		//开始进行用户有关标签的id的查询
			int idUser=user.getIdUser();
			List<UserAndSlType> list=userService.findTasetedByUserId(idUser);
			
		//现在有的还是类型的id ，还需要获取的是类型的名字emmmm
			List<type> types=userService.findTypeNameByTypeId(list);
			
			//目前得到的types里面有id对应的名字，list里面有次数
			List<userandsltype> userandsltypes=new LinkedList<userandsltype>();
			int index=0;
			while(index<list.size())
			{
				//取出对象
				userandsltype userandsltype=new userandsltype();
				userandsltype.setTypeName(types.get(index).getTypeName());
				userandsltype.setIdType(types.get(index).getIdtype());
				userandsltype.setTimes(list.get(index).getTimes());
				//添加对象
				userandsltypes.add(userandsltype);
				index++;
			}
			//存入值栈
			ActionContext.getContext().getValueStack().set("userandsltype", userandsltypes);
		}
		return "taste";
	}


	/**
	 * 用户登录的方法
	 */
	public String login()
	{
		//1：接受用户名和密码，模型驱动会自动接收
		//2：调用userService进行查询，根据用户名和密码以及状态进行查询
		User existUser=userService.login(user);
		//对返回的user进行判断
		if(existUser==null)
		{
			//登录失败
			this.addActionError("登录失败，用户名或者密码错误或用户未激活！");
			return LOGIN;
		}
		else {
			//登录成功,将用户信息存入session，再完成页面的跳转 
			ServletActionContext.getRequest().getSession()
				.setAttribute("existUser", existUser);//将user存入session中
			//页面跳转，首页
			//除了进行页面的跳转，还要查询用户的收藏记录，然后保存到session 中，后面用户再添加收藏的话就再追加
			List<CollectSong> collectSongs= userService.findUserCollectSongByUserId(existUser.getIdUser());
			//将collectSongs 保存到session
			//ServletActionContext.getRequest().getSession()
			//.setAttribute("collectSongs", collectSongs);
			ActionContext.getContext().getSession().put("collectSongs", collectSongs);
			return "loginSuccess";
		}
	}
	/**
	 * 校验用户名是否存在的方法
	 * AjAx进行异步校验
	 * @throws IOException 
	 */
	public String findByName() throws IOException
	{
		/**
		 * 调用service进行查询：
		 */
		//接收用户名
		System.out.println(user.getUserName());
		User existUser=userService.findByUsername(user.getUserName());
		//获得response对象，向页面输出
		HttpServletResponse response= ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");//设置编码
		
		//进行判断
		if(existUser!=null)//查询到了用户
		{
			//说明用户名已经存在
			response.getWriter().println("用户名已经存在");
			
		}
		else {
			//没查到:该用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	/**
	 * 用于登录页面的跳转
	 */
	public String  loginPage()
	{
		return "loginPage";
	}
	/**
	 * 用于用户退出的方法
	 */
	public String quit()
	{
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	//用于新注册用户选择音乐口味
	public String registRec()
	{
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
			//页面跳转
			return "registRec";
		}
		return "registRec";
	}
	public String registRec2() throws SQLException
	{
		//目前selected数组里面已经保存了用户提交的内容
				//开始循环单个处理每一个内容
				//1：首先是进行session的判断，如果session 中并不存在用户，那就提醒用户登录
				User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
				if(user!=null)
				{
				int index=0;
				
				while(index<selected.length)
				{
					//根据标签内容，去dao中查询id,然后结合此用户，保存到用户次数表中
					userService.SavaUserTaste(selected[index],user.getIdUser());
					index++;
				}
				System.out.println(selected);
				}
				System.out.print("保存成功，正在计算推荐！");
				//先查询用户的id
				String  selectid="select idUser from user where userName='"+user.getUserName()+"'";
				PreparedStatement preparedStatement=GetConn.GetConn().prepareStatement(selectid);
				ResultSet resultSet=preparedStatement.executeQuery();
				int userid=1;
				if(resultSet.next())
				{
					userid=resultSet.getInt(1);
				}
				preparedStatement.close();
				GetConn.CloseConn();
		        
				
				//计算推荐的过程，清空当前用户的推荐记录
				//String  clear="truncate userrectype";
				//PreparedStatement preparedStatement=GetConn.GetConn().prepareStatement(clear);
				//preparedStatement.execute();
				//preparedStatement.close();
				//GetConn.CloseConn();
				//开始计算
				CFaction cFaction=new CFaction();
				//cFaction.run_songListRec();
				cFaction.run_firstSongListRec(userid);
				//跳转到歌单推荐页面
				return "songlistrec";
	}
	//音乐播放列表，用户点击收藏，保存到session
	public String nowplay()
	{
		
		return "";
	}
}
