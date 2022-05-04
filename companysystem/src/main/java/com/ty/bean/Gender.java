package com.ty.bean;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author TY
 *封装从数据库中查询到的性别数据
 */
//利用注解自动生成get set方法
@Getter
@Setter
public class Gender {
	private String genderName;
	private String genderCd;
	}
