package com.wyb.rec.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.SongListDao;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.SongListService;
import com.wyb.rec.utils.PageBean;
@Transactional
public class SongListServiceImpl implements SongListService {
	private SongListDao songListDao;

	
	
	public void setSongListDao(SongListDao songListDao) {
		this.songListDao = songListDao;
	}



	//ʵ���û���������ղظ赥�Ĳ����������ֵΪ�û���id�Լ��ղصĸ赥��ListId
	//δʵ�֣��û��ղظ赥��Ҫ�Ը赥�����еĸ���������+2�����������м�2����֮�����
	//δʵ�֣��û�ȡ���ղأ��۳�����
	@Override
	public boolean saveUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		return songListDao.saveUserCollectSongList(userId,listId);
	}


	//ʵ���û�ȡ���ղز�����
	@Override
	public boolean cancleUserCollectSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		return songListDao.cancleUserCollectSongList(userId, listId);
	}
	
	
	/***
	 * ���·������ڼ���һ���赥ҳ���������Ϣ
	 * 1���赥��Ϣ
	 * 2����ҳ��ʾ��������
	 * 3�������Ƽ�
	 * 4���赥�Ƿ��ղ�
	 */
	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		return songListDao.findOneSongListByListId(listId);
	}



	@Override
	public PageBean<Song> findDetailByListId(String listId, int page) {
		PageBean<Song> pageBean=new PageBean<Song>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿһҳ��ʾ�ļ�¼��
		int limit=10;
		pageBean.setLimit(limit);
		//�����ܵļ�¼��
		int totalCount=0;
		totalCount=songListDao.findCountByListId(listId);//���ݸ赥��listId��ѯ�����ĸ�������Ŀ
		pageBean.setTotalCount(totalCount);
		//�����ܵ�ҳ��
		int totalpage=0;
		if(totalCount % limit == 0)
		{
			totalpage=totalCount/limit;
		}
		else {	
			totalpage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalpage);
		//ÿҳ��ʾ�����ݼ���
		//�����￪ʼ
		int begin= (page-1)*limit;
		
		List<Song> list=songListDao.findPageByListId(listId,begin,limit);
		pageBean.setList(list);
		//�������͵�����
		//String typeName=genresDao.findTypeNameByCid(cid);
		//pageBean.setTypeName(typeName);
		return pageBean;
	}



	@Override
	public List<type> findSongListTypeByListId(String listId) {
		// TODO Auto-generated method stub
		return songListDao.findSongListTypeByListId(listId);
	}



	@Override
	public List<SongList> findRecSongListByType(int type_id, String listId) {
		// TODO Auto-generated method stub
				List<SongList> songLists= songListDao.findRecSongListByType(type_id);
				//�ж�������뵱ǰlistId�ظ��Ĳ�����
				List<SongList> recSongList = new LinkedList<SongList>();
				for(int i=0;i<6;i++)
				{
					SongList songList=songLists.get(i);
					String listId_2=songList.getListId();
					if(! listId .equals(listId_2) )//����
					{
						recSongList.add(songList);
					}
				}
				return recSongList;
	}

	
	//�����жϴ��û��Ƿ��ղ��˴˸赥
	@Override
	public List<CollectSongList> isCollectThisSongList(int userId, String listId) {
		// TODO Auto-generated method stub
		return songListDao.isCollectThisSongList(userId,listId);
	}


	//��ѯ�û��ղظ赥����Ϣ
	@Override
	public List<CollectSongList> findCollectedByUserid(Integer idUser) {
		// TODO Auto-generated method stub
		return songListDao.findCollectedByUserid(idUser);
	}

	//���ݴ����ListID���ϣ�����SongLists
	@Override
	public List<SongList> findCollectedSongListByListIds(List<CollectSongList> collectSongLists) {
		// TODO Auto-generated method stub
		List<SongList> songLists=new LinkedList<SongList>();
		int index=0;
		while(index<collectSongLists.size())
		{
			SongList songList=songListDao.findCOllectedSongListByListIds(collectSongLists.get(index));
			songLists.add(songList);
			index++;
		}
		return songLists;
	}



	
}
