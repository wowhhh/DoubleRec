package com.wyb.rec.service;

import java.util.List;

import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;

public interface UserRecService {

	boolean findRecTodayDone();//查询今日的推荐是否计算完成

	List<Rec> findRecSongByUserId(Integer idUser);//根据用户的id查询计算出来的推荐的歌曲

	List<RecSongList> findRecTypeByUserId(Integer idUser);

	List<Song> findSongBySongId(List<Rec> list);

	List<type> findTypeByTypeId(List<RecSongList> typeList);


	List<SongList> findRecSongListByType(List<type> types, String userName);

}
