package com.wyb.rec.dao;

import java.util.List;

import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;

public interface SongListDao {

	boolean saveUserCollectSongList(int userId, String listId);

	boolean cancleUserCollectSongList(int userId, String listId);

	List<SongList> findOneSongListByListId(String listId);

	int findCountByListId(String listId);

	List<Song> findPageByListId(String listId, int begin, int limit);

	List<type> findSongListTypeByListId(String listId);

	List<SongList> findRecSongListByType(int type_id);

	List<CollectSongList> isCollectThisSongList(int userId, String listId);

	List<CollectSongList> findCollectedByUserid(Integer idUser);

	SongList findCOllectedSongListByListIds(CollectSongList collectSongList);

	
}
