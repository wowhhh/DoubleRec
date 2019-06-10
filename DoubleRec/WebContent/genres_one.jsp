<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

                <!-- / side content -->

                <section>

                  <section class="vbox">

                    <section class="scrollable padder-lg">

                      <h2 class="font-thin m-b"><s:property value="pageBean.typeName"/></h2>

                      <div class="row row-sm">
                      
                      <!-- 单个歌单信息的显示 -->
                      <s:iterator var="sl" value="pageBean.list" >
                        <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">

                          <div class="item">

                            <div class="pos-rlt">

                              <div class="item-overlay opacity r r-2x bg-black">

                                <div class="center text-center m-t-n">
                                <!-- 点击事件，显示歌单详情 -->
                                  <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#sl.ListId"/>&page=1"><i class="fa fa-play-circle i-2x"></i></a>

                                </div>

                              </div>
                               <!-- 点击事件，显示歌单详情 -->
                              <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#sl.ListId"/>&page=1"><img src=<s:property value="#sl.ListLogo"/> alt="" class="r r-2x img-full"></a>

                            </div>

                            <div class="padder-v">
                             <!-- 点击事件，显示歌单详情 -->
                              <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#sl.ListId"/>&page=1" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis"><s:property value="#sl.ListTitle"/></a>
                               <!-- 点击事件，显示歌单详情 -->
                              <a href="${pageContext.request.contextPath }/genres_detail.action?ListId=<s:property value="#sl.ListId"/>&page=1" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted"><s:property value="#sl.ListAuthor"/></a>

                            </div>

                          </div>

                        </div>
                        </s:iterator>
                        <!-- 单个歌单信息的显示 -->
                     
                      </div>
                      
                      <!-- 分页显示 -->
                      <ul class="pagination pagination">
                     <span> 第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页</span>
                       <s:if test="pageBean.page!=1"><!-- 加入判断，当前的页面不是第一页的时候可以点上一页 -->
                        <li><a href="${pageContext.request.contextPath }/genres_one.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1" />"><i class="fa fa-chevron-left"></i></a></li><!-- 前一页 -->
                       </s:if>
                       <!-- 迭代显示下面的页数 -->
                       <s:iterator var="i" begin="1" end="pageBean.totalPage">
                       		<!-- 下面这个页面时用来判断当前的页面是否被选中，然后给予不同的显示方式 -->
                       		<s:if test="pageBean.page !=#i">
                       		<li><a href="${pageContext.request.contextPath }/genres_one.action?cid=<s:property value="cid"/>&page=<s:property value="#i" />"><s:property value="#i"/></a></li>
                        	</s:if>
                        	<s:else>
                        	<li class="active"><a href="${pageContext.request.contextPath }/genres_one.action?cid=<s:property value="cid"/>&page=<s:property value="#i" />"><s:property value="#i"/></a></li>
                        	</s:else>
                        </s:iterator>
                        <s:if test="pageBean.page!=pageBean.totalPage">
                        <li><a href="${pageContext.request.contextPath }/genres_one.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1" />"><i class="fa fa-chevron-right"></i></a></li><!-- 后一页 -->
                        </s:if>
                      </ul>
                       <!-- 分页显示 -->
                       
                    </section>                    

                  </section>

                </section>