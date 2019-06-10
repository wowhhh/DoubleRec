package com.wyb.rec.web.action;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.GenresService;
import com.wyb.rec.utils.PageBean;

import freemarker.ext.util.ModelFactory;

/**
 * ���ڸ赥���ص�action�������赥���͵ļ��أ�ȫ�����͵ļ��أ����������������ĸ赥�ļ��أ������赥��Ϣ�ļ���
 * ���ܻ����û��赥�ĸ��²�ѯ֮���
 * @author wyb
 *
 */
public class GenresAction extends ActionSupport implements ModelDriven<SongList> {
	

	private SongList songList;

	private String ListId;
	
	//Listid��set����
	
	public String getListId() {
		return ListId;
	}

	@Override
	public SongList getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setListId(String listId) {
		ListId = listId;
	}

	//ע��genres��service
	private  GenresService genresService;
	//����cid�������id
	private Integer cid;
	//���յ�ǰ��ҳ��
	private int page;
	

	public void setPage(int page) {
		this.page = page;
	}



	//cid��set����
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	//genresService��set����
	public void setGenresService(GenresService genresService) {
		this.genresService = genresService;
	}
	
	//����GenresĬ��ִ�е�Action
	public String execute()
	{
		//������Ҫ�ǽ��в�ѯ����ѯ��ǩ����ǩ�Ǻ͸赥��Զ��
		List<type> glist= genresService.findPart();//���Ҳ��֣�11-24
		//������ֵջ��,�汾2�����浽session��
		//ActionContext.getContext().getValueStack().set("glist", glist);
		ActionContext.getContext().getSession().put("glist", glist);
		
		//�����ݿ���в�ѯ��Ȼ�󱣴浽ֵջ�С��汾2�����浽session�����
		List<type> agList= genresService.findAll();
		//����ѯ���ļ��ϱ��浽ֵջ��
		ActionContext.getContext().getSession().put("agList", agList);
		//ActionContext.getContext().getValueStack().set("agList", agList);
		//�ȶ���һ���յ�pageBean
		//PageBean<SongList> pageBean=null;
		//ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "genres";//��������ҳ��
	}
	/*
	 �����û����ȥ�󣬷����id����ѯ��ǰid��Ӧ�µĸ赥
	 */
	public String one()
	{
		//����cid,Ȼ�����cid��ѯ��cid�µĸ赥
		PageBean<SongList> pageBean= genresService.findByPageCid(cid,page);//����cid�Ĳ�ѯ���з�ҳ������pageBean
		//��ȡpagebean����pagebean���뵽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//��tyeName���浽ֵջ��
		//ActionContext.getContext().put("typeName", typeName);;
		return "genres_one";
	}

	/**
	 �����û����Ҫ��ʾ�ĸ赥�����������ʾ
	 */
	public String detail()//�������⣬�赥������ʾ������
	{
		//ֵ��ע����ǣ����˻�ȡ�����������⣬����Ҫ��ȡ��ListId�赥�������Լ�����
		List<SongList> songLists= genresService.findOneSongListByListId(ListId);
		ActionContext.getContext().getValueStack().set("SongListByListId", songLists.get(0));
		//����SongList��ListId��ֻ���ڽ��水��������ĸ�ʽ�ſ��Լ���
		//System.out.println(songList.getListId());
		//����service����ʵ���ò�ѯ�赥����Ϣ�˰ɣ�ֱ�Ӳ�ѯ��Զ�ĸ���
		PageBean<Song> pageBeanSong= genresService.findDetailByListId(ListId,page);//���ݸ赥��id���в�ѯ�����з�ҳ������pagebean
		ActionContext.getContext().getValueStack().set("pageBeanSong", pageBeanSong);
		
		//���Ӳ�����������ȡ����ʾ�˸赥�ı�ǩ����Զ�Ĳ�ѯ������ListId
		List<type> SongList_type=genresService.findSongListTypeByListId(ListId);
		//��ѯ�ɹ�����SongList_type ����ֵջ��
		ActionContext.getContext().getValueStack().set("SongListType", SongList_type);
		
		//���Ӳ�����������ʾ��һ����ǩͬ�����������
		String type_first=SongList_type.get(0).getTypeName();
		int type_id=SongList_type.get(0).getIdtype();
		//����type��id���������������ѯһ�Σ���ѯ�������ɣ���ʾ��� ��ȡ���ظ���,��ѯ��type,listId����ȥ����ǰ�赥
		List<SongList> RecSongList= genresService.findRecSongListByType(type_id,ListId);
		//����ֵջ
		ActionContext.getContext().getValueStack().set("RecSongList", RecSongList);
		//���Ӳ����������жϴ˸赥�Ƿ��û��ղأ�Ҫ���ж��Ƿ����û���¼
		
		return "songlist_detail";
	}
	

}
