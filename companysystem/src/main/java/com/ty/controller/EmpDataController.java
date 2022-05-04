package com.ty.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.ty.service.EmpDataService;

@Controller
public class EmpDataController {

	@Resource
	private EmpDataService empDataService;
	/*
	 * 
		public List<EmpData> findAll();//查询所有
	public void updateByempCd(EmpData empd);//根据empCd更新信息
	public void deleteByEmpCd(String empCd);//根据empCd删除信息
	public void addEmpData(EmpData empd);//添加信息
	public List<EmpData> selectByName(String name);//根据name查询信息
	public List<EmpData> selectByEmpCd(String empCd);//根据empCd查询信息
	public List<EmpData> searchEmp(String keyWord);//根据关键字查询
	}
	 */
	
	
	
	/**
	 * @GetMapping("/emplist")
	public String emplist(Model model) {
	 // List<EmpData>  emplist =empDataService.findAll();
	//	model.addAttribute("emplist", emplist);
		return ("emplist");
	} 
	
		
		//public List<EmpData> findAll();//查询所有
	//@PostMapping
	@RequestMapping("/findAll")//查询所有
	public String findAll(Model model) {
		
		List<EmpDataAll>  emplist =empDataService.findAll();
		model.addAttribute("emplist", emplist);
		String url = "emplist";
		return url;
		
	}
	
	//public void updateByempCd(EmpData empd);//根据empCd更新信息
	@RequestMapping("/updateByempCd")//根据empCd更新信息
	public String updateByempCd(
			@ModelAttribute("edform")@Valid EmpDataForm edform,
			BindingResult result,Model model) {
		if(result.hasErrors()) {
			List<ObjectError> errorList=result.getAllErrors();
			
			model.addAttribute("updateerror", errorList);
			
			return ("update");

		}else  {
			empDataService.updateByempCd(edform);
			//List<EmpData>list= empDataService.selectByEmpCd(edform.getEmpCd());
			
			return ("emplist");
		}
		
	};
	
	@RequestMapping("/toaddemp")
	//public void addEmpData(EmpData empd);//添加信息
	public String addEmpData1(
			@ModelAttribute("edform")@Valid EmpDataForm edform,
			BindingResult result,Model model){
	
		return "addemp";
		
		
	}
	@RequestMapping("/addEmpData")
	public String addEmpData2(
			@ModelAttribute("edform")@Valid EmpDataForm edform,
			BindingResult result,Model model){
		empDataService.addEmpData(edform);
		
		return "emplist";	
	}
	
	//public void deleteByEmpCd(String empCd);//根据empCd删除信息
		@RequestMapping("/deleteByEmpCd")//根据empCd删除信息
		public String deleteByEmpCd(String empCd) {
			empDataService.deleteByEmpCd(empCd);
			return "emplist";
		};
	
	//public List<EmpData> selectByName(String name);//根据name查询信息
	@RequestMapping("/selectByName")//根据name查询信息
	public String selectByName(
			@RequestParam(value="name")String name,
			Model model){
		List<EmpDataAll>list= empDataService.selectByName("%"+name+"%");
		model.addAttribute("empData",list);
		return "empDetails";
	};
	
	//public List<EmpData> selectByEmpCd(String empCd);//根据empCd查询信息
	
	@RequestMapping("/selectByEmpCd")//根据empCd查询信息
	public String selectByEmpCd(
			@RequestParam(value="empCd")String empCd,
			Model model) {
		
		List<EmpDataAll>list= empDataService.selectByEmpCd(empCd);

		model.addAttribute("empData",list);
		return "empDetails";
	};
	
	//public List<EmpData> searchEmp(String keyWord);//根据关键字查询
	@RequestMapping("/searchEmp")
	public String searchEmp(
			@RequestParam(value="keyWord") String keyWord,
			Model model){
		   
		List<EmpDataAll>list= empDataService.searchEmp(keyWord);
		model.addAttribute("empData",list);
		return "empDetails";
	}
}

	 * 
	 */
	
}