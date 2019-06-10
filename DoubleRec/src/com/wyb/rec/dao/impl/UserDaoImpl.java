package com.wyb.rec.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.UserDao;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;

import java.util.List;
/**
 * 用户管理的Dao的实现类
 * @author wyb
 *
 */
public class UserDaoImpl  extends HibernateDaoSupport implements UserDao{

	//dao中保存用户的方法，用户注册
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}
	
	//按用户名查询是否有该用户
	public User findByUserName(String username)
	{
		String hql="from User where username=?";
		//Hql查询
		List<User> list= (List<User>) this.getHibernateTemplate().find(hql, username);
		
		if(list!=null && list.size()>0)//查到了
		{
			return list.get(0);
		}
		else{
			return null;
		}
	}

	//用户登录的方法
	@Override
	public User login(User user) {
		//String hql="from User where userName= ? and userPass =? and userState = ?";
		
		//List<User> list= (List<User>) this.getHibernateTemplate().find(hql,user.getUserName(),user.getUserPass(),1);
		String hql="from User where userName= ? and userPass =? ";
		
		List<User> list= (List<User>) this.getHibernateTemplate().find(hql,user.getUserName(),user.getUserPass());
		
		//判断是否查到了用户
		if(list!=null && list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	
	//根据类型名字查找类型id
	@Override
	public type findTypeIdByTypeName(String string) {
		// TODO Auto-generated method stub
		String hql="from type where typeName=?";
		List<type> types= (List<type>) this.getHibernateTemplate().find(hql, string);
		return types.get(0);
	}

	//保存用户与类型
	@Override
	public void SaveUserTaste(int idtype, Integer idUser) {
		// TODO Auto-generated method stub
		String select="from ";
	}
	
	//根据用户id查询用户有关的标签
	@Override
	public List<UserAndSlType> findTasetedByUserId(int idUser) {
		// TODO Auto-generated method stub
		String hql="from UserAndSlType where idUser=?";
		List<UserAndSlType> list=(List<UserAndSlType>) this.getHibernateTemplate().find(hql, idUser);
		return list;
	}
	
	//根据类型id查找名字
	@Override
	public type findTypeNameByTypeId(Integer idtype) {
		// TODO Auto-generated method stub
		String hql="from type where idtype=?";
		List<type> types=(List<type>) this.getHibernateTemplate().find(hql, idtype);
		return types.get(0);
	}

	//根据用户id，查询收藏记录
	@Override
	public List<CollectSong> findUserCollectSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from CollectSong where idUser=?";
		List<CollectSong> collectSongs=(List<CollectSong>) this.getHibernateTemplate().find(hql, idUser);
		return collectSongs;
	}

}
