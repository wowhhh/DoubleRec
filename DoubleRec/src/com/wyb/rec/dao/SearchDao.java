package com.wyb.rec.dao;

import java.util.List;

import com.wyb.rec.domain.Song;

public interface SearchDao {

	void saveSearchSongs(List<Song> songs);

}
