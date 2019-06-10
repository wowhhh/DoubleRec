package com.wyb.rec.service;

import java.util.List;

import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;

/**
 * 用户管理的Service接口
 * @author wyb
 *
 */
public interface UserService {

	boolean regist(User user);//用于用户注册
	User findByUsername(String username);//用于查找用户名是否存在
	User login(User user);
	void SavaUserTaste(String string, Integer idUser);
	List<UserAndSlType> findTasetedByUserId(int idUser);
	List<type> findTypeNameByTypeId(List<UserAndSlType> list);
	List<CollectSong> findUserCollectSongByUserId(Integer idUser);
}
