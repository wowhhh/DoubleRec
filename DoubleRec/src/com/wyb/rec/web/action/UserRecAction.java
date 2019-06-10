package com.wyb.rec.web.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.CollaborationFiltering.CFaction;
import com.wyb.rec.domain.Rec;
import com.wyb.rec.domain.RecSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.UserRecService;

public class UserRecAction extends ActionSupport {
	
	//ע��service
	private UserRecService userRecService;
	//�ṩset����
	
	public void setUserRecService(UserRecService userRecService) {
		this.userRecService = userRecService;
	}
	
	//Ĭ��ִ�еķ����������Ƽ�����Ȼ�Ҳ������û�һ�����ִ���Ƽ�������ϵͳ�ļ�����̫���ˣ���һ����һ���Ƽ���
	//Ĭ��ִ�еķ����ͽ���ҳ��ĵ�������ʾ
	
	public String execute()
	{
		//1�������ǽ���session���жϣ����session �в��������û����Ǿ������û���¼
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		//2���ж�user�Ƿ�Ϊ�գ�����ǿգ��Ͳ����Ƽ�����ʾ��ɶ�û�Ҫ��¼
		
		if(user !=null)//����û���Ϊ��
		{
		//�ж����ݿ����Ƿ������ݣ�Ҳ���������û��Ľ����Ƽ��Ƿ��Ѿ��������
		boolean isRecDone=userRecService.findRecTodayDone();
		//����У����ѯ���ݲ����浽ֵջ
		if(isRecDone)//���յļ����Ѿ����
		{
			//���в�ѯ���û����Ƽ����û������¼���Ǳ��浽��session�е�
			List<Rec> list= userRecService.findRecSongByUserId(user.getIdUser());
			//�˼���Ŀǰ��ֻ�и�����id������Ҫ���ݸ���id����ѯ������ȫ����Ϣ���Ƚ���Щid������ʾ��
			ActionContext.getContext().getValueStack().set("IdSong", list);
			
			List<Song> songs=userRecService.findSongBySongId(list);//��service�н�����Ҫ����
			ActionContext.getContext().getValueStack().set("songs", songs);
			
			return "recsong";
		}
		//���û�У�����ת��GetRecAction ��
		//return "GetRec";
		}
		return "recsong";//�û�û��¼
	}
	
	//�赥�Ƽ�������
	public String SongListRec()
	{
		//1�������ǽ���session���жϣ����session �в��������û����Ǿ������û���¼
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
				
		//2���ж�user�Ƿ�Ϊ�գ�����ǿգ��Ͳ����Ƽ�����ʾ��ɶ�û�Ҫ��¼
				
		if(user !=null)//����û���Ϊ��
		{
			//���ü����Ƽ����Ƽ��������ֵջemmmmm����ʵ�Ƽ����Ǹ��������ͣ����Ƽ�������
			List<RecSongList> typeList=userRecService.findRecTypeByUserId(user.getIdUser());
			ActionContext.getContext().getValueStack().set("IdType", typeList);
			//���ݸ赥���͵�id ȥ��ѯ�赥���͵�����
			List<type> types=userRecService.findTypeByTypeId(typeList); 
			ActionContext.getContext().getValueStack().set("types", types);
			
			//��ѯÿ�������µĸ赥�����Ƽ�
			List<SongList> songLists=userRecService.findRecSongListByType(types,user.getUserName());
			//���صĸ赥���ܻ����ظ��ġ�������ѭ��ȥ���ɡ���ɾ�����Ա��е��ظ�����
			List<SongList> songLists2=new LinkedList<SongList>();
			
			for(int i=0;i<songLists.size();i++)
			{
				int flag=1;
				for(int j=i+1;j<songLists.size();j++)
				{
					if(songLists.get(i) == songLists.get(j))
					{
						flag=0;//���ظ���
					}
					
				}
				if(flag==1)
				{
					songLists2.add(songLists.get(i));
				}
			}
			//����ֵջ����ʾ�赥
			ActionContext.getContext().getValueStack().set("RecSongList", songLists2);
			return "recType";
		}
		return "recType";
	}

	//��ȡ�Ƽ����ݣ��Ƽ�����Ӧ���Ǽ�����˵ģ�Ӧ�����ø�����������ǵ�һ�ξͽ��м��㣬��֮��ֱ��ȡ
	public String GetRec()
	{
		CFaction cFaction=new CFaction();
		Map<Integer,Integer[]> user2songRecMatrix=new HashMap<Integer, Integer[]>();
		user2songRecMatrix=cFaction.run();//���ܷ���ֵ
		
		return null;
	}
}
