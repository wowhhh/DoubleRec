package com.wyb.rec.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wyb.rec.dao.SearchDao;
import com.wyb.rec.domain.Song;
import com.wyb.rec.service.SearchService;

@Transactional
public class SearchServiceImpl implements SearchService {
	private SearchDao searchDao;

	public SearchDao getSearchDao() {
		return searchDao;
	}

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}
	
	//±£´æËÑË÷¸èÇú
	@Override
	public void saveSearchSongs(List<Song> songs) {
		// TODO Auto-generated method stub
		searchDao.saveSearchSongs(songs);
	}
}
