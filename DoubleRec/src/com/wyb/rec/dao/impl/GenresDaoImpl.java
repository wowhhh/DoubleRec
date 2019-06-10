package com.wyb.rec.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.GenresDao;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.utils.PageHibernateCallback;
//继承HibernateDaoSupport
public class GenresDaoImpl extends HibernateDaoSupport implements GenresDao  {

	//查询部分标签，用于分类的显示
	@Override
	public List<type> findPart() {
		// TODO Auto-generated method stub
		String hql="from type where idtype between 9 and 24";
		List<type> list= (List<type>) this.getHibernateTemplate().find(hql);
		return list;
	}
	//查询全部标签，用于全部页面分类的显示
	@Override
	public List<type> findAll() {
		// TODO Auto-generated method stub
		String hql="from type";
		List<type> list=(List<type>)this.getHibernateTemplate().find(hql);
		return list;
	}
	//根据点击的分类id，查询该分类下所属的歌单数目
	@Override
	public int findCountCid(Integer cid) {
		// TODO Auto-generated method stub
		//查询歌单数目
		//Long count=0L;
		//String hql="select count(*) from songlist as sl where sl.types.idtype=?";
	   //	List<Long> temp2=(List<Long>) this.getHibernateTemplate().find(hql, cid);
		//我寻思，不用count也行啊，直接查出来，然后list.size()不也能成
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, cid);
		int temp=list.size();
		return temp;
	}
	//查询某type_id标签下的所有歌单
	@Override
	public List<SongList> findPageByCid(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		//distinct是去除重复的数据，先这样做吧，
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		//普通的全部查询：List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, cid);
		//分页的写法，分页查询
	    List<SongList>	list=this.getHibernateTemplate().execute(new PageHibernateCallback<SongList>(hql, new Object[]{cid}, begin, limit));
		//List<SongList> songLists=(List<SongList>) this.getHibernateTemplate().find(hql, cid);
	    if(list !=null && list.size()>0)
	    {
	    	return list;
	    }
		return null;
	}
	//根据用户点击的标签值，来查询该标签的类型名字
	@Override
	public String findTypeNameByCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql="from type  where idtype = ?";
		List<type> type=(List<com.wyb.rec.domain.type>) this.getHibernateTemplate().find(hql, cid);
		return type.get(0).typeName;
	}
	//根据用户点击的SongList，来查询ListId对应的详细歌单信息
	@Override
	public List<SongList> findDetailByListId() {
		// TODO Auto-generated method stub
		return null;
	}
	//根据传入的ListId去查询歌曲的数目
	@Override
	public int findCountByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="select  sl.songs from SongList as sl  where sl.ListId= ?";
		List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, listId);
		int temp=list.size();
		return temp;
	}
	//根据传入的listId以及begin limit 去进行分页返回歌曲
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
	
	//根据ListId查询详细信息的啦，歌单的
	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="from SongList as sl where sl.ListId=?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql,listId);
		return songLists;
	}
	
	//根据传入的ListId,查询歌单的类型
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
	//根据歌单分类查询推荐的歌单
	@Override
	public List<SongList> findRecSongListByType(int type_id) {
		// TODO Auto-generated method stub
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
	    List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql, type_id);
		return songLists;
	}

	
}
