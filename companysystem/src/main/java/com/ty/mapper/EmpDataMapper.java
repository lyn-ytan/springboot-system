package com.ty.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ty.bean.EmpDataAll;


/**
 * 
 * @author TY
 *
 */
@Mapper
public interface EmpDataMapper {

	public List<EmpDataAll> findAll();//查询所有
	public void updateByempCd(EmpDataAll empd);//根据empCd更新信息
	public void deleteByEmpCd(String empCd);//根据empCd删除信息
	public void addEmpData(EmpDataAll empd);//添加信息
	public List<EmpDataAll> selectByName(String name);//根据name查询信息
	public List<EmpDataAll> selectByEmpCd(String empCd);//根据empCd查询信息
	public List<EmpDataAll> searchEmp(String keyWord);//根据关键字查询
	
	public String findnationalityName(String nationalityCd);
	public String findgenderName(String genderCd);
	
}
