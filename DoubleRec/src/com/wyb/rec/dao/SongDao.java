package com.wyb.rec.dao;

import java.util.List;

import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.DiscoverSong;
import com.wyb.rec.domain.NewSong;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.TopSong;

public interface SongDao {

	List<Song> findDiscoverSong();

	List<NewSong> findNewSong();

	List<TopSong> findTopSong();

	void saveUserCollectSongId(int idUser, String songId);

	void delUserCollectSongId(int idUser, String songId);

	List<CollectSong> findUserCollectSong(int idUser);

	Song findSongBySongId(String songId);

	void updateSongTimes(String songId);


}
