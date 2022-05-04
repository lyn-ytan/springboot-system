package com.ty.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ty.bean.LoginUser;
import com.ty.mapper.LoginUserMapper;
/**
 * 
 * @author TY
 *
 */
@Service
//接口的实现类
public class LoginUserServiceImpl implements LoginUserService{

	@Resource//自动注入
	//以成员属性方式实例化LoginUserMapper
	private LoginUserMapper loginUserMapper; 
	
	
	@Override//重写LoginUserService接口方法
	public LoginUser findUser(String accountId) {
		//调用LoginUserMapper中的findUser方法
		return loginUserMapper.findUser(accountId);
		
	}

}
