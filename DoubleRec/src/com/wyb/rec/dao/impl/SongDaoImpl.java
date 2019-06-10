package com.wyb.rec.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.websocket.Session;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.SongDao;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.DiscoverSong;
import com.wyb.rec.domain.NewSong;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.TopSong;
/**
 * 音乐持久层
 * @author wyb
 *
 */
public class SongDaoImpl extends HibernateDaoSupport implements SongDao {

	//查询DiscoverSong
	@Override
	public List<Song> findDiscoverSong() {
		//使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Song.class);
		String hql="from Song order by Songtimes desc";
		//设置查询个数
		
		//执行查询
		List<Song> list= (List<Song>) this.getHibernateTemplate().find(hql);
	
		//查询12个
		List<Song> newlist=new LinkedList<Song>();
		int index=0;
		while(index<=11)
		{
			newlist.add(list.get(index));
			index++;
		}
		//使用execute方法
		return newlist;
	}

	//查询newSong
	@Override
	public List<NewSong> findNewSong() {
		String hql="from NewSong";
		//执行查询，返回list集合
		List<NewSong> list=(List<NewSong>) this.getHibernateTemplate().find(hql);
		return list;
	}

	//查询topSong
	@Override
	public List<TopSong> findTopSong() {
		String hql="from TopSong";
		//执行查询，返回list集合
		List<TopSong> list=(List<TopSong>) this.getHibernateTemplate().find(hql);
		return list;
	}
	
	//保存
	@Override
	public void saveUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		//新建对象，然后保存
		CollectSong collectSong=new CollectSong();
		collectSong.setIdUser(idUser);
		collectSong.setSongId(songId);
		this.getHibernateTemplate().save(collectSong);
	}
	
	//删除用户收藏记录
	@Override
	public void delUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		//先查询，再删除
		String hql="from CollectSong where idUser=? and SongId=?";
		List<CollectSong> collectSongs= (List<CollectSong>) this.getHibernateTemplate().find(hql, idUser,songId);
		
		this.getHibernateTemplate().delete(collectSongs.get(0));
	}
	
	//查询用户收藏的所有歌曲
	@Override
	public List<CollectSong> findUserCollectSong(int idUser) {
		// TODO Auto-generated method stub
		String hql="from CollectSong where idUser=?";
		List<CollectSong> collectSongs= (List<CollectSong>) this.getHibernateTemplate().find(hql, idUser);
		return collectSongs;
	}
	
	//根据SongId查询歌曲详情
	@Override
	public Song findSongBySongId(String songId) {
		// TODO Auto-generated method stub
		String hql ="from Song where SongId=?";
		List<Song> songs= (List<Song>) this.getHibernateTemplate().find(hql, songId);
		return songs.get(0);
	}

	@Override
	public void updateSongTimes(String songId) {
		// TODO Auto-generated method stub
		//先查询
		String hql="from Song where SongId=?";
		List<Song> songs=(List<Song>) this.getHibernateTemplate().find(hql, songId);
		Song song=songs.get(0);
		System.out.println("当前此音乐的播放次数是"+song.getSongtimes());
		System.out.println("更新后此音乐的播放次数是"+song.getSongtimes()+1);
		song.setSongtimes(song.getSongtimes()+1);
		//再更新
		System.out.println(song.getSongtimes());
		this.getHibernateTemplate().update(song);
	}


	
	

}
