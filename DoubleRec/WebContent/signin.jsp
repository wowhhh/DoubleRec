<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%><%@ taglib uri="/struts-tags" prefix="s" %><!DOCTYPE html><html   class="app">
<head>  
  <title>DoubleRec | 登录</title>
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
<body class="bg-info dker">
  <section id="content" class="m-t-lg wrapper-md animated fadeInUp">    
    <div class="container aside-xl">
      <a class="navbar-brand block" href="${ pageContext.request.contextPath }/index.action"><span class="h1 font-bold">DoubleRec</span></a>
      <section class="m-b-lg">
        <header class="wrapper text-center">
          <strong>登录以使用更多功能</strong>          <strong><s:actionerror/></strong><!-- 回显登录的错误信息 -->
        </header>
        <form id=form1 name=form1 action="${ pageContext.request.contextPath }/user_login.action" method=post>
          <div class="form-group">
            <input name="userName" placeholder="请输入用户名..." class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="form-group">
             <input name="userPass" type="password" placeholder="请输入密码..." class="form-control rounded input-lg text-center no-border">
          </div>
          <button type="submit" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">登录</span></button>
          <div class="text-center m-t m-b"><a href="#"><small>忘记密码？</small></a></div>
          <div class="line line-dashed"></div>
          <p class="text-muted text-center"><small>没有账户怎么办</small></p>
          <a href="signup.jsp" class="btn btn-lg btn-info btn-block rounded">选我，选我，创建一个</a>
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder">
      <p>
        <small>Web app framework base on Bootstrap<br>&copy; 2019</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
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