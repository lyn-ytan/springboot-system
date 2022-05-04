package com.ty.service;

import com.ty.bean.LoginUser;
/**
 * 
 * @author TY
 *
 */
//服务层LoginUserService接口
public interface LoginUserService {
	//findUser方法，参数String accountld，返回值LoginUser
	public LoginUser findUser(String accountId);
}
