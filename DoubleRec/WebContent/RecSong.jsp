<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >

<head> 

  <title>DoubleRec | 歌曲推荐页</title>

  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />

  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

  <link rel="stylesheet" href="js/jPlayer/jplayer.flat.css" type="text/css" />

  <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />

  <link rel="stylesheet" href="css/animate.css" type="text/css" />

  <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />

  <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />

  <link rel="stylesheet" href="css/font.css" type="text/css" />

  <link rel="stylesheet" href="css/app.css" type="text/css" />  

    <!--[if lt IE 9]>

    <script src="js/ie/html5shiv.js"></script>

    <script src="js/ie/respond.min.js"></script>

    <script src="js/ie/excanvas.js"></script>

  <![endif]-->

</head>

<body class="">

  <section class="vbox">

  	<!-- 引入header -->
	
	<%@ include file="header.jsp"%>
	<!-- 引入header -->
    <section>

      <section class="hbox stretch">

        <!-- .aside -->
        <%@ include file="aside.jsp"%>

        <!-- /.aside -->

        <section id="content">

          <section class="vbox">

          <section class="w-f-md">

            <section class="hbox stretch bg-black dker">

              <!-- side content -->

              <aside class="col-sm-5 no-padder" id="sidebar">

                <section class="vbox animated fadeInUp">

                  <section class="scrollable">

                    

                    <ul class="list-group list-group-lg no-radius no-border no-bg m-t-n-xxs m-b-none auto">
                    <!-- 推荐音乐的显示，pearson算法 -->
                    
                    <s:if test="songs!=null">
                    <!-- 迭代显示 -->
                    <s:iterator var="song" value="songs" >
                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src=<s:property value="#song.SongPic" /> alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis"><s:property value="#song.SongName" /></span>

                          <small class="text-muted"><s:property value="#song.SongSinger" /></small>

                        </a>

                      </li>
                    
                      </s:iterator>
                      <span>注意：音乐推荐数据为10条，由于计算量过大，此推荐结果会于每天早上进行计算。若无您的推荐结果，则会在明日计算推荐。</span>
                    
                      </s:if>
                      <!-- 推荐音乐的显示，pearson算法 -->
                      <s:else>
                      		<div class="clear text-ellipsis">

                          <span>哎呀，大兄弟，没登录！！！</span>

                          
                        </div>
                      </s:else>
                    </ul>

                  </section>

                </section>

              </aside>

              <!-- / side content -->
<!-- 
              <section class="col-sm-4 no-padder bg">

                <section class="vbox">

                  <section class="scrollable hover">

                    <ul class="list-group list-group-lg no-bg auto m-b-none m-t-n-xxs">

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/m0.jpg" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Little Town</span>

                          <small class="text-muted">by Soph Ashe</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a1.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Get lacinia odio sem nec elit</span>

                          <small class="text-muted">by Filex</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a2.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Donec sed odio du</span>

                          <small class="text-muted">by Dan Doorack</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a3.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Curabitur blandit tempu</span>

                          <small class="text-muted">by Foxes</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a4.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Urna mollis ornare vel eu leo</span>

                          <small class="text-muted">by Chris Fox</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a5.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Faucibus dolor auctor</span>

                          <small class="text-muted">by Lauren Taylor</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a6.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Praesent commodo cursus magn</span>

                          <small class="text-muted">by Chris Fox</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a7.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Vestibulum id</span>

                          <small class="text-muted">by Milian</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a8.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Blandit tempu</span>

                          <small class="text-muted">by Amanda Conlan</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a9.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Vestibulum ullamcorpe quis malesuada augue mco rpe</span>

                          <small class="text-muted">by Dan Doorack</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a10.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Natis ipsum ac feugiat</span>

                          <small class="text-muted">by Hamburg</small>

                        </a>

                      </li>

                      <li class="list-group-item clearfix">

                        <a href="#" class="jp-play-me pull-right m-t-sm m-l text-md">

                          <i class="icon-control-play text"></i>

                          <i class="icon-control-pause text-active"></i>

                        </a>

                        <a href="#" class="pull-left thumb-sm m-r">

                          <img src="images/a0.png" alt="...">

                        </a>

                        <a class="clear" href="#">

                          <span class="block text-ellipsis">Sec condimentum au</span>

                          <small class="text-muted">by Amanda Conlan</small>

                        </a>

                      </li>

                    </ul>

                  </section>

                </section>

              </section>
               -->
            </section>

          </section>

        <!-- 引入footer -->
        <%@ include file="footer.jsp"%>
        <!-- 引入footer -->
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



</body>

</html>