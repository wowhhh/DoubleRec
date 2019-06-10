package com.wyb.rec.service;

import java.util.List;

import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;

public interface UserRecService {

	boolean findRecTodayDone();//��ѯ���յ��Ƽ��Ƿ�������

	List<Rec> findRecSongByUserId(Integer idUser);//�����û���id��ѯ����������Ƽ��ĸ���

	List<RecSongList> findRecTypeByUserId(Integer idUser);

	List<Song> findSongBySongId(List<Rec> list);

	List<type> findTypeByTypeId(List<RecSongList> typeList);


	List<SongList> findRecSongListByType(List<type> types, String userName);

}
