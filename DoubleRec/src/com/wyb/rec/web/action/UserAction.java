package com.wyb.rec.web.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wyb.rec.CollaborationFiltering.CFaction;
import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;
import com.wyb.rec.service.UserService;
import com.wyb.rec.utils.userandsltype;
import com.wyb.rec.utils.SavaSongList.GetConn;

/**
 * �û������Action
 * @author wyb
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	//ģ������ʹ�õĶ���
	private User user=new User();
	//private String selected[];
	private String[] selected;
	private InputStream inputStream;
	
	/*public void setSelected(String[] selected) {
		this.selected = selected;
	}
	public String[] getSelected() {
		return selected;
	}*/

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//ע��Service
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * �û�ע���һ�ַ�����ֱ�ӵ�¼
	 */
	public String regist(){
		boolean flag= userService.regist(user);	
		
		if(flag)
		{
		//��¼�ɹ�,���û���Ϣ����session�������ҳ�����ת 
		ServletActionContext.getRequest().getSession()
			.setAttribute("existUser", user);//��user����session��
		//ҳ����ת����ҳ
		//���˽���ҳ�����ת����Ҫ��ѯ�û����ղؼ�¼��Ȼ�󱣴浽session �У������û�������ղصĻ�����׷��
		List<CollectSong> collectSongs= userService.findUserCollectSongByUserId(user.getIdUser());
		//��collectSongs ���浽session
		//ServletActionContext.getRequest().getSession()
		//.setAttribute("collectSongs", collectSongs);
		ActionContext.getContext().getSession().put("collectSongs", collectSongs);
		return "registSuccess";
		}
		else
		{
			return "signup";
		}
	}
	
	/**
	 * �����û���ζ�ķ��������ж��Ƿ��¼��js���жϣ�,��ʾ���ݿ����Ѿ���¼���û��й���Ϊ�Ŀ�ζ
	 * @throws UnsupportedEncodingException 
	 */
	public String taste() throws UnsupportedEncodingException
	{
		//Ŀǰselected���������Ѿ��������û��ύ������
		//��ʼѭ����������ÿһ������
		//1�������ǽ���session���жϣ����session �в��������û����Ǿ������û���¼
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
		int index=0;
		
		while(index<selected.length)
		{
			//���ݱ�ǩ���ݣ�ȥdao�в�ѯid,Ȼ���ϴ��û������浽�û���������
			userService.SavaUserTaste(selected[index],user.getIdUser());
			index++;
		}
		System.out.println(selected);
		}
		
        //����һ���ǣ�ȡ��iscollectsonglist����Ϊȡ���ղ�֮��session������������ʾ����ı�
		return "taste";
	}
	
	/**
	 * �����û���ζҳ��ķ���
	 */
	public String gettaste()
	{
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
		//��ʼ�����û��йر�ǩ��id�Ĳ�ѯ
			int idUser=user.getIdUser();
			List<UserAndSlType> list=userService.findTasetedByUserId(idUser);
			
		//�����еĻ������͵�id ������Ҫ��ȡ�������͵�����emmmm
			List<type> types=userService.findTypeNameByTypeId(list);
			
			//Ŀǰ�õ���types������id��Ӧ�����֣�list�����д���
			List<userandsltype> userandsltypes=new LinkedList<userandsltype>();
			int index=0;
			while(index<list.size())
			{
				//ȡ������
				userandsltype userandsltype=new userandsltype();
				userandsltype.setTypeName(types.get(index).getTypeName());
				userandsltype.setIdType(types.get(index).getIdtype());
				userandsltype.setTimes(list.get(index).getTimes());
				//��Ӷ���
				userandsltypes.add(userandsltype);
				index++;
			}
			//����ֵջ
			ActionContext.getContext().getValueStack().set("userandsltype", userandsltypes);
		}
		return "taste";
	}


	/**
	 * �û���¼�ķ���
	 */
	public String login()
	{
		//1�������û��������룬ģ���������Զ�����
		//2������userService���в�ѯ�������û����������Լ�״̬���в�ѯ
		User existUser=userService.login(user);
		//�Է��ص�user�����ж�
		if(existUser==null)
		{
			//��¼ʧ��
			this.addActionError("��¼ʧ�ܣ��û����������������û�δ���");
			return LOGIN;
		}
		else {
			//��¼�ɹ�,���û���Ϣ����session�������ҳ�����ת 
			ServletActionContext.getRequest().getSession()
				.setAttribute("existUser", existUser);//��user����session��
			//ҳ����ת����ҳ
			//���˽���ҳ�����ת����Ҫ��ѯ�û����ղؼ�¼��Ȼ�󱣴浽session �У������û�������ղصĻ�����׷��
			List<CollectSong> collectSongs= userService.findUserCollectSongByUserId(existUser.getIdUser());
			//��collectSongs ���浽session
			//ServletActionContext.getRequest().getSession()
			//.setAttribute("collectSongs", collectSongs);
			ActionContext.getContext().getSession().put("collectSongs", collectSongs);
			return "loginSuccess";
		}
	}
	/**
	 * У���û����Ƿ���ڵķ���
	 * AjAx�����첽У��
	 * @throws IOException 
	 */
	public String findByName() throws IOException
	{
		/**
		 * ����service���в�ѯ��
		 */
		//�����û���
		System.out.println(user.getUserName());
		User existUser=userService.findByUsername(user.getUserName());
		//���response������ҳ�����
		HttpServletResponse response= ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");//���ñ���
		
		//�����ж�
		if(existUser!=null)//��ѯ�����û�
		{
			//˵���û����Ѿ�����
			response.getWriter().println("�û����Ѿ�����");
			
		}
		else {
			//û�鵽:���û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}
	/**
	 * ���ڵ�¼ҳ�����ת
	 */
	public String  loginPage()
	{
		return "loginPage";
	}
	/**
	 * �����û��˳��ķ���
	 */
	public String quit()
	{
		//����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	//������ע���û�ѡ�����ֿ�ζ
	public String registRec()
	{
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user!=null)
		{
			//ҳ����ת
			return "registRec";
		}
		return "registRec";
	}
	public String registRec2() throws SQLException
	{
		//Ŀǰselected���������Ѿ��������û��ύ������
				//��ʼѭ����������ÿһ������
				//1�������ǽ���session���жϣ����session �в��������û����Ǿ������û���¼
				User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
				if(user!=null)
				{
				int index=0;
				
				while(index<selected.length)
				{
					//���ݱ�ǩ���ݣ�ȥdao�в�ѯid,Ȼ���ϴ��û������浽�û���������
					userService.SavaUserTaste(selected[index],user.getIdUser());
					index++;
				}
				System.out.println(selected);
				}
				System.out.print("����ɹ������ڼ����Ƽ���");
				//�Ȳ�ѯ�û���id
				String  selectid="select idUser from user where userName='"+user.getUserName()+"'";
				PreparedStatement preparedStatement=GetConn.GetConn().prepareStatement(selectid);
				ResultSet resultSet=preparedStatement.executeQuery();
				int userid=1;
				if(resultSet.next())
				{
					userid=resultSet.getInt(1);
				}
				preparedStatement.close();
				GetConn.CloseConn();
		        
				
				//�����Ƽ��Ĺ��̣���յ�ǰ�û����Ƽ���¼
				//String  clear="truncate userrectype";
				//PreparedStatement preparedStatement=GetConn.GetConn().prepareStatement(clear);
				//preparedStatement.execute();
				//preparedStatement.close();
				//GetConn.CloseConn();
				//��ʼ����
				CFaction cFaction=new CFaction();
				//cFaction.run_songListRec();
				cFaction.run_firstSongListRec(userid);
				//��ת���赥�Ƽ�ҳ��
				return "songlistrec";
	}
	//���ֲ����б��û�����ղأ����浽session
	public String nowplay()
	{
		
		return "";
	}
}
