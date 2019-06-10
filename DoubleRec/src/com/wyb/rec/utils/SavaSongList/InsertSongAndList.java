package com.wyb.rec.utils.SavaSongList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.GenericDeclaration;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.html.HTML.Tag;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.mysql.jdbc.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InsertSongAndList {
	
	public static Statement stat;
	public static String Tag[]=new String[65];
	public static PreparedStatement prestat=null;
	public static Connection connection=null;
	public static String[] categoryId=new String[63];//���ڱ����ǩ��id
	public static String[] songlistID=new String[630];//���ڱ���赥��id
	
	//���ݴ����id��������صĺ���
	
	public  void first() throws Exception
	{
		//������ǩ������
		//GetTypeId();
		//����ÿ����ǩ��list��ȡ��һ��ʮ��
		//GetSongListId();
		//�ɹ����е���һ���Ļ���songlistID����0-629��id��
	
		//��ÿ����ǩ�ٽ��в���
		//�����ݿ��ж�ȡ������������
		
		    int i=0;
			String select="select * from songidtemp";
			connection=GetConn.GetConn();
			prestat=connection.prepareStatement(select);
			java.sql.ResultSet resultSet= prestat.executeQuery();
			while(resultSet.next())
			{
				String temp=resultSet.getString(2);
				songlistID[i]=temp;
				i++;
			}
		
		for(int index=572;index<630;index++)
		{
		try {
			String id=songlistID[index];
			
			System.out.println("��ǰ�±꣺"+index);
			if(id==null)
			{
				System.out.println("��������");
				return;
			}
			
			stat=GetConn.GetStat();
			connection=GetConn.GetConn();
			
			//ѭ��
			getLable(id);
			if(Tag[0]==null)//˵��û�б�ǩ���ǾͲ����ڸ赥��ֱ��continue��
			{
				System.out.println("�ޱ�ǩ");
				return ;
			}
			GetSongList(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		//ֹͣ����������
		System.out.println("ץȡһ���赥����Ϣ��ϣ���������30s������");
		Thread.sleep(10000);
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        System.out.println(df2.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		
		}
	}
	

	//��ȡ�赥����:<span class="tag_edit__tags" data-id="166" data-pid="166" title="����">
	public static void getLable(String id)
	{
		try {
			//��ȡdocument
			Document document=Jsoup.connect("https://y.qq.com/n/yqq/playsquare/"+id+".html").get();
			Elements elements=document.getElementsByClass("tag_edit__tags");
			for(int i=0;i<elements.size();i++)//һ�����ֵı�ǩ���ܺܶ��
			{
				String tag=elements.get(i).select("span").attr("title");
				Tag[i]=tag;
				System.out.println(tag);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ȡ�赥��Ϣ
	public static void GetSongList(String id)
	{
		String url="https://api.itooi.cn/music/tencent/songList?key=579621905&id="+id;
		
		String json2=InsertSongAndList.getURLContent(url);
		
		System.out.println(json2);
		
		analyze(json2);//����json
		
	}
	//��ȡjson�ļ�
	public static String getURLContent(String urlStr) {               
	       
		//�����url 
	    URL url = null;      
	    
	    //������http����  
	    HttpURLConnection httpConn = null;  
	    
	    //�����������
	    BufferedReader in = null;   
	    
	    //�������Ļ���
	    StringBuffer sb = new StringBuffer(); 
	    
	    try{     
		     url = new URL(urlStr);     
		     
		     in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") ); 
		     
		     String str = null;  
		    
		     //һ��һ�н��ж���
		     while((str = in.readLine()) != null) {    
		        sb.append( str );     
	         }     
        } catch (Exception ex) {   
	            
        } finally{    
	         try{             
		          if(in!=null) {  
		           in.close(); //�ر���    
	              }     
            }catch(IOException ex) {      
	        
            }     
        }     
        String result =sb.toString();     
        return result;    
    } 
	//����json�ļ�
	public static void analyze(String result)
	{
		JSONObject data1=JSONObject.fromObject(result);
		
		String data_string=data1.getString("data");
		System.out.println(data_string);
		
		JSONObject data2=JSONObject.fromObject(data_string);
		
		JSONArray songs=data2.getJSONArray("songs");
		songlist_test songList=new songlist_test();
		
		//��ȡ�赥��id
		String ListId=data2.getString("id");
		songList.setListId(ListId);
		
		//��ȡ�赥�ı���
		String ListTitle=data2.getString("title");
		songList.setListTitle(ListTitle);
		
		//��ȡ�赥�ļ��
		String ListDesc=data2.getString("desc");
		songList.setListDesc(ListDesc);
		
		//��ȡ�û���author
		String ListAuthor=data2.getString("author");
		songList.setListAuthor(ListAuthor);
		
		//��ȡ��������num
		int num=data2.optInt("songnum");//�����ڵ���������
		songList.setListSongNum(num);
		
		//��ȡ�赥��logo��ַ
		String ListLogo=data2.getString("logo");
		songList.setListLogo(ListLogo);
		
		InsertSongList(songList);
		System.out.println(songList.toString());
		
		//ѭ������������
		for(int i=0;i<num;i++)
		{
			song_test song_single=new song_test();
			String string=songs.getString(i);
			JSONObject song=JSONObject.fromObject(string);
			//��ȡ������id
			String songId=song.getString("id");
			song_single.setSongId(songId);
			
			//��ȡ������name
			String songName=song.getString("name");
			song_single.setSongName(songName);
			
			//��ȡ������singer
			String songSinger=song.getString("singer");
			song_single.setSongSinger(songSinger);
			
			//��ȡ������url
			String songUrl=song.getString("url");
			song_single.setSongUrl(songUrl);
			
			//��ȡ������pic��ͼƬ
			String songPic=song.getString("pic");
			song_single.setSongPic(songPic);
			
			//��ȡ������lrc�����
			String songLrc=song.getString("lrc");
			song_single.setSongLrc(songLrc);
			
			//��ȡ�����Ĳ���ʱ��
			String songTime=song.getString("time");
			song_single.setSongTime(songTime);
			
			InsertSongAndList(song_single, songList);
			Matrix(song_single, songList);
		}
	}
	//�����û�-���־���
	public static void Matrix(song_test song,songlist_test songList)
	{
		//�õ��������ֺͲ��ŵ�ַ
		String songName=song.getSongName();
		String songUrl=song.getSongUrl();
		int SongId=0;
		//�õ��û�����
		String UserName=songList.getListAuthor();
		int UserId=0;
		try{
		//��ѯ������id,Ԥ���룬���ñ�������ѯ��������
		String SelectSongId="Select idsong from song where SongName= ? and SongUrl =?";
		prestat=connection.prepareStatement(SelectSongId);
		prestat.setString(1, songName);
		prestat.setString(2, songUrl);
		java.sql.ResultSet resultSet1=prestat.executeQuery();
		if(resultSet1.next())
		{
			SongId=resultSet1.getInt(1);
		}
		prestat.close();
		//��ѯ�û���id��Ԥ���룬���ñ�������ѯ��������
		String SelectUserId="Select iduser from user where userName =? ";
		prestat=connection.prepareStatement(SelectUserId);
		prestat.setString(1, UserName);
		java.sql.ResultSet resultSet2=prestat.executeQuery();
		if(resultSet2.next())
		{
			UserId=resultSet2.getInt(1);
		}
		prestat.close();
		//��ȡid���,����1-10�������
		int rate=(int)(1+Math.random()*(10-1+1));
		
		//д�������,ȱ�ݣ��޷��ж��û��Դ˸����������Ƿ���ڣ������Ƚ��в�ѯ����������Ȳ����в���
		String selectRate="select * from rating where idUser=? and idSong=?";//д��ѯ��䣬�жϴ��û��Դ������Ƿ����
		prestat=connection.prepareStatement(selectRate);
		prestat.setInt(1, UserId);
		prestat.setInt(2, SongId);
		java.sql.ResultSet resultSet3=prestat.executeQuery(); //ִ�в�ѯ���жϷ��ؽ���Ƿ�Ϊ�գ�Ϊ�վͼ���
		if( resultSet3.next())
		{
			System.out.println("�û���"+UserName+"�����֣�"+songName+"�������Ѿ������ˣ�����");
			prestat.close();
		}
		else{//��ѯΪ���ټ���
		prestat.close();//�ȹر��ϴε�ʹ��
		String InsertRate="Insert Into rating(idUser,idSong,rate) values (?,?,?)";
		prestat=connection.prepareStatement(InsertRate);
		prestat.setInt(1, UserId);
		prestat.setInt(2, SongId);
		prestat.setInt(3, rate);
		prestat.executeUpdate();
		prestat.close();
		System.out.println("�û���"+UserName+"�����֣�"+songName+"������Ϊ��"+rate);
		}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		//��ѯ�û���id
		//�������1-10�ķ���
		//���뵽�û����ֱ�
		
	}
	//���뵥��������Ϣ������ҲҪ�������ڸ赥��Ϣ
	public static void InsertSongAndList(song_test song,songlist_test songList)
	{
		
		try {
			
			//����ǰ����id�ж��Ƿ��ظ�
			String SongId=song.getSongId();
			String IsId="select * from song where SongId='"+SongId+"'";
			java.sql.ResultSet resultSet = stat.executeQuery(IsId);
			
			if(resultSet.next())//�˸����Ѿ�ץ����
			{
				System.out.println(song.getSongName()+"   �Ѿ����ڣ���");
				
			}
			else
			{
				//���������������������
				String InserSong="Insert Into song(SongId,SongName,SongTime,SongSinger,SongUrl,SongPic,SongLrc)"
						+ "values(?,?,?,?,?,?,?)";
				prestat=connection.prepareStatement(InserSong);
				prestat.setString(1, song.getSongId());
				prestat.setString(2, song.getSongName());
				prestat.setString(3, song.getSongTime());
				prestat.setString(4, song.getSongSinger());
				prestat.setString(5, song.getSongUrl());
				prestat.setString(6, song.getSongPic());
				prestat.setString(7, song.getSongLrc());
				prestat.executeUpdate();//���������������
				prestat.close();
				System.out.println(song.getSongName()+"�������ֱ����");	
			}
			/**���ܸ�����û�в嵽�����棬����-�赥�Ƕ�Զ�ġ�
			//��������赥��Ϣ���뵽����-�赥����
				String InsertSongAndList="INSERT INTO songandlist(idSongList,idSong) values(?,?)";
				//��ѯ��ʱ����ĸ赥�ڸ赥���е�id
				String SelectIdList="Select id from songlist where ListId='"++"'";
				//��ѯ��ʱ����ĸ����ڸ������е�id*/
				//ֱ�ӱ���赥��Ψһid�͸�����Ψһid�ɣ�Ҳû���ж��Ƿ�˸����Ѿ������ڴ˸赥�У�Ҫ�Ƚ����ж�
				String selectSongAndList="select * from songandlist where idSongList=? and idSong=? ";
				prestat=connection.prepareStatement(selectSongAndList);
				prestat.setString(1,  songList.getListId());
				prestat.setString(2, song.getSongId());
			    java.sql.ResultSet resultSet2=	prestat.executeQuery();
				if(resultSet2.next())//˵���Ѿ�����
				{
					System.out.println(song.getSongName()+"��"+songList.getListTitle()+"�Ѿ�����");
				}
				else {
				String InsertSongAndList="Insert Into songandlist(idSongList,idSong) values(?,?)";
				prestat=connection.prepareStatement(InsertSongAndList);
				prestat.setString(1, songList.getListId());
				prestat.setString(2, song.getSongId());
				prestat.executeUpdate();
				prestat.close();
				System.out.println(song.getSongName()+"��"+songList.getListTitle()+"�������-�赥�����");
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//����赥��Ϣ�������赥��������Ϣ
	public static void  InsertSongList(songlist_test songList)
	{
		
		try {
			//���˴��Ӧ���ȼ���û��Ƿ��Ѿ����ڣ�������ھͲ������û��Ĳ���
			//��ȡ�û����������û�����
			String UserName=songList.getListAuthor();
			
			//�Ƚ��в���
			String SelectUserName="Select * from user where userName=?";
			prestat=connection.prepareStatement(SelectUserName);
			prestat.setString(1, UserName);
			java.sql.ResultSet namerSet=prestat.executeQuery();
			if(namerSet.next())
			{
				//˵�������û�
				System.out.println(UserName+"�û��Ѿ�����");
				prestat.close();
			}
			else
			{
				prestat.close();
				
				String InsertUserName="Insert Into user(userName,userPass) values (?,?)";
				prestat=connection.prepareStatement(InsertUserName);
				prestat.setString(1, UserName);
				prestat.setString(2, "123456");
				prestat.executeUpdate();
				prestat.close();
				System.out.println("�û��� "+UserName+" �Ѳ����û���");
			}
			
			//����ǰ����id�ж��Ƿ��ظ�
			String idSongList=songList.getListId();
			String IsId="select * from songlist where ListId='"+idSongList+"'";
			java.sql.ResultSet resultSet = stat.executeQuery(IsId);
			
			if(resultSet.next())//�˸赥�Ѿ�ץ����
			{
				System.out.println(songList.getListTitle()+"   �Ѿ����ڣ���");
				
			}
			else
			{
				String InsertSql="Insert Into songlist(ListId,ListTitle,ListAuthor,ListDesc,ListSongNum,ListLogo)"
						+ "values(?,?,?,?,?,?)";
				prestat=connection.prepareStatement(InsertSql);
				prestat.setString(1, songList.getListId());
				prestat.setString(2, songList.getListTitle());
				prestat.setString(3, songList.getListAuthor());
				prestat.setString(4, songList.getListDesc());
				prestat.setInt(5, songList.getListSongNum());
				prestat.setString(6, songList.getListLogo());
				
				prestat.executeUpdate();
				
				prestat.close();
				
				int i=0;
				//ѭ�����������ݵĳ��ȵĴ���
				while(Tag[i]!=null)
				{
					int idtype=0;
					//���Ҵ�ʱ�����������ݿ��е�id
					String selectId="Select idtype from type where typename='"+Tag[i]+"'";
					
					java.sql.ResultSet rSet=stat.executeQuery(selectId);
					
					if(rSet.next())//����
					{
					idtype=rSet.getInt(1);
					String InsertListAndType="Insert Into songlistandtype(ListId,idtype) values(?,?)";
					prestat=connection.prepareStatement(InsertListAndType);
					prestat.setString(1, songList.getListId());
					prestat.setInt(2, idtype);
					prestat.executeUpdate();
					
					prestat.close();
					System.out.println(songList.getListTitle()+"������"+Tag[i]);
					i++;
					}
					
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ǩid�Ѿ���ȡ��ɣ������Ǹ��ݱ�ǩidȥ��ȡÿһ��id�µ�10���赥
	public void GetTypeId()
	{
		//��ȡjson�ļ�
		String url="https://api.itooi.cn/music/tencent/songListCategory?key=579621905";
		String json_category=InsertSongAndList.getURLContent(url);
		//���н���
		JSONObject data_category=JSONObject.fromObject(json_category);
		String data=data_category.getString("data");
		JSONArray data_array=JSONArray.fromObject(data);//data�������һ��������
		//ѭ������data_array
		int index=0;//���ڱ��浽������
		for(int i=1;i<=5;i++)//��0��ֱ�ӷ���
		{
			String one=data_array.getString(i);//��ȡ��i��
			JSONObject two = JSONObject.fromObject(one);//����ת��ΪJSONObject����
			String one_array=two.getString("items");//�ٻ�ȡitems����
			JSONArray two_array=JSONArray.fromObject(one_array);//ת��
			//����ȡitems����allsorts���������
			
			for(int j=0;j<two_array.size();j++)
			{
				//��ȡ��j����items
				String items=two_array.getString(j);//����ֱ����ȡ��
				JSONObject last=JSONObject.fromObject(items);
				//��ȡid
				
				categoryId[index++]=last.getString("categoryId");
				System.out.println(last.getString("categoryId"));
			}
			
		}
	}
	
	//����id ��ȡ10���赥��Ӧ��id���  �����������ȡid����
	//https://api.itooi.cn/music/tencent/hotSongList?key=579621905&categoryId=28&sortId=3&limit=9
	public void GetSongListId() throws Exception
	{
		int index_list=0;
		
		int index=0;
		for(index =0;index<63;index++)
		{
		//��ȡJSON�ļ�
		String url="https://api.itooi.cn/music/tencent/hotSongList?key=579621905&categoryId="+categoryId[index]+"&sortId=3&limit=9";
		String json_SongList=InsertSongAndList.getURLContent(url);
		//���н���
		JSONObject jsonObject=JSONObject.fromObject(json_SongList);
		String data=jsonObject.getString("data");//��ȡ���������Ӵ
		JSONArray jsonArray=JSONArray.fromObject(data);
		System.out.println(categoryId[index]);
		for(int i=0;i<jsonArray.size();i++)
		{
			//��ȡ��i��
			String songlist=jsonArray.getString(i);
			JSONObject last=JSONObject.fromObject(songlist);
			//��ȡ
			System.out.println(last.getString("id"));
			//����
			songlistID[index_list++]=last.getString("id");
			//���ǲ����
			connection=GetConn.GetConn();
			String insertsql="insert into songidtemp(SongListId,SongListName) values(?,?)";
			prestat=connection.prepareStatement(insertsql);
			prestat.setString(1,last.getString("id") );
			prestat.setString(2,last.getString("name") );
			prestat.executeUpdate();
			prestat.close();
			connection.close();
		}
		//��Ϣ30s
		System.out.println("ץȡһ�����ĸ赥id��ϣ���������3���ӣ�����");
		Thread.sleep(30000);
		
		}
	}
}
