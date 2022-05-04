package com.ty.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author TY
 *2022.4.24
 *描述：封装用户数据，对应的是页面用户传来的数据（form表单）
 */

@Getter
@Setter
public class UserForm {
	//验证用户登录时输入的数据
	
	//验证 账号不为空
	@NotEmpty(message="{login.error.accountId.notEmpty}")
	//验证 账号为Email格式
	@Email(message="{login.error.accountId.isEmail}")
	private String accountId;
	
	//验证 密码不为空
	@NotEmpty(message="{login.error.password.notEmpty}")
	//验证 密码为6位
	@Size(min=6,max=6,message="{login.error.password.length}")
	private String password;
}
