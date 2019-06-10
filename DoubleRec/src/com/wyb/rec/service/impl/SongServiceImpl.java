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
	//ע��dao
	private SongDao songDao;
	//set����
	public void setSongDao(SongDao songDao) {
		this.songDao = songDao;
	}
	//��ѯdiscoverSong��������ҳ��������ʾ
	@Override
	public List<Song> findDiscoverSong() {
		// TODO Auto-generated method stub
		return songDao.findDiscoverSong();
	}
	//��ѯnewSong ��������ҳ��������ʾ
	@Override
	public List<NewSong> findNewSong() {
		// TODO Auto-generated method stub
		return songDao.findNewSong();
	}
	//��ѯͶƱ�� ��������ҳ��������ʾ
	@Override
	public List<TopSong> findTopSong() {
		// TODO Auto-generated method stub
		return songDao.findTopSong();
	}
	//�����û��ղ�
	@Override
	public void saveUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		songDao.saveUserCollectSongId(idUser,songId);
	}
	//ɾ���ղؼ�¼
	@Override
	public void delUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		songDao.delUserCollectSongId(idUser,songId);
	}
	//��ѯ�û��ղظ���
	@Override
	public List<CollectSong> findUserCollectSong(int idUser) {
		// TODO Auto-generated method stub
		return songDao.findUserCollectSong(idUser);
	}
	//�����û�id��ѯ�û��ղع��ĸ���id
	@Override
	public List<CollectSong> findCollectedSongByUserid(Integer idUser) {
		// TODO Auto-generated method stub
		return songDao.findUserCollectSong(idUser);
	}
	//���ݸ���id���ϣ���ѯ������ϸ��Ϣ
	@Override
	public List<Song> findCollectedSongBySongIds(List<CollectSong> collectSongs) {
		// TODO Auto-generated method stub
		List<Song> songs=new LinkedList<Song>();
		int index=0;
		while(index<collectSongs.size())
		{
			//��ѯ
			Song song= songDao.findSongBySongId(collectSongs.get(index).getSongId());
			songs.add(song);
			index++;
		}
		return songs;
	}
	//���¸�����������
	@Override
	public void updateSongTimes(String songId) {
		// TODO Auto-generated method stub
		songDao.updateSongTimes(songId);
	}
	
}
