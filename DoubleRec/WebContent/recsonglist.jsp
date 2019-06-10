<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >
<head>  

  <meta charset="utf-8" />

  <title>DoubleRec | 歌单推荐页</title>

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
         	<a href="#" class="btn btn-s-md btn-default disabled">根据计算：推测您可能喜欢以下歌单：</a>
         				  <div class="row row-sm">
                      
                      <!-- 单个歌单信息的显示 -->
                      <s:iterator var="slrec" value="RecSongList" >
                        <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">

                          <div class="item">

                            <div class="pos-rlt">

                              <div class="item-overlay opacity r r-2x bg-black">

                                <div class="center text-center m-t-n">
                                <!-- 点击事件，显示歌单详情 -->
                                  <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#slrec.ListId"/>&page=1"><i class="fa fa-play-circle i-2x"></i></a>

                                </div>

                              </div>
                               <!-- 点击事件，显示歌单详情 -->
                              <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#slrec.ListId"/>&page=1"><img src=<s:property value="#slrec.ListLogo"/> alt="" class="r r-2x img-full"></a>

                            </div>

                            <div class="padder-v">
                             <!-- 点击事件，显示歌单详情 -->
                              <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#slrec.ListId"/>&page=1" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis"><s:property value="#slrec.ListTitle"/></a>
                               <!-- 点击事件，显示歌单详情 -->
                              <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#slrec.ListId"/>&page=1" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted"><s:property value="#slrec.ListAuthor"/></a>

                            </div>

                          </div>

                        </div>
                        </s:iterator>
                        <!-- 单个歌单信息的显示 -->
                     
                      </div>
                  </div>

                </div>

                <div class="col-sm-4">

                  <div class="panel panel-default">

                    <div class="panel-heading">根据计算，推测您喜欢的其他音乐体裁为：</div>

                   <div class="doc-buttons">
                  
                  		<s:if test="types !=null">
		                <s:iterator var="type" value="types">
		                <a href="#" class="btn btn-s-md btn-primary btn-rounded"><s:property value="#type.typeName" /></a>
		                </s:iterator>
		                </s:if>
		                <s:else>
		                	<a href="#" class="btn btn-s-md btn-default disabled">哎呀，大兄弟，没登录啊！</a>
		                </s:else>
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