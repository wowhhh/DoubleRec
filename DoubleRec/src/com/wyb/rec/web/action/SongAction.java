package com.wyb.rec.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.User;
import com.wyb.rec.service.SongService;
import com.wyb.rec.utils.userbehaviour.SongBehavior;

public class SongAction extends ActionSupport {
	
	private String SongId;//���ڽ����û�Ҫ�ղصĸ�����id
	private SongService songService;//ע��songservice
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}


	//set����
	public void setSongService(SongService songService) {
		this.songService = songService;
	}
	
	public String getSongId() {
		return SongId;
	}

	public void setSongId(String songId) {
		SongId = songId;
	}
	
	//�û��Ը������ղأ�ȡ���ղأ���ӵ��Լ������Ĳ����б�
	
	//�û��Ը�������ղأ�����תҳ��
	  public String  collect() throws UnsupportedEncodingException
	  {
		//��session�л�ȡuser
			User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
			if(user!=null)
			{
				int idUser=user.getIdUser();
				//��������idUser��SongId ��Ҫ���ľ��ǲ��뵽���ݿ���
 				songService.saveUserCollectSongId(idUser,SongId);
				String str = "ȡ���ղ�";  
		        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		        //���ϲ����Ѿ������ղ���Ϣ�Ĳ��룬����Ҫ�������ղظ�������ʾ���ղظ�����ʾ���������ÿ��������ʾ���涼Ӧ�ý���
		        //�Ȳ�ѯ��Ȼ����ÿ��������ʾ�����жϵ�ǰid�Ƿ��ڲ�ѯ�����ղ��б��С�
		        //���session�����ڼ����ղغ����ʾ����
		      //�������������ݵ�ɾ�����������²�ѯ�ղ���Ϣ��Ȼ����������session
		        List<CollectSong> collectSongs= songService.findUserCollectSong(idUser);
		        //���浽session
		        ServletActionContext.getRequest().getSession().setAttribute("collectSongs", collectSongs);
		        
		        //�û���Ϊ���ּ�¼
		        SongBehavior songBehavior=new SongBehavior();
		        songBehavior.AddScore(user.getUserName(), SongId,1);
		        return SUCCESS;  
				
			}
		  return SUCCESS;
	  }
	  
	  //�û�ȡ���ղ�
	  public String  canclecollect() throws UnsupportedEncodingException
	  {
		//��session�л�ȡuser
			User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
			if(user!=null)
			{
				int idUser=user.getIdUser();
				//��������idUser��SongId ��Ҫ���ľ��Ǵ����ݿ���ɾ������
 				songService.delUserCollectSongId(idUser,SongId);
				String str = "�ղ�";  
		        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		        //�������������ݵ�ɾ�����������²�ѯ�ղ���Ϣ��Ȼ����������session
		        List<CollectSong> collectSongs= songService.findUserCollectSong(idUser);
		        //���浽session
		        ServletActionContext.getRequest().getSession().setAttribute("collectSongs", collectSongs);  //�û���Ϊ���ּ�¼
		        SongBehavior songBehavior=new SongBehavior();
		        songBehavior.DelScore(user.getUserName(), SongId);
		        return SUCCESS;  
				
			}
		  return SUCCESS;
	  }
	  //��ѯ�û��ղص����и���
	  public String UserCollected()
	  {
		  //��session�л�ȡuser
		  User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		  if(user!=null)
		  {
			  List<CollectSong> collectSongs = songService.findCollectedSongByUserid(user.getIdUser());
				//�ȱ��浽ֵջ������һ��
				//ActionContext.getContext().getValueStack().set("collectSongLists",collectSongLists);
				//���ݻ�ȡ����id����ѯ����Song����Ϣ
				List<Song> songs= songService.findCollectedSongBySongIds(collectSongs);
				//����ֵջ��������ʾ
				ActionContext.getContext().getValueStack().set("collectedSongs",songs );
				return "collectedSongs";
		  }
		  return "collectedSongs";
	  }
	  //�û��������֮�����Ϊ��Ϣ
	  public String play() throws UnsupportedEncodingException
	  {
		  System.out.println("diiddi");
		  //��ȡ�û���
		  User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		  if(user!=null)
		  {
			SongBehavior songBehavior=new SongBehavior();
			//��ȡSongId
			  songBehavior.AddScore(user.getUserName(), SongId,0);
			  //����Song�е�Songtimes
			  //�������־���
			  //�û�������ʷ
		  }
		  songService.updateSongTimes(SongId);
		  String str = "ok";  
		  inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		  return SUCCESS; 
	  }

}
