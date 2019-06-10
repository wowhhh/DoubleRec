package com.wyb.rec.utils.SavaSongList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.ObjectUtils.Null;
import org.junit.Test;

/**
 * ��������ͳ���������ݿ��У��û������ĸ赥���������ͣ�ͳ��ÿ���û��Ӵ����ĸ赥
 * @author wyb
 *
 */
public class UserAndType {
	/**
	 * ���岽�裺��ȡһ���û�����ȡ���й���Ϊ�����и赥�������и赥��ȡ�����ͣ�Ȼ���û������Ͳ������
	 * �������ж��Ƿ���ڣ��������+1���������½�
	 */
	private String[] user_name=new String[1000];
	private int[] user_id=new int[1000]; 
	
	private Connection connection=null;
	private Statement statement=null;
	private PreparedStatement pStatement=null;
	
	@Test
	public void run()
	{
		GetUser();
		int index=1;
		while(user_id[index]!=0)
		{
			GetSongList(user_id[index], user_name[index]);
			index++;
		}
	}
	
	//��ȡ�û�,�±��1��ʼ
	public void GetUser()
	{
		//��ȡ����
		try {
			String sql="select * from user";
			statement=GetConn.GetStat();
			ResultSet resultSet= statement.executeQuery(sql);
			int index=1;
			while (resultSet.next()) {
				user_name[index]=resultSet.getString(2);
				user_id[index]=resultSet.getInt(1);
				index++;
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�����û�id����ȡ�й���Ϊ�ĸ赥id���տ�ʼд��ʱ��song1_nameû����գ����Ը赥������ÿ�ζ����ۼ�
	public void GetSongList(int user_id,String user_name)
	{
		
		try {
			String[] songl_name=new String[1000];
			String sql="select * from songlist where ListAuthor= ?";
			connection=GetConn.GetConn();
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, user_name);
			ResultSet resultSet=pStatement.executeQuery();
			int index=1;
			while(resultSet.next())
			{
				songl_name[index]=resultSet.getString(2);
				index++;
			}
			pStatement.close();
			
			for(int i=1;songl_name[i]!=null;i++)//ѭ��ÿ���赥
			{
			//�ٸ��ݸ赥id���û�id���в�ѯ�˸赥��Ӧ�����Ͳ�����userandsltype
			String sql2="select * from songlistandtype where ListId= ? ";
			pStatement=connection.prepareStatement(sql2);
			pStatement.setString(1, songl_name[i]);
			ResultSet resultSet2=pStatement.executeQuery();
				
				//�ٴ�ѭ�����в���
				while(resultSet2.next())
				{
					int idtype=resultSet2.getInt(3);
					//�Ȳ�ѯ���û����id�Ƿ�����ڱ�userandsltype��
					String exist_u_sl_t="select * from userandsltype where idUser=? and idtype=?";
					pStatement=connection.prepareStatement(exist_u_sl_t);
					pStatement.setInt(1, user_id);
					pStatement.setInt(2, idtype);
					ResultSet resultSet3=pStatement.executeQuery();
					if(resultSet3.next())//��Ϊ��
					{
						//��iftype����+1
						String updatesql="update userandsltype set times=times+1 where idUser=? and idtype=?";
						pStatement=connection.prepareStatement(updatesql);
						pStatement.setInt(1, user_id);
						pStatement.setInt(2, idtype);
						pStatement.execute();
						pStatement.close();
					}
					else {
						String insertsql="Insert Into userandsltype(idUser,idtype,times) values(?,?,1)";
						pStatement=connection.prepareStatement(insertsql);
						pStatement.setInt(1, user_id);
						pStatement.setInt(2, idtype);
						pStatement.execute();
						pStatement.close();
					}
					pStatement.close();
				}
				pStatement.close();
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//���ݸ赥id���û�id 
}
