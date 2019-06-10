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


	//��ѯ���������û����Ƽ�����Ƿ����
	@Override
	public boolean findRecTodayDone() {
		// TODO Auto-generated method stub
		return userRecDao.findRecTodayDone();
	}

	//�����û���id��ѯ��������ĸ���������ֱ�ӵ���dao 
	@Override
	public List<Rec> findRecSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return userRecDao.findRecSongByUserId(idUser);
	}

	//�����û���id��ѯ��������ĸ���������ֱ�ӵ���dao
	@Override
	public List<RecSongList> findRecTypeByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return userRecDao.findRecTypeByUserId(idUser);
	}

	//ѭ���˼��ϣ���ÿһ��id���в�ѯ���Ӧ�ĸ���
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

	//���ݸ赥����id��ѯ�赥��
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

	//���ݸ赥�����Ͳ�ѯ�����¶�Ӧ�ĸ赥
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
