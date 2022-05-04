package com.ty.controller;

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.ty.bean.EmpData;
import com.ty.bean.Gender;
import com.ty.bean.Nationality;
import com.ty.form.EmpDataForm;
import com.ty.service.EmpService;
import com.ty.service.GenderService;
import com.ty.service.NationalityService;

/**
 * 
 * @author TY
 *2022.4.25
 *@version 2.0
 *描述:控制类
 */
@Controller
public class AddEmpController {
	/*
	 * @Autowired：完成自动注入
	 * 成员属性：
	 * NationalityService nationalityService：
	 * 		NationalityService类型，属性名nationalityService
	 * GenderService genderService：
	 * 		GenderService类型，属性名genderService
	 * EmpService empService：
	 * 		EmpService 类型，属性名 empService
	 * MessageSource messageSource：
	 * 		MessageSource 类型，属性名 messageSource
	 * HttpSession session：
	 * 		HttpSession 类型，属性名 session
	 */
	@Autowired
	private NationalityService nationalityService;
	@Autowired
	private GenderService genderService;
	@Autowired
	private EmpService empService;	
	@Autowired
	private MessageSource messageSource;
	
	  @Autowired
	  HttpSession session; 

	  /*
	   * 方法名：toAddEmp
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 @ModelAttribute("empForm") 
	   *		对应前端empForm对象
	   *	EmpDataForm empForm：
	   *		接受前端表单数据，并带有数据格式验证
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *描述：为变更页面提供国籍和性别数据
	   * 
	   * 社員性別と国籍/性別の初期値を取得
	   * 社員性別と国籍/性別の初期値をSessionに追加   
	   * 新規登録画面へ遷移
	   * 
	   */
	@GetMapping(value = "/toAddEmp")
	public String toAddEmp(@ModelAttribute("empForm") EmpDataForm empForm,
			Model model) {
		
		//国籍信息关键字"nationalityList"
		List<Nationality> nationalityList = nationalityService.getNationalityList();
		session.setAttribute("nationalityList", nationalityList);
		
		//性别信息
		List<Gender> genderList = genderService.getGenderList();
		session.setAttribute("genderList", genderList);
		
		return ("addemp");
	}

	/*
	   * 方法名：addEmp
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 @ModelAttribute("empForm") 
	   *		对应前端empForm对象
	   *	EmpDataForm empForm：
	   *		接受前端表单数据，并带有数据格式验证
	   *	BindingResult result：
	   *		验证的结果
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *	Locale locale：
	   *		用于国际化，当前语言环境
	   *描述：无错误信息 执行添加操作
	   *
	   * 社員情報登録を検証、社員情報フォームの検証結果から全てのエラーを取得、
	   * エラーメッセージのリストをModelに入れる、社員情報登録画面へ遷移；
	   * この社員番号が既に登録した場合、
	   * エラーメッセージのリストをModelに入れる、社員情報登録画面へ遷移
	   * 
	   */
	@PostMapping( "/addEmp")
	public String addEmp(@ModelAttribute("empForm") @Valid EmpDataForm empForm,
			BindingResult result, 
				Model model, Locale locale) {	
		
		if (result.hasErrors()) {
			//输入有错误的情况下
			List<ObjectError> errorList = result.getAllErrors();
			//错误信息给"errorList"，以便传给页面
			model.addAttribute("errorList", errorList);
			
			return ("addemp");//返回addemp.html页面
		} else {
			//根据empCd进行查询，结果给empData
			EmpData empData = empService.getEmpData(empForm.getEmpCd());
			if (empData != null) {
				//不为空，说明数据库中该数据已经存在
				model.addAttribute("message", messageSource.getMessage("addEmp.error", null, locale));
				
				return ("addemp");//返回addemp.html页面
			} else {
				//不为空，执行添加
				empService.insertEmp(empForm);
				return ("redirect:/emplist");//返回emplist.html页面
			}
		}
	}
}
