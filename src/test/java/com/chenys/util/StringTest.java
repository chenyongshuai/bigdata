package com.chenys.util;

import org.apache.tools.ant.taskdefs.Replace;

/** @author:  v_chenyongshuai@:
  * @date:  2018年3月26日 下午12:29:43 
  * @version：   1.0.0
  * @describe:    
  */
public class StringTest {
	public static void main(String[] args) {
		String string = "btch_prdct_rt	NUMBER(7,3),";
		if(string.contains("NUMBER")){
			String sub = string.substring(string.indexOf("NUMBER("), string.indexOf(")")+1);
			if( sub.contains(",")){
				String replace = sub.replace(",", "%");
				string = string.replace(sub, replace);
			}
		}
		System.out.println(string);
	}
}
