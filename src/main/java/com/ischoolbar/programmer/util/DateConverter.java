/**
 * 
 */
package com.ischoolbar.programmer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


//该类的作用是通过指定的日期模式将字符串转换为日期对象
public class DateConverter implements Converter<String, Date> {
	private String datePattern;
	public DateConverter(String datePattern){
		this.datePattern=datePattern;
	}
	@Override
	public Date convert(String s) {
		Date date=null;
		try {
			date=new SimpleDateFormat(datePattern).parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


}
