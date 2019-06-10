package com.wyb.rec.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.SearchDao;
import com.wyb.rec.domain.Song;
import com.wyb.rec.utils.SavaSongList.GetConn;

public class SearchDaoImpl extends HibernateDaoSupport implements SearchDao {

	//保存所有歌曲
	@Override
	public void saveSearchSongs(List<Song> songs) {
		// TODO Auto-generated method stub
		int index=0;
		while(index<songs.size())
		{
			Song temp=songs.get(index);
			Song song=new Song();
			song.setSongId(temp.getSongId());
			song.setSongLrc(temp.getSongLrc());
			song.setSongName(temp.getSongName());
			song.setSongPic(temp.getSongPic());
			song.setSongSinger(temp.getSongSinger());
			song.setSongTime(temp.getSongTime());
			song.setSongUrl(temp.getSongUrl());
			try
			{
				//先查询此歌曲是否已经保存
			String hql="from Song where SongId=?";
			List<Song> songs2= (List<Song>) this.getHibernateTemplate().find(hql, song.getSongId());
			if(songs2.size()==0)
			{
			//this.getHibernateTemplate().save(song);
				//用JDBC进行插入
				String insertsong="   insert into song (SongId, SongName, SongTime, SongSinger, SongUrl, SongPic, SongLrc) values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement=GetConn.GetConn().prepareStatement(insertsong);
				preparedStatement.setString(1,song.getSongId() );
				preparedStatement.setString(2, song.getSongName());
				preparedStatement.setString(3, song.getSongTime());
				preparedStatement.setString(4, song.getSongSinger());
				preparedStatement.setString(5, song.getSongUrl());
				preparedStatement.setString(6, song.getSongPic());
				preparedStatement.setString(7,song.getSongLrc() );
				preparedStatement.executeUpdate();
				preparedStatement.close();
				GetConn.CloseConn();
			}
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
			index++;
		}
	}

}
