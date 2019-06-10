package com.wyb.rec.CollaborationFiltering;
/**
 * ��Ҫ���ܣ���ȡ���ݿ�������ݵ�ת��
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * ���ڴ����ݿ��ж�ȡ����Ȼ�����ɾ���
 * �û�id
 * ����id
 * �û�-�������־���
 * @author wyb
 *
 */
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

import com.sun.tracing.dtrace.ProviderAttributes;
import com.wyb.rec.utils.SavaSongList.GetConn;
public class DataTranslate {
	private static Statement statement=null;
	private static Connection conn=null;
	private static ResultSet RateSet=null;
	private static ResultSet resultSet=null;
	/**
	 * ��Ҫ���ص����ݣ�
	 * @return userIdList �û���
	 * @return songIdList ������
	 * @return user-songRatingMatrix �û��������־���
	 * @throws SQLException 
	 */
	
	
	
	//��ȡ�û�id�� bingo
	
	public static  List<Integer> GetuserIdList () 
	{
		List<Integer> userIdList =new LinkedList<Integer>();
		String sql="select iduser from user";
		try {
			statement=GetSqlConn.GetStat();
		
		resultSet=statement.executeQuery(sql);
		
		while(resultSet.next())
		{
			int x=resultSet.getInt(1);
			userIdList.add( x);
			
		}
		statement.close();
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(userIdList);
		return userIdList;
	}
	
	//��ȡ����id��
	public static List<Integer> GetsongIdList() 
	{
		List<Integer> songIdList=new LinkedList<Integer>();
		//String sql="select * from song";//������䣬���ֺ���û�к������Ĳ��룬�м��м�������Բ�ѯ���һ��id
		String sql="select * from song order by idsong DESC limit 1";
		try {
			statement=GetSqlConn.GetStat();
		
		resultSet=statement.executeQuery(sql);
		int index=1;
		if(resultSet.next())
		{
			
		
		for(index=1;index<=resultSet.getInt(1);index++)
		{
			songIdList.add(index);
		}
		
		
		}
		statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(songIdList);
		
		return songIdList;
	}
	
	//��ȡ�û�-�������־���
	public static Map<Integer, double[]> GetUserItemMatrix(List<Integer> userIdList,List<Integer> songIdList)
	{
		
		int songLen=songIdList.size();
		final Map<Integer,double[]> user2songRatingMatrix=new HashMap<Integer, double[]>();
		
		userIdList.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer userId) {
				// TODO Auto-generated method stub
				double[] curUserRatingArray=new double[songLen+1];//��ǰ�û����ֵ������С
				for(int i=0;i<songLen;i++)
				{
				curUserRatingArray[i]=0.0;
				}
				
				try
				{
				String GetData="Select * from rating";//дsql���
				statement=GetSqlConn.GetStat();
				 RateSet=statement.executeQuery(GetData);//��ȡ��ѯ�õ��Ľ����
				//�����ǰ���е��û������ݿ������
				
					while(RateSet.next())
					{
					
						if(userId==RateSet.getInt(2))
						{
							    //�����Ǳ���ÿ������
						
								//�����������е�λ����RateSet.getInt(3)
							    System.out.println(RateSet.getInt(3));
							    System.out.println(RateSet.getInt(4));
								curUserRatingArray[RateSet.getInt(3)]=RateSet.getInt(4);//����������	
								
						}
					}
					statement.close();

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				user2songRatingMatrix.put(userId, curUserRatingArray);
			}
			
		});
		
		
		return user2songRatingMatrix;
	}
	
	//�����ǹ��ڸ赥���Ƽ����û�id���Ѿ���ȡ��ɣ������ȡ����id����ȡ�û���������Ȼ��ʼ����
	public static List<Integer> GetTypeIdList()
	{
		List<Integer> typeIdList=new LinkedList<Integer>();
		//String sql="select * from song";//������䣬���ֺ���û�к������Ĳ��룬�м��м�������Բ�ѯ���һ��id
		String sql="select * from type ";
		try {
			statement=GetSqlConn.GetStat();
		
	    resultSet=statement.executeQuery(sql);
		int index=1;
		while(resultSet.next())
		{
			typeIdList.add(resultSet.getInt(1));
		}
		statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(typeIdList);
		
		return typeIdList;
	}
	//��ȡ�û�-��������
	public static Map<Integer, double[]> GetUserTimesMatrix(List<Integer> userIdList,List<Integer> typeIdList)
	{
		
		int songLen=typeIdList.size();
		final Map<Integer,double[]> user2typeTimesMatrix=new HashMap<Integer, double[]>();
		
		userIdList.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer userId) {
				
				// TODO Auto-generated method stub
				double[] curUserRatingArray=new double[songLen+1];//��ǰ�û����ֵ������С
				for(int i=0;i<songLen;i++)
				{
				curUserRatingArray[i]=0.0;
				}
				
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost:3306/doublerec";//���ӱ������ݿ�
					//String url="jdbc:mysql://101.132.40.184:3306/musicRe";//�����ƶ����ݿ�
				 conn = DriverManager.getConnection(url,"root", "sspu");
					
				String GetData="Select * from userandsltype";//дsql���
				statement=conn.createStatement();
				 RateSet=statement.executeQuery(GetData);//��ȡ��ѯ�õ��Ľ����
				//�����ǰ���е��û������ݿ������
				
					while(RateSet.next())
					{
					
						if(userId==RateSet.getInt(2))
						{
							    //�����Ǳ���ÿ������
						
								//�����������е�λ����RateSet.getInt(3)
							    System.out.println(RateSet.getInt(3));
							    System.out.println(RateSet.getInt(4));
								curUserRatingArray[RateSet.getInt(3)]=RateSet.getInt(4);//����������	
								
						}
					}
					statement.close();
					conn.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally {
					 try {
				            if(RateSet !=null)RateSet.close();
				        } catch (Exception e) {
				          
				        }

				        try {
				            if(statement !=null)statement.close();
				        } catch (Exception e) {
				            
				        }

				        try {
				            if(conn !=null)conn.close();
				        } catch (Exception e) {
				            
				        }

				}
				
				user2typeTimesMatrix.put(userId, curUserRatingArray);
			}
			
		});
		
		
		return user2typeTimesMatrix;
	}
}
