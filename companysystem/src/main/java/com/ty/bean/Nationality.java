
package com.ty.bean;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author TY
 *封装从数据库中查询到的国籍数据
 */
@Getter
@Setter
public class Nationality {
	private String nationalityCd;
	private String nationalityName;
}
