package com.wyb.rec.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.SongListDao;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.SongListService;
import com.wyb.rec.utils.PageBean;
@Transactional
public class SongListServiceImpl implements SongListService {
	private SongListDao songListDao;

	
	
	public void setSongListDao(SongListDao songListDao) {
		this.songListDao = songListDao;
	}



	//实现用户点击进行收藏歌单的操作，传入的值为用户的id以及收藏的歌单的ListId
	//未实现：用户收藏歌单是要对歌单内所有的歌曲的评分+2，如果有则进行加2，反之则添加
	//未实现：用户取消收藏，扣除评分
	@Override
	public boolean saveUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		return songListDao.saveUserCollectSongList(userId,listId);
	}


	//实现用户取消收藏操作，
	@Override
	public boolean cancleUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		return songListDao.cancleUserCollectSongList(userId, listId);
	}
	
	
	/***
	 * 以下方法用于加载一个歌单页面的所有信息
	 * 1：歌单信息
	 * 2：分页显示歌曲内容
	 * 3：分类推荐
	 * 4：歌单是否被收藏
	 */
	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		return songListDao.findOneSongListByListId(listId);
	}



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
		totalCount=songListDao.findCountByListId(listId);//根据歌单的listId查询下属的歌曲的数目
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
		
		List<Song> list=songListDao.findPageByListId(listId,begin,limit);
		pageBean.setList(list);
		//设置类型的名称
		//String typeName=genresDao.findTypeNameByCid(cid);
		//pageBean.setTypeName(typeName);
		return pageBean;
	}



	@Override
	public List<type> findSongListTypeByListId(String listId) {
		// TODO Auto-generated method stub
		return songListDao.findSongListTypeByListId(listId);
	}



	@Override
	public List<SongList> findRecSongListByType(int type_id, String listId) {
		// TODO Auto-generated method stub
				List<SongList> songLists= songListDao.findRecSongListByType(type_id);
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

	
	//用于判断此用户是否收藏了此歌单
	@Override
	public List<CollectSongList> isCollectThisSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		return songListDao.isCollectThisSongList(userId,listId);
	}


	//查询用户收藏歌单的信息
	@Override
	public List<CollectSongList> findCollectedByUserid(Integer idUser) {
		// TODO Auto-generated method stub
		return songListDao.findCollectedByUserid(idUser);
	}

	//根据传入的ListID集合，返回SongLists
	@Override
	public List<SongList> findCollectedSongListByListIds(List<CollectSongList> collectSongLists) {
		// TODO Auto-generated method stub
		List<SongList> songLists=new LinkedList<SongList>();
		int index=0;
		while(index<collectSongLists.size())
		{
			SongList songList=songListDao.findCOllectedSongListByListIds(collectSongLists.get(index));
			songLists.add(songList);
			index++;
		}
		return songLists;
	}



	
}
