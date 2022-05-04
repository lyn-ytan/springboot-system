package com.ty.service;

import java.util.ArrayList;

import java.util.Locale;


import com.ty.form.UserForm;
/**
 * 
 * @author TY
 *
 */
// 接口
public interface UserService {
public ArrayList<String> getResult(UserForm userForm, Locale locale);
}
