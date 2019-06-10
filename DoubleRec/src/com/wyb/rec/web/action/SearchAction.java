package com.wyb.rec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.domain.Song;
import com.wyb.rec.service.SearchService;
import com.wyb.rec.utils.JSON.GetAndAnalyseJSON;

/**
 * ���ڸ�������
 * @author wyb
 *
 */
public class SearchAction extends ActionSupport {
	private String message;//�����û�Ҫ����������

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    private SearchService searchService;	//ע��service
	
	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	//Ĭ��ִ�еķ�����������ʵ���ǵ��ýӿڣ�����json�ļ���Ȼ����б��棬���������ĸ������Խ���ֱ�ӱ���
	//�����赥��ʱ�����������Ǹ���
	public String execute()
	{
		if(message!=null)
		{
		//����util��д�õ�GetAndAnalyseJSON
		GetAndAnalyseJSON getAndAnalyseJSON=new GetAndAnalyseJSON();
		//���ж�message�ǲ���Ϊ�ա����������js�н����жϰɣ����ǲ���js�м����ж���
		List<Song> songs= getAndAnalyseJSON.GetSearchSongs(message);//��ȡ�����ļ���
		
		//����ֵջ������ʾ��
		ActionContext.getContext().getValueStack().set("searchsongs", songs);
		//���浽���ݿ��У�����service
		if(songs!=null)
		{
		searchService.saveSearchSongs(songs);
		}
		return "song";
		}
		return "song";
	}
}
