<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<footer class="footer bg-dark">

<!-- 用于测试 -->
				<audio  id="audio">
				<s:iterator var="s" value="#session.nowplay" >
					<source title=<s:property value="#s.SongName"/>  data-img=<s:property value="#s.SongPic"/> src=<s:property value="#s.SongUrl"/>></source>
				</s:iterator>
				</audio>
<!-- 用于测试 -->        
							
                  <div id="jp_container_N">

                    <div class="jp-type-playlist">

                      <div id="jplayer_N" class="jp-jplayer hide"></div>

                      <div class="jp-gui">

                      

                        <div class="jp-interface">
                        
                        <!-- 播放控制标签 ,包括播放控制，列表，歌名，循环方式-->
                          <div class="jp-controls">
                          <!-- 上一曲 -->
                            <div><a  id="prev" href="javascript:;">prey</i></a></div>
                          <!-- 上一曲 -->
                           
                            <!-- 播放与暂停 -->
                            <div>

                              <a   id="play" href="javascript:;">play</i></a>


                            </div>
                            <!-- 播放与暂停 -->
                            
                            <!-- 下一曲 -->
                            <div><a  id="next" href="javascript:;">next</a></div>
                            <!-- 下一曲 -->
                            
                            <div class="hide"><a class="jp-stop"><i class="fa fa-stop"></i></a></div>
                            <!-- 播放列表 -->
                          
                            <div  style="display:none;"><span id="title"></span></div>
                            
                           
				<!--音乐进度条-->
							<span id="music-bar">
								<span id="load-bar"></span>
								<span id="played-bar"></span>
							</span>
				
                            
                            <!-- 显示歌曲名 -->
                           
							
							<!-- 音乐开始时间 -->
                            <div class="hidden-xs hidden-sm jp-current-time text-xs text-muted" id="current-time"></div>
                            <!-- 音乐开始时间 -->
                            
                             <!-- 音乐结束时间 -->
                            <div class="hidden-xs hidden-sm jp-duration text-xs text-muted " id="total-time"></div>
                            <!-- 音乐结束时间 -->
                            
                            <!-- 音量标志，可以用来做静音 -->
                            <div class="hidden-xs hidden-sm" id="jingyin" href="javascript:;">

                              <a class="jp-mute" title="mute"><i class="icon-volume-2"></i></a>

                              <a class="jp-unmute hid" title="unmute"><i class="icon-volume-off"></i></a>

                            </div>
                            <!-- 音量标志，可以用来做静音 -->
                            
                            <!-- 音量进度条 -->
                            <div class="hidden-xs hidden-sm jp-volume">

                              <div class="jp-volume-bar dk" id="voice-bar">

                                <div class="jp-volume-bar-value lter" id="voiced-bar"></div>

                              </div>

                            </div>
                            <!-- 音量进度条 -->
                            
                        
                            <div class="hide">

                              <a class="jp-full-screen" title="full screen"><i class="fa fa-expand"></i></a>

                              <a class="jp-restore-screen" title="restore screen"><i class="fa fa-compress text-lt"></i></a>

                            </div>

                          </div>
                          <!-- 播放控制标签 -->
                        </div>

                      </div>

                      <div class="jp-playlist dropup" id="playlist">

                        <ul class="dropdown-menu aside-xl dker">

                          <!-- The method Playlist.displayPlaylist() uses this unordered list -->

                          <li class="list-group-item"></li>

                        </ul>

                      </div>

                      <div class="jp-no-solution hide">

                    
                      </div>

                    </div>

                  </div>

                </footer>