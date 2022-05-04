package com.ty.mapperTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ty.CompanysystemApplicationTests;
import com.ty.bean.LoginUser;
import com.ty.mapper.LoginUserMapper;

public class LoginUserMapperTest extends CompanysystemApplicationTests{

	@Autowired
	private LoginUserMapper loginUserMapper;
	
	@Test
	/*
	 * testQueryUser1
	 * 模拟用户输入了正确的账户密码，111@abc.com-000001
	 * 测试是否与数据库中查到的相等
	 */
	public void testQueryUser1() throws Exception{
		LoginUser user=loginUserMapper.findUser("111@abc.com");
		assertEquals("111@abc.com",user.getAccountId());
		assertEquals("000001", user.getPassword());
	}
	
	@Test
	/*
	 * testQueryUser2
	 * 模拟用户输入了不存在的账户，1@abc.com
	 * 测试是否为null
	 */
	public void testQueryUser2() throws Exception{
		LoginUser user=loginUserMapper.findUser("1@abc.com");
		assertEquals(null, user);
	}
}
