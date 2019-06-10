package com.wyb.rec.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.websocket.Session;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.SongDao;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.DiscoverSong;
import com.wyb.rec.domain.NewSong;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.TopSong;
/**
 * ���ֳ־ò�
 * @author wyb
 *
 */
public class SongDaoImpl extends HibernateDaoSupport implements SongDao {

	//��ѯDiscoverSong
	@Override
	public List<Song> findDiscoverSong() {
		//ʹ������������ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Song.class);
		String hql="from Song order by Songtimes desc";
		//���ò�ѯ����
		
		//ִ�в�ѯ
		List<Song> list= (List<Song>) this.getHibernateTemplate().find(hql);
	
		//��ѯ12��
		List<Song> newlist=new LinkedList<Song>();
		int index=0;
		while(index<=11)
		{
			newlist.add(list.get(index));
			index++;
		}
		//ʹ��execute����
		return newlist;
	}

	//��ѯnewSong
	@Override
	public List<NewSong> findNewSong() {
		String hql="from NewSong";
		//ִ�в�ѯ������list����
		List<NewSong> list=(List<NewSong>) this.getHibernateTemplate().find(hql);
		return list;
	}

	//��ѯtopSong
	@Override
	public List<TopSong> findTopSong() {
		String hql="from TopSong";
		//ִ�в�ѯ������list����
		List<TopSong> list=(List<TopSong>) this.getHibernateTemplate().find(hql);
		return list;
	}
	
	//����
	@Override
	public void saveUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		//�½�����Ȼ�󱣴�
		CollectSong collectSong=new CollectSong();
		collectSong.setIdUser(idUser);
		collectSong.setSongId(songId);
		this.getHibernateTemplate().save(collectSong);
	}
	
	//ɾ���û��ղؼ�¼
	@Override
	public void delUserCollectSongId(int idUser, String songId) {
		// TODO Auto-generated method stub
		//�Ȳ�ѯ����ɾ��
		String hql="from CollectSong where idUser=? and SongId=?";
		List<CollectSong> collectSongs= (List<CollectSong>) this.getHibernateTemplate().find(hql, idUser,songId);
		
		this.getHibernateTemplate().delete(collectSongs.get(0));
	}
	
	//��ѯ�û��ղص����и���
	@Override
	public List<CollectSong> findUserCollectSong(int idUser) {
		// TODO Auto-generated method stub
		String hql="from CollectSong where idUser=?";
		List<CollectSong> collectSongs= (List<CollectSong>) this.getHibernateTemplate().find(hql, idUser);
		return collectSongs;
	}
	
	//����SongId��ѯ��������
	@Override
	public Song findSongBySongId(String songId) {
		// TODO Auto-generated method stub
		String hql ="from Song where SongId=?";
		List<Song> songs= (List<Song>) this.getHibernateTemplate().find(hql, songId);
		return songs.get(0);
	}

	@Override
	public void updateSongTimes(String songId) {
		// TODO Auto-generated method stub
		//�Ȳ�ѯ
		String hql="from Song where SongId=?";
		List<Song> songs=(List<Song>) this.getHibernateTemplate().find(hql, songId);
		Song song=songs.get(0);
		System.out.println("��ǰ�����ֵĲ��Ŵ�����"+song.getSongtimes());
		System.out.println("���º�����ֵĲ��Ŵ�����"+song.getSongtimes()+1);
		song.setSongtimes(song.getSongtimes()+1);
		//�ٸ���
		System.out.println(song.getSongtimes());
		this.getHibernateTemplate().update(song);
	}


	
	

}
