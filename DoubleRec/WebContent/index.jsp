<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%><%@ taglib uri="/struts-tags" prefix="s" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >
<head>  
  <title>DoubleRec | 主页</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="css/animate.css" type="text/css" />
  <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="css/font.css" type="text/css" />
  <link rel="stylesheet" href="css/app.css" type="text/css" />    <link rel="stylesheet" href="css/music.css" type="text/css" /> 
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
        <!-- .aside -->        <%@ include file="aside.jsp" %>
        <!-- /.aside -->
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">
                <section class="scrollable padder-lg w-f-md" id="bjax-target">
                 
                  <h2 class="font-thin m-b">总排行推荐 <span class="musicbar animate inline m-l-sm" style="width:20px;height:20px">
                    <span class="bar1 a1 bg-primary lter"></span>
                    <span class="bar2 a2 bg-info lt"></span>
                    <span class="bar3 a3 bg-success"></span>
                    <span class="bar4 a4 bg-warning dk"></span>
                    <span class="bar5 a5 bg-danger dker"></span>
                  </span></h2>                                                    
                  <div class="row row-sm">                  <!-- 单个音乐项的显示 ，然后进行迭代-->                  <s:iterator var="s" value="dSList" >                  <!-- 注意下面的data-url,data-pic,data-lrc，是查询出来的一些播放需要用的属性 -->
                    <div id="result"  class="col-xs-6 col-sm-4 col-md-3 col-lg-2"  data-songid=<s:property value="#s.SongId" />  data-audio=<s:property value="#s.SongUrl" />                     data-pic=<s:property value="#s.SongPic" /> datalrc=<s:property value="#s.SongLrc" /> title=<s:property value="#s.SongName"/>>                    
                      <div class="item">                                                                                                                                                                                                                                                           
                        <dir class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">

                            <div class="center text-center m-t-n">
                              <a href="javascript:;" id="to-play"><i class="icon-control-play i-2x" data-songid=<s:property value="#s.SongId" />  data-audio=<s:property value="#s.SongUrl" />                     data-pic=<s:property value="#s.SongPic" /> datalrc=<s:property value="#s.SongLrc" /> title=<s:property value="#s.SongName"/>></i></a>
                            </div>
                            
                          </div>                          <!-- 图片的显示 -->
                          <a href="#"><img src="<s:property value="#s.SongPic" />" alt="" class="r r-2x img-full"></a>
                        </dir>                                                <div class="bottom padder m-b-sm">                                                         <!-- 音乐添加喜欢 -->                             <!-- 循环用户收藏歌曲的session -->                             <s:set var="flag" value="true"/>                              <s:iterator var="collectsong" value="#session.collectSongs">                                                           <s:if test="#collectsong.SongId ==#s.SongId">                              	<s:if test="flag">                              <!-- 取消喜欢,即本歌曲已经添加到喜欢 -->                            	  <a href="javascript:void(0)"  onclick="cancleCollect(this)"  data-songid=<s:property value="#s.SongId" />  class="pull-right active"  id="collect2<s:property value="#s.SongId" />">										 <i class="fa fa-heart text-active text-danger"></i>								   </a>								   <a  href="javascript:void(0)"  style="display: none;" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#s.SongId" /> id="collect1<s:property value="#s.SongId" />">                                		 <i class="fa fa-heart-o text"></i>                                                             		</a>								  <s:set var="flag" value="false"/>								  </s:if>                              </s:if>                           								</s:iterator>                     <!-- 如果循环了一圈，此歌曲不在收藏里面，那么flag肯定是true -->                     <s:if test="flag">                     	<!-- 添加喜欢，即本歌曲未添加到用户喜欢 -->                              	<a  href="javascript:void(0)" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#s.SongId" /> id="collect1<s:property value="#s.SongId" />">                                 <i class="fa fa-heart-o text"></i>                                                             	</a>                              	 <a href="javascript:void(0)"  onclick="cancleCollect(this)" style="display: none;" data-songid=<s:property value="#s.SongId" />  class="pull-right active"  id="collect2<s:property value="#s.SongId" />">										 <i class="fa fa-heart text-active text-danger"></i>								   </a>                     </s:if>                                 <i class="fa fa-heart text-active text-danger"></i>                              </a>                                                         </div>                                                                        </div>
                        <div class="padder-v">                        
                        <a href="#" class="text-ellipsis"><s:property value="#s.SongName" /></a>
                          <a href="#" class="text-ellipsis text-xs text-muted"><s:property value="#s.SongSinger" /></a>                             </div>
                      </div>					</s:iterator>
                    </div>                                        <!-- 单个音乐项的显示 -->
                   
                  <div class="row">
                    <div class="col-md-7">
                      <h3 class="font-thin">酷玩</h3>
                      <div class="row row-sm">                      <!-- 单个音乐项的显示 ，最新音乐-->                      <s:iterator var="s" value="nSList" >
                        <div class="col-xs-6 col-sm-3" data-id=<s:property value="#s.SongId" /> data-audio=<s:property value="#s.SongUrl" />                     data-pic=<s:property value="#s.SongPic" /> datalrc=<s:property value="#s.SongLrc" /> title=<s:property value="#s.SongName"/>>
                          <div class="item">
                            <div class="pos-rlt">
                              <div class="item-overlay opacity r r-2x bg-black">
                                <div class="center text-center m-t-n">
                                  <a href="#"><i class="fa fa-play-circle i-2x"></i></a>
                                </div>
                              </div>
                              <a href="#"><img src=<s:property value="#s.SongPic" /> alt="" class="r r-2x img-full"></a>                                                        <!-- 音乐添加喜欢 -->                             <!-- 循环用户收藏歌曲的session -->                             <s:set var="flag" value="true"/>                              <s:iterator var="collectsong" value="#session.collectSongs">                                                           <s:if test="#collectsong.SongId ==#s.SongId">                              	<s:if test="flag">                              <!-- 取消喜欢,即本歌曲已经添加到喜欢 -->                            	  <a href="javascript:void(0)"  onclick="cancleCollect(this)"  data-songid=<s:property value="#s.SongId" />  class="pull-right active"  id="collect2<s:property value="#s.SongId" />">										 <i class="fa fa-heart text-active text-danger"></i>								   </a>								   <a  href="javascript:void(0)"  style="display: none;" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#s.SongId" /> id="collect1<s:property value="#s.SongId" />">                                		 <i class="fa fa-heart-o text"></i>                                                             		</a>								  <s:set var="flag" value="false"/>								  </s:if>                              </s:if>                           								</s:iterator>                     <!-- 如果循环了一圈，此歌曲不在收藏里面，那么flag肯定是true -->                     <s:if test="flag">                     	<!-- 添加喜欢，即本歌曲未添加到用户喜欢 -->                              	<a  href="javascript:void(0)" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#s.SongId" /> id="collect1<s:property value="#s.SongId" />">                                 <i class="fa fa-heart-o text"></i>                                                             	</a>                              	 <a href="javascript:void(0)"  onclick="cancleCollect(this)" style="display: none;" data-songid=<s:property value="#s.SongId" />  class="pull-right active"  id="collect2<s:property value="#s.SongId" />">										 <i class="fa fa-heart text-active text-danger"></i>								   </a>                     </s:if>                                 <i class="fa fa-heart text-active text-danger"></i>                                                           
                            </div>
                            <div class="padder-v">
                              <a href="#" class="text-ellipsis"><s:property value="#s.SongName" /></a>
                              <a href="#" class="text-ellipsis text-xs text-muted"><s:property value="#s.SongSinger" /></a>
                            </div>
                          </div>
                        </div>                        </s:iterator>                        <!-- 单个音乐项的显示 -->
                      </div>
                    </div>
                    <div class="col-md-5">
                      <h3 class="font-thin">其他</h3>
                      <div class="list-group bg-white list-group-lg no-bg auto">                                                                      <!-- 单个音乐显示项  music -->                      <s:iterator var="s" value="tSList" >
                        <a href="#" class="list-group-item clearfix"  data-audio=<s:property value="#s.SongUrl" />                     data-pic=<s:property value="#s.SongPic" /> datalrc=<s:property value="#s.SongLrc" /> title=<s:property value="#s.SongName"/>>                         
                          <span class="pull-right h2 text-muted m-l">                                                  	 <!-- 音乐添加喜欢 -->                             <!-- 循环用户收藏歌曲的session -->                             <s:set var="flag" value="true"/>                              <s:iterator var="collectsong" value="#session.collectSongs">                                                           <s:if test="#collectsong.SongId ==#s.SongId">                              	<s:if test="flag">                              <!-- 取消喜欢,即本歌曲已经添加到喜欢 -->                            	  <a href="javascript:void(0)"  onclick="cancleCollect(this)"  data-songid=<s:property value="#s.SongId" />  class="pull-right active"  id="collect2<s:property value="#s.SongId" />">										 <i class="fa fa-heart text-active text-danger"></i>								   </a>								   <a  href="javascript:void(0)"  style="display: none;" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#s.SongId" /> id="collect1<s:property value="#s.SongId" />">                                		 <i class="fa fa-heart-o text"></i>                                                             		</a>								  <s:set var="flag" value="false"/>								  </s:if>                              </s:if>                           								</s:iterator>                     <!-- 如果循环了一圈，此歌曲不在收藏里面，那么flag肯定是true -->                     <s:if test="flag">                     	<!-- 添加喜欢，即本歌曲未添加到用户喜欢 -->                              	<a  href="javascript:void(0)" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#s.SongId" /> id="collect1<s:property value="#s.SongId" />">                                 <i class="fa fa-heart-o text"></i>                                                             	</a>                              	 <a href="javascript:void(0)"  onclick="cancleCollect(this)" style="display: none;" data-songid=<s:property value="#s.SongId" />  class="pull-right active"  id="collect2<s:property value="#s.SongId" />">										 <i class="fa fa-heart text-active text-danger"></i>								   </a>                     </s:if>                                 <i class="fa fa-heart text-active text-danger"></i>                              </a>                                                  </span>
                          <span class="pull-left thumb-sm avatar m-r">                                                    	
                            <img src=<s:property value="#s.SongPic" /> alt="...">
                          </span>
                          <span class="clear">                          
                            <span><s:property value="#s.SongName" /></span>
                            <small class="text-muted clear text-ellipsis"><s:property value="#s.SongSinger"/></small>
                          </span>                                                   
                        </a>                        </s:iterator>                        <!-- 单个音乐显示项  topmusic -->                        
                        
                      </div>
                    </div>
                  </div>
                </section>                 <%@ include file="footer.jsp" %>
                
              </section>
            </section>
            <!-- side content -->
            <!-- / side content -->
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
        </section>
      </section>
    </section>    
  </section><!-- 用于添加收藏和取消收藏 -->  <script type="text/javascript">  //应该在这里就加入用户不存在的判断，如果用户没有登录，在这里就提示用户进行登录        function cancleCollect(target){  		  var username="<s:property value="#session.existUser.userName"/>";	      if(username)    	 {    	 var SongId=$(target).attr("data-songid");//获取SongId    	      $.get("song_canclecollect.action?SongId="+SongId,function(data,status){          document.getElementById("collect1"+SongId).style.display="block";         document.getElementById("collect2"+SongId).style.display="none";         alert("取消成功，您对本音乐的评分-2");      });      return false;                           	 }     else    	 { 			alert("登录才能体验更多功能的，兄弟！"); 			event.preventDefault(); 		    	 }    }      function addCollect(target){    	//先获取用户名验证是否为空，为空就不进行那个添加收藏了    	var username="<s:property value="#session.existUser.userName"/>";        	if(username)    	{    		var SongId=$(target).attr("data-songid");//获取SongId            $.get("song_collect.action?SongId="+SongId,function(data,status){        	 document.getElementById("collect1"+SongId).style.display="none";             document.getElementById("collect2"+SongId).style.display="block";            alert("添加收藏成功！您对本音乐的评分+2");        });        return false;    	}    	else//用户名为空    		{    			alert("登录才能体验更多功能的，兄弟！");    			event.preventDefault();    		}    }</script>
  <script src="js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="js/bootstrap.js"></script>
  <!-- App -->
  <script src="js/app.js"></script>  
  <script src="js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/app.plugin.js"></script>
  <script type="text/javascript" src="js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="js/jPlayer/demo.js"></script>  <script type="text/javascript" src="js/music.js"></script>
</body>
</html>