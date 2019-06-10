package com.wyb.rec.utils.userbehaviour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.wyb.rec.utils.SavaSongList.GetConn;

public class SongBehavior {
	
	private PreparedStatement prestat;
	private Connection connection;
	
	@Test
	public void test()
	{
		DelScore("����", "002uSyaM1p1MyX");
	}
	
	//����ֵΪ�û�id�Լ�����id�������У��ղأ�ȡ���ղأ�����,flag�����ж��û����ղػ��ǲ��ţ�1�ղأ�0����
	public void AddScore(String UserName,String SongId,int flag)
	{
		//�Ȳ�ѯ�Ƿ���ڣ�������ڣ�׷�����֣������ڲ���
		//�õ��������ֺͲ��ŵ�ַ
				
		//��ѯ�Ȼ���û���id�Լ�������id
				int idSong=0;			
				int idUser=0;
				
				try{
					connection=GetConn.GetConn();
				//��ѯ������id,Ԥ���룬���ñ�������ѯ��������
				String SelectSongId="Select idsong from song where SongId= ?";
				prestat=connection.prepareStatement(SelectSongId);
				prestat.setString(1, SongId);
				java.sql.ResultSet resultSet1=prestat.executeQuery();
				if(resultSet1.next())
				{
					idSong=resultSet1.getInt(1);
				}
				prestat.close();
				//��ѯ�û���id��Ԥ���룬���ñ�������ѯ��������
				String SelectUserId="Select iduser from user where userName =? ";
				prestat=connection.prepareStatement(SelectUserId);
				prestat.setString(1, UserName);
				java.sql.ResultSet resultSet2=prestat.executeQuery();
				if(resultSet2.next())
				{
					idUser=resultSet2.getInt(1);
				}
				prestat.close();
				//��ȡid���,����1-10�������
				int rate=0;
				if(flag==1)
				{
				rate=2;
				}
				else
				{
					 rate =1;
				}
				//д�������,ȱ�ݣ��޷��ж��û��Դ˸����������Ƿ���ڣ������Ƚ��в�ѯ����������Ȳ����в���
				String selectRate="select * from rating where idUser=? and idSong=?";//д��ѯ��䣬�жϴ��û��Դ������Ƿ����
				prestat=connection.prepareStatement(selectRate);
				prestat.setInt(1, idUser);
				prestat.setInt(2, idSong);
				java.sql.ResultSet resultSet3=prestat.executeQuery(); //ִ�в�ѯ���жϷ��ؽ���Ƿ�Ϊ�գ�Ϊ�վͼ���
				if( resultSet3.next())
				{
					System.out.println("�û���"+UserName+"�����֣�"+SongId+"�������Ѿ������ˣ����������м�"+rate+"��");
					int rated=resultSet3.getInt(4);//��ȡ��ǰ������
					rate+=rated;//��ǰ����
					prestat.close();
					//�����µ�����
					String InsertRate="update rating set rate=? where idUser=? and idSong=?";
					prestat=connection.prepareStatement(InsertRate);
					prestat.setInt(1, rate);
					prestat.setInt(2, idUser);
					prestat.setInt(3, idSong);
					prestat.executeUpdate();
					prestat.close();
				}
				else{//��ѯΪ���ټ���
				prestat.close();//�ȹر��ϴε�ʹ��
				String InsertRate="Insert Into rating(idUser,idSong,rate) values (?,?,?)";
				prestat=connection.prepareStatement(InsertRate);
				prestat.setInt(1, idUser);
				prestat.setInt(2, idSong);
				prestat.setInt(3, rate);
				prestat.executeUpdate();
				prestat.close();
				System.out.println("�û���"+UserName+"�����֣�"+SongId+"������Ϊ��"+rate);
				}
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
	}
	
	public void DelScore(String UserName,String SongId)
	{
		//������ڣ���ȥ���֣�������ڲ����ڵ����
		//�Ȳ�ѯ�Ƿ���ڣ�������ڣ�׷�����֣������ڲ���
				//�õ��������ֺͲ��ŵ�ַ
						
				//��ѯ�Ȼ���û���id�Լ�������id
						int idSong=0;			
						int idUser=0;
						
						try{
							connection=GetConn.GetConn();
						//��ѯ������id,Ԥ���룬���ñ�������ѯ��������
						String SelectSongId="Select idsong from song where SongId= ?";
						prestat=connection.prepareStatement(SelectSongId);
						prestat.setString(1, SongId);
						java.sql.ResultSet resultSet1=prestat.executeQuery();
						if(resultSet1.next())
						{
							idSong=resultSet1.getInt(1);
						}
						prestat.close();
						//��ѯ�û���id��Ԥ���룬���ñ�������ѯ��������
						String SelectUserId="Select iduser from user where userName =? ";
						prestat=connection.prepareStatement(SelectUserId);
						prestat.setString(1, UserName);
						java.sql.ResultSet resultSet2=prestat.executeQuery();
						if(resultSet2.next())
						{
							idUser=resultSet2.getInt(1);
						}
						prestat.close();
						//��ȡid���,����1-10�������
						int rate=2;
						//ɾ����������

						//д�������,ȱ�ݣ��޷��ж��û��Դ˸����������Ƿ���ڣ������Ƚ��в�ѯ����������Ȳ����в���
						String selectRate="select * from rating where idUser=? and idSong=?";//д��ѯ��䣬�жϴ��û��Դ������Ƿ����
						prestat=connection.prepareStatement(selectRate);
						prestat.setInt(1, idUser);
						prestat.setInt(2, idSong);
						java.sql.ResultSet resultSet3=prestat.executeQuery(); //ִ�в�ѯ���жϷ��ؽ���Ƿ�Ϊ�գ�Ϊ�վͼ���
						if( resultSet3.next())
						{
						int rated=resultSet3.getInt(4);//��ȡ��ǰ������
						rate=rated-rate;//��ǰ����
						prestat.close();
						//�����µ�����
						String InsertRate="update rating set rate=? where idUser=? and idSong=?";
						prestat=connection.prepareStatement(InsertRate);
						prestat.setInt(1, rate);
						prestat.setInt(2, idUser);
						prestat.setInt(3, idSong);
						prestat.executeUpdate();
						prestat.close();
						}
						}
						catch (Exception e) {
							// TODO: handle exception
						}
	}
}
