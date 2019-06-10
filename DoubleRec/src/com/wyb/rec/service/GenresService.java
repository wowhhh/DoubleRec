package com.wyb.rec.service;

import java.util.List;

import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.utils.PageBean;

public interface GenresService {

	List<type> findPart();

	List<type> findAll();

	PageBean<SongList> findByPageCid(Integer cid, int page);

	PageBean<Song> findDetailByListId(String listId, int page);

	List<SongList> findOneSongListByListId(String listId);

	List<type> findSongListTypeByListId(String listId);

	List<SongList> findRecSongListByType(int type_id, String listId);

	

}
