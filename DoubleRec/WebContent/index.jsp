<%@ page language="java" contentType="text/html; charset=UTF-8"
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
  <link rel="stylesheet" href="css/app.css" type="text/css" />  
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
                  <div class="row row-sm">
                    <div id="result"  class="col-xs-6 col-sm-4 col-md-3 col-lg-2"  data-songid=<s:property value="#s.SongId" />  data-audio=<s:property value="#s.SongUrl" />
                      <div class="item">                                                                                                                                                                                                                                                           
                        <dir class="pos-rlt">
                          <div class="item-overlay opacity r r-2x bg-black">

                            <div class="center text-center m-t-n">
                              <a href="javascript:;" id="to-play"><i class="icon-control-play i-2x" data-songid=<s:property value="#s.SongId" />  data-audio=<s:property value="#s.SongUrl" />
                            </div>
                            
                          </div>
                          <a href="#"><img src="<s:property value="#s.SongPic" />" alt="" class="r r-2x img-full"></a>
                        </dir>
                        <div class="padder-v">
                        <a href="#" class="text-ellipsis"><s:property value="#s.SongName" /></a>
                          <a href="#" class="text-ellipsis text-xs text-muted"><s:property value="#s.SongSinger" /></a>
                      </div>
                    </div>
                   
                  <div class="row">
                    <div class="col-md-7">
                      <h3 class="font-thin">酷玩</h3>
                      <div class="row row-sm">
                        <div class="col-xs-6 col-sm-3" data-id=<s:property value="#s.SongId" /> data-audio=<s:property value="#s.SongUrl" />
                          <div class="item">
                            <div class="pos-rlt">
                              <div class="item-overlay opacity r r-2x bg-black">
                                <div class="center text-center m-t-n">
                                  <a href="#"><i class="fa fa-play-circle i-2x"></i></a>
                                </div>
                              </div>
                              <a href="#"><img src=<s:property value="#s.SongPic" /> alt="" class="r r-2x img-full"></a>
                            </div>
                            <div class="padder-v">
                              <a href="#" class="text-ellipsis"><s:property value="#s.SongName" /></a>
                              <a href="#" class="text-ellipsis text-xs text-muted"><s:property value="#s.SongSinger" /></a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-5">
                      <h3 class="font-thin">其他</h3>
                      <div class="list-group bg-white list-group-lg no-bg auto">                          
                        <a href="#" class="list-group-item clearfix"  data-audio=<s:property value="#s.SongUrl" />
                          <span class="pull-right h2 text-muted m-l">
                          <span class="pull-left thumb-sm avatar m-r">
                            <img src=<s:property value="#s.SongPic" /> alt="...">
                          </span>
                          <span class="clear">
                            <span><s:property value="#s.SongName" /></span>
                            <small class="text-muted clear text-ellipsis"><s:property value="#s.SongSinger"/></small>
                          </span>
                        </a>
                        
                      </div>
                    </div>
                  </div>
                </section>
                
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
</body>
</html>