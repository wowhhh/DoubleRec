package com.wyb.rec.web.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wyb.rec.domain.CollectSongList;
import com.wyb.rec.domain.Song;
import com.wyb.rec.domain.SongList;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.SongListService;
import com.wyb.rec.utils.CollectFlag;
import com.wyb.rec.utils.PageBean;
/**
 * ����赥��action,�ղ�
 * @author wyb
 *
 */
public class SongListAction extends ActionSupport implements ModelDriven<SongList> {

	private String  ListId;//���ڽ��յ������ĸ赥id
	//ע��service
	private SongListService songListService;
	
	private int page;
	
	private InputStream inputStream;
	
	//
	
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setSongListService(SongListService songListService) {
		this.songListService = songListService;
	}

	public void setListId(String listId) {
		ListId = listId;
	}

	@Override
	public SongList getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 �����û����Ҫ��ʾ�ĸ赥�����������ʾ
	 */
	public String detail()//�������⣬�赥������ʾ������
	{
		//1:ֵ��ע����ǣ����˻�ȡ�����������⣬����Ҫ��ȡ��ListId�赥�������Լ�����
		List<SongList> songLists= songListService.findOneSongListByListId(ListId);
		ActionContext.getContext().getValueStack().set("SongListByListId", songLists.get(0));
		//����SongList��ListId��ֻ���ڽ��水��������ĸ�ʽ�ſ��Լ���
		//System.out.println(songList.getListId());
		//2:����service����ʵ���ò�ѯ�赥����Ϣ�˰ɣ�ֱ�Ӳ�ѯ��Զ�ĸ���
		PageBean<Song> pageBeanSong= songListService.findDetailByListId(ListId,page);//���ݸ赥��id���в�ѯ�����з�ҳ������pagebean
		ActionContext.getContext().getValueStack().set("pageBeanSong", pageBeanSong);
		
		//3:���Ӳ�����������ȡ����ʾ�˸赥�ı�ǩ����Զ�Ĳ�ѯ������ListId
		List<type> SongList_type=songListService.findSongListTypeByListId(ListId);
		//��ѯ�ɹ�����SongList_type ����ֵջ��
		ActionContext.getContext().getValueStack().set("SongListType", SongList_type);
		
		//4:���Ӳ�����������ʾ��һ����ǩͬ�����������
		String type_first=SongList_type.get(0).getTypeName();
		int type_id=SongList_type.get(0).getIdtype();
		//����type��id���������������ѯһ�Σ���ѯ�������ɣ���ʾ��� ��ȡ���ظ���,��ѯ��type,listId����ȥ����ǰ�赥
		List<SongList> RecSongList= songListService.findRecSongListByType(type_id,ListId);
		//����ֵջ
		ActionContext.getContext().getValueStack().set("RecSongList", RecSongList);
		//5:���Ӳ����������жϴ˸赥�Ƿ��û��ղأ�Ҫ���ж��Ƿ����û���¼
		//��ȡsession,�ж��Ƿ����û���¼��
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user !=null)//�������û���¼�������ִ�д˲���
		{
			int userId=user.getIdUser();
		    List<CollectSongList> list= songListService.isCollectThisSongList(userId,ListId);
		    if(list.size()>0)//������û��Ѿ�������赥�����˸ı�
		    {
		    	//������һ�£����Ǳ����ѯ���Ľ����session�ɣ�Ȼ������ж�
		    	ServletActionContext.getRequest().getSession().setAttribute("iscollectsonglist",list.get(0));
		    }
		}
		
		return "songlist_detail";
	}
	
	public String getListId() {
		return ListId;
	}

	/************
	 * ��������ղغ�ȡ���ղأ��û��Ƿ��¼����Ϊ�Ѿ���js�н������ж�
	 * ����Ҫ�����Ƿ��ղسɹ���ȡ���ղسɹ����жϣ���Ϊ�Ѿ�ȷ������ʾ�ղص�ʱ�����ݿ��е�ȷ�����ڴ��ղ���Ϣ
	 * ���ȡ���ղص�ʱ�����ݿ��е�ȷ�Ѿ�����˴��ղ���Ϣ
	 * @throws UnsupportedEncodingException 
	 * ****************/
	public String canclecollect2() throws UnsupportedEncodingException
	{	
		//��session�л�ȡuser
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		if(user!=null)//����Ѿ���¼
		{
			int userId=user.getIdUser();
			//Ŀǰ�����û���id�Լ��赥��id���������ʱ��Ϳ��Ե���service����ɾ����
			boolean cancle=  songListService.cancleUserCollectSongList(userId,ListId);
			//�������
			//���response������ҳ�����
				//HttpServletResponse response= ServletActionContext.getResponse();
			 //response.setContentType("text/html;charset=UTF-8");//���ñ���
				
				//�����жϣ�����ղسɹ��������ֱ����ʾȡ���ղ�
				
				//	response.getWriter().println("<font color='red'>ȡ���ղ�</font>");
			 String str = "�ղ�";  
		        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));

		        //����һ���ǣ�ȡ��iscollectsonglist����Ϊȡ���ղ�֮��session������������ʾ����ı�
		       ServletActionContext.getRequest().getSession().setAttribute("iscollectsonglist", null); 
		        return SUCCESS;  
				
			//	return NONE;
			 
			 //��������û�취�õ�ListId���Ǿ�������ִ�в����ղ���Ϣ֮���ˢ�°�
			 /*CollectFlag collectFlag=new CollectFlag();
				collectFlag.setListId(ListId.toString());//null
				collectFlag.setUserId(user.getIdUser());
				
				ServletActionContext.getRequest().getSession()
				.setAttribute("CollectFlag", collectFlag);//��user����session��*/
				//return "Getdetail";
		}
		
		return SUCCESS;
	}
	
	//��¼�û����ղأ���Ҫ��ȡ�Ĳ������û���id����session���ã����赥��id��ҳ�洫��
	public String collect2() throws IOException
	{
			//��session�л�ȡuser
			User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
			
			if(user!=null)//����Ѿ���¼
			{
				CollectSongList collectSongList=new CollectSongList();
				int userId=user.getIdUser();
				//Ŀǰ�����û���id�Լ��赥��id���������ʱ��Ϳ��Ե���service���б�����
				 songListService.saveUserCollectSongList(userId,ListId);
				//�������
				//���response������ҳ�����
					//HttpServletResponse response= ServletActionContext.getResponse();
				 //response.setContentType("text/html;charset=UTF-8");//���ñ���
					
					//�����жϣ�����ղسɹ��������ֱ����ʾȡ���ղ�
					
					//	response.getWriter().println("<font color='red'>ȡ���ղ�</font>");
				 	String str = "ȡ���ղ�";  
			        inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			        //���session�����ڼ����ղغ����ʾ����
			        
			        List<CollectSongList> collectSongLists = songListService.findCollectedByUserid(user.getIdUser());
			      //�û��ղص�������Ϣ���浽ֵջ��
					 ServletActionContext.getRequest().getSession().setAttribute("collectedSongLists", collectSongLists);
			        return SUCCESS;  
					
				//	return NONE;
				 
				 //��������û�취�õ�ListId���Ǿ�������ִ�в����ղ���Ϣ֮���ˢ�°�
				 /*CollectFlag collectFlag=new CollectFlag();
					collectFlag.setListId(ListId.toString());//null
					collectFlag.setUserId(user.getIdUser());
					
					ServletActionContext.getRequest().getSession()
					.setAttribute("CollectFlag", collectFlag);//��user����session��*/
					//return "Getdetail";
			}
			
			return SUCCESS;
		}
	
	//��ѯ�û��ղع��ĸ赥����ȡ�û�id��Ȼ��
	public String UserCollected()
	{
		//���Ի�ȡ���û�id�͸赥�ĳ�id
		//��session�л�ȡuser
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
			List<CollectSongList> collectSongLists = songListService.findCollectedByUserid(user.getIdUser());
			//�ȱ��浽ֵջ������һ��
			//ActionContext.getContext().getValueStack().set("collectSongLists",collectSongLists);
			//���ݻ�ȡ����id����ѯ����SongList����Ϣ
			List<SongList> songLists= songListService.findCollectedSongListByListIds(collectSongLists);
			//����ֵջ��������ʾ
			ActionContext.getContext().getValueStack().set("collectedSonglists",songLists );
		
			//�û��ղص�������Ϣ���浽ֵջ��
			 ServletActionContext.getRequest().getSession().setAttribute("collectedSongLists", collectSongLists);
		        
		}
		return "GetCollect";
	}
		
		/***********���º���û�õ�������*************/
	
	//��¼�û����ղأ���Ҫ��ȡ�Ĳ������û���id����session���ã����赥��id��ҳ�洫��
	public String collect() throws IOException
	{
		//��session�л�ȡuser
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		if(user!=null)//����Ѿ���¼
		{
			int userId=user.getIdUser();
			//Ŀǰ�����û���id�Լ��赥��id���������ʱ��Ϳ��Ե���service���б�����
			 songListService.saveUserCollectSongList(userId,ListId);
			//�������
			//���response������ҳ�����
				HttpServletResponse response= ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=UTF-8");//���ñ���
				
				//�����жϣ�����ղسɹ��������ֱ����ʾȡ���ղ�
				
			response.getWriter().println("<font color='red'>ȡ���ղ�</font>");
					
				return NONE;
			 
			 //��������û�취�õ�ListId���Ǿ�������ִ�в����ղ���Ϣ֮���ˢ�°�
			 /*CollectFlag collectFlag=new CollectFlag();
				collectFlag.setListId(ListId.toString());//null
				collectFlag.setUserId(user.getIdUser());
				
				ServletActionContext.getRequest().getSession()
				.setAttribute("CollectFlag", collectFlag);//��user����session��*/
				//return "Getdetail";
		}
		
		return NONE;//��ת�������action
	}
	
	//�����ղ�ͼ�ꡱ��Ϊ�����ղء���֮���Ե����г�������Ϊ���û����ѵ�¼״̬�����ȥ��ʱ�򣬻���Ҫ�жϴ˸赥�Ƿ��ղ�
	public String Collected()
	{
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//
		CollectFlag collectFlag=new CollectFlag();
		collectFlag.setListId(ListId);//null
		collectFlag.setUserId(user.getIdUser());
		
		ServletActionContext.getRequest().getSession()
		.setAttribute("CollectFlag", collectFlag);//��user����session��
		return "songlist_detail";
	}
	
	
	
	//��ѯ�赥�Ƿ��Ѿ��ղء�
	public String isCollect()
	{
		return null;
	}
}
