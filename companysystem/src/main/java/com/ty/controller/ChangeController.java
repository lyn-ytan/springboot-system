package com.ty.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
 * 2022.4.25
 * 
 * 描述：控制类
 *変更ボタンを押すと、社員基本情報変更画面へ遷移する
 *確定ボタンを押すと、社員情報を更新し、一覧画面へ遷移する
 */
@Controller
public class ChangeController {
	/*
	 * @Autowired：完成自动注入
	 * 成员属性：
	 * NationalityService nationalityService：
	 * 		NationalityService类型，属性名nationalityService
	 * GenderService genderService：
	 * 		GenderService类型，属性名genderService
	 * EmpService empService：
	 * 		EmpService 类型，属性名 empService
	 * 
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
	HttpSession session;

	 /*
	   * 方法名：toChangeEmp
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 @RequestParam(value = "empCd") String empCd
	   *	从客户端获取empCd数据
			
			@ModelAttribute("empForm") 
	   *		对应前端empForm对象
	   *	EmpDataForm empForm：
	   *		接受前端表单数据，并带有数据格式验证
	   *描述：根据empCd进行查询，并转到empChange页面
	   *社員番号で社員情報検索、社員情報変更画面へ遷移する
	   * 
	   */
	@PostMapping("/toChangeEmp")
	public String toChangeEmp(@RequestParam(value = "empCd") String empCd,
			@ModelAttribute("empForm") EmpDataForm empForm) {
		
		//根据empCd进行查询，结果给empData
		EmpData empData = empService.getEmpData(empCd);
		//将信息传给empForm
		empForm.setEmpCd(empData.getEmpCd());
		empForm.setName(empData.getName());
		empForm.setBirthday(empData.getBirthday().toString());
		empForm.setNationalityCd(empData.getNationality().getNationalityCd());
		empForm.setGenderCd(empData.getGender().getGenderCd());
		
		List<Nationality> nationalityList = nationalityService.getNationalityList();
		session.setAttribute("nationalityList", nationalityList);
		
		List<Gender> genderList = genderService.getGenderList();
		session.setAttribute("genderList", genderList);
		return "empChange";//empChange.html
	}
	
	 /*
	   * 方法名：changeEmp
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 
			@ModelAttribute("empForm") 
	   *		对应前端empForm对象
	   *	@Valid：信息验证
	   *	EmpDataForm empForm：
	   *		接受前端表单数据，并带有数据格式验证
	   *	BindingResult result：
	   *		验证的结果
	   *	@RequestParam(value = "empCd") String empCd：
	   *	从客户端获取empCd数据
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *描述：根据empCd进行查询，更新，并转到相应页面
	   *社員番号より、社員情報を変更し、社員一覧画面へ遷移する
	   * 
	   */
	@PostMapping("/changeEmp")
	public String changeEmp(@ModelAttribute("empForm")  @Valid EmpDataForm empForm,
			BindingResult result,
	@RequestParam(value="empCd") String empCd, Model model) {		
		if (result.hasErrors()) {
			//有错误信息
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("updateerror", errorList);
			return "empChange";//empChange.html
		}else {
			//根据页面信息进行更新
			empService.changeEmp(empForm);
			return "redirect:/emplist";
		}
}
}