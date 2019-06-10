package com.wyb.rec.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.httpclient.NTCredentials;
import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.GenresDao;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.GenresService;
import com.wyb.rec.utils.PageBean;

@Transactional
public class GenresServiceImpl  implements GenresService{
	//注入genresdao
	private GenresDao genresDao;
	//提供set方法
	public void setGenresDao(GenresDao genresDao) {
		this.genresDao = genresDao;
	}
	
	//查询部分标签，用于分类页面标题的显示
	@Override
	public List<type> findPart() {
		
		return genresDao.findPart();
	}
	//查询全部标签，用于全部分类的显示
	@Override
	public List<type> findAll() {
		// TODO Auto-generated method stub
		return genresDao.findAll();
	}

	//根据分类的id ，去查询相应的歌单，注意分页
	@Override
	public PageBean<SongList> findByPageCid(Integer cid, int page) {
		PageBean<SongList> pageBean=new PageBean<SongList>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每一页显示的记录数
		int limit=12;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount=0;
		totalCount=genresDao.findCountCid(cid);//根据类型的cid查询下属的歌单的总数
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalpage=0;
		if(totalCount % limit == 0)
		{
			totalpage=totalCount/limit;
		}
		else {	
			totalpage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalpage);
		//每页显示的数据集合
		//从哪里开始
		int begin= (page-1)*limit;
		
		List<SongList> list=genresDao.findPageByCid(cid,begin,limit);
		pageBean.setList(list);
		//设置类型的名称
		String typeName=genresDao.findTypeNameByCid(cid);
		pageBean.setTypeName(typeName);
		return pageBean;
	}
	//根据歌单的ListId，去查询歌曲
	@Override
	public PageBean<Song> findDetailByListId(String listId, int page) {
		PageBean<Song> pageBean=new PageBean<Song>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每一页显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount=0;
		totalCount=genresDao.findCountByListId(listId);//根据歌单的listId查询下属的歌曲的数目
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalpage=0;
		if(totalCount % limit == 0)
		{
			totalpage=totalCount/limit;
		}
		else {	
			totalpage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalpage);
		//每页显示的数据集合
		//从哪里开始
		int begin= (page-1)*limit;
		
		List<Song> list=genresDao.findPageByListId(listId,begin,limit);
		pageBean.setList(list);
		//设置类型的名称
		//String typeName=genresDao.findTypeNameByCid(cid);
		//pageBean.setTypeName(typeName);
		return pageBean;
	}

	//根据ListId查询一个歌单的信息
	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		return genresDao.findOneSongListByListId(listId);
	}

	//根据传入歌单的id，查询歌单的类型，emmm其实用爬虫更简单吧
	@Override
	public List<type> findSongListTypeByListId(String listId) {
		// TODO Auto-generated method stub
		return genresDao.findSongListTypeByListId(listId);
	}
	
	//根据歌单类型查询同类歌单
	@Override
	public List<SongList> findRecSongListByType(int type_id, String listId) {
		// TODO Auto-generated method stub
		List<SongList> songLists= genresDao.findRecSongListByType(type_id);
		//判断如果有与当前listId重复的不反悔
		List<SongList> recSongList = new LinkedList<SongList>();
		for(int i=0;i<6;i++)
		{
			SongList songList=songLists.get(i);
			String listId_2=songList.getListId();
			if(! listId .equals(listId_2) )//不等
			{
				recSongList.add(songList);
			}
		}
		return recSongList;
	}
	
	
	//根据用户点击要查看的SongList的ListId，去查询相关的歌单并进行显示
	/*@Override
	public List<SongList> findDetailByListId() {
		// TODO Auto-generated method stub
		
		return genresDao.findDetailByListId();
	}*/

	


	
}
