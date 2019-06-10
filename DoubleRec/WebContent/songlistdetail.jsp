<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >
<head>  

  <meta charset="utf-8" />

  <title>DoubleRec | 歌单详情页</title>

  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />

  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

  <link rel="stylesheet" href="js/jPlayer/jplayer.flat.css" type="text/css" />

  <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />

  <link rel="stylesheet" href="css/animate.css" type="text/css" />

  <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />

  <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />

  <link rel="stylesheet" href="css/font.css" type="text/css" />

  <link rel="stylesheet" href="css/app.css" type="text/css" />  
  <link rel="stylesheet" href="css/music.css" type="text/css" /> 
  <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
  

  <!--[if lt IE 9]>

    <script src="js/ie/html5shiv.js"></script>

    <script src="js/ie/respond.min.js"></script>

    <script src="js/ie/excanvas.js"></script>

  <![endif]-->

</head>

<body class="">

  <section class="vbox">
		<!-- 引入header -->
		<%@ include file="header.jsp" %><!-- 引入header -->
    <section>

      <section class="hbox stretch">

        <!-- .aside -->
        	  <%@ include file="aside.jsp" %><!-- 引入aside -->
        <!-- .aside -->
   

        <section id="content">

          <section class="vbox" id="bjax-el">

            <section class="scrollable wrapper-lg">

              <div class="row">

                <div class="col-sm-8">

                  <div class="panel wrapper-lg">

                    <div class="row">
                     <!-- 显示歌单的图片 -->
                      <div class="col-sm-5">

                        <img src=<s:property value="SongListByListId.ListLogo"/> class="img-full m-b">

                      </div>
                       <!-- 显示歌单的图片 -->
                       
                       <!-- 显示歌单的其他内容 -->
                      <div class="col-sm-7" >
                      	<!-- 歌单名 -->
                        <h2 class="m-t-none text-black"><s:property value="SongListByListId.ListTitle"/> </h2>
                         <!-- 歌单名 -->
                         <!-- 歌单的作者 -->
                        <div class="clearfix m-b-lg">

                          <a href="#" class="thumb-sm pull-left m-r">

                            <img src="" class="img-circle">

                          </a>

                          <div class="clear">

                            <a href="#" class="text-info"><s:property value="SongListByListId.ListAuthor"/></a>

                           <!--  <small class="block text-muted">2,415 followers / 225 following</small>--> 

                          </div>

                        </div>
                        <!-- 歌单的作者 -->
                        
                        <!-- 播放按钮 -->
                        <div class="m-b-lg">
                        
                  
                   
                     
                     <!-- 循环用户收藏歌曲的session -->
                             <s:set var="flag" value="true"/>
                             
                              <s:iterator var="collectsonglist" value="#session.collectedSongLists">
                           
                              <s:if test="#collectsonglist.ListId ==SongListByListId.ListId">
                              	<s:if test="flag">
                              <!-- 取消喜欢,即本歌曲已经添加到喜欢 -->
                            	 <a href="javascript:cancleSLCollect();" class="btn btn-default" id="strong2" >取消收藏</a>
                            <a href="javascript:addSLCollect();" class="btn btn-default" id="strong1" style="display: none;">收藏</a>
                        
								  <s:set var="flag" value="false"/>
								  </s:if>
                              </s:if>
                           
								</s:iterator>
                     <!-- 如果循环了一圈，此歌曲不在收藏里面，那么flag肯定是true -->
                     <s:if test="flag">
                     	<!-- 添加喜欢，即本歌曲未添加到用户喜欢 -->
                              <a href="javascript:addSLCollect();" class="btn btn-default" id="strong1">收藏</a>
                          <a href="javascript:cancleSLCollect();" class="btn btn-default" id="strong2" style="display: none;" >取消收藏</a>
                           
                     </s:if>
                        
                        </div>
                        <!-- 播放按钮 -->
                        <!-- 歌单类型标签 -->
                        <div>

                          歌单类型： <!-- 迭代显示此歌单的类型 -->
                          <s:iterator var="sltype" value="SongListType" >
                          <a href="#" class="badge bg-light"> <s:property value="#sltype.typeName"/></a> 
                          </s:iterator>
                        </div>
                        <!-- 歌单类型标签 -->
                      </div>

                    </div>
                    
                    <!-- 简介 -->
                    <div class="m-t">

                      <p> <s:property value="SongListByListId.ListDesc"/></p>

                    </div>
                     <!-- 简介 -->
                    <h4 class="m-t-lg m-b">歌单内容</h4>
                    
                    <!-- 迭代显示单个歌曲的内容，包括名称，时间 -->
                    <ul class="list-group list-group-lg">
                    
                    <!-- 歌曲信息是保存到了pageBeanSong中 -->
                    <s:iterator var="song" value="pageBeanSong.list">
                      <li class="list-group-item"  data-audio=<s:property value="#song.SongUrl" />
                     data-pic=<s:property value="#song.SongPic" />
                      datalrc=<s:property value="#song.SongLrc" /> title=<s:property value="#song.SongName"/>>

                        <div class="pull-right m-l">

                        
                          <!-- 显示收藏图标，用于收藏 -->

                         <!-- 音乐添加喜欢 -->
                             <!-- 循环用户收藏歌曲的session -->
                             <s:set var="flag" value="true"/>
                              <s:iterator var="collectsong" value="#session.collectSongs">
                             
                              <s:if test="#collectsong.SongId ==#song.SongId">
                              	<s:if test="flag">
                              <!-- 取消喜欢,即本歌曲已经添加到喜欢 -->
                            	  <a href="javascript:void(0)"  onclick="cancleCollect(this)"  data-songid=<s:property value="#song.SongId" />  class="pull-right active"  id="collect2<s:property value="#song.SongId" />">
										 <i class="fa fa-heart text-active text-danger"></i>
								   </a>
								   <a  href="javascript:void(0)"  style="display: none;" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#song.SongId" /> id="collect1<s:property value="#song.SongId" />">

                                		 <i class="fa fa-heart-o text"></i>
                               
                              		</a>
								  <s:set var="flag" value="false"/>
								  </s:if>
                              </s:if>
                           
								</s:iterator>
                     <!-- 如果循环了一圈，此歌曲不在收藏里面，那么flag肯定是true -->
                     <s:if test="flag">
                     	<!-- 添加喜欢，即本歌曲未添加到用户喜欢 -->
                              	<a  href="javascript:void(0)" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#song.SongId" /> id="collect1<s:property value="#song.SongId" />">

                                 <i class="fa fa-heart-o text"></i>
                               
                              	</a>
                              	 <a href="javascript:void(0)"  onclick="cancleCollect(this)" style="display: none;" data-songid=<s:property value="#song.SongId" />  class="pull-right active"  id="collect2<s:property value="#song.SongId" />">
										 <i class="fa fa-heart text-active text-danger"></i>
								   </a>
                     </s:if>
                                 <i class="fa fa-heart text-active text-danger"></i>

                        </div>

                        
                        <div class="clear text-ellipsis">

                          <span><s:property value="#song.SongName"/></span>

                          <span class="text-muted"> -- <s:property value="#song.SongSinger"/></span>

                        </div>

                      </li>
                      </s:iterator>
                      
                    </ul>
                     
                      <!-- 分页显示 -->
                      <ul class="pagination pagination">
                     <span> 第<s:property value="pageBeanSong.page"/>/<s:property value="pageBeanSong.totalPage"/>页</span>
                       <s:if test="pageBeanSong.page!=1"><!-- 加入判断，当前的页面不是第一页的时候可以点上一页 -->
                        <li><a href="${pageContext.request.contextPath }/songlist_detail.action?ListId=<s:property value="ListId"/>&page=<s:property value="pageBeanSong.page-1" />"><i class="fa fa-chevron-left"></i></a></li><!-- 前一页 -->
                       </s:if>
                       <!-- 迭代显示下面的页数 -->
                       <s:iterator var="i" begin="1" end="pageBeanSong.totalPage">
                       		<!-- 下面这个页面时用来判断当前的页面是否被选中，然后给予不同的显示方式 -->
                       		<s:if test="pageBeanSong.page !=#i">
                       		<li><a href="${pageContext.request.contextPath }/songlist_detail.action?ListId=<s:property value="ListId"/>&page=<s:property value="#i" />"><s:property value="#i"/></a></li>
                        	</s:if>
                        	<s:else>
                        	<li class="active"><a href="${pageContext.request.contextPath }/songlist_detail.action?ListId=<s:property value="ListId"/>&page=<s:property value="#i" />"><s:property value="#i"/></a></li>
                        	</s:else>
                        </s:iterator>
                        <s:if test="pageBeanSong.page!=pageBeanSong.totalPage">
                        <li><a href="${pageContext.request.contextPath }/songlist_detail.action?ListId=<s:property value="ListId"/>&page=<s:property value="pageBeanSong.page+1" />"><i class="fa fa-chevron-right"></i></a></li><!-- 后一页 -->
                        </s:if>
                      </ul>
                       <!-- 分页显示 -->
                       
                    
                    <!-- 歌单评论 -->
                   

                  </div>

                </div>

                <div class="col-sm-4">

                  <div class="panel panel-default">

                    <div class="panel-heading">同类歌单推荐：</div>

                   <div class="panel-body">
                   
                   <!-- 显示单个推荐的歌单 -->
                    <s:iterator var="rec" value="RecSongList" >
                      <article class="media">

                        <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#rec.ListId"/>&page=1" class="pull-left thumb-md m-t-xs">

                          <img src=<s:property value="#rec.ListLogo" />>

                        </a>

                        <div class="media-body">                        
                        <!-- 歌单名 -->
                          <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#rec.ListId"/>&page=1" class="font-semibold"><s:property value="#rec.ListTitle" /></a>
                          <!-- 作者ming -->
                          <div class="text-xs block m-t-xs"><s:property value="#rec.ListAuthor" /></div>

                        </div>

                      </article>
                      </s:iterator>
                      <!-- 显示单个推荐的歌单 -->

                    </div>

                  </div>

                </div>

              </div>
            
            </section>
		<%@ include file="footer.jsp" %>
          </section>
 	
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>

        </section>
        
        
 			
        <!-- /.aside -->
      </section>

    </section>    

  </section>
  
  <!-- 用于添加收藏和取消收藏 -->
  <script type="text/javascript">
  //应该在这里就加入用户不存在的判断，如果用户没有登录，在这里就提示用户进行登录
  
  
    function cancleSLCollect(){
  		var ListId="<s:property value="SongListByListId.ListId"/>";
     
      $.get("songlist_canclecollect2.action?ListId="+ListId,function(data,status){
          document.getElementById("strong1").style.display="block";
          document.getElementById("strong2").style.display="none";
      });
      return false;                       
    }
  
    function addSLCollect(){
    	//先获取用户名验证是否为空，为空就不进行那个添加收藏了
    	var username="<s:property value="#session.existUser.userName"/>";
    	if(username)
    	{
    		var ListId="<s:property value="SongListByListId.ListId"/>";
       
        $.get("songlist_collect2.action?ListId="+ListId,function(data,status){
            document.getElementById("strong1").style.display="none";
            document.getElementById("strong2").style.display="block";
        });
        return false;
    	}
    	else//用户名为空
    		{
    			alert("登录才能体验更多功能的，兄弟！");
    		}
    }
</script>
  
  <script >
  		function saveCollect()
  		{
  			//获取ListId以及用户id
  			var username="<s:property value="#session.existUser.userName"/>";
  			var ListId="<s:property value="SongListByListId.ListId"/>";
  			//已经成功获取
  			
  		//使用ajax
  			//1:创建异步交互的对象 
  			var xhr=null;
  			xhr=creatXmlHttp();
  			//2:设置监听
  			xhr.onreadystatechange=function()
  			{
  				if(xhr.readyState==4)
  					{
  						if(xhr.status==200)
  							{
  								document.getElementById("strong1").innerHTML= xhr.responseText;
  							}
  					}
  			}
  			//3:打开链接
  			xhr.open("GET","songlist_collect.action?ListId="+ListId,true);
  			//4:发送
  			xhr.send(null);
  		}
  		
  		//获取httprequerst对象
  		function creatXmlHttp()
  		{
  			var xmlHttp;
  			try
  			{
  				xmlHttp=new XMLHttpRequest();
  			}
  			catch(e)
  			{
  				try
  				{
  					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
  				}
  				catch(e)
  				{
  					try
  					{
  						xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
  					}
  					catch(e)
  					{
  						
  					}
  				}
  			}
  			return xmlHttp;
  		}
  		
  		 //应该在这里就加入用户不存在的判断，如果用户没有登录，在这里就提示用户进行登录
  	  

  	  
  	    function cancleCollect(target){
  	  	
  		  var username="<s:property value="#session.existUser.userName"/>";
  		  if(username)
  	    	 {
  	    	 var SongId=$(target).attr("data-songid");//获取SongId
  	    	 
  	      $.get("song_canclecollect.action?SongId="+SongId,function(data,status){
  	          document.getElementById("collect1"+SongId).style.display="block";
  	         document.getElementById("collect2"+SongId).style.display="none";
  	         alert("取消成功，您对本音乐的评分-2");
  	      });
  	      return false;                       
  	    	 }
  	     else
  	    	 {
  	 			alert("登录才能体验更多功能的，兄弟！");
  	 			event.preventDefault();
  	 		
  	    	 }
  	    }
  	  
  	    function addCollect(target){
  	    	//先获取用户名验证是否为空，为空就不进行那个添加收藏了
  	    	var username="<s:property value="#session.existUser.userName"/>";
  	    	
  	    	if(username)
  	    	{
  	    		var SongId=$(target).attr("data-songid");//获取SongId
  	    
  	        $.get("song_collect.action?SongId="+SongId,function(data,status){
  	        	 document.getElementById("collect1"+SongId).style.display="none";
  	             document.getElementById("collect2"+SongId).style.display="block";
  	            alert("添加收藏成功！您对本音乐的评分+2");
  	        });
  	        return false;
  	    	}
  	    	else//用户名为空
  	    		{
  	    			alert("登录才能体验更多功能的，兄弟！");
  	    			event.preventDefault();
  	    		}
  	    }
  		
  </script>

  <script src="js/jquery.min.js"></script>

  <!-- Bootstrap -->

  <script src="js/bootstrap.js"></script>

  <!-- App -->

  <script src="js/app.js"></script>  

  <script src="js/slimscroll/jquery.slimscroll.min.js"></script>

  

  <script src="js/app.plugin.js"></script>

  <script type="text/javascript" src="js/jPlayer/jquery.jplayer.min.js"></script>

  <script type="text/javascript" src="js/jPlayer/add-on/jplayer.playlist.min.js"></script>

  <script type="text/javascript" src="js/jPlayer/demo.js"></script>
    <script type="text/javascript" src="js/music.js"></script>

</body>

</html>