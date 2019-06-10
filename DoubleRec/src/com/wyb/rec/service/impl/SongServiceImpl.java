package com.wyb.rec.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.SongDao;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.DiscoverSong;
import com.wyb.rec.domain.NewSong;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.TopSong;
import com.wyb.rec.service.SongService;

/**
 * 
 * @author wyb
 *
 */
@Transactional
public class SongServiceImpl implements SongService {
	//注入dao
	private SongDao songDao;
	//set方法
	public void setSongDao(SongDao songDao) {
		this.songDao = songDao;
	}
	//查询discoverSong，用于首页的音乐显示
	@Override
	public List<Song> findDiscoverSong() {
		// TODO Auto-generated method stub
		return songDao.findDiscoverSong();
	}
	//查询newSong ，用于首页的音乐显示
	@Override
	public List<NewSong> findNewSong() {
		// TODO Auto-generated method stub
		return songDao.findNewSong();
	}
	//查询投票送 ，用于首页的音乐显示
	@Override
	public List<TopSong> findTopSong() {
		// TODO Auto-generated method stub
		return songDao.findTopSong();
	}
	//保存用户收藏
	@Override
	public void saveUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		songDao.saveUserCollectSongId(idUser,songId);
	}
	//删除收藏记录
	@Override
	public void delUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		songDao.delUserCollectSongId(idUser,songId);
	}
	//查询用户收藏歌曲
	@Override
	public List<CollectSong> findUserCollectSong(int idUser) {
		// TODO Auto-generated method stub
		return songDao.findUserCollectSong(idUser);
	}
	//根据用户id查询用户收藏过的歌曲id
	@Override
	public List<CollectSong> findCollectedSongByUserid(Integer idUser) {
		// TODO Auto-generated method stub
		return songDao.findUserCollectSong(idUser);
	}
	//根据歌曲id集合，查询歌曲详细信息
	@Override
	public List<Song> findCollectedSongBySongIds(List<CollectSong> collectSongs) {
		// TODO Auto-generated method stub
		List<Song> songs=new LinkedList<Song>();
		int index=0;
		while(index<collectSongs.size())
		{
			//查询
			Song song= songDao.findSongBySongId(collectSongs.get(index).getSongId());
			songs.add(song);
			index++;
		}
		return songs;
	}
	//更新歌曲收听次数
	@Override
	public void updateSongTimes(String songId) {
		// TODO Auto-generated method stub
		songDao.updateSongTimes(songId);
	}
	
}
