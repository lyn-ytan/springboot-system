package com.ty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
/**
 * 
 * @author TY
 *管理springboot下的国际化信息和验证信息
 */

@Configuration//工具类
public class MessageConfig {
	@Autowired
	private MessageSource messageSource; 
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean=new LocalValidatorFactoryBean();

		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}
}
