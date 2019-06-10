package com.wyb.rec.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.aspectj.internal.lang.reflect.PointcutBasedPerClauseImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.domain.DiscoverSong;
import com.wyb.rec.domain.NewSong;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.TopSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.service.SongService;

/**
 * ������ҳ���ʵ�Action,����Ҫ�������ݣ��Ͳ���ʵ��ģ��������
 * @author wyb
 *
 */
public class IndexAction extends ActionSupport {

	//ע�����ֵ�service ,������ҳ�������ݵļ���
	private SongService songService;
	//set����
	public void setSongService(SongService songService) {
		this.songService = songService;
	}

	//ִ�еķ�����ҳ�ķ���
	/**
	 * ���̣����մ������в�ѯ������ֵջ���ؼ���
	 */
	public String execute()
	{
		//��ѯdiscoverSong
		List<Song> dSList= songService.findDiscoverSong();
		//��list���ϱ��浽ֵջ��
		ActionContext.getContext().getValueStack().set("dSList", dSList);
		
		//��ѯnewSong
		List<NewSong> nSList=songService.findNewSong();
		//��list���ϱ�����ֵջ��
		ActionContext.getContext().getValueStack().set("nSList", nSList);
		
		//��ѯtopSong
		List<TopSong> tSList=songService.findTopSong();
		ActionContext.getContext().getValueStack().set("tSList", tSList);
		ServletActionContext.getRequest().getSession().setAttribute("nowplay", tSList);
		//�ж��û��Ƿ���ڣ�������û��Ļ����ղ����ֵ�session�Ͳ�����Ϊ��
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null)
		{
		ServletActionContext.getRequest().getSession()
		.setAttribute("collectSongs", null);
		}
		
		return "index";
	}
	
	
}
