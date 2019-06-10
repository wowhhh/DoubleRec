package com.wyb.rec.utils.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.wyb.rec.domain.Song;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 接受连接，然后获取json文件，进行解析，返回结果是一个歌曲信息的集合
 * @author wyb
 *
 */
public class GetAndAnalyseJSON {
	
	private List<Song> songs=new LinkedList<Song>();//用于保存每首歌曲并返回
	
	public List<Song> GetSearchSongs(String message)//获取歌曲搜索结果
	{
		String url=GetUrl(message);
		String jsonfile=this.GetJSON(url);
		System.out.println(jsonfile);
		if(jsonfile.hashCode()==0)
		{
			songs=null;
		
		}
		else
		{
			Analyse(jsonfile);
		}
		return songs;
	}
	
	@Test
	public void test()
	{
		//值得注意的是，链接中不可以有中文，如果有中文要重新进行编码
		String url=GetUrl("Taylor");
		String jsonfile=this.GetJSON(url);
		System.out.println(jsonfile);
		//判断jsonfile是不是空
	
		System.out.println("end");
	}
	
	public String GetUrl(String message)
	{
		//对message重新编码
		try {
			message=URLEncoder.encode(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//组合成新的url
		String url="https://api.itooi.cn/music/tencent/search?key=579621905&s="+message+"&limit=20&offset=0&type=song";
		return url;
	}
	
	public String GetJSON(String urlStr)
	{
	       
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
	        } 
		    catch (Exception ex) {   
		            
	        } 
		    finally{  
		    	
		         try{             
			          if(in!=null) {  
			           in.close(); //关闭流    
		              }     
	            }
		         catch(IOException ex) {      
		        
	            }     
	        }     
	        String result =sb.toString();     
	        return result;    
	}
	public void Analyse(String result)
	{
		JSONObject data1=JSONObject.fromObject(result);
		
		String data_string=data1.getString("data");
		System.out.println(data_string);
		
		//有可能搜索到的是空，那么data_string 就是[]
		if(data_string.equals("[]"))
		{
			songs=null;
		}
		else {
		
		JSONArray data_array=JSONArray.fromObject(data_string);//data本身就是一个数组了
		//我已知是由20条歌曲数据的
		for(int i=0;i<20;i++)
		{
			String one=data_array.getString(i);//逐条获取
			JSONObject  song=JSONObject.fromObject(one);//转化为json对象
			Song song_single =new Song();//新建一个实体类
			
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
			
			//存入list
			songs.add(song_single);
		}
		}
		
	}
}
