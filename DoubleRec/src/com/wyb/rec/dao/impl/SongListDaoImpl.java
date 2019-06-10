package com.wyb.rec.dao.impl;

import java.util.List;

import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.SongListDao;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.utils.PageHibernateCallback;

public class SongListDaoImpl extends HibernateDaoSupport implements SongListDao {

	
	
	@Override
	public boolean saveUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		//先查询此收藏记录是否存在，然后再进行保存，不用查询是否存在，因为页面已经确定了此歌单是否收藏
		String sql="from CollectSongList where idUser =? and ListId = ? ";
		List<CollectSongList> list= (List<CollectSongList>) this.getHibernateTemplate().find(sql,userId, listId);
		//判断是否为空
		if(list.size()==0)//为空
		{
			CollectSongList collectSongList=new CollectSongList();
			collectSongList.setIdUser(userId);
			collectSongList.setListId(listId);
			this.getHibernateTemplate().save(collectSongList);//保存
			return true;
		}
		return false;
	}

	//删除用户收藏的歌单记录，先取出对象然后对对象进行删除
	@Override
	public boolean cancleUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		//先获取collect
		String gethql="from CollectSongList where idUser =? and ListId = ? ";
		//CollectSongList collectSongList=(CollectSongList) this.getHibernateTemplate().find(gethql, userId,listId);
		//获取list集合
		List<CollectSongList> list= (List<CollectSongList>) this.getHibernateTemplate().find(gethql, userId,listId);
		CollectSongList collectSongList=list.get(0);
		//再进行删除
		if(collectSongList !=null)//如果存在这个数据就进行删除
		{
		this.getHibernateTemplate().delete(collectSongList);
		}
		return false;
	}
	
	/******以下方法用于歌单详情页面的显示
	 * 
	 * ******/

	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="from SongList as sl where sl.ListId=?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql,listId);
		return songLists;
	}

	@Override
	public int findCountByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="select  sl.songs from SongList as sl  where sl.ListId= ?";
		List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, listId);
		int temp=list.size();
		return temp;
	}

	@Override
	public List<Song> findPageByListId(String listId, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="select distinct sl.songs from SongList as sl where sl.ListId= ?";
		List<Song>	list=this.getHibernateTemplate().execute(new PageHibernateCallback<Song>(hql, new Object[]{listId}, begin, limit));
		//List<SongList> songLists=(List<SongList>) this.getHibernateTemplate().find(hql, cid);
		if(list !=null && list.size()>0)
			{
				 return list;
			}
		return null;
	}

	@Override
	public List<type> findSongListTypeByListId(String listId) {
		//distinct是去除重复的数据，先这样做吧，
				String hql="select distinct sl.types from SongList as sl where sl.ListId= ?";
				//普通的全部查询：List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, cid);
				//分页的写法，分页查询
				List<type> list=  (List<type>) this.getHibernateTemplate().find(hql, listId);
				//List<SongList> songLists=(List<SongList>) this.getHibernateTemplate().find(hql, cid);
				if(list !=null && list.size()>0)
					{
					   return list;
					}
				return null;
	}
	
	//根据类型查推荐音乐
	@Override
	public List<SongList> findRecSongListByType(int type_id) {
		// TODO Auto-generated method stub
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql, type_id);
		return songLists;
	}
	//查看此用户是否收藏了此歌单
	@Override
	public List<CollectSongList> isCollectThisSongList(int userId, String listId) {
		String gethql="from CollectSongList where idUser =? and ListId = ? ";
		//CollectSongList collectSongList=(CollectSongList) this.getHibernateTemplate().find(gethql, userId,listId);
		//获取list集合
		List<CollectSongList> list= (List<CollectSongList>) this.getHibernateTemplate().find(gethql, userId,listId);
		return list;
	}
	
	//查询收藏过的歌单的id
	@Override
	public List<CollectSongList> findCollectedByUserid(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from CollectSongList where idUser=?";
		List<CollectSongList> collectSongLists=(List<CollectSongList>) this.getHibernateTemplate().find(hql, idUser);
		return collectSongLists;
	}
	//根据歌单的ListId，查询歌单信息
	@Override
	public SongList findCOllectedSongListByListIds(CollectSongList collectSongList) {
		String listId=collectSongList.getListId();
		String hql="from SongList as sl where sl.ListId=?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql,listId);
		return songLists.get(0);
	}

	

}
