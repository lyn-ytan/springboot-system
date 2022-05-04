package com.ty.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ty.bean.LoginUser;
/**
 * 
 * @author TY
 *
 */
//mybatis代理的接口，对应mybatis映射文件（与接口同名），定义对数据库的操作方法
//Mapper接口，封装findUser方法
@Mapper
public interface LoginUserMapper {
/*
 * findUser方法，
 * 参数String accountId，
 * 返回值LoginUser
 */
	
	public LoginUser findUser(String accountId);
	
}
