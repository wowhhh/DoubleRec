<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
 <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">

      <div class="navbar-header aside bg-info nav-xs">

        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">

          <i class="icon-list"></i>

        </a>

        <a href="index.html" class="navbar-brand text-lt">

          <i class="icon-earphones"></i>

          <img src="images/logo.png" alt="." class="hide">

          <span class="hidden-nav-xs m-l-sm">Music</span>

        </a>

        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">

          <i class="icon-settings"></i>

        </a>

      </div>      <ul class="nav navbar-nav hidden-xs">

        <li>

          <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted">

            <i class="fa fa-indent text"></i>

            <i class="fa fa-dedent text-active"></i>

          </a>

        </li>

      </ul>

      <form action="${ pageContext.request.contextPath }/search.action"  onsubmit="return checkSearchForm();" method=post class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs" role="search">

        <div class="form-group">

          <div class="input-group">

            <span class="input-group-btn">

              <button type="submit" class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></button>

            </span>

            <input type="text" name="message" id="txtMessage" class="form-control input-sm no-border rounded" placeholder="搜索歌曲、歌单......">

          </div>

        </div>

      </form>

<!-- 修改登录连接，将其改为action的访问路径 -->
      <div class="navbar-right ">

        <ul class="nav navbar-nav m-n hidden-xs nav-user user">

		  <s:if test="#session.existUser != null"><!-- 如果存在用户 -->
		  <li class="dropdown">

            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">当前用户：<s:property value="#session.existUser.userName"/><b class="caret"></b></a>

            <ul class="dropdown-menu animated fadeInRight">       
              <li><a href="docs.html">帮助</a> </li>

              <li class="divider"></li>

              <li><a href="${pageContext.request.contextPath}/user_quit.action"  >退出登录</a></li>

            </ul>

          </li>
          </s:if>
          
          <s:else>
           <li class="dropdown">

            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">未登录 <b class="caret"></b></a>

            <ul class="dropdown-menu animated fadeInRight">            
              
              <li><a href="docs.html">帮助</a> </li>

              <li class="divider"></li>

              <li><a href="${pageContext.request.contextPath}/user_loginPage.action" data-toggle="ajaxModal" >点击登录</a></li>

            </ul>

          </li>
          </s:else>
          
        </ul>

      </div>      

    </header>