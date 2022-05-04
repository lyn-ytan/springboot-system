package com.ty.bean;

import lombok.Getter;
import lombok.Setter;
//实体类entity

//对应数据库表，类中属性与表中列名完全一致，数据类型一致
//封装数据库内查询得到的数据

//通过注解自动生成get set方法
@Getter
@Setter 
public class LoginUser {
	private String accountId;
	private String password;
}
