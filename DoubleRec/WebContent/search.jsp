<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >

<head>  

  <title>DoubleRec | 搜索结果显示页</title>

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

    <!--[if lt IE 9]>

    <script src="js/ie/html5shiv.js"></script>

    <script src="js/ie/respond.min.js"></script>

    <script src="js/ie/excanvas.js"></script>

  <![endif]-->

</head>

<body class="">

  <section class="vbox">

   <%@ include file="header.jsp" %>

    <section>

      <section class="hbox stretch">

        <!-- .aside -->
        <%@ include file="aside.jsp" %>

        <!-- /.aside -->

        <section id="content">

          <section class="hbox stretch">

            <section>

              <section class="vbox">

                <section class="scrollable padder-lg w-f-md" id="bjax-target">

<s:if test="searchsongs!=null">
<div class="col-md-5">

                      <h3 class="font-thin">搜索结果如下：</h3>

                      <div class="list-group bg-white list-group-lg no-bg auto">                          
                      
                      <!-- 先判断搜索结果是不是0，也就是searchsongs是不是null -->
                      
                      <!-- 循环显示10个搜索结果 -->
                      <s:iterator var="song" value="searchsongs"  begin="0" end="9" status="s">
                       <a href="#" class="list-group-item clearfix"  data-audio=<s:property value="#song.SongUrl" />
                     data-pic=<s:property value="#song.SongPic" /> datalrc=<s:property value="#song.SongLrc" /> title=<s:property value="#song.SongName"/>>
                         
                          <span class="pull-right h2 text-muted m-l">
                          
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
                             
                        
                          </span>

                          <span class="pull-left thumb-sm avatar m-r">
                          
                          	
                            <img src=<s:property value="#song.SongPic" /> alt="...">

                          </span>

                          <span class="clear">
                          
                            <span><s:property value="#song.SongName" /></span>

                            <small class="text-muted clear text-ellipsis"><s:property value="#song.SongSinger"/></small>

                          </span>
                          

                         
                        </a>
					</s:iterator>
                      
                      

                      </div>

</div>
<div class="col-md-5">

                      <h3 class="font-thin">搜索结果如下：</h3>

                      <div class="list-group bg-white list-group-lg no-bg auto">                          

                        <!-- 循环显示10个搜索结果 -->
                      <s:iterator var="song" value="searchsongs"  begin="10" end="19" status="s">
                       <a href="#" class="list-group-item clearfix"  data-audio=<s:property value="#song.SongUrl" />
                     data-pic=<s:property value="#song.SongPic" /> datalrc=<s:property value="#song.SongLrc" /> title=<s:property value="#song.SongName"/>>
                         
                          <span class="pull-right h2 text-muted m-l">
                          
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
                             
                        
                          </span>

                          <span class="pull-left thumb-sm avatar m-r">
                          
                          	
                            <img src=<s:property value="#song.SongPic" /> alt="...">

                          </span>

                          <span class="clear">
                          
                            <span><s:property value="#song.SongName" /></span>

                            <small class="text-muted clear text-ellipsis"><s:property value="#song.SongSinger"/></small>

                          </span>
                          

                         
                        </a>
					</s:iterator>
                       

                      </div>

</div>
</s:if>
<!-- 为空 -->
 <s:else>
 <div class="col-md-5">

                      <h3 class="font-thin">搜索结果如下：</h3>

                      <div class="list-group bg-white list-group-lg no-bg auto">       
                       <a href="#" class="list-group-item clearfix">

                          <span class="pull-right h2 text-muted m-l">emmm:</span>

                          <span class="pull-left thumb-sm avatar m-r">


                          </span>

                          <span class="clear">

                            <span>搜索结果为空！</span>

                            <small class="text-muted clear text-ellipsis">可以考虑更换搜索词重新搜索。</small>

                          </span>

                        </a>
                        </div>
                        </div>
                       </s:else>

  </section>
                 <%@ include file="footer.jsp" %>
                

              </section>

            </section>

            <!-- side content -->

            <!-- / side content -->

          </section>

          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>

        </section>

      </section>

    </section>    

  </section>

  <script src="js/jquery.min.js"></script>

  <!-- Bootstrap -->

  <script src="js/bootstrap.js"></script>

  <!-- App -->

	<!-- 用于添加收藏和取消收藏 -->
  <script type="text/javascript">
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
  <script src="js/app.js"></script>  

  <script src="js/slimscroll/jquery.slimscroll.min.js"></script>

    <script src="js/app.plugin.js"></script>

  <script type="text/javascript" src="js/jPlayer/jquery.jplayer.min.js"></script>

  <script type="text/javascript" src="js/jPlayer/add-on/jplayer.playlist.min.js"></script>

  <script type="text/javascript" src="js/jPlayer/demo.js"></script>
  <script type="text/javascript" src="js/music.js"></script>

</body>

</html>