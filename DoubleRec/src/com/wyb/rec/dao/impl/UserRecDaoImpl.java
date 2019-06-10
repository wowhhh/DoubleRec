package com.wyb.rec.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.UserRecDao;
import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;

public class UserRecDaoImpl extends HibernateDaoSupport implements UserRecDao {

	//��ѯ�����Ƽ��Ƿ������ɣ���ѯ���ݱ��е������Լ�����
	@Override
	public boolean findRecTodayDone() {
		// TODO Auto-generated method stub
		 String hql_date="from Rec";
		
		List<Rec> list=  (List<Rec>) this.getHibernateTemplate().find(hql_date);
		if(list.size()!=0)
		{
			String date_mysql=list.get(0).getDate();//�ϴμ��������
			
			//��ȡʱ�䲢����
			String date_today=null;
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date_today=formatter.format(cal.getTime());//����
			
			//�ж��Ƿ�Ϊ�����������
			if(date_mysql.equals(date_today))
			{
				return true;//������ݿ��е����ںͽ�����һ�µģ��Ͳ����ټ�����
			}
			return false;
		}
		
		return false;
	}
	
	//�����û�idȥ��ѯ�Ѿ�����õ��Ƽ��ĸ���
	@Override
	public List<Rec> findRecSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from Rec where userId=?";
		List<Rec> list= (List<Rec>) this.getHibernateTemplate().find(hql, idUser);
		
		return list;
	}
	//�����û�idȥ��ѯ�Ѿ�����õ��Ƽ�������
	@Override
	public List<RecSongList> findRecTypeByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from RecSongList where userId=?";
		List<RecSongList> list=(List<RecSongList>) this.getHibernateTemplate().find(hql, idUser);
		return list;
	}
	
	//���ݸ���intid��ѯ������ϸ��Ϣ
	@Override
	public Song findSongBySongId(Integer songId) {
		// TODO Auto-generated method stub
		String hql="from Song where idsong=?";
		List<Song> songs=(List<Song>) this.getHibernateTemplate().find(hql, songId);
		
		return songs.get(0);
	}


	//���ݸ赥���͵�idȥ��ѯ�Ѿ�����õ��Ƽ������͵�����
	
	@Override
	public type findTypeByTypeId(Integer integer) {
		String hql="from type where idtype=?";
		List<type> types=(List<type>) this.getHibernateTemplate().find(hql, integer);
		
		return types.get(0);
	}
	
	//����idtype��ѯ�赥��Ϣ
	@Override
	public SongList findRecSongListByType(Integer idtype,String string) {
		// TODO Auto-generated method stub
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql, idtype);
		//ѭ��ȡ��һ�����û�û�й���Ϊ�ĸ赥�����贫���û���name
		int index=0;
		SongList RecSongList=new SongList();
		while(index<songLists.size())
		{
			if(! string.equals(songLists.get(index).getListAuthor()))//������ǵ�ǰ�û������ĸ赥
			{
				RecSongList=songLists.get(index);
				break;
			}
			index++;
		}
		return RecSongList;

	}
	

}
