package com.wyb.rec.service.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.support.DaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.UserDao;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.UserService;
import com.wyb.rec.utils.MD5Utils;
import com.wyb.rec.utils.SavaSongList.GetConn;

//事务的管理
@Transactional
public class UserServiceImpl implements UserService {

	//注入dao 
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//业务层注册用户所用的方法
	@Override
	public boolean regist(User user) {
		//对密码进行加密的处理
		user.setUserPass(MD5Utils.md5(user.getUserPass()));
		//设置注册时间,4.3,先用null进行测试
		user.setRegisterDate(null);
		//设置注册的状态
		//判断用户是否存在
		User user2=userDao.findByUserName(user.getUserName());
		//调用dao
		if(user2==null)
		{
		userDao.save(user);
		return true;
		}
		return false;
	}
	
	//按用户名查询用户的方法
	@Override
	public User findByUsername(String username)
	{
		return userDao.findByUserName(username);
	}

	@Override
	public User login(User user) {
		//对密码进行解密
		user.setUserPass(MD5Utils.md5(user.getUserPass()));
		return userDao.login(user);
	}

	

	//根据标签名和用户id保存
	
	@Override
	public void SavaUserTaste(String string, Integer idUser) {
		// TODO Auto-generated method stub
		//先查询此标签名的id
		type type=userDao.findTypeIdByTypeName(string);
		int idtype=type.getIdtype();
		
		//userDao.SaveUserTaste(idtype,idUser);
		
		
		//有了idtype和idUser，可以进行保存或者更新了
		Connection connection;
		try {
			connection = GetConn.GetConn();
		
		PreparedStatement pStatement;
		String exist_u_sl_t="select * from userandsltype where idUser=? and idtype=?";
		pStatement=connection.prepareStatement(exist_u_sl_t);
		pStatement.setInt(1, idUser);
		pStatement.setInt(2, idtype);
		ResultSet resultSet3=pStatement.executeQuery();
		if(resultSet3.next())//不为空
		{
			//对iftype进行+1
			String updatesql="update userandsltype set times=times+1 where idUser=? and idtype=?";
			pStatement=connection.prepareStatement(updatesql);
			pStatement.setInt(1, idUser);
			pStatement.setInt(2, idtype);
			pStatement.execute();
			pStatement.close();
			System.out.println(idUser+"的"+idtype+"已更新");
		}
		else {
			String insertsql="Insert Into userandsltype(idUser,idtype,times) values(?,?,1)";
			pStatement=connection.prepareStatement(insertsql);
			pStatement.setInt(1, idUser);
			pStatement.setInt(2, idtype);
			pStatement.execute();
			pStatement.close();
			System.out.println(idUser+"的"+idtype+"已插入");
		}
		pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据用户id 查询与用户有关的标签
	@Override
	public List<UserAndSlType> findTasetedByUserId(int idUser) {
		// TODO Auto-generated method stub
		return userDao.findTasetedByUserId(idUser);
	}
	
	//通过类型标签id查询类型标签的名字
	@Override
	public List<type> findTypeNameByTypeId(List<UserAndSlType> list) {
		// TODO Auto-generated method stub
		List<type> types=new LinkedList<type>();
		int index=0;
		while(index < list.size())
		{
			//调用dao进行查找
			types.add(userDao.findTypeNameByTypeId(list.get(index).getIdtype()));
			index++;
		}
		return types;
	}

	//根据用户id查询所有收藏的歌曲
	@Override
	public List<CollectSong> findUserCollectSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return userDao.findUserCollectSongByUserId(idUser);
	}
	
}
