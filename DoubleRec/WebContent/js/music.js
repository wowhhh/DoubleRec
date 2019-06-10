
window.onload = function () {

	var audio = document.getElementById('audio'), // 音乐
	 	sourceList = audio.getElementsByTagName('source'), // 音乐列表
	 	play = document.getElementById('play'), // 播放按钮
		pause=document.getElementById('pause');
	 	prev = document.getElementById('prev'), // 上一曲
	 	next = document.getElementById('next'), // 下一曲
	 	jingyin = document.getElementById('jingyin'), // 静音
	 	voiceBar = document.getElementById('voice-bar'), // 音量调节
	 	musicBar = document.getElementById('music-bar'), // 播放进度控制
	 	playedBar = document.getElementById('played-bar'), // 已播放进度条
	 	voicedBar = document.getElementById('voiced-bar'), // 已播放进度条
	 	musicTitle = document.getElementById('title'), // 音乐标题
	 	loadBar = document.getElementById('load-bar'), // 加载进度条
	 	currentTime = document.getElementById('current-time'), // 当前音乐时间
	 	totalTime = document.getElementById('total-time'), // 当前音乐时间
	 	musicImg = document.getElementById('music-img'), // 音乐封面
	 	xunhuan = document.getElementById('xunhuan'); // 循环按钮
	 	currentSrcIndex = 0;

	 	// 音乐模块
		var keyword = document.getElementById('keyword'); // 搜索歌名
		var searchBtn = document.getElementById('search-btn'); // 搜索按钮
		var result = document.getElementById('result'); // 结果区
		var toPlay = document.getElementById('to-play'); // 立即播放按钮


	// 是否循环播放 
	audio.loop = false;

	// 是否自动播放
	audio.autoplay = false;

	// 初始化音量
	audio.volume = 0.5;
	var voicedBarWidth = (audio.volume / 1) * voiceBar.clientWidth;
	voicedBar.style.width = voicedBarWidth + 'px';

	// 是否自动缓冲加载
	audio.autobuffer = false;

	
	//主页，音乐点击播放事项 discoverSong 与newsong
	/**
	 * 用户点击播放：本音乐播放次数+1，用户播放历史，用户行为评分
	 */
	$(".icon-control-play").on("click",function()
	{
		// alert($(this).index());
		//获取当前点击位置的src
		var src=$(this).attr('data-audio');//获取当前点击位置的音乐的播放路径,data-audio是discover位置的歌曲
		var id=$(this).attr('data-songid');//获取音乐SongId
		
		
		 $.get("song_play.action?SongId="+id,function(data,status){

				alert("播放次数+1");
	        });
		//执行播放次数+1，历史+1，评分+1
		 // $.get("song_play.action?SongId="+SongId,function(data,status){
	     //   	alert("播放次数+1");
	     //   });
		//audio.set
		audio.setAttribute('src', src);
		audio.play();
		play.innerHTML = 'Pause';
		musicTime();
		
		//更换标题
		
		musicTitle.innerHTML =$(this).attr('title');
		return false;
	});
	//音乐点击播放事项 topSong
	$(".list-group-item").on("click",function()
	{
		//获取地址
		var src=$(this).attr('data-audio');
		audio.setAttribute('src',src);
		audio.play();
		play.innerHTML='Pause';
		musicTime();
		//更换标题
		musicTitle.innerHTML=$(this).attr('title');
	});
	
	// 显示第一首歌曲时长
	(function () {
		var fen = parseInt(audio.duration / 60);
		var miao = parseInt(audio.duration % 60);
		if (miao < 10) {
			miao = '0'+miao;
		}
		totalTime.innerHTML = fen + ':' + miao;
	})();
	

	// 播放 暂停
	play.onclick = function () {
		if (audio.paused) {
			audio.play();
			this.innerHTML = 'Pause';
			
		
		} else {
			audio.pause();
			this.innerHTML = 'Play';
			
		}
		musicTime();
	};

	// 下一曲
	next.onclick = function () {
		changeMusic('next');
	};

	// 上一曲
	prev.onclick = function () {
		changeMusic('prev');
	};

	// 切换歌曲
	function changeMusic (direct) {
		if (direct === 'next') {
			++ currentSrcIndex > sourceList.length - 1 && (currentSrcIndex = 0); // 下一曲
		} else {
			-- currentSrcIndex < 0 && (currentSrcIndex = sourceList.length -1); // 上一曲
		}
		currentSrc = sourceList[currentSrcIndex].getAttribute('src');
		audio.setAttribute('src', currentSrc);
		audio.play();
		play.innerHTML = 'Pause';
		musicTime();
	}

	// 音量调节（增加黄色现在音量显示）
	voiceBar.onclick = function (event) {
		var voiceBarWidth = voiceBar.clientWidth;
		var newVolume = (event.offsetX / voiceBarWidth);
		audio.volume = newVolume;

		// 音量大小更新
		var voicedBarWidth = (audio.volume / 1) * voiceBarWidth;
		voicedBar.style.width = voicedBarWidth + 'px';
		
	};

	// 播放进度控制
	musicBar.onclick = function (event) {
		var musicBarWidth = musicBar.clientWidth;
		var newCurrentTime = (event.offsetX / musicBarWidth) * audio.duration;
		audio.currentTime = newCurrentTime;
		var playedBarWidth = (audio.currentTime / audio.duration) * musicBarWidth;
		playedBar.style.width = playedBarWidth + 'px';
	};

	// 播放进度实时更新(修改为歌曲播放时开启定时器，暂停和页面load时清除定时器)
	setInterval(function updatePlayedBar (){
		var musicBarWidth = musicBar.clientWidth;
		var playedBarWidth = (audio.currentTime / audio.duration) * musicBarWidth;
		playedBar.style.width = playedBarWidth + 'px';
		if (audio.currentTime % 60 < 10) {
			currentTime.innerHTML = parseInt(audio.currentTime / 60) + ':0' + parseInt(audio.currentTime % 60);
		} else {
			currentTime.innerHTML = parseInt(audio.currentTime / 60) + ':' + parseInt(audio.currentTime % 60);
		}
		//如果是时间结束，并且是非单曲循环，自动下一曲
		if (audio.currentTime === audio.duration && !audio.loop) {
			next.onclick();
		}
	}, 1000);
	
	// 静音
	jingyin.onclick = function () {
		if (!audio.muted) {
			audio.muted = true;
			voicedBar.style.width = 0 +'px';
		}else {
			audio.muted = false;
			var voiceBarWidth = voiceBar.clientWidth;

			// 音量大小更新
			var voicedBarWidth = (audio.volume / 1) * voiceBarWidth;
			voicedBar.style.width = voicedBarWidth + 'px';
		}
	};

	// 判断文件缓冲进度
	setInterval(function updateCache () {
		var buffered, percent;
		// 已缓冲部分
		audio.readyState == 4 && (buffered = audio.buffered.end(0));

		// 已缓冲百分百
		audio.readyState == 4 && (percent = Math.round(buffered / audio.duration * 100) + '%');
		loadBar.style.width = (Math.round(buffered / audio.duration * 100) * musicBar.clientWidth * 0.01) + 'px';
	}, 1000);

	// 计算音乐时间

	function musicTime() {
		// 更换播放歌曲
		musicTitle.innerHTML = sourceList[currentSrcIndex].title;
		// 播放时间显示
		audio.addEventListener("canplay", function(){
			var sc=audio.duration;

			var fen = parseInt(sc / 60);
			var miao = parseInt(sc % 60);
			if (miao < 10) {
				totalTime.innerHTML = fen + ':0' + miao;
			}else {
				totalTime.innerHTML = fen + ':' + miao;
			}
		});
	};

	// 是否单曲循环
	/*xunhuan.onclick = function () {
		if (audio.loop) {
			audio.loop = false;
			this.innerHTML = '循环';
		} else {
			audio.loop = true;
			this.innerHTML = '单曲';
		}
	};*/

	var toPlay = document.getElementById('to-play'); // 立即播放按钮，有很多toPlay按钮，但是这里只能获取一个的，所以只能播放第一个歌曲
	var result = document.getElementById('result'); // 结果区

/*	toPlay.onclick=function()
	{
		var MusicSrc=result.getAttribute('data-audio');
		audio.setAttribute('src',MusicSrc);
		audio.play();
		play.innerHTML = 'Pause';
	};*/
	
	


	function CreateScript (src) {
		var el = document.createElement('script');
		el.src = src;
		el.async = true;
		el.defer = true;
		document.body.appendChild(el);
	};
	

};


			





		

