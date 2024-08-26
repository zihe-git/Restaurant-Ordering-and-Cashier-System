package com.ischoolbar.programmer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//是一个用于生成账单编码的工具类，用于生成唯一的账单
public class BillCodeUtil {

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	//格式化日期部分的账单编码
	public static String getBillCode(){
		Date date=new Date();
		String dateString=sdf.format(date);
		dateString+=System.currentTimeMillis();
		return dateString;
	}
}
