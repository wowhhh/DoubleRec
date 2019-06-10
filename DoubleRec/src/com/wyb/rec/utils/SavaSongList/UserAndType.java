package com.wyb.rec.utils.SavaSongList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.ObjectUtils.Null;
import org.junit.Test;

/**
 * 此类用于统计现有数据库中，用户听过的歌单的所属类型，统计每个用户接触过的歌单
 * @author wyb
 *
 */
public class UserAndType {
	/**
	 * 具体步骤：读取一个用户，提取其有过行为的所有歌单，对所有歌单提取其类型，然后将用户与类型插入表中
	 * 并且先判断是否存在，如果存在+1，不存在新建
	 */
	private String[] user_name=new String[1000];
	private int[] user_id=new int[1000]; 
	
	private Connection connection=null;
	private Statement statement=null;
	private PreparedStatement pStatement=null;
	
	@Test
	public void run()
	{
		GetUser();
		int index=1;
		while(user_id[index]!=0)
		{
			GetSongList(user_id[index], user_name[index]);
			index++;
		}
	}
	
	//获取用户,下标从1开始
	public void GetUser()
	{
		//获取链接
		try {
			String sql="select * from user";
			statement=GetConn.GetStat();
			ResultSet resultSet= statement.executeQuery(sql);
			int index=1;
			while (resultSet.next()) {
				user_name[index]=resultSet.getString(2);
				user_id[index]=resultSet.getInt(1);
				index++;
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//根据用户id，获取有过行为的歌单id，刚开始写的时候，song1_name没有清空，所以歌单类型是每次都在累加
	public void GetSongList(int user_id,String user_name)
	{
		
		try {
			String[] songl_name=new String[1000];
			String sql="select * from songlist where ListAuthor= ?";
			connection=GetConn.GetConn();
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, user_name);
			ResultSet resultSet=pStatement.executeQuery();
			int index=1;
			while(resultSet.next())
			{
				songl_name[index]=resultSet.getString(2);
				index++;
			}
			pStatement.close();
			
			for(int i=1;songl_name[i]!=null;i++)//循环每个歌单
			{
			//再根据歌单id和用户id进行查询此歌单对应的类型并插入userandsltype
			String sql2="select * from songlistandtype where ListId= ? ";
			pStatement=connection.prepareStatement(sql2);
			pStatement.setString(1, songl_name[i]);
			ResultSet resultSet2=pStatement.executeQuery();
				
				//再次循环进行插入
				while(resultSet2.next())
				{
					int idtype=resultSet2.getInt(3);
					//先查询此用户与此id是否存在于表userandsltype中
					String exist_u_sl_t="select * from userandsltype where idUser=? and idtype=?";
					pStatement=connection.prepareStatement(exist_u_sl_t);
					pStatement.setInt(1, user_id);
					pStatement.setInt(2, idtype);
					ResultSet resultSet3=pStatement.executeQuery();
					if(resultSet3.next())//不为空
					{
						//对iftype进行+1
						String updatesql="update userandsltype set times=times+1 where idUser=? and idtype=?";
						pStatement=connection.prepareStatement(updatesql);
						pStatement.setInt(1, user_id);
						pStatement.setInt(2, idtype);
						pStatement.execute();
						pStatement.close();
					}
					else {
						String insertsql="Insert Into userandsltype(idUser,idtype,times) values(?,?,1)";
						pStatement=connection.prepareStatement(insertsql);
						pStatement.setInt(1, user_id);
						pStatement.setInt(2, idtype);
						pStatement.execute();
						pStatement.close();
					}
					pStatement.close();
				}
				pStatement.close();
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//根据歌单id和用户id 
}
