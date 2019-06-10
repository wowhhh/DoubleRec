package com.wyb.rec.web.action;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.GenresService;
import com.wyb.rec.utils.PageBean;

import freemarker.ext.util.ModelFactory;

/**
 * 用于歌单加载的action，包括歌单类型的加载，全部类型的加载，单个类型所包括的歌单的加载，单个歌单信息的加载
 * 可能还有用户歌单的更新查询之类的
 * @author wyb
 *
 */
public class GenresAction extends ActionSupport implements ModelDriven<SongList> {
	

	private SongList songList;

	private String ListId;
	
	//Listid的set方法
	
	public String getListId() {
		return ListId;
	}

	@Override
	public SongList getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setListId(String listId) {
		ListId = listId;
	}

	//注入genres的service
	private  GenresService genresService;
	//接受cid。分类的id
	private Integer cid;
	//接收当前的页数
	private int page;
	

	public void setPage(int page) {
		this.page = page;
	}



	//cid的set方法
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	//genresService的set方法
	public void setGenresService(GenresService genresService) {
		this.genresService = genresService;
	}
	
	//进入Genres默认执行的Action
	public String execute()
	{
		//功能主要是进行查询，查询标签，标签是和歌单多对多的
		List<type> glist= genresService.findPart();//查找部分，11-24
		//保存在值栈中,版本2：保存到session中
		//ActionContext.getContext().getValueStack().set("glist", glist);
		ActionContext.getContext().getSession().put("glist", glist);
		
		//对数据库进行查询，然后保存到值栈中。版本2：保存到session里面吧
		List<type> agList= genresService.findAll();
		//将查询到的集合保存到值栈中
		ActionContext.getContext().getSession().put("agList", agList);
		//ActionContext.getContext().getValueStack().set("agList", agList);
		//先定义一个空的pageBean
		//PageBean<SongList> pageBean=null;
		//ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "genres";//返回类型页面
	}
	/*
	 根据用户点进去后，分类的id，查询当前id对应下的歌单
	 */
	public String one()
	{
		//接受cid,然后根据cid查询该cid下的歌单
		PageBean<SongList> pageBean= genresService.findByPageCid(cid,page);//根据cid的查询带有分页，返回pageBean
		//获取pagebean，将pagebean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//将tyeName保存到值栈中
		//ActionContext.getContext().put("typeName", typeName);;
		return "genres_one";
	}

	/**
	 根据用户点击要显示的歌单，对其进行显示
	 */
	public String detail()//存在问题，歌单内容显示有问题
	{
		//值得注意的是，除了获取歌曲的数据外，还需要获取此ListId歌单的数据以及类型
		List<SongList> songLists= genresService.findOneSongListByListId(ListId);
		ActionContext.getContext().getValueStack().set("SongListByListId", songLists.get(0));
		//接受SongList的ListId，只有在界面按照类里面的格式才可以加载
		//System.out.println(songList.getListId());
		//调用service，其实不用查询歌单的信息了吧，直接查询多对多的歌曲
		PageBean<Song> pageBeanSong= genresService.findDetailByListId(ListId,page);//根据歌单的id进行查询，带有分页，返回pagebean
		ActionContext.getContext().getValueStack().set("pageBeanSong", pageBeanSong);
		
		//增加操作，用于提取并显示此歌单的标签，多对多的查询，传入ListId
		List<type> SongList_type=genresService.findSongListTypeByListId(ListId);
		//查询成功。将SongList_type 存入值栈中
		ActionContext.getContext().getValueStack().set("SongListType", SongList_type);
		
		//增加操作，用于显示第一个标签同类的三个歌曲
		String type_first=SongList_type.get(0).getTypeName();
		int type_id=SongList_type.get(0).getIdtype();
		//根据type的id，再联合两个表查询一次，查询出六个吧，显示五个 ，取出重复的,查询出type,listId用来去除当前歌单
		List<SongList> RecSongList= genresService.findRecSongListByType(type_id,ListId);
		//传入值栈
		ActionContext.getContext().getValueStack().set("RecSongList", RecSongList);
		//增加操作，用于判断此歌单是否被用户收藏，要先判断是否有用户登录
		
		return "songlist_detail";
	}
	

}
