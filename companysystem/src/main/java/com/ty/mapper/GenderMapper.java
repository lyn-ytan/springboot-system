package com.ty.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ty.bean.Gender;
/**
 * 
 * @author TY
 *
 */
@Mapper
public interface GenderMapper {
	//获取性别信息
	public List<Gender> getGenderList();
}
