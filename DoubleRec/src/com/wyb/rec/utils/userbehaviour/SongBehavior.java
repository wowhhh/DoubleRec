package com.wyb.rec.utils.userbehaviour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.wyb.rec.utils.SavaSongList.GetConn;

public class SongBehavior {
	
	private PreparedStatement prestat;
	private Connection connection;
	
	@Test
	public void test()
	{
		DelScore("北鹤", "002uSyaM1p1MyX");
	}
	
	//传入值为用户id以及歌曲id，类型有，收藏，取消收藏，听歌,flag用于判断用户是收藏还是播放，1收藏，0播放
	public void AddScore(String UserName,String SongId,int flag)
	{
		//先查询是否存在，如果存在，追加两分，不存在插入
		//得到歌曲名字和播放地址
				
		//查询先获得用户的id以及歌曲的id
				int idSong=0;			
				int idUser=0;
				
				try{
					connection=GetConn.GetConn();
				//查询歌曲的id,预编译，设置变量，查询，处理结果
				String SelectSongId="Select idsong from song where SongId= ?";
				prestat=connection.prepareStatement(SelectSongId);
				prestat.setString(1, SongId);
				java.sql.ResultSet resultSet1=prestat.executeQuery();
				if(resultSet1.next())
				{
					idSong=resultSet1.getInt(1);
				}
				prestat.close();
				//查询用户的id，预编译，设置变量，查询，处理结果
				String SelectUserId="Select iduser from user where userName =? ";
				prestat=connection.prepareStatement(SelectUserId);
				prestat.setString(1, UserName);
				java.sql.ResultSet resultSet2=prestat.executeQuery();
				if(resultSet2.next())
				{
					idUser=resultSet2.getInt(1);
				}
				prestat.close();
				//获取id完毕,生成1-10的随机数
				int rate=0;
				if(flag==1)
				{
				rate=2;
				}
				else
				{
					 rate =1;
				}
				//写插入语句,缺陷，无法判断用户对此歌曲的评分是否存在，所以先进行查询，如果存在先不进行插入
				String selectRate="select * from rating where idUser=? and idSong=?";//写查询语句，判断此用户对此评分是否存在
				prestat=connection.prepareStatement(selectRate);
				prestat.setInt(1, idUser);
				prestat.setInt(2, idSong);
				java.sql.ResultSet resultSet3=prestat.executeQuery(); //执行查询，判断返回结果是否为空，为空就继续
				if( resultSet3.next())
				{
					System.out.println("用户："+UserName+"对音乐："+SongId+"的评分已经存在了！！！，进行加"+rate+"分");
					int rated=resultSet3.getInt(4);//获取以前的评分
					rate+=rated;//当前评分
					prestat.close();
					//插入新的评分
					String InsertRate="update rating set rate=? where idUser=? and idSong=?";
					prestat=connection.prepareStatement(InsertRate);
					prestat.setInt(1, rate);
					prestat.setInt(2, idUser);
					prestat.setInt(3, idSong);
					prestat.executeUpdate();
					prestat.close();
				}
				else{//查询为空再继续
				prestat.close();//先关闭上次的使用
				String InsertRate="Insert Into rating(idUser,idSong,rate) values (?,?,?)";
				prestat=connection.prepareStatement(InsertRate);
				prestat.setInt(1, idUser);
				prestat.setInt(2, idSong);
				prestat.setInt(3, rate);
				prestat.executeUpdate();
				prestat.close();
				System.out.println("用户："+UserName+"对音乐："+SongId+"的评分为："+rate);
				}
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
	}
	
	public void DelScore(String UserName,String SongId)
	{
		//如果存在，减去两分，不会存在不存在的情况
		//先查询是否存在，如果存在，追加两分，不存在插入
				//得到歌曲名字和播放地址
						
				//查询先获得用户的id以及歌曲的id
						int idSong=0;			
						int idUser=0;
						
						try{
							connection=GetConn.GetConn();
						//查询歌曲的id,预编译，设置变量，查询，处理结果
						String SelectSongId="Select idsong from song where SongId= ?";
						prestat=connection.prepareStatement(SelectSongId);
						prestat.setString(1, SongId);
						java.sql.ResultSet resultSet1=prestat.executeQuery();
						if(resultSet1.next())
						{
							idSong=resultSet1.getInt(1);
						}
						prestat.close();
						//查询用户的id，预编译，设置变量，查询，处理结果
						String SelectUserId="Select iduser from user where userName =? ";
						prestat=connection.prepareStatement(SelectUserId);
						prestat.setString(1, UserName);
						java.sql.ResultSet resultSet2=prestat.executeQuery();
						if(resultSet2.next())
						{
							idUser=resultSet2.getInt(1);
						}
						prestat.close();
						//获取id完毕,生成1-10的随机数
						int rate=2;
						//删除分数更新

						//写插入语句,缺陷，无法判断用户对此歌曲的评分是否存在，所以先进行查询，如果存在先不进行插入
						String selectRate="select * from rating where idUser=? and idSong=?";//写查询语句，判断此用户对此评分是否存在
						prestat=connection.prepareStatement(selectRate);
						prestat.setInt(1, idUser);
						prestat.setInt(2, idSong);
						java.sql.ResultSet resultSet3=prestat.executeQuery(); //执行查询，判断返回结果是否为空，为空就继续
						if( resultSet3.next())
						{
						int rated=resultSet3.getInt(4);//获取以前的评分
						rate=rated-rate;//当前评分
						prestat.close();
						//插入新的评分
						String InsertRate="update rating set rate=? where idUser=? and idSong=?";
						prestat=connection.prepareStatement(InsertRate);
						prestat.setInt(1, rate);
						prestat.setInt(2, idUser);
						prestat.setInt(3, idSong);
						prestat.executeUpdate();
						prestat.close();
						}
						}
						catch (Exception e) {
							// TODO: handle exception
						}
	}
}
