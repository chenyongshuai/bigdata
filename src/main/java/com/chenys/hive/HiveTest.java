package com.chenys.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** @author:  v_chenyongshuai@:
  * @date:  2018年3月12日 下午3:21:19 
  * @version：   1.0.0
  * @describe:    
  */
public class HiveTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection("jdbc:hive2://192.168.126.128:10000/default");
		System.out.println(conn);
		PreparedStatement ps = conn.prepareStatement("select count(*) from T_BK ");
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int int1 = rs.getInt(1);
			//String string = rs.getString(2);
			System.out.println(int1);
		}
		rs.close();
		ps.close();
		conn.close();
	}	
}
