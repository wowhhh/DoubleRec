<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html class="app" >
<head>  

  <meta charset="utf-8" />

  <title>DoubleRec | 首次音乐口味选择页</title>

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
                  	<div class="doc-buttons">
                  	<!-- 做btn的点击时间，然后显示到右边，右边可以点击删除 -->
                  		<a href="#" class="btn btn-s-md btn-default disabled">关于语言：</a>
		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-primary btn-rounded">韩语</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-primary btn-rounded">日语</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-primary btn-rounded">国语</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-primary btn-rounded">粤语</a>
						
		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-primary btn-rounded">英语</a>
		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-primary btn-rounded">小语种</a>

			</div>
			<br/><br/>
			<div class="doc-buttons">
					<a href="#" class="btn btn-s-md btn-default disabled">关于体裁：</a>
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-info btn-rounded">流行</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-info btn-rounded">轻音乐</a>
						
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-info btn-rounded">摇滚</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-info btn-rounded">民谣</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-info btn-rounded">电子</a>
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-info btn-rounded">爵士</a>
						
		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-info btn-rounded">蓝调</a>

			</div>
			<br/><br/>
			<div class="doc-buttons">
			<a href="#" class="btn btn-s-md btn-default disabled">关于心情：</a>
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-success btn-rounded">睡前</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-success btn-rounded">伤感</a>
						
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-success btn-rounded">治愈</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-success btn-rounded">励志</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-success btn-rounded">思念</a>
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-success btn-rounded">快乐</a>

			</div>
			<br/><br/>
			<div class="doc-buttons">
			<a href="#" class="btn btn-s-md btn-default disabled">关于场景：</a>
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-default btn-rounded">工作</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-default btn-rounded">派对</a>
						
						<a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-default btn-rounded">校园</a>

		                <a href="javascript:void(0)" onclick="select(this)" class="btn btn-s-md btn-default btn-rounded">旅行</a>

			</div>
			<br/><br/>
                  </div>

                </div>

                <div class="col-sm-4">

                  <div class="panel panel-default">

                    <div class="panel-heading">您已选择的音乐口味：</div>

                   <div class="panel-body">
                   
                  	<div class="doc-buttons" >
                  			 <FORM id="selectedbutton" name=form1 action="${ pageContext.request.contextPath }/user_registRec2.action"  onsubmit="return checkForm();" method=post>
                  				<input  type="hidden"></input>	
                  				<button type="submit" class="btn btn-sm btn-default" onclick="isuser()">提交并计算推荐</button>
                  			</FORM>
                  	</div>
                   </div>

                  </div>

                </div>
                
                 <div class="col-sm-4">

                  <div class="panel panel-default">

                    <div class="panel-heading">提示：</div>

                   <div class="panel-body">
                   
                  	<div class="doc-buttons" >
                  	<span>首次注册的用户，要选择音乐口味，系统会根据此数据，计算合适的歌单并推荐给您。</span>
                  	</div>
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
  
  <script type="text/javascript">
  function select(target){
	  var selectname=$(target).text();
	
	//判断数量，最多五个
		if(document.getElementById("selectedbutton").getElementsByTagName("input").length>=6)
		{
			alert("选多了老弟，最多选择五个标签！！");
			return;
		}
	//判断当前选的标签，是否出现在了已选 当中，如果出现就弹框，主要难度是获取已经选择的文本数组
	var names=$(".form-control");
	
	for(var i=1;i<names.length;i++)
	{
		name=names[i].value;
		if(selectname==name)
			{
			alert("大兄弟，一个口味来一个就行！");
			return;
			}
	}
	document.getElementById("selectedbutton").innerHTML +="<input name=\"selected\" readOnly=\"true\" type=\"email\" class=\"form-control\" value=\""+selectname+"\" placeholder=\""+selectname+"\"/>";
		
		
  }
  function isuser()
  {
	  var username="<s:property value="#session.existUser.userName"/>";
  	if(username)
  	{
  		var names=$(".form-control");
  		if(names.length==1)
  			{
  			alert("啥都不喜欢还想提交？23333");
  			event.preventDefault();
  			}
  	}
  	else//用户名为空
	{
		alert("登录才能体验更多功能的，兄弟！");
		event.preventDefault();
		return;
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