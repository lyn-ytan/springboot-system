package com.ty.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ty.form.UserForm;
import com.ty.service.UserServiceImpl;
/**
 * 
 * @author TY
 *2022.2.24
 *描述：接收页面请求数据，调用服务层类的方法，并将具体数据响应给页面
 */


@Controller //控制类
//@ComponentScan({"service"})//开启注解，扫描service可不写
public class LoginUserController {

	/*
	 * @Resource与@Autowired：完成自动注入
	 * 成员属性：
	 * UserServiceImpl userServiceImpl：
	 * 		UserServiceImpl 类型，属性名是 userServiceImpl
	 */
	@Resource
	//private LoginUserService loginUserService;//都可以，推荐面向接口编程
	private UserServiceImpl userServiceImpl;  
	
	/*
	   * 方法名：login
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 @ModelAttribute("form")
	   *		对应前端form对象
	   *	UserForm userForm：
	   *		接受前端表单数据，并带有数据格式验证
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *描述：实现页面跳转
	   * 
	   * 
	   */
	//@RequestMapping(value="/login",method=RequestMethod.GET)
	@GetMapping("/login")
	public String login(@ModelAttribute("form") UserForm userForm,Model model) {
		
		return ("login");
	} 
	
	//@RequestMapping(value="/auth",method=RequestMethod.POST)
	
	/*
	 * Modelからformオブジェクトを取得、且つ検証し、検証結果をresultに入れる
	 */
	/*
	   * 方法名：auth
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 @ModelAttribute("form")
	   *		对应前端form对象
	   *	@Valid属性字段规则检测
	   *	UserForm userForm：
	   *		接受前端表单数据，并带有数据格式验证
	   *	BindingResult result：
	   *		验证的结果
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *	Locale locale：
	   *		用于国际化，当前语言环境
	   *描述：验证登录信息，实现登录功能
	   * 
	   * 
	   */
	
	@PostMapping("/auth")
	public String auth(@ModelAttribute("form")@Valid UserForm userForm,
			BindingResult result,Model model,Locale locale) {
		String url=null;
		//判定本次访问之前是否已经具备了错误信息（之前登陆失败）
		if(result.hasErrors()) {//返回结果有错误
			List<ObjectError> errorList=result.getAllErrors();
			model.addAttribute("errorList", errorList);
			url="login";
			return url;//通过返回路径，返回login页面
		}
		
		List<String> errorList=userServiceImpl.getResult(userForm, locale);
		if(!(errorList.size()==0)) {
			//以键值对形式将errorlist错误信息赋值给message，以便在页面上显示错误信息
			model.addAttribute("message", errorList.get(0));//集合中只有一条数据
			url="login";
			return url;//登录信息错误，login页面
		}else {
			
			return "redirect:/emplist";//登陆成功，
		}
		
	}
	
	
	
	
	
	
}
