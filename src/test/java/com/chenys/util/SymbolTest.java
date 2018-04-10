package com.chenys.util;
/** @author:  v_chenyongshuai@:
  * @date:  2018年3月27日 下午10:18:19 
  * @version：   1.0.0
  * @describe:    
  */
public class SymbolTest {
	public static void main(String[] args) {
		int a = 62;
		int b = 0;
		a |= 1 << b;
		System.out.println(a);
		//32 48 56 60 62 63
		//6 15 20 15 6 1
	}
}
