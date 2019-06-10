<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >

<head>  

  <title>DoubleRec | 歌曲收藏页</title>

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


<h3 class="font-thin m-b">已收藏</h3>
   <div class="row row-sm">
   					
   					
                  <!-- 单个音乐项的显示 ，然后进行迭代-->
                  <s:if test="collectedSongs!=null">
                  <s:iterator var="s" value="collectedSongs" >
                  <!-- 注意下面的data-url,data-pic,data-lrc，是查询出来的一些播放需要用的属性 -->
                    <div id="result"  class="col-xs-6 col-sm-4 col-md-3 col-lg-2"  data-songid=<s:property value="#s.SongId" />  data-audio=<s:property value="#s.SongUrl" />
                     data-pic=<s:property value="#s.SongPic" /> datalrc=<s:property value="#s.SongLrc" /> title=<s:property value="#s.SongName"/>>
                    
                      <div class="item">                                                                                                                                                                                                                                                           

                        <dir class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">


                            <div class="center text-center m-t-n">

                              <a href="javascript:;" id="to-play"><i class="icon-control-play i-2x" data-songid=<s:property value="#s.SongId" />  data-audio=<s:property value="#s.SongUrl" />
                     data-pic=<s:property value="#s.SongPic" /> datalrc=<s:property value="#s.SongLrc" /> title=<s:property value="#s.SongName"/>></i></a>

                            </div>
                            
                          </div>
                          <!-- 图片的显示 -->
                          <a href="#"><img src="<s:property value="#s.SongPic" />" alt="" class="r r-2x img-full"></a>

                        </dir>
                        
                        <div class="bottom padder m-b-sm">
                            
                             <!-- 音乐添加喜欢 -->
                               <!-- 取消喜欢,即本歌曲已经添加到喜欢 -->
                            	  <a href="javascript:void(0)"  onclick="cancleCollect(this)"  data-songid=<s:property value="#s.SongId" />  class="pull-right active"  id="collect2<s:property value="#s.SongId" />">
										 <i class="fa fa-heart text-active text-danger"></i>
								   </a>
								   <a  href="javascript:void(0)"  style="display: none;" onclick="addCollect(this)" class="pull-right"  data-songid=<s:property value="#s.SongId" /> id="collect1<s:property value="#s.SongId" />">

                                		 <i class="fa fa-heart-o text"></i>
                               
                              		</a>
							
							
                                 <i class="fa fa-heart text-active text-danger"></i>
                             
                            </div>
                        
                        
                        </div>
                        <div class="padder-v">
                        
                        <a href="#" class="text-ellipsis"><s:property value="#s.SongName" /></a>

                          <a href="#" class="text-ellipsis text-xs text-muted"><s:property value="#s.SongSinger" /></a>

                        </div>

                      </div>
					</s:iterator>
					</s:if>
					<s:else>
						<span>老哥。你还没收藏歌曲啊！</span>
					</s:else>
                    </div>

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

  <script src="js/app.js"></script>  

  <script src="js/slimscroll/jquery.slimscroll.min.js"></script>

    <script src="js/app.plugin.js"></script>

  <script type="text/javascript" src="js/jPlayer/jquery.jplayer.min.js"></script>

  <script type="text/javascript" src="js/jPlayer/add-on/jplayer.playlist.min.js"></script>

  <script type="text/javascript" src="js/jPlayer/demo.js"></script>
  <script type="text/javascript" src="js/music.js"></script>

</body>

</html>