package com.wyb.rec.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.UserDao;
import com.wyb.rec.dao.UserRecDao;
import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.UserRecService;
@Transactional
public class UserRecServiceImpl implements UserRecService {

	private UserRecDao userRecDao;
	
	
	public void setUserRecDao(UserRecDao userRecDao) {
		this.userRecDao = userRecDao;
	}


	//查询今日所有用户的推荐结果是否完成
	@Override
	public boolean findRecTodayDone() {
		// TODO Auto-generated method stub
		return userRecDao.findRecTodayDone();
	}

	//根据用户的id查询计算出来的歌曲，这里直接调用dao 
	@Override
	public List<Rec> findRecSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return userRecDao.findRecSongByUserId(idUser);
	}

	//根据用户的id查询计算出来的歌曲，这里直接调用dao
	@Override
	public List<RecSongList> findRecTypeByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return userRecDao.findRecTypeByUserId(idUser);
	}

	//循环此集合，对每一个id进行查询其对应的歌曲
	@Override
	public List<Song> findSongBySongId(List<Rec> list) {
		// TODO Auto-generated method stub
		List<Song> songs=new LinkedList<Song>();
		for(int index=0;index<list.size();index++)
		{
		 Song song=	userRecDao.findSongBySongId(list.get(index).getSongId());
		 songs.add(song);
		}
		return songs;
	}

	//根据歌单类型id查询歌单名
	@Override
	public List<type> findTypeByTypeId(List<RecSongList> typeList) {
		// TODO Auto-generated method stub
		List<type> types=new LinkedList<type>();
		for(int index=0;index<typeList.size();index++)
		{
			type type=userRecDao.findTypeByTypeId(typeList.get(index).getIsSongList());
			types.add(type);
		}
		return types;
	}

	//根据歌单的类型查询类型下对应的歌单
	@Override
	public List<SongList> findRecSongListByType(List<type> types,String string) {
		// TODO Auto-generated method stub
		List<SongList> songLists=new LinkedList<SongList>();
		for(int index=0;index<types.size();index++)
		{
			SongList songList=userRecDao.findRecSongListByType(types.get(index).idtype,string);
			songLists.add(songList);
		}
		return songLists;
	}


	

}
