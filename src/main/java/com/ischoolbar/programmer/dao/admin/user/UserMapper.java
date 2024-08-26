package com.ischoolbar.programmer.dao.admin.user;

import java.util.List;

import com.ischoolbar.programmer.entity.admin.Pager;
import com.ischoolbar.programmer.entity.admin.User;

public interface UserMapper {
	/**
	 * 查询所有user对象
	 * @return
	 */
	public List<User> getUser();
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public User login(User user);


	/**
	 * userIsExist
	 */
	public int userIsExist(User user);

	/**
	 * addUser
	 */
	public int addUser(User user);

	/**
	 * 显示用户列表
	 */
	public List<User> showUserList(Pager pager);
	public int getTotalCount(Pager pager);
	/**
	 * 删除用户
	 */
	public int delUserById(User user);
	/**
	 * 更改用户
	 */
	public int updateUser(User user);
}
