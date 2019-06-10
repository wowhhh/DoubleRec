package com.wyb.rec.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wyb.rec.dao.UserRecDao;
import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;

public class UserRecDaoImpl extends HibernateDaoSupport implements UserRecDao {

	//查询今日推荐是否计算完成，查询数据表中的数量以及日期
	@Override
	public boolean findRecTodayDone() {
		// TODO Auto-generated method stub
		 String hql_date="from Rec";
		
		List<Rec> list=  (List<Rec>) this.getHibernateTemplate().find(hql_date);
		if(list.size()!=0)
		{
			String date_mysql=list.get(0).getDate();//上次计算的日期
			
			//获取时间并保存
			String date_today=null;
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date_today=formatter.format(cal.getTime());//今天
			
			//判断是否为今天所计算的
			if(date_mysql.equals(date_today))
			{
				return true;//如果数据库中的日期和今天是一致的，就不用再计算了
			}
			return false;
		}
		
		return false;
	}
	
	//根据用户id去查询已经计算好的推荐的歌曲
	@Override
	public List<Rec> findRecSongByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from Rec where userId=?";
		List<Rec> list= (List<Rec>) this.getHibernateTemplate().find(hql, idUser);
		
		return list;
	}
	//根据用户id去查询已经计算好的推荐的类型
	@Override
	public List<RecSongList> findRecTypeByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		String hql="from RecSongList where userId=?";
		List<RecSongList> list=(List<RecSongList>) this.getHibernateTemplate().find(hql, idUser);
		return list;
	}
	
	//根据歌曲intid查询歌曲详细信息
	@Override
	public Song findSongBySongId(Integer songId) {
		// TODO Auto-generated method stub
		String hql="from Song where idsong=?";
		List<Song> songs=(List<Song>) this.getHibernateTemplate().find(hql, songId);
		
		return songs.get(0);
	}


	//根据歌单类型的id去查询已经计算好的推荐的类型的名字
	
	@Override
	public type findTypeByTypeId(Integer integer) {
		String hql="from type where idtype=?";
		List<type> types=(List<type>) this.getHibernateTemplate().find(hql, integer);
		
		return types.get(0);
	}
	
	//根据idtype查询歌单信息
	@Override
	public SongList findRecSongListByType(Integer idtype,String string) {
		// TODO Auto-generated method stub
		String hql="select distinct t.songLists from type as t where t.idtype= ?";
		List<SongList> songLists= (List<SongList>) this.getHibernateTemplate().find(hql, idtype);
		//循环取出一个此用户没有过行为的歌单，还需传入用户的name
		int index=0;
		SongList RecSongList=new SongList();
		while(index<songLists.size())
		{
			if(! string.equals(songLists.get(index).getListAuthor()))//如果不是当前用户创建的歌单
			{
				RecSongList=songLists.get(index);
				break;
			}
			index++;
		}
		return RecSongList;

	}
	

}
