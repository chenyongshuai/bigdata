package com.chenys.kylin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/** @author:  v_chenyongshuai@:
  * @date:  2018年4月16日 下午10:53:01 
  * @version：   1.0.0
  * @describe:    
  */
public class PerformanceTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/chenys/Desktop/work/kylin性能测试/data.txt");
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		PrintWriter pw = new PrintWriter(osw);
		for (int i = 0; i < 100000; i++) {
			String randomUUID = UUID.randomUUID().toString();
			System.out.println(randomUUID);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(date);
			String[] split = format.split(" ");
			pw.write("INSERT INTO KEY_PART VALUES('"+randomUUID.substring(0, 8)+"','"+randomUUID.substring(8, 16)+"','"+randomUUID.substring(16, 24)+"','"+randomUUID.substring(24, 32)+"','"+randomUUID.substring(4, 12)+"','"+randomUUID.substring(12, 20)+"','"+randomUUID.substring(20, 28)+"','"+format+"','"+split[0]+"','"+split[1]+"')\n");
		}
		pw.flush();
		pw.close();
		osw.close();
		fos.close();
	}
}
