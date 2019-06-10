package com.wyb.rec.service;

import java.util.List;

import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.DiscoverSong;
import com.wyb.rec.domain.NewSong;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.TopSong;

/**
 * 音乐管理的接口
 * @author wyb
 *
 */
public interface SongService {

	List<Song> findDiscoverSong();

	List<NewSong> findNewSong();

	List<TopSong> findTopSong();

	void saveUserCollectSongId(int idUser, String songId);

	void delUserCollectSongId(int idUser, String songId);

	List<CollectSong> findUserCollectSong(int idUser);

	List<CollectSong> findCollectedSongByUserid(Integer idUser);

	List<Song> findCollectedSongBySongIds(List<CollectSong> collectSongs);

	void updateSongTimes(String songId);


}
