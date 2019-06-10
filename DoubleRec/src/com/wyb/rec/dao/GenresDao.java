package com.wyb.rec.dao;

import java.util.List;

import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;

public interface GenresDao {

	List<type> findPart();

	List<type> findAll();

	List<SongList> findPageByCid(Integer cid, int begin, int limit);

	int findCountCid(Integer cid);

	String findTypeNameByCid(Integer cid);

	List<SongList> findDetailByListId();

	int findCountByListId(String listId);

	List<Song> findPageByListId(String listId, int begin, int limit);

	List<SongList> findOneSongListByListId(String listId);

	List<type> findSongListTypeByListId(String listId);

	List<SongList> findRecSongListByType(int type_id);

}
