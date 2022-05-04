package com.ty.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.bean.EmpData;
import com.ty.service.EmpService;
/**
 * 
 * @author TY
 *2022.2.25
 *
 *描述：控制类
 *
 */
@Controller
public class EmpListController {
	/*
	 * @Autowired：自动注入
	 * 成员属性：
	 *  EmpService empService
	 *  EmpService 类型，属性名 empService
	 */
	@Autowired
	private EmpService empService;

	/*
	   * 方法名：listEmp
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *描述：查询所有信息
	   *全ての社員詳細情報を取得、社員情報一覧画面に設定する
	   * 
	   */
	@GetMapping("/emplist")
	public String listEmp(Model model) {
		//调用查询方法，查询所有
		List<EmpData> empList = empService.listEmp();
		
		model.addAttribute("empList",empList);
		
		return "emplist";//emplist.html
	}
	
	/*
	   * 方法名：searchEmp
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 
	   *	@RequestParam(value="keyWord") String keyWord：
	   *	从客户端获取keyWord关键字
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *描述：从客户端获取keyWord关键字，根据关键字进行查询
	   *画面から入力するkeyWordをリクエストパラメータとして受け取る、
	   *keywordで社員情報を検索、社員詳細情報を取得
	   * 
	   */
	@GetMapping("/searchEmp")	
	public String searchEmp(@RequestParam(value="keyWord") String keyWord,
			Model model) {
		//根据关键字进行查找
		List<EmpData> empList = empService.searchEmp(keyWord);
		model.addAttribute("empList",empList);	
		return "emplist";//emplist.html
	}
	
	/*
	   * 方法名：showDetails
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 
	   *	@RequestParam(value="empCd")String empCd：
	   *	从客户端获取empCd
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *描述：从客户端获取empCd，根据empCd进行查询并显示详细信息
	   *社員番号で社員情報検索、社員詳細情報を取得、社員詳細情報を表示
	   * 
	   */
	@GetMapping("/showDetails")
	public String showDetails(@RequestParam(value="empCd")String empCd,
			Model model) {
		//根据empCd进行查找
		
		EmpData empData = empService.getEmpData(empCd);
		
		model.addAttribute("empData",empData);
		return "empDetails";//empDetails.html
	}
	
	/*
	   * 方法名：deleteEmp
	   * 范围修饰符：public
	   * 返回值：String 需要访问的web资源地址
	   * 参数：
	   *	 
	   *	@RequestParam(value="empCd")String empCd：
	   *	获取empCd数据
	   *	Model model：
	   *		以键值对形式存储对象，传递给前端
	   *描述：获取empCd，根据empCd进行删除操作
	   *社員番号より社員情報を削除する
	   * 
	   */
	@RequestMapping("/deleteEmp")
	public String deleteEmp(@RequestParam(value="empCd")String empCd) {		
		//执行删除操作
		empService.deleteEmp(empCd);				
		return "redirect:/emplist";//emplist.html
	}
	
}
