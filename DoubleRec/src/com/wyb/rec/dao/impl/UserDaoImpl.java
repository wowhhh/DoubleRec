package com.wyb.rec.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.UserDao;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;

import java.util.List;
/**
 * �û������Dao��ʵ����
 * @author wyb
 *
 */
public class UserDaoImpl  extends HibernateDaoSupport implements UserDao{

	//dao�б����û��ķ������û�ע��
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}
	
	//���û�����ѯ�Ƿ��и��û�
	public User findByUserName(String username)
	{
		String hql="from User where username=?";
		//Hql��ѯ
		List<User> list= (List<User>) this.getHibernateTemplate().find(hql, username);
		
		if(list!=null && list.size()>0)//�鵽��
		{
			return list.get(0);
		}
		else{
			return null;
		}
	}

	//�û���¼�ķ���
	@Override
	public User login(User user) {
		//String hql="from User where userName= ? and userPass =? and userState = ?";
		
		//List<User> list= (List<User>) this.getHibernateTemplate().find(hql,user.getUserName(),user.getUserPass(),1);
		String hql="from User where userName= ? and userPass =? ";
		
		List<User> list= (List<User>) this.getHibernateTemplate().find(hql,user.getUserName(),user.getUserPass());
		
		//�ж��Ƿ�鵽���û�
		if(list!=null && list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	
	//�����������ֲ�������id
	@Override
	public type findTypeIdByTypeName(String string) {
		// TODO Auto-generated method stub
		String hql="from type where typeName=?";
		List<type> types= (List<type>) this.getHibernateTemplate().find(hql, string);
		return types.get(0);
	}

	//�����û�������
	@Override
	public void SaveUserTaste(int idtype, Integer idUser) {
		// TODO Auto-generated method stub
		String select="from ";
	}
	
	//�����û�id��ѯ�û��йصı�ǩ
	@Override
	public List<UserAndSlType> findTasetedByUserId(int idUser) {
		// TODO Auto-generated method stub
		String hql="from UserAndSlType where idUser=?";
		List<UserAndSlType> list=(List<UserAndSlType>) this.getHibernateTemplate().find(hql, idUser);
		return list;
	}
	
	//��������id��������
	@Override
	public type findTypeNameByTypeId(Integer idtype) {
		// TODO Auto-generated method stub
		String hql="from type where idtype=?";
		List<type> types=(List<type>) this.getHibernateTemplate().find(hql, idtype);
		return types.get(0);
	}

	//�����û�id����ѯ�ղؼ�¼
	@Override
	public List<CollectSong> findUserCollectSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from CollectSong where idUser=?";
		List<CollectSong> collectSongs=(List<CollectSong>) this.getHibernateTemplate().find(hql, idUser);
		return collectSongs;
	}

}
