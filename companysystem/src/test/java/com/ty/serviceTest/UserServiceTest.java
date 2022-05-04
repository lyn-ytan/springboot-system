package com.ty.serviceTest;

import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.ty.CompanysystemApplicationTests;
import com.ty.form.UserForm;
import com.ty.service.UserService;

public class UserServiceTest extends CompanysystemApplicationTests{

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	//
	private Locale locale=Locale.getDefault();
	
	@Test
	/*
	 * testGetResult1 
	 * 账号密码都正确的情况，errors内应当没有存储错误信息，测试集合size是否为0
	 */
	public void testGetResult1() throws Exception{
		UserForm loginForm = new UserForm();
		loginForm.setAccountId("111@abc.com");
		loginForm.setPassword("000001");
		ArrayList<String> errors=null;
		errors=userService.getResult(loginForm, locale);
		Assertions.assertEquals(0, errors.size());
	}
	
	@Test
	/*
	 * testGetResult2 
	 * 账号错误，密码正确的情况，errors内应当有存储错误信息，
	 *errorlist.add(messageSource.getMessage("login.message.accountId.error",null, locale)); 
	 * 测试集合errors内错误信息是否如上
	 */
	public void testGetResult2 () throws Exception{
		UserForm loginForm = new UserForm();
		loginForm.setAccountId("111@aaa.com");
		loginForm.setPassword("000001");
		ArrayList<String> errors=null;
		errors=userService.getResult(loginForm, locale);
		Assertions.assertEquals(
				messageSource.getMessage("login.message.accountId.error", null, locale), 
				errors.get(0));
	}
	
	@Test
	/*
	 * testGetResult3
	 * 账号正确，密码错误的情况，errors内应当有存储错误信息，
	 *errorlist.add(messageSource.getMessage("login.message.password.error", null, locale)); 
	 * 测试集合errors内错误信息是否如上
	 */
	public void testGetResult3() throws Exception{
		UserForm loginForm = new UserForm();
		loginForm.setAccountId("111@abc.com");
		loginForm.setPassword("000002");
		ArrayList<String> errors=null;
		errors=userService.getResult(loginForm, locale);
		Assertions.assertEquals(
				messageSource.getMessage("login.message.password.error", null, locale),
				errors.get(0));
	}
}
