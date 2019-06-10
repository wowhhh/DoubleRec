<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >

<head>  

  <title>DoubleRec | 歌单收藏页</title>

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

				<!-- 显示收藏的单个歌单的信息，迭代显示 -->
				<s:iterator var="collected" value="collectedSonglists">
                <div class="col-xs-6 col-sm-4 col-md-3">

                  <div class="item">

                    <div class="pos-rlt">

                      <div class="item-overlay opacity r r-2x bg-black">

                        <div class="center text-center m-t-n">

                          <a href="#"><i class="fa fa-play-circle i-2x"></i></a>

                        </div>

                      </div>

                      <a href="#"><img src=<s:property value="#collected.ListLogo" /> alt="" class="r r-2x img-full"></a>

                    </div>

                    <div class="padder-v">

                      <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#collected.ListId"/>&page=1" class="text-ellipsis"><s:property value="#collected.ListTitle" /></a>

                      <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#collected.ListId"/>&page=1" class="text-ellipsis text-xs text-muted"><s:property value="#collected.ListAuthor" /></a>

                    </div>

                  </div>

                </div>
                </s:iterator>
                

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