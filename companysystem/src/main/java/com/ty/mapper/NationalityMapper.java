package com.ty.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ty.bean.Nationality;
/**
 * 
 * @author TY
 *
 */
@Mapper
public interface NationalityMapper {
	//获取国籍信息
	public List<Nationality> getNationalityList();
}
