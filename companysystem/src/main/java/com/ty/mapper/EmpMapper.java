package com.ty.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ty.bean.EmpData;
import com.ty.form.EmpDataForm;

/**
 * 
 * @author TY
 *
 */
@Mapper
public interface EmpMapper {
	//查询所有
	public List<EmpData> listEmp();
	//根据关键字进行查询
	public List<EmpData> searchEmp(String keyWord);
	//根据empCd进行查询
	public EmpData getEmpData(String empCd);
	//根据页面的empForm信息添加信息
	public void insertEmp(EmpDataForm empForm);
	//根据页面的empForm信息进行更新
	public void changeEmp(EmpDataForm empForm);
	//根据empCd进行删除
	public void deleteEmp(String empCd);
}
