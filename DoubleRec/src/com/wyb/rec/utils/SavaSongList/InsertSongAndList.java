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
	public static String[] categoryId=new String[63];//用于保存标签的id
	public static String[] songlistID=new String[630];//用于保存歌单的id
	
	//根据传入的id，调用相关的函数
	
	public  void first() throws Exception
	{
		//填满标签的数组
		//GetTypeId();
		//进行每个标签的list获取，一个十个
		//GetSongListId();
		//成功进行到这一步的话，songlistID就有0-629个id了
	
		//对每个标签再进行插入
		//从数据库中读取，并放入数组
		
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
			
			System.out.println("当前下标："+index);
			if(id==null)
			{
				System.out.println("出错！！！");
				return;
			}
			
			stat=GetConn.GetStat();
			connection=GetConn.GetConn();
			
			//循环
			getLable(id);
			if(Tag[0]==null)//说明没有标签，那就不存在歌单，直接continue；
			{
				System.out.println("无标签");
				return ;
			}
			GetSongList(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		//停止程序两分钟
		System.out.println("抓取一个歌单的信息完毕，进行修正30s！！！");
		Thread.sleep(10000);
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df2.format(new Date()));// new Date()为获取当前系统时间
		
		}
	}
	

	//获取歌单类型:<span class="tag_edit__tags" data-id="166" data-pid="166" title="粤语">
	public static void getLable(String id)
	{
		try {
			//获取document
			Document document=Jsoup.connect("https://y.qq.com/n/yqq/playsquare/"+id+".html").get();
			Elements elements=document.getElementsByClass("tag_edit__tags");
			for(int i=0;i<elements.size();i++)//一个音乐的标签可能很多个
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
	
	//获取歌单信息
	public static void GetSongList(String id)
	{
		String url="https://api.itooi.cn/music/tencent/songList?key=579621905&id="+id;
		
		String json2=InsertSongAndList.getURLContent(url);
		
		System.out.println(json2);
		
		analyze(json2);//分析json
		
	}
	//获取json文件
	public static String getURLContent(String urlStr) {               
	       
		//请求的url 
	    URL url = null;      
	    
	    //建立的http链接  
	    HttpURLConnection httpConn = null;  
	    
	    //请求的输入流
	    BufferedReader in = null;   
	    
	    //输入流的缓冲
	    StringBuffer sb = new StringBuffer(); 
	    
	    try{     
		     url = new URL(urlStr);     
		     
		     in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") ); 
		     
		     String str = null;  
		    
		     //一行一行进行读入
		     while((str = in.readLine()) != null) {    
		        sb.append( str );     
	         }     
        } catch (Exception ex) {   
	            
        } finally{    
	         try{             
		          if(in!=null) {  
		           in.close(); //关闭流    
	              }     
            }catch(IOException ex) {      
	        
            }     
        }     
        String result =sb.toString();     
        return result;    
    } 
	//解析json文件
	public static void analyze(String result)
	{
		JSONObject data1=JSONObject.fromObject(result);
		
		String data_string=data1.getString("data");
		System.out.println(data_string);
		
		JSONObject data2=JSONObject.fromObject(data_string);
		
		JSONArray songs=data2.getJSONArray("songs");
		songlist_test songList=new songlist_test();
		
		//获取歌单的id
		String ListId=data2.getString("id");
		songList.setListId(ListId);
		
		//获取歌单的标题
		String ListTitle=data2.getString("title");
		songList.setListTitle(ListTitle);
		
		//获取歌单的简介
		String ListDesc=data2.getString("desc");
		songList.setListDesc(ListDesc);
		
		//获取用户名author
		String ListAuthor=data2.getString("author");
		songList.setListAuthor(ListAuthor);
		
		//获取歌曲数量num
		int num=data2.optInt("songnum");//数组内的数据数量
		songList.setListSongNum(num);
		
		//获取歌单的logo地址
		String ListLogo=data2.getString("logo");
		songList.setListLogo(ListLogo);
		
		InsertSongList(songList);
		System.out.println(songList.toString());
		
		//循环处理单个数据
		for(int i=0;i<num;i++)
		{
			song_test song_single=new song_test();
			String string=songs.getString(i);
			JSONObject song=JSONObject.fromObject(string);
			//获取歌曲的id
			String songId=song.getString("id");
			song_single.setSongId(songId);
			
			//获取歌曲的name
			String songName=song.getString("name");
			song_single.setSongName(songName);
			
			//获取歌曲的singer
			String songSinger=song.getString("singer");
			song_single.setSongSinger(songSinger);
			
			//获取歌曲的url
			String songUrl=song.getString("url");
			song_single.setSongUrl(songUrl);
			
			//获取歌曲的pic，图片
			String songPic=song.getString("pic");
			song_single.setSongPic(songPic);
			
			//获取歌曲的lrc，歌词
			String songLrc=song.getString("lrc");
			song_single.setSongLrc(songLrc);
			
			//获取歌曲的播放时间
			String songTime=song.getString("time");
			song_single.setSongTime(songTime);
			
			InsertSongAndList(song_single, songList);
			Matrix(song_single, songList);
		}
	}
	//制作用户-评分矩阵
	public static void Matrix(song_test song,songlist_test songList)
	{
		//得到歌曲名字和播放地址
		String songName=song.getSongName();
		String songUrl=song.getSongUrl();
		int SongId=0;
		//得到用户名字
		String UserName=songList.getListAuthor();
		int UserId=0;
		try{
		//查询歌曲的id,预编译，设置变量，查询，处理结果
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
		//查询用户的id，预编译，设置变量，查询，处理结果
		String SelectUserId="Select iduser from user where userName =? ";
		prestat=connection.prepareStatement(SelectUserId);
		prestat.setString(1, UserName);
		java.sql.ResultSet resultSet2=prestat.executeQuery();
		if(resultSet2.next())
		{
			UserId=resultSet2.getInt(1);
		}
		prestat.close();
		//获取id完毕,生成1-10的随机数
		int rate=(int)(1+Math.random()*(10-1+1));
		
		//写插入语句,缺陷，无法判断用户对此歌曲的评分是否存在，所以先进行查询，如果存在先不进行插入
		String selectRate="select * from rating where idUser=? and idSong=?";//写查询语句，判断此用户对此评分是否存在
		prestat=connection.prepareStatement(selectRate);
		prestat.setInt(1, UserId);
		prestat.setInt(2, SongId);
		java.sql.ResultSet resultSet3=prestat.executeQuery(); //执行查询，判断返回结果是否为空，为空就继续
		if( resultSet3.next())
		{
			System.out.println("用户："+UserName+"对音乐："+songName+"的评分已经存在了！！！");
			prestat.close();
		}
		else{//查询为空再继续
		prestat.close();//先关闭上次的使用
		String InsertRate="Insert Into rating(idUser,idSong,rate) values (?,?,?)";
		prestat=connection.prepareStatement(InsertRate);
		prestat.setInt(1, UserId);
		prestat.setInt(2, SongId);
		prestat.setInt(3, rate);
		prestat.executeUpdate();
		prestat.close();
		System.out.println("用户："+UserName+"对音乐："+songName+"的评分为："+rate);
		}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		//查询用户的id
		//随机生成1-10的分数
		//插入到用户评分表
		
	}
	//插入单个歌曲信息，其中也要包括所在歌单信息
	public static void InsertSongAndList(song_test song,songlist_test songList)
	{
		
		try {
			
			//插入前根据id判断是否重复
			String SongId=song.getSongId();
			String IsId="select * from song where SongId='"+SongId+"'";
			java.sql.ResultSet resultSet = stat.executeQuery(IsId);
			
			if(resultSet.next())//此歌曲已经抓过了
			{
				System.out.println(song.getSongName()+"   已经存在！！");
				
			}
			else
			{
				//将单个歌曲插入歌曲表中
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
				prestat.executeUpdate();//单个歌曲插入完成
				prestat.close();
				System.out.println(song.getSongName()+"插入音乐表完成");	
			}
			/**不管歌曲有没有插到表里面，歌曲-歌单是多对多的。
			//将歌曲与歌单信息插入到歌曲-歌单表中
				String InsertSongAndList="INSERT INTO songandlist(idSongList,idSong) values(?,?)";
				//查询此时插入的歌单在歌单表中的id
				String SelectIdList="Select id from songlist where ListId='"++"'";
				//查询此时插入的歌曲在歌曲表中的id*/
				//直接保存歌单的唯一id和歌曲的唯一id吧，也没有判断是否此歌曲已经存在于此歌单中，要先进行判断
				String selectSongAndList="select * from songandlist where idSongList=? and idSong=? ";
				prestat=connection.prepareStatement(selectSongAndList);
				prestat.setString(1,  songList.getListId());
				prestat.setString(2, song.getSongId());
			    java.sql.ResultSet resultSet2=	prestat.executeQuery();
				if(resultSet2.next())//说明已经插入
				{
					System.out.println(song.getSongName()+"与"+songList.getListTitle()+"已经插入");
				}
				else {
				String InsertSongAndList="Insert Into songandlist(idSongList,idSong) values(?,?)";
				prestat=connection.prepareStatement(InsertSongAndList);
				prestat.setString(1, songList.getListId());
				prestat.setString(2, song.getSongId());
				prestat.executeUpdate();
				prestat.close();
				System.out.println(song.getSongName()+"与"+songList.getListTitle()+"插入歌曲-歌单表完成");
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//插入歌单信息，包括歌单的类型信息
	public static void  InsertSongList(songlist_test songList)
	{
		
		try {
			//犯了大错，应该先检查用户是否已经存在，如果存在就不进行用户的插入
			//获取用户名并插入用户表中
			String UserName=songList.getListAuthor();
			
			//先进行查找
			String SelectUserName="Select * from user where userName=?";
			prestat=connection.prepareStatement(SelectUserName);
			prestat.setString(1, UserName);
			java.sql.ResultSet namerSet=prestat.executeQuery();
			if(namerSet.next())
			{
				//说明存在用户
				System.out.println(UserName+"用户已经存在");
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
				System.out.println("用户： "+UserName+" 已插入用户表");
			}
			
			//插入前根据id判断是否重复
			String idSongList=songList.getListId();
			String IsId="select * from songlist where ListId='"+idSongList+"'";
			java.sql.ResultSet resultSet = stat.executeQuery(IsId);
			
			if(resultSet.next())//此歌单已经抓过了
			{
				System.out.println(songList.getListTitle()+"   已经存在！！");
				
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
				//循环数组有内容的长度的次数
				while(Tag[i]!=null)
				{
					int idtype=0;
					//查找此时的类型在数据库中的id
					String selectId="Select idtype from type where typename='"+Tag[i]+"'";
					
					java.sql.ResultSet rSet=stat.executeQuery(selectId);
					
					if(rSet.next())//存在
					{
					idtype=rSet.getInt(1);
					String InsertListAndType="Insert Into songlistandtype(ListId,idtype) values(?,?)";
					prestat=connection.prepareStatement(InsertListAndType);
					prestat.setString(1, songList.getListId());
					prestat.setInt(2, idtype);
					prestat.executeUpdate();
					
					prestat.close();
					System.out.println(songList.getListTitle()+"类型是"+Tag[i]);
					i++;
					}
					
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//标签id已经获取完成，下面是根据标签id去获取每一个id下的10个歌单
	public void GetTypeId()
	{
		//获取json文件
		String url="https://api.itooi.cn/music/tencent/songListCategory?key=579621905";
		String json_category=InsertSongAndList.getURLContent(url);
		//进行解析
		JSONObject data_category=JSONObject.fromObject(json_category);
		String data=data_category.getString("data");
		JSONArray data_array=JSONArray.fromObject(data);//data本身就是一个数组了
		//循环处理data_array
		int index=0;//用于保存到数组中
		for(int i=1;i<=5;i++)//第0个直接放弃
		{
			String one=data_array.getString(i);//获取第i个
			JSONObject two = JSONObject.fromObject(one);//将其转化为JSONObject对象
			String one_array=two.getString("items");//再获取items数组
			JSONArray two_array=JSONArray.fromObject(one_array);//转化
			//再提取items里面allsorts里面的内容
			
			for(int j=0;j<two_array.size();j++)
			{
				//获取第j个的items
				String items=two_array.getString(j);//可以直接提取了
				JSONObject last=JSONObject.fromObject(items);
				//获取id
				
				categoryId[index++]=last.getString("categoryId");
				System.out.println(last.getString("categoryId"));
			}
			
		}
	}
	
	//根据id 获取10个歌单对应的id序号  ，这里仅仅获取id即可
	//https://api.itooi.cn/music/tencent/hotSongList?key=579621905&categoryId=28&sortId=3&limit=9
	public void GetSongListId() throws Exception
	{
		int index_list=0;
		
		int index=0;
		for(index =0;index<63;index++)
		{
		//获取JSON文件
		String url="https://api.itooi.cn/music/tencent/hotSongList?key=579621905&categoryId="+categoryId[index]+"&sortId=3&limit=9";
		String json_SongList=InsertSongAndList.getURLContent(url);
		//进行解析
		JSONObject jsonObject=JSONObject.fromObject(json_SongList);
		String data=jsonObject.getString("data");//获取完是数组的哟
		JSONArray jsonArray=JSONArray.fromObject(data);
		System.out.println(categoryId[index]);
		for(int i=0;i<jsonArray.size();i++)
		{
			//获取第i个
			String songlist=jsonArray.getString(i);
			JSONObject last=JSONObject.fromObject(songlist);
			//获取
			System.out.println(last.getString("id"));
			//保存
			songlistID[index_list++]=last.getString("id");
			//还是插入吧
			connection=GetConn.GetConn();
			String insertsql="insert into songidtemp(SongListId,SongListName) values(?,?)";
			prestat=connection.prepareStatement(insertsql);
			prestat.setString(1,last.getString("id") );
			prestat.setString(2,last.getString("name") );
			prestat.executeUpdate();
			prestat.close();
			connection.close();
		}
		//休息30s
		System.out.println("抓取一个类别的歌单id完毕，进行修正3分钟！！！");
		Thread.sleep(30000);
		
		}
	}
}
