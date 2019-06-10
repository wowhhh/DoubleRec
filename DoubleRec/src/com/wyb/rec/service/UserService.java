package com.wyb.rec.service;

import java.util.List;

import com.wyb.rec.domain.CollectSong;
import com.wyb.rec.domain.User;
import com.wyb.rec.domain.UserAndSlType;
import com.wyb.rec.domain.type;

/**
 * �û������Service�ӿ�
 * @author wyb
 *
 */
public interface UserService {

	boolean regist(User user);//�����û�ע��
	User findByUsername(String username);//���ڲ����û����Ƿ����
	User login(User user);
	void SavaUserTaste(String string, Integer idUser);
	List<UserAndSlType> findTasetedByUserId(int idUser);
	List<type> findTypeNameByTypeId(List<UserAndSlType> list);
	List<CollectSong> findUserCollectSongByUserId(Integer idUser);
}
