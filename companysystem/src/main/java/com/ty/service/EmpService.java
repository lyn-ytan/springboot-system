package com.ty.service;
import java.util.List;


import com.ty.bean.EmpData;
import com.ty.form.EmpDataForm;
/**
 * 
 * @author TY
 *
 */
public interface EmpService {
	public List<EmpData> listEmp();
	
	public List<EmpData> searchEmp(String keyWord);
	
	public EmpData getEmpData(String empCd);
	
	public void insertEmp(EmpDataForm empForm);
	
	public void changeEmp(EmpDataForm empForm);
	
	public void deleteEmp(String empCd);
}


