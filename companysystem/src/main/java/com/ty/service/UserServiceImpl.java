package com.ty.service;

import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.ty.bean.LoginUser;
import com.ty.form.UserForm;
import com.ty.mapper.LoginUserMapper;
/**
 * 
 * @author TY
 *
 */
//引用mybatis代理的mapper接口，定义业务逻辑方法

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private LoginUserMapper loginUserMapper;
	
	@Autowired
	private MessageSource messageSource; 
	
	@Override
	public ArrayList<String> getResult(UserForm userForm, Locale locale) {
		//根据userForm的accountId 调用findUser方法找到数据库中对应信息
		LoginUser user=loginUserMapper.findUser(userForm.getAccountId());
		ArrayList<String> errorlist=new ArrayList<String>();
		if(user==null) {
			
			//数据库中不存在
			//开始添加错误信息和国际化相关对象 
			//add-添加方法   getMessage-获取信息   
			//"login.message.accountId.error"-根据key找到国际化文件中对应信息 
			//没有数组对象，赋null
			//locale-本地化语言环境对象
			errorlist.add(messageSource.getMessage("login.message.accountId.error",null, locale));
		}else if(!user.getPassword().equals(userForm.getPassword())) {
			
			//信息不相等，说明密码错误
			//开始添加错误信息和国际化相关对象
			errorlist.add(messageSource.getMessage("login.message.password.error",null, locale));
		}
		
		// TODO Auto-generated method stub
		return errorlist;
		}

	}
