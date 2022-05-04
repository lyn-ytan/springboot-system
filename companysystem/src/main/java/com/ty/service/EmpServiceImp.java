package com.ty.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.bean.EmpData;
import com.ty.form.EmpDataForm;
import com.ty.mapper.EmpMapper;

/**
 * 
 * @author TY
 *
 */
@Service

public class EmpServiceImp implements EmpService{
	
	@Autowired
	private EmpMapper empMapper;
	
	 
	/*
	 * すべての社員情報データを取得する
	 * 查询所有
	 */
	@Override
	public List<EmpData> listEmp(){
		
		return empMapper.listEmp();
	}
	
		
	/*
	 * キーワードより検索する社員情報を取得する
	 * 根据关键字进行查询
	 */
	@Override
	public List<EmpData> searchEmp(String keyWord)	{
		return empMapper.searchEmp("%"+keyWord+"%");
	}
	
		
	/*
	 * 社員番号より社員情報を取得する
	 * 根据empCd进行查询
	 */
	@Override
	public EmpData getEmpData(String empCd) {
		return empMapper.getEmpData(empCd);
	}
	
		//根据页面的empForm信息添加信息
		
	@Override
	public void insertEmp(EmpDataForm empForm) {
		empMapper.insertEmp(empForm);
	}
	
		
	/*
	 * EmpMapper.changeEmp()メソッドを呼出し、
	 * 社員情報データを変更する
	 * 根据页面的empForm信息进行更新
	 */
	@Override
	public void changeEmp(EmpDataForm empForm) {
		empMapper.changeEmp(empForm);
	}
	
	
		 
	/*
	 * 社員情報を削除する
	 * 根据empCd进行删除
	 */
	@Override
	public void  deleteEmp(String empCd) {
		empMapper.deleteEmp(empCd);
	}
}
