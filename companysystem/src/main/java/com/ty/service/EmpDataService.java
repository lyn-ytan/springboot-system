package com.ty.service;

import java.util.List;

import com.ty.bean.EmpDataAll;
import com.ty.form.EmpDataForm;

/**
 * 
 * @author TY
 *
 */
// 接口
public interface EmpDataService {

public List<EmpDataAll> findAll();//查询所有
public void updateByempCd(EmpDataForm edform);//根据empCd更新信息
public void deleteByEmpCd(String empCd);//根据empCd删除信息
public void addEmpData(EmpDataForm edform);//添加信息
public List<EmpDataAll> selectByName(String name);//根据name查询信息
public List<EmpDataAll> selectByEmpCd(String empCd);//根据empCd查询信息
public List<EmpDataAll> searchEmp(String keyWord);//根据关键字查询

public String findnationalityName(String nationalityCd);
public String findgenderName(String genderCd);
}
