package com.chenys.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/** @author:  v_chenyongshuai@:
  * @date:  2018年3月26日 上午11:14:28 
  * @version：   1.0.0
  * @describe:    
  */
public class GenerateCreateTableHiveQLTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/chenys/Desktop/规范化/lg/tmsql(lg).txt");
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null){
			sb.append(line+"\n");
		}
		System.out.println(sb.toString());
		br.close();
		isr.close();
	}
}
