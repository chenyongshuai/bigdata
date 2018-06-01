package com.chenys.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/** @author:  v_chenyongshuai@:
  * @date:  2018年3月26日 上午10:14:58 
  * @version：   1.0.0
  * @describe:   生成Hive建表语句
  */
public class GenerateCreateTableHiveQL2 {
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/chenys/Desktop/规范化/lg/other.txt");
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
		BufferedReader br = new BufferedReader(isr);
		StringBuilder stringb = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null){
			if(line.contains("NUMBER")){
				String sub = line.substring(line.indexOf("NUMBER("), line.indexOf(")")+1);
				if( sub.contains(",")){
					String replace = sub.replace(",", "%");
					line = line.replace(sub, replace);
				}
			}
			stringb.append(line);
		}
		System.out.println(stringb.toString());
		String sql = stringb.toString();
		br.close();
		isr.close();
		sql = sql.replaceAll("VARCHAR2", "VARCHAR");
		sql = sql.replaceAll(" CHAR ", " VARCHAR ");
		sql = sql.replaceAll("NUMBER", "DECIMAL");
		sql = sql.replaceAll(";", ",");
		sql = sql.replaceAll("	", " ");
		String[] ss = sql.split("/");
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		for (String onesql : ss) {
			if(onesql == "" || onesql.trim().length()==0){
				continue;
			}
			String[] split = onesql.split(",");
			List<String>columns = new ArrayList<String>();
			List<String>comments = new ArrayList<String>();
			for (String string : split) {
				if(string.contains("create table")&&string.contains("constraint")){
					String sub = string.substring(0,string.indexOf("constraint")-1);
					System.out.println(sub);
					columns.add(sub);
				}else if(string.contains("comment on column")){
					String id = string.substring( string.indexOf(".")+1, string.indexOf(" is "));
					String sub = string.substring( string.indexOf("\'")+1, string.lastIndexOf("\'"));
					System.out.println(id+" "+sub);
					comments.add(id+" "+sub);
				}else{
					if(!string.contains("comment on table")){
						System.out.println(string);
						columns.add(string);
					}
				}
				/*if(string.contains("create table")){
					if(string.contains("constraint")){
						String sub = string.substring(0,string.indexOf("constraint")-1);
						columns.add(sub);
					}else{
						columns.add(string);
					}
				}else{
					if(string.contains("column")){
						String id = string.substring( string.indexOf(".")+1, string.indexOf(" is"));
						String sub = string.substring( string.indexOf("'")+1, string.lastIndexOf("'"));
						System.out.println(id+" "+sub);
						comments.add(id+" "+sub);
					}else if(!string.contains("table")){
						if(string.contains("constraint")){
							String sub = string.substring(0,string.indexOf("constraint")-1);
							columns.add(sub);
						}
						columns.add(string);
					}
				}*/
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < columns.size(); i++) {
				String column = columns.get(i);
				String string = comments.get(i);
				if(column.contains("%")){
					column = column.replace("%", ",");
				}
				if(i == columns.size()-1){
					String string2 = column.substring(0, column.length()-1) + " comment '"+string.split(" ")[1]+"')stored as orc;"+"\n";
					sb.append(string2);
				}else{
					String string2 = column + " comment '"+string.split(" ")[1]+"',";
					sb.append(string2);
				}
			}
			System.out.println(sb.toString());
			File f = new File("C:/Users/chenys/Desktop/规范化/lg/hiveql.txt");
			fos = new FileOutputStream(f, true);
			osw = new OutputStreamWriter(fos, "GBK");
			osw.write(sb.toString());
			osw.flush();
		}
		fos.close();
		osw.close();
	}
}
