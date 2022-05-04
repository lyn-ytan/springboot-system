package com.ty.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.ObjectError;

import com.ty.CompanysystemApplicationTests;
import com.ty.controller.LoginUserController;

public class LoginUserControllerTest extends CompanysystemApplicationTests{

	@Resource
	@InjectMocks//添加mock对象
	private LoginUserController loginUserController;
	
	@Autowired
	private MessageSource messageSource;
	
	//@Rule//
	//public MockitoRule mockito=MockitoJUnit.rule();
	//在此代码中用@InjectMocks来完成功能
	
	private Locale locale=Locale.getDefault();
	MockMvc mockMvc;//模拟客户端请求
	
	@BeforeEach//在每个测试方法之前都会先执行
	public void setUp() throws Exception{
		//初始化MockMvc对象
		mockMvc=MockMvcBuilders.standaloneSetup(loginUserController).build();
		
	}
	
	
	/*
	 * MockHttpServletRequestBuilder
	 * 创建请求，可以设置参数、头信息、编码、Cookies等基本http请求所含的所有信息。
	 * MockMvc：客户端，主要入口，执行请求。
	 * ResultActions：结果与动作，
	 * MockMvc将MockHttpServletRequestBuilder构造的请求发出后，返回的结果。
	 * 并且可以在此基础上，针对结果添加一些动作。包含：
	 * andExpect：预期结果是否与真实结果一致
	 * andDo：针对结果执行脚本，常用的为print()，打印结果
	 * andReturn：获取结果
	 * 其中andExpect与andDo返回的类型扔为ResultActions，故可以使用链式的方式添加多个动作。
	 * 
	 * 
	 */
	
	//测试账号密码均正确，登录成功
	@Test
	public void testLoginSuccess() throws Exception{
		//loginUserController.login(userForm, model)
		//getRequest构建一个请求，模拟客户端传入正确数据
		MockHttpServletRequestBuilder getRequest= MockMvcRequestBuilders.post("/auth")
				.param("accountId", "111@abc.com")
				.param("password", "000001");//添加参数
		//执行请求
		ResultActions results=mockMvc.perform(getRequest);
		//andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）
		results.andDo(print());//打印出请求和相应的内容
		//期待跳转到success页面
		//results.andExpect(view().name("success"));//地址与controller中一致
		results.andExpect(view().name("redirect:/emplist"));
		//验证无错误信息
		results.andExpect(model().errorCount(0));
		//andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确（对返回的数据进行的判断）		
	}
	
	 //测试账号为空
		@Test
		public void testAccountIdIsEmpty() throws Exception {
			//模拟客户端传来账号为空的数据，给测试方法提供参数
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "")
					.param("password", "000001");
			//执行上面的请求
			ResultActions results = mockMvc.perform(getRequest);
			results.andDo(print());//打印出请求和相应的内容
			
			results.andExpect(view().name("login"));//验证跳转地址，地址与controller中一致
			//验证错误信息1条，账号为空
			results.andExpect(model().errorCount(1));
			
			//拿到service的errorlist
			@SuppressWarnings("unchecked")//忽略unchecked警告信息
			List<ObjectError> errorList = (List<ObjectError>) results
				.andReturn().getModelAndView().getModel().get("errorList");
			String message = errorList.get(0).getDefaultMessage();
			//验证错误信息包含
			assertTrue(message.contains("login.error.accountId.notEmpty"));
		}
		
		//测试账号不为邮箱格式，输入错误
		@Test
		public void testAccountIdNotMail() throws Exception {
			
			//模拟输入 账号不为邮箱格式，输入错误
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111")
					.param("password", "000001");
			//执行上面请求
			ResultActions results = mockMvc.perform(getRequest);
			//测试执行时，生成日志
			results.andDo(print());
	        //预期结果，访问login，错误信息为1条
			results.andExpect(view().name("login"));//地址与controller中一致
			results.andExpect(model().errorCount(1));//账号不为邮箱格式
			//获取错误信息表 
			//告诉编译器忽略 unchecked 警告信息
			@SuppressWarnings("unchecked")
			List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
					.get("errorList");
			//获取错误信息
			String message = errorList.get(0).getDefaultMessage();
			//预期错误信息为login.error.accountId.isEmail
			assertTrue(message.contains("login.error.accountId.isEmail"));
		}
		
		//测试密码为空/
		@Test
		public void testPasswordIsEmpty() throws Exception {
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@abc.com")
					.param("password", "");
			ResultActions results = mockMvc.perform(getRequest);
			results.andDo(print()); 
			results.andExpect(view().name("login"));//地址与controller中一致
			//错误信息2，form中密码为空，密码不为6位
			results.andExpect(model().errorCount(2));
			@SuppressWarnings("unchecked")
			List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
					.get("errorList");		
			List<String> messages = new ArrayList<>();
			//错误信息不止1条，循环取出错误信息
			for(ObjectError error:errorList) {
				String message = error.getDefaultMessage();
				messages.add(message);
			}
			assertTrue(messages.contains("login.error.password.notEmpty"));
			}
		
		//测试密码不是6位半角英文数字
		@Test
		public void testPasswordLength() throws Exception {
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@abc.com")
					.param("password", "0000000");
			ResultActions results = mockMvc.perform(getRequest);
			results.andDo(print());
			results.andExpect(view().name("login"));//地址与controller中一致
			results.andExpect(model().errorCount(1));//密码数字长度错误
			@SuppressWarnings("unchecked")
			List<ObjectError> errorList = (List<ObjectError>) results.andReturn().getModelAndView().getModel()
					.get("errorList");
			String message = errorList.get(0).getDefaultMessage();
			assertTrue(message.contains("login.error.password.length"));
		}
		
		//测试输入账号不存在
		@Test
		public void testLoginAccountError() throws Exception {
			
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@aaaa.com")
					.param("password", "000001");
			getRequest.locale(locale);
			ResultActions results = mockMvc.perform(getRequest);
			results.andDo(print());
			results.andExpect(view().name("login"));//地址与controller中一致
			results.andExpect(model().errorCount(0));//格式没问题
			String message = (String) results.andReturn().getModelAndView().getModel().get("message");
			assertEquals(messageSource.getMessage("login.message.accountId.error", null, locale), message);
		}
		
		//测试密码不正确
		@Test
		public void testLoginPasswordError() throws Exception {
			MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.post("/auth")
					.param("accountId", "111@abc.com")
					.param("password", "000002");
			getRequest.locale(locale);
			ResultActions results = mockMvc.perform(getRequest);
			results.andDo(print());
			results.andExpect(view().name("login"));//地址与controller中一致
			results.andExpect(model().errorCount(0));//格式没问题
			String message = (String) results.andReturn().getModelAndView().getModel().get("message");
			assertEquals(messageSource.getMessage("login.message.password.error", null, locale), message);
		}
		
}
