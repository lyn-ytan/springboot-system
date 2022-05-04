package com.ty.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author TY
 *2022.4.25
 *描述：封装客户端传来的社员详细信息
 */
@Getter
@Setter
public class EmpDataForm {
	//验证 雇员编号不为空
	@NotEmpty(message = "{empCode.notEmpty}")
	private String empCd;
	
	//验证 雇员姓名不为空
	@NotEmpty(message = "{empName.notEmpty}")
	//验证 雇员姓名为汉字/全角片假名/全角平假名/半角英文文字
	@Pattern(regexp = "^[一-龥 ア-ン あ-ん a-z A-Z]*$", message = "{empName.error}")
	private String name;

	//验证 雇员出生年月不为空
	@NotEmpty(message = "{empBirthday.notEmpty}")
	private String birthday;
	
	//验证 雇员国籍编号不为空
	@NotEmpty(message = "{nationality.notEmpty}")
	private String nationalityCd;
	
	////验证 雇员性别不为空
	@NotEmpty(message = "{empGender.notEmpty}")
	private String genderCd;
}
