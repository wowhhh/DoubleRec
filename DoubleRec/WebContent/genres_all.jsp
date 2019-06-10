<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section>

                  <section class="vbox">

                    <section class="scrollable padder-lg">

                      <h2 class="font-thin m-b">全部歌单类型</h2>

<div class="mod_playlist_tag" id="taglist">
	<div class="js_normal" style="display:;">
	
	<!-- 语种类型歌单的显示 -->
            <div class="playlist_tag__list playlist_tag__list--lang">
                <h3 class="playlist_tag__tit c_tx_thin">语种</h3>
		
                <i class="playlist_tag__line"></i>
		
                <ul class="playlist_tag__tags">
                <s:iterator var="ag" value="#session.agList" begin="0" end="7"><!-- 控制循环次数 -->
                    <li class="playlist_tag__itembox"><a href="javascript:;" class="playlist_tag__item js_tag playlist_tag__item--select" data-id="165" id=<s:property value="#ag.idtype" />><s:property value="#ag.typeName" /></a></li>
                </s:iterator>
                <!-- 补一个 -->
                <s:iterator var="ag" value="a#session.gList" begin="62" end="62"><!-- 控制循环次数 -->
                    <li class="playlist_tag__itembox"><a href="javascript:;" class="playlist_tag__item js_tag playlist_tag__item--select" data-id="165" id=<s:property value="#ag.idtype" />><s:property value="#ag.typeName" /></a></li>
                </s:iterator>
                </ul>
            </div>
            
    <!-- 流派类型歌单的显示 -->
            <div class="playlist_tag__list ">
                <h3 class="playlist_tag__tit c_tx_thin">流派</h3>
		
                <i class="playlist_tag__line"></i>
		
                <ul class="playlist_tag__tags">
				<s:iterator var="ag" value="#session.agList" begin="8" end="23"><!-- 控制循环次数 -->
                    <li class="playlist_tag__itembox"><a href="javascript:;" class="playlist_tag__item js_tag playlist_tag__item--select" data-id="165" id=<s:property value="#ag.idtype" />><s:property value="#ag.typeName" /></a></li>
                </s:iterator>
            </div>
	    

            <div class="playlist_tag__list ">
                <h3 class="playlist_tag__tit c_tx_thin">主题</h3>
		
                <i class="playlist_tag__line"></i>
		
                <ul class="playlist_tag__tags">
				<s:iterator var="ag" value="#session.agList" begin="24" end="39"><!-- 控制循环次数 -->
                    <li class="playlist_tag__itembox"><a href="javascript:;" class="playlist_tag__item js_tag playlist_tag__item--select" data-id="165" id=<s:property value="#ag.idtype" />><s:property value="#ag.typeName" /></a></li>
                </s:iterator>
                </ul>
            </div>
	    
            
	    

            <div class="playlist_tag__list ">
                <h3 class="playlist_tag__tit c_tx_thin">心情</h3>
		
                <i class="playlist_tag__line"></i>
		
                <ul class="playlist_tag__tags">
				<s:iterator var="ag" value="#session.agList" begin="40" end="48"><!-- 控制循环次数 -->
                    <li class="playlist_tag__itembox"><a href="javascript:;" class="playlist_tag__item js_tag playlist_tag__item--select" data-id="165" id=<s:property value="#ag.idtype" />><s:property value="#ag.typeName" /></a></li>
                </s:iterator>
		    
                </ul>
            </div>
	    

            <div class="playlist_tag__list ">
                <h3 class="playlist_tag__tit c_tx_thin">场景</h3>
		
                <ul class="playlist_tag__tags">
				<s:iterator var="ag" value="#session.agList" begin="49" end="61"><!-- 控制循环次数 -->
                    <li class="playlist_tag__itembox"><a href="javascript:;" class="playlist_tag__item js_tag playlist_tag__item--select" data-id="165" id=<s:property value="#ag.idtype" />><s:property value="#ag.typeName" /></a></li>
                </s:iterator>
                </ul>
            </div>
	    

	</div>
</div>
</section>
</section>
</section>