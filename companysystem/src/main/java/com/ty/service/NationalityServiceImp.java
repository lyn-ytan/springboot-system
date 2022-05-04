package com.ty.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.bean.Nationality;
import com.ty.mapper.NationalityMapper;

/**
 * 
 * @author TY
 *
 */
@Service
public class NationalityServiceImp implements NationalityService{
	@Autowired
	private NationalityMapper  nationalityMapper;
	
	public List<Nationality> getNationalityList(){
		return nationalityMapper.getNationalityList();
	}
}
