<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML >

<head>  



  <title>DoubleRec | 注册</title>

  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />

  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/jPlayer/jplayer.flat.css" type="text/css" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" type="text/css" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/simple-line-icons.css" type="text/css" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css" type="text/css" />

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" type="text/css" />  

    <!--[if lt IE 9]>

    <script src="${pageContext.request.contextPath}/js/ie/html5shiv.js"></script>

    <script src="${pageContext.request.contextPath}/js/ie/respond.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/ie/excanvas.js"></script>

  <![endif]-->


</head>

<body class="bg-info dker">

  <section id="content" class="m-t-lg wrapper-md animated fadeInDown">

    <div class="container aside-xl">

      <a class="navbar-brand block" href="${ pageContext.request.contextPath }/index.action"><span class="h1 font-bold">DoubleRec</span></a>

      <section class="m-b-lg">

        <header class="wrapper text-center">
          <strong>注册以体验更多功能</strong>
          <strong><s:fielderror></s:fielderror></strong><!-- 进行错误数据的回显 -->
        </header>
        <!-- action 是调用相应的action 事件，onsubmit是检验用户注册所输入的信息 -->
        <FORM id=form1 name=form1 action="${ pageContext.request.contextPath }/user_regist.action"  onsubmit="return checkForm();" method=post>

          <div class="form-group">
          <!-- 添加onblur 用于检验用户名是否已经存在 -->
          <!-- 这里name=""，里面的内容要和定义的User类的属性一模一样，因为模型驱动要去自动加载这些到User类里面 -->
            <input id="txtName" name="userName" placeholder="请输入用户名..." onblur="checkUsername()" class="form-control rounded input-lg text-center no-border">
            <header class="wrapper text-center">

         		 <strong id="strong1"></strong>

        	</header>
          </div>

          <div class="form-group">

            <input id="txtEmail" type="email" name="userEmail" placeholder="请输入邮箱..." class="form-control rounded input-lg text-center no-border">

          </div>

          <div class="form-group">

             <input id="txtPwd"  type="password" name="userPass" placeholder="请输入密码..." class="form-control rounded input-lg text-center no-border">

          </div>
          <div class="from-group">
          	<input  id="txtRePwd" type="password" name="reuserPass"  placeholder="请再次输入密码..." class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="checkbox i-checks m-b">

            <label class="m-l">

                                                                  查看<a href="#">相关政策</a>

            </label>

          </div>

          <button type="submit" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">同意相关政策并注册</span></button>

          <div class="line line-dashed"></div>

          <p class="text-muted text-center"><small>已经有账户了怎么办？</small></p>

          <a href="signin.jsp" class="btn btn-lg btn-info btn-block btn-rounded">选我直接登录啦！</a>

        </form>

      </section>

    </div>

  </section>

  <!-- footer -->

  <footer id="footer">

    <div class="text-center padder clearfix">

      <p>

        <small>Web app framework base on Bootstrap<br>&copy; 2019</small>

      </p>

    </div>

  </footer>

<!-- 写一手用来验证用户注册信息的js -->
<script >
	function checkForm()
	{
		//校验用户名
		//获得用户名文本框的值
		var username=null;
		username=document.getElementById("txtName").value;
		if(username == null || username=='')
			{
			alert("用户名不能为空！");
			return false;
			}
		if(username.search(/[\s]/g)!=-1)//如果搜不到空格，那么search函数就返回-1
			{
			alert("用户名不能有空格！");
			return false;
			}
		//校验邮箱不能为空
		var Email=null;
		Email=document.getElementById("txtEmail").value;
		if(Email == null || Email=='')
		{
		alert("邮箱不能为空！");
		return false;
		}
	    if(Email.search(/[\s]/g)!=-1)//如果搜不到空格，那么search函数就返回-1
		{
		alert("邮箱不能有空格！");
		return false;
		}
		//校验密码
		//获得密码文本框的值
		var password=null;
		var repassword=null;
		password=document.getElementById("txtPwd").value;
		if(password == null || password=='')
		{
		alert("密码不能为空！");
		return false;
		}
	    if(password.search(/[\s]/g)!=-1)//如果搜不到空格，那么search函数就返回-1
		{
		alert("密码不能有空格！");
		return false;
		}
	    //校验确认密码
	    repassword=document.getElementById("txtRePwd").value;
		if(repassword!=password)
		{
		alert("两次密码输入不一致！");	
		return false;
		}	
		//校验用户名是否存在，获取strong1
	
	}
	//检查用户名是否存在
	function checkUsername()
	{
	
		//获得文本框的值
		var username= document.getElementById("txtName").value;
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
		xhr.open("GET","${pageContext.request.contextPath}/user_findByName.action?time="+new Date().getTime()+"&userName="+username,true);
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
	
</script>
  <!-- / footer -->

  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

  <!-- Bootstrap -->

  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

  <!-- App -->

  <script src="${pageContext.request.contextPath}/js/app.js"></script>  

  <script src="${pageContext.request.contextPath}/js/slimscroll/jquery.slimscroll.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/app.plugin.js"></script>

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jPlayer/jquery.jplayer.min.js"></script>

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jPlayer/add-on/jplayer.playlist.min.js"></script>

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jPlayer/demo.js"></script>



</body>
</HTML>
