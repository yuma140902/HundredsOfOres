package yuma140902.hundredsofores.util;

import yuma140902.hundredsofores.orefamilies.core.OreID;

public final class StringUtil {
	
	/**
	 * パスカルケース(アッパーキャメルケース)に変換します
	 * @param strs
	 * @return 
	 */
	public static String ToCase_XxxXxx(String[] strs) {
		String[] result = new String[strs.length];
		
		for(int i = 0; i < strs.length; ++i) {
			result[i] = ToCase_Xxx(strs[i]);
		}
		
		return String.join("", result); //TODO String.joinはJava8の機能。古いマイクラ環境にはJava8がないかもしれない
	}
	public static String ToCase_XxxXxx(OreID oreid) {
		return ToCase_XxxXxx(oreid.getRawData());
	}
	
	
	/**
	 * キャメルケース(ローワーキャメルケース)に変換します
	 * @param strs
	 * @return
	 */
	public static String ToCase_xxxXxx(String[] strs) {
		String[] result = new String[strs.length];
		
		result[0] = strs[0].toLowerCase();
		
		for(int i = 1; i < strs.length; ++i) {
			result[i]= ToCase_Xxx(strs[i]); 
		}
		
		return String.join("", result);
	}
	public static String ToCase_xxxXxx(OreID oreid) {
		return ToCase_xxxXxx(oreid.getRawData());
	}
	
	
	/**
	 * スネークケースに変換します
	 * @param strs
	 * @return
	 */
	public static String ToCase_xxx_xxx(String[] strs) {
		String[] result = new String[strs.length];
		
		for(int i = 0; i < strs.length; ++i) {
			result[i] = strs[i].toLowerCase();
		}
		
		return String.join("_", result);
	}
	public static String ToCase_xxx_xxx(OreID oreid) {
		return ToCase_xxx_xxx(oreid.getRawData());
	}
	
	
	public static String ToCase_Xxx(String str) {
		char[] tmp = new char[1];
		str.getChars(0, 1, tmp, 0);
		String first = new String(tmp);
		first = first.toUpperCase();
		
		String rest = str.substring(1);
		rest = rest.toLowerCase();
		
		return first + rest;
	}
	
}
