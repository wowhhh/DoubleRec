package com.wyb.rec.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.GenresDao;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.utils.PageHibernateCallback;
//�̳�HibernateDaoSupport
public class GenresDaoImpl extends HibernateDaoSupport implements GenresDao  {

	//��ѯ���ֱ�ǩ�����ڷ������ʾ
	@Override
	public List<type> findPart() {
		// TODO Auto-generated method stub
		String hql="from type where idtype between 9 and 24";
		List<type> list= (List<type>) this.getHibernateTemplate().find(hql);
		return list;
	}
	//��ѯȫ����ǩ������ȫ��ҳ��������ʾ
	@Override
	public List<type> findAll() {
		// TODO Auto-generated method stub
		String hql="from type";
		List<type> list=(List<type>)this.getHibernateTemplate().find(hql);
		return list;
	}
	//���ݵ���ķ���id����ѯ�÷����������ĸ赥��Ŀ
	@Override
	public int findCountCid(Integer cid) {
		// TODO Auto-generated method stub
		//��ѯ�赥��Ŀ
		//Long count=0L;
		//String hql="select count(*) from songlist as sl where sl.types.idtype=?";
	   //	List<Long> temp2=(List<Long>) this.getHibernateTemplate().find(hql, cid);
		//��Ѱ˼������countҲ�а���ֱ�Ӳ������Ȼ��list.size()��Ҳ�ܳ�
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, cid);
		int temp=list.size();
		return temp;
	}
	//��ѯĳtype_id��ǩ�µ����и赥
	@Override
	public List<SongList> findPageByCid(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		//distinct��ȥ���ظ������ݣ����������ɣ�
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		//��ͨ��ȫ����ѯ��List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, cid);
		//��ҳ��д������ҳ��ѯ
	    List<SongList>	list=this.getHibernateTemplate().execute(new PageHibernateCallback<SongList>(hql, new Object[]{cid}, begin, limit));
		//List<SongList> songLists=(List<SongList>) this.getHibernateTemplate().find(hql, cid);
	    if(list !=null && list.size()>0)
	    {
	    	return list;
	    }
		return null;
	}
	//�����û�����ı�ǩֵ������ѯ�ñ�ǩ����������
	@Override
	public String findTypeNameByCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql="from type  where idtype = ?";
		List<type> type=(List<com.wyb.rec.domain.type>) this.getHibernateTemplate().find(hql, cid);
		return type.get(0).typeName;
	}
	//�����û������SongList������ѯListId��Ӧ����ϸ�赥��Ϣ
	@Override
	public List<SongList> findDetailByListId() {
		// TODO Auto-generated method stub
		return null;
	}
	//���ݴ����ListIdȥ��ѯ��������Ŀ
	@Override
	public int findCountByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="select  sl.songs from SongList as sl  where sl.ListId= ?";
		List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, listId);
		int temp=list.size();
		return temp;
	}
	//���ݴ����listId�Լ�begin limit ȥ���з�ҳ���ظ���
	@Override
	public List<Song> findPageByListId(String listId, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="select distinct sl.songs from SongList as sl where sl.ListId= ?";
		 List<Song>	list=this.getHibernateTemplate().execute(new PageHibernateCallback<Song>(hql, new Object[]{listId}, begin, limit));
			//List<SongList> songLists=(List<SongList>) this.getHibernateTemplate().find(hql, cid);
		    if(list !=null && list.size()>0)
		    {
		    	return list;
		    }
			return null;
	}
	
	//����ListId��ѯ��ϸ��Ϣ�������赥��
	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="from SongList as sl where sl.ListId=?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql,listId);
		return songLists;
	}
	
	//���ݴ����ListId,��ѯ�赥������
	@Override
	public List<type> findSongListTypeByListId(String listId) {
		//distinct��ȥ���ظ������ݣ����������ɣ�
		String hql="select distinct sl.types from SongList as sl where sl.ListId= ?";
		//��ͨ��ȫ����ѯ��List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, cid);
		//��ҳ��д������ҳ��ѯ
		List<type> list=  (List<type>) this.getHibernateTemplate().find(hql, listId);
		//List<SongList> songLists=(List<SongList>) this.getHibernateTemplate().find(hql, cid);
		if(list !=null && list.size()>0)
			{
			   return list;
			}
		return null;
	}
	//���ݸ赥�����ѯ�Ƽ��ĸ赥
	@Override
	public List<SongList> findRecSongListByType(int type_id) {
		// TODO Auto-generated method stub
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
	    List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql, type_id);
		return songLists;
	}

	
}
