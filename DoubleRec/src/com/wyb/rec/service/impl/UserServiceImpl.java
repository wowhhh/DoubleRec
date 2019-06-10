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

//����Ĺ���
@Transactional
public class UserServiceImpl implements UserService {

	//ע��dao 
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//ҵ���ע���û����õķ���
	@Override
	public boolean regist(User user) {
		//��������м��ܵĴ���
		user.setUserPass(MD5Utils.md5(user.getUserPass()));
		//����ע��ʱ��,4.3,����null���в���
		user.setRegisterDate(null);
		//����ע���״̬
		//�ж��û��Ƿ����
		User user2=userDao.findByUserName(user.getUserName());
		//����dao
		if(user2==null)
		{
		userDao.save(user);
		return true;
		}
		return false;
	}
	
	//���û�����ѯ�û��ķ���
	@Override
	public User findByUsername(String username)
	{
		return userDao.findByUserName(username);
	}

	@Override
	public User login(User user) {
		//��������н���
		user.setUserPass(MD5Utils.md5(user.getUserPass()));
		return userDao.login(user);
	}

	

	//���ݱ�ǩ�����û�id����
	
	@Override
	public void SavaUserTaste(String string, Integer idUser) {
		// TODO Auto-generated method stub
		//�Ȳ�ѯ�˱�ǩ����id
		type type=userDao.findTypeIdByTypeName(string);
		int idtype=type.getIdtype();
		
		//userDao.SaveUserTaste(idtype,idUser);
		
		
		//����idtype��idUser�����Խ��б�����߸�����
		Connection connection;
		try {
			connection = GetConn.GetConn();
		
		PreparedStatement pStatement;
		String exist_u_sl_t="select * from userandsltype where idUser=? and idtype=?";
		pStatement=connection.prepareStatement(exist_u_sl_t);
		pStatement.setInt(1, idUser);
		pStatement.setInt(2, idtype);
		ResultSet resultSet3=pStatement.executeQuery();
		if(resultSet3.next())//��Ϊ��
		{
			//��iftype����+1
			String updatesql="update userandsltype set times=times+1 where idUser=? and idtype=?";
			pStatement=connection.prepareStatement(updatesql);
			pStatement.setInt(1, idUser);
			pStatement.setInt(2, idtype);
			pStatement.execute();
			pStatement.close();
			System.out.println(idUser+"��"+idtype+"�Ѹ���");
		}
		else {
			String insertsql="Insert Into userandsltype(idUser,idtype,times) values(?,?,1)";
			pStatement=connection.prepareStatement(insertsql);
			pStatement.setInt(1, idUser);
			pStatement.setInt(2, idtype);
			pStatement.execute();
			pStatement.close();
			System.out.println(idUser+"��"+idtype+"�Ѳ���");
		}
		pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�����û�id ��ѯ���û��йصı�ǩ
	@Override
	public List<UserAndSlType> findTasetedByUserId(int idUser) {
		// TODO Auto-generated method stub
		return userDao.findTasetedByUserId(idUser);
	}
	
	//ͨ�����ͱ�ǩid��ѯ���ͱ�ǩ������
	@Override
	public List<type> findTypeNameByTypeId(List<UserAndSlType> list) {
		// TODO Auto-generated method stub
		List<type> types=new LinkedList<type>();
		int index=0;
		while(index < list.size())
		{
			//����dao���в���
			types.add(userDao.findTypeNameByTypeId(list.get(index).getIdtype()));
			index++;
		}
		return types;
	}

	//�����û�id��ѯ�����ղصĸ���
	@Override
	public List<CollectSong> findUserCollectSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return userDao.findUserCollectSongByUserId(idUser);
	}
	
}
