package com.wyb.rec.CollaborationFiltering;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.catalina.authenticator.SavedRequest;
import org.junit.Test;

import sun.security.x509.KeyIdentifier;

/**
 * ���ڿ��������㷨�Ĺ���
 * @author wyb
 *
 */
public class CFaction {
	
	@Test
	public void test()
	{
		this.run();
		//this.run_songListRec();
	}
	
	public Map<Integer,Integer[]> run()
	{
	//��ʼ
	//�û�-���� �Ƽ��б�
	Map<Integer,Integer[]> user2songRecMatrix=new HashMap<Integer, Integer[]>();
	
	//��ȡ�û�
	List<Integer> userIdList=DataTranslate.GetuserIdList();
	//��ȡ����
	List<Integer> songIdList=DataTranslate.GetsongIdList();
	//��ȡ�û����־���
	Map<Integer, double[]> user2songRatingMatrix=DataTranslate.GetUserItemMatrix(userIdList, songIdList);
	//�����û����ƶȣ���ȡ�û�k�������û�
	Map<Integer,Integer[]> userKNNMatrix=UserKNN.getKNN(userIdList,user2songRatingMatrix,3);
	//ʹ��Эͬ���ˣ������Ƽ�
	user2songRecMatrix=CollaborativeFiltering.userKNNBasedCF(userIdList, userKNNMatrix, user2songRatingMatrix, songIdList, 10);
	System.out.println(user2songRecMatrix);
	
	//ֵ��ע����ǣ�����֮ǰҪ��������ݵ�
	saveRecResult(userIdList, user2songRecMatrix);
	return user2songRecMatrix;
	}
	
	public boolean saveRecResult(List<Integer> userIdList, Map<Integer,Integer[]> user2songRecMatrix)
	{
		//����ÿһ���û���idȥ����������һ�Σ�ȡ���Ƽ������飬Ȼ������������һ�����Ĳ���
		userIdList.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer curUserId) {
				// TODO Auto-generated method stub
				Integer[] curUserRecSongId=user2songRecMatrix.get(curUserId);
				//����������б�����
				for(int i=0;i<curUserRecSongId.length;i++)
				{
					//ȡ��songid
					Integer SongId=curUserRecSongId[i];
					//curUserId������������ݵĲ����
					String sql="Insert Into userrec(userId,songId,Date) values(?,?,?)";
					try {
						Connection connection=GetSqlConn.GetConn();
						PreparedStatement preparedStatement=connection.prepareStatement(sql);
						preparedStatement.setInt(1, curUserId);
						preparedStatement.setInt(2, SongId);
						
						//��ȡʱ�䲢����
						String utilDate=null;
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						utilDate=formatter.format(cal.getTime());
	
						preparedStatement.setString(3, utilDate);
						preparedStatement.execute();
						
						connection.close();
						preparedStatement.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
			}
		});
		return true;
	}
	//���û��赥���Ƽ�
	public void run_firstSongListRec(int userid)
	{
		//��ʼ
		//�û�-���� �Ƽ��б�
		Map<Integer,Integer[]> user2songListRecMatrix=new HashMap<Integer, Integer[]>();
		
		//��ȡ�û�
		List<Integer> userIdList=DataTranslate.GetuserIdList();
		//��ȡ�赥����
		List<Integer> typeIdList=DataTranslate.GetTypeIdList();
		//��ȡ�û����־���
		Map<Integer, double[]> user2songRatingMatrix=DataTranslate.GetUserTimesMatrix(userIdList, typeIdList);
		//�����û����ƶȣ���ȡ�û�k�������û�
		Map<Integer,Integer[]> userKNNMatrix=UserKNN.getKNN(userIdList,user2songRatingMatrix,3);
		//ʹ��Эͬ���ˣ������Ƽ�
		Integer[] singleSongListRec= CollaborativeFiltering.singleUserRec(userIdList, userKNNMatrix, user2songRatingMatrix, typeIdList, 10, userid);
		System.out.println(user2songListRecMatrix);
		
		//ֵ��ע����ǣ�����֮ǰҪ��������ݵ�
		saveSingleSongListRecResult(userid, singleSongListRec);
	}
	

	//���ڸ赥���Ƽ�
	public Map<Integer,Integer[]> run_songListRec()
	{
	//��ʼ
	//�û�-���� �Ƽ��б�
	Map<Integer,Integer[]> user2songListRecMatrix=new HashMap<Integer, Integer[]>();
	
	//��ȡ�û�
	List<Integer> userIdList=DataTranslate.GetuserIdList();
	//��ȡ�赥����
	List<Integer> typeIdList=DataTranslate.GetTypeIdList();
	//��ȡ�û����־���
	Map<Integer, double[]> user2songRatingMatrix=DataTranslate.GetUserTimesMatrix(userIdList, typeIdList);
	//�����û����ƶȣ���ȡ�û�k�������û�
	Map<Integer,Integer[]> userKNNMatrix=UserKNN.getKNN(userIdList,user2songRatingMatrix,3);
	//ʹ��Эͬ���ˣ������Ƽ�
	user2songListRecMatrix=CollaborativeFiltering.userKNNBasedCF(userIdList, userKNNMatrix, user2songRatingMatrix, typeIdList, 10);
	System.out.println(user2songListRecMatrix);
	
	//ֵ��ע����ǣ�����֮ǰҪ��������ݵ�
	saveSongListRecResult(userIdList, user2songListRecMatrix);
	return user2songListRecMatrix;
	}
	
	
	//���浥���û����Ƽ��赥���
	private void saveSingleSongListRecResult(int userid, Integer[] curUserRecSongId) {
			// TODO Auto-generated method stub
		for(int i=0;i<curUserRecSongId.length;i++)
		{
			//ȡ��songid
			Integer SongId=curUserRecSongId[i];
			//curUserId������������ݵĲ����
			String sql="Insert Into userrectype(userId,typeId,Date) values(?,?,?)";
			try {
				Connection connection=GetSqlConn.GetConn();
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, userid);
				preparedStatement.setInt(2, SongId);
				
				//��ȡʱ�䲢����
				String utilDate=null;
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				utilDate=formatter.format(cal.getTime());

				preparedStatement.setString(3, utilDate);
				preparedStatement.execute();
				
				connection.close();
				preparedStatement.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}
	
	public boolean saveSongListRecResult(List<Integer> userIdList, Map<Integer,Integer[]> user2songListRecMatrix)
	{
		//����ÿһ���û���idȥ����������һ�Σ�ȡ���Ƽ������飬Ȼ������������һ�����Ĳ���
		userIdList.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer curUserId) {
				// TODO Auto-generated method stub
				Integer[] curUserRecSongId=user2songListRecMatrix.get(curUserId);
				//����������б�����
				for(int i=0;i<curUserRecSongId.length;i++)
				{
					//ȡ��songid
					Integer SongId=curUserRecSongId[i];
					//curUserId������������ݵĲ����
					String sql="Insert Into userrectype(userId,typeId,Date) values(?,?,?)";
					try {
						Connection connection=GetSqlConn.GetConn();
						PreparedStatement preparedStatement=connection.prepareStatement(sql);
						preparedStatement.setInt(1, curUserId);
						preparedStatement.setInt(2, SongId);
						
						//��ȡʱ�䲢����
						String utilDate=null;
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						utilDate=formatter.format(cal.getTime());
	
						preparedStatement.setString(3, utilDate);
						preparedStatement.execute();
						
						connection.close();
						preparedStatement.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
			}
		});
		return true;
	}
	
}
