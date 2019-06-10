package com.wyb.rec.dao;

import java.util.List;

import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;

public interface UserRecDao {

	boolean findRecTodayDone();

	List<Rec> findRecSongByUserId(Integer idUser);

	List<RecSongList> findRecTypeByUserId(Integer idUser);

	Song findSongBySongId(Integer songId);

	com.wyb.rec.domain.type findTypeByTypeId(Integer integer);


	SongList findRecSongListByType(Integer idtype, String string);


}
