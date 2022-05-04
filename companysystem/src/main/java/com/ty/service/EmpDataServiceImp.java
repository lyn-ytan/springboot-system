package com.ty.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ty.bean.EmpDataAll;
import com.ty.form.EmpDataForm;
import com.ty.mapper.EmpDataMapper;
/**
 * 
 * @author TY
 *
 */
//引用mybatis代理的mapper接口，定义业务逻辑方法

@Service
public class EmpDataServiceImp implements EmpDataService{

	@Resource
	private EmpDataMapper empDataMapper;

	@Override
	public List<EmpDataAll> findAll() {
		// TODO Auto-generated method stub
		return empDataMapper.findAll();
	}

	@Override
	public void updateByempCd(EmpDataForm edform) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByEmpCd(String empCd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEmpData(EmpDataForm edform) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmpDataAll> selectByName(String name) {
		// TODO Auto-generated method stub
		return empDataMapper.selectByName(name);
	}

	@Override
	public List<EmpDataAll> selectByEmpCd(String empCd) {
		// TODO Auto-generated method stub
		return empDataMapper.selectByEmpCd(empCd);
	}

	@Override
	public List<EmpDataAll> searchEmp(String keyWord) {
		// TODO Auto-generated method stub
		return empDataMapper.searchEmp("%"+keyWord+"%");
	}

	@Override
	public String findnationalityName(String nationalityCd) {
		// TODO Auto-generated method stub
		return empDataMapper.findnationalityName(nationalityCd);
	}

	@Override
	public String findgenderName(String genderCd) {
		// TODO Auto-generated method stub
		return empDataMapper.findgenderName(genderCd);
	}
	
	

}