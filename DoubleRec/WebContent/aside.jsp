<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

        <aside class="bg-black dk nav-xs aside hidden-print" id="nav">          

          <section class="vbox">

            <section class="w-f-md scrollable">

              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2">

                





                <!-- nav -->                 

                <nav class="nav-primary hidden-xs">

                  <ul class="nav bg clearfix">

                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">

                      发现

                    </li>

                    <li>

                      <a href="${ pageContext.request.contextPath }/index.action">

                        <i class="icon-disc icon text-success"></i>

                        <span class="font-bold">首页</span>

                      </a>

                    </li>

                    <li>

                      <a href="${ pageContext.request.contextPath }/genres.action">

                        <i class="icon-music-tone-alt icon text-info"></i>

                        <span class="font-bold">分类</span>

                      </a>

                    </li>
	

                    <li>

                      <a href="${pageContext.request.contextPath}/userrec.action">

                        <i class="icon-list icon  text-info-dker"></i>

                        <span class="font-bold">私人推荐</span>

                      </a>

                    </li>
                     <li>

                      <a href="${pageContext.request.contextPath}/userrec_SongListRec.action">

                        <i class="icon-list icon  text-info-dker"></i>

                        <span class="font-bold">歌单推荐</span>

                      </a>

                    </li>
                    
                    <li>

                      <a href="${pageContext.request.contextPath}/user_gettaste.action">

                        <i class="icon-social-youtube icon  text-primary"></i>

                        <span class="font-bold">我的音乐口味</span>

                      </a>

                    </li>
                  
                    
                    <li class="m-b hidden-nav-xs"></li>

                  </ul>
   <s:if test="#session.existUser != null"><!-- 如果存在用户 -->
                  <ul class="nav text-sm">

                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">

                      <span class="pull-right"><a href="#"></a></span>

                      个人音乐空间

                    </li>

                    <li>

                      <a href="${pageContext.request.contextPath}/song_UserCollected.action">

                        <i class="icon-music-tone icon"></i>

                        <span>我喜欢的歌曲</span>

                      </a>

                    </li>

                    
                    <li>

                      <a href="${pageContext.request.contextPath}/songlist_UserCollected.action">

                        <i class="icon-music-tone icon"></i>

                        <span>收藏的歌单</span>

                      </a>

                    </li>

                  </ul>
</s:if>
                </nav>

                <!-- / nav -->

              </div>

            </section>

            


            <footer class="footer hidden-xs no-padder text-center-nav-xs">

              <div class="bg hidden-xs ">
              
              <s:if test="#session.existUser != null"><!-- 如果存在用户 -->
              <div class="dropdown dropup wrapper-sm clearfix">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <span class="thumb-sm avatar pull-left m-l-xs"><s:property value="#session.existUser.userName"/></span>
                    </a>

                    <ul class="dropdown-menu animated fadeInRight aside text-left">                      

                    

                      <li class="divider"></li>

                      <li>

                        <a href="${pageContext.request.contextPath}/user_quit.action"  >退出登录</a>

                      </li>

                    </ul>

                  </div>
              </s:if>
              
              
              <s:else>
                  <div class="dropdown dropup wrapper-sm clearfix">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <span class="thumb-sm avatar pull-left m-l-xs">未登录</span>
                    </a>

                    <ul class="dropdown-menu animated fadeInRight aside text-left">                      

                   

                      <li class="divider"></li>

                      <li>

                        <a href="${pageContext.request.contextPath}/user_loginPage.action" data-toggle="ajaxModal" >点击登录</a>

                      </li>

                    </ul>

                  </div>
                  </s:else>
                  
                </div>            </footer>

          </section>

        </aside>