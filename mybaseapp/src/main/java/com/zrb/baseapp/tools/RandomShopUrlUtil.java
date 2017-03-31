package com.zrb.baseapp.tools;

import java.util.Random;

public class RandomShopUrlUtil {
	private static char[] number= {
			'q','w','e','r','t','y','u','i','o','p',
			'a','s','d','f','g','h','j','k','l',
			'z','x','c','v','b','n','m',
			'0','1','2','3','4','5','6','7','8','9'
	};
	
	public static String ramdomStr(){
		Random r = new Random();
		String s = "" ;
		
		for (int i = 0; i < 6; i++) {
			int nextInt = r.nextInt(number.length);
			s+=number[nextInt];
		}
		return s;
	}
}
