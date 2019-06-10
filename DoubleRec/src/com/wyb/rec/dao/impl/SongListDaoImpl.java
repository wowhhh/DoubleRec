package com.wyb.rec.dao.impl;

import java.util.List;

import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.SongListDao;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.utils.PageHibernateCallback;

public class SongListDaoImpl extends HibernateDaoSupport implements SongListDao {

	
	
	@Override
	public boolean saveUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		//�Ȳ�ѯ���ղؼ�¼�Ƿ���ڣ�Ȼ���ٽ��б��棬���ò�ѯ�Ƿ���ڣ���Ϊҳ���Ѿ�ȷ���˴˸赥�Ƿ��ղ�
		String sql="from CollectSongList where idUser =? and ListId = ? ";
		List<CollectSongList> list= (List<CollectSongList>) this.getHibernateTemplate().find(sql,userId, listId);
		//�ж��Ƿ�Ϊ��
		if(list.size()==0)//Ϊ��
		{
			CollectSongList collectSongList=new CollectSongList();
			collectSongList.setIdUser(userId);
			collectSongList.setListId(listId);
			this.getHibernateTemplate().save(collectSongList);//����
			return true;
		}
		return false;
	}

	//ɾ���û��ղصĸ赥��¼����ȡ������Ȼ��Զ������ɾ��
	@Override
	public boolean cancleUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		//�Ȼ�ȡcollect
		String gethql="from CollectSongList where idUser =? and ListId = ? ";
		//CollectSongList collectSongList=(CollectSongList) this.getHibernateTemplate().find(gethql, userId,listId);
		//��ȡlist����
		List<CollectSongList> list= (List<CollectSongList>) this.getHibernateTemplate().find(gethql, userId,listId);
		CollectSongList collectSongList=list.get(0);
		//�ٽ���ɾ��
		if(collectSongList !=null)//�������������ݾͽ���ɾ��
		{
		this.getHibernateTemplate().delete(collectSongList);
		}
		return false;
	}
	
	/******���·������ڸ赥����ҳ�����ʾ
	 * 
	 * ******/

	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="from SongList as sl where sl.ListId=?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql,listId);
		return songLists;
	}

	@Override
	public int findCountByListId(String listId) {
		// TODO Auto-generated method stub
		String hql="select  sl.songs from SongList as sl  where sl.ListId= ?";
		List<SongList> list= (List<SongList>) this.getHibernateTemplate().find(hql, listId);
		int temp=list.size();
		return temp;
	}

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
	
	//�������Ͳ��Ƽ�����
	@Override
	public List<SongList> findRecSongListByType(int type_id) {
		// TODO Auto-generated method stub
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql, type_id);
		return songLists;
	}
	//�鿴���û��Ƿ��ղ��˴˸赥
	@Override
	public List<CollectSongList> isCollectThisSongList(int userId, String listId) {
		String gethql="from CollectSongList where idUser =? and ListId = ? ";
		//CollectSongList collectSongList=(CollectSongList) this.getHibernateTemplate().find(gethql, userId,listId);
		//��ȡlist����
		List<CollectSongList> list= (List<CollectSongList>) this.getHibernateTemplate().find(gethql, userId,listId);
		return list;
	}
	
	//��ѯ�ղع��ĸ赥��id
	@Override
	public List<CollectSongList> findCollectedByUserid(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from CollectSongList where idUser=?";
		List<CollectSongList> collectSongLists=(List<CollectSongList>) this.getHibernateTemplate().find(hql, idUser);
		return collectSongLists;
	}
	//���ݸ赥��ListId����ѯ�赥��Ϣ
	@Override
	public SongList findCOllectedSongListByListIds(CollectSongList collectSongList) {
		String listId=collectSongList.getListId();
		String hql="from SongList as sl where sl.ListId=?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql,listId);
		return songLists.get(0);
	}

	

}
