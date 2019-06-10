package com.wyb.rec.dao;

import java.util.List;

import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;

/**
 * 用户管理的一个dao的接口
 * @author wyb
 *
 */
public interface UserDao {

	void save(User user);
	User findByUserName(String username);
	User login(User user);
	com.wyb.rec.domain.type findTypeIdByTypeName(String string);
	void SaveUserTaste(int idtype, Integer idUser);
	List<UserAndSlType> findTasetedByUserId(int idUser);
	type findTypeNameByTypeId(Integer idtype);
	List<CollectSong> findUserCollectSongByUserId(Integer idUser);
}
