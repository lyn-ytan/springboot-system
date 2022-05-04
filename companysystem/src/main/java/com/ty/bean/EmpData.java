package com.ty.bean;


import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author TY
 *封装从数据库中查询到的数据
 */
//利用注解自动生成get set方法
@Getter
@Setter
public class EmpData {
	private String empCd;
	private String name;
	private Date birthday;
	private Nationality nationality;
	private Gender gender;	
}
