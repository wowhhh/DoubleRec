package com.wyb.rec.service;

import java.util.List;

import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.utils.PageBean;

public interface SongListService {

	boolean saveUserCollectSongList(int userId, String listId);

	boolean cancleUserCollectSongList(int userId, String listId);

	List<SongList> findOneSongListByListId(String listId);

	PageBean<Song> findDetailByListId(String listId, int page);

	List<type> findSongListTypeByListId(String listId);

	List<SongList> findRecSongListByType(int type_id, String listId);

	List<CollectSongList> isCollectThisSongList(int userId, String listId);

	List<CollectSongList> findCollectedByUserid(Integer idUser);

	List<SongList> findCollectedSongListByListIds(List<CollectSongList> collectSongLists);

	
}
