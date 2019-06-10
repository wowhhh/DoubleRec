package com.wyb.rec.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.httpclient.NTCredentials;
import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.GenresDao;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.GenresService;
import com.wyb.rec.utils.PageBean;

@Transactional
public class GenresServiceImpl  implements GenresService{
	//ע��genresdao
	private GenresDao genresDao;
	//�ṩset����
	public void setGenresDao(GenresDao genresDao) {
		this.genresDao = genresDao;
	}
	
	//��ѯ���ֱ�ǩ�����ڷ���ҳ��������ʾ
	@Override
	public List<type> findPart() {
		
		return genresDao.findPart();
	}
	//��ѯȫ����ǩ������ȫ���������ʾ
	@Override
	public List<type> findAll() {
		// TODO Auto-generated method stub
		return genresDao.findAll();
	}

	//���ݷ����id ��ȥ��ѯ��Ӧ�ĸ赥��ע���ҳ
	@Override
	public PageBean<SongList> findByPageCid(Integer cid, int page) {
		PageBean<SongList> pageBean=new PageBean<SongList>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿһҳ��ʾ�ļ�¼��
		int limit=12;
		pageBean.setLimit(limit);
		//�����ܵļ�¼��
		int totalCount=0;
		totalCount=genresDao.findCountCid(cid);//�������͵�cid��ѯ�����ĸ赥������
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
		
		List<SongList> list=genresDao.findPageByCid(cid,begin,limit);
		pageBean.setList(list);
		//�������͵�����
		String typeName=genresDao.findTypeNameByCid(cid);
		pageBean.setTypeName(typeName);
		return pageBean;
	}
	//���ݸ赥��ListId��ȥ��ѯ����
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
		totalCount=genresDao.findCountByListId(listId);//���ݸ赥��listId��ѯ�����ĸ�������Ŀ
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
		
		List<Song> list=genresDao.findPageByListId(listId,begin,limit);
		pageBean.setList(list);
		//�������͵�����
		//String typeName=genresDao.findTypeNameByCid(cid);
		//pageBean.setTypeName(typeName);
		return pageBean;
	}

	//����ListId��ѯһ���赥����Ϣ
	@Override
	public List<SongList> findOneSongListByListId(String listId) {
		// TODO Auto-generated method stub
		return genresDao.findOneSongListByListId(listId);
	}

	//���ݴ���赥��id����ѯ�赥�����ͣ�emmm��ʵ��������򵥰�
	@Override
	public List<type> findSongListTypeByListId(String listId) {
		// TODO Auto-generated method stub
		return genresDao.findSongListTypeByListId(listId);
	}
	
	//���ݸ赥���Ͳ�ѯͬ��赥
	@Override
	public List<SongList> findRecSongListByType(int type_id, String listId) {
		// TODO Auto-generated method stub
		List<SongList> songLists= genresDao.findRecSongListByType(type_id);
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
	
	
	//�����û����Ҫ�鿴��SongList��ListId��ȥ��ѯ��صĸ赥��������ʾ
	/*@Override
	public List<SongList> findDetailByListId() {
		// TODO Auto-generated method stub
		
		return genresDao.findDetailByListId();
	}*/

	


	
}
