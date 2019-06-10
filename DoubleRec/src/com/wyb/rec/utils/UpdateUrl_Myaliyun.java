package com.wyb.rec.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.wyb.rec.utils.SavaSongList.GetConn;
/**
 * 功能是更换歌曲图片以及歌词的连接为我云端服务器上
 * @author wyb
 *
 */
public class UpdateUrl_Myaliyun {
	//获取数据库连接，查询id，更新url ,img ，lrc
	@Test
	public void run()
	{
		//GetByTableName("topsong");
		GetByTableName("discoversong");
		GetByTableName("newsong");
	}
	
	
	public void GetByTableName(String tableName)
	{
		String sql_getSongId="select * from " + tableName;
		Statement statement;
		try {
			statement = GetConn.GetStat();
			ResultSet resultSet=statement.executeQuery(sql_getSongId);
			while(resultSet.next())
			{
				String songId=resultSet.getString(2);
				//设置songUrl_new,imageUrl_new,lrcUrl_new
				String songUrl_new="http://101.132.40.184:8080/DoubleRec/music/"+songId+".mp3";
				String imageUrl_new="http://101.132.40.184:8080/DoubleRec/image/"+songId+".jpg";
				String lrcUrl_new="http://101.132.40.184:8080/DoubleRec/lrc/"+songId+".txt";
				//调用
				UpdateNewUrl(songId, tableName, songUrl_new, imageUrl_new, lrcUrl_new);
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//不更新歌词
	
	//更新歌词
	public void UpdateNewUrl(String songId,String tableName, String songUrl_new,String imageUrl_new,String lrcUrl_new )
	{
		String songsql="UPDATE "+ tableName+ " SET SongUrl=?, SongPic=?, SongLrc=? WHERE SongId=?";
		Connection connection;
		try {
			connection = GetConn.GetConn();
		
		PreparedStatement pStatement=connection.prepareStatement(songsql);
		pStatement.setString(1, songUrl_new);
		pStatement.setString(2, imageUrl_new);
		pStatement.setString(3, lrcUrl_new);
		pStatement.setString(4, songId);
		
		//执行
		
		pStatement.execute();
		pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
