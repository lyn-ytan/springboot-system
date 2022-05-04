package com.ty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.bean.Gender;
import com.ty.mapper.GenderMapper;
/**
 * 
 * @author TY
 *
 */
@Service
public class GenderServiceImp implements GenderService{	
	@Autowired
	private GenderMapper genderMapper;	
	public List<Gender> getGenderList(){
		return genderMapper.getGenderList();
	}
}
