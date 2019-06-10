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
 * �������ӣ�Ȼ���ȡjson�ļ������н��������ؽ����һ��������Ϣ�ļ���
 * @author wyb
 *
 */
public class GetAndAnalyseJSON {
	
	private List<Song> songs=new LinkedList<Song>();//���ڱ���ÿ�׸���������
	
	public List<Song> GetSearchSongs(String message)//��ȡ�����������
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
		//ֵ��ע����ǣ������в����������ģ����������Ҫ���½��б���
		String url=GetUrl("Taylor");
		String jsonfile=this.GetJSON(url);
		System.out.println(jsonfile);
		//�ж�jsonfile�ǲ��ǿ�
	
		System.out.println("end");
	}
	
	public String GetUrl(String message)
	{
		//��message���±���
		try {
			message=URLEncoder.encode(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ϳ��µ�url
		String url="https://api.itooi.cn/music/tencent/search?key=579621905&s="+message+"&limit=20&offset=0&type=song";
		return url;
	}
	
	public String GetJSON(String urlStr)
	{
	       
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
	        } 
		    catch (Exception ex) {   
		            
	        } 
		    finally{  
		    	
		         try{             
			          if(in!=null) {  
			           in.close(); //�ر���    
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
		
		//�п������������ǿգ���ôdata_string ����[]
		if(data_string.equals("[]"))
		{
			songs=null;
		}
		else {
		
		JSONArray data_array=JSONArray.fromObject(data_string);//data�������һ��������
		//����֪����20���������ݵ�
		for(int i=0;i<20;i++)
		{
			String one=data_array.getString(i);//������ȡ
			JSONObject  song=JSONObject.fromObject(one);//ת��Ϊjson����
			Song song_single =new Song();//�½�һ��ʵ����
			
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
			
			//����list
			songs.add(song_single);
		}
		}
		
	}
}
