package yuma140902.hundredsofores.util;

import java.util.ArrayList;
import java.util.List;

public final class ListUtil {
	private ListUtil() {}
	
	public static boolean contains(int[] list, int item) {
		for(int i = 0; i < list.length; ++i) {
			if(list[i] == item) {
				return true;
			}
		}
		
		return false;
	}
	
	public static int[] ToIntList(String[] list) {
		List<Integer> tmpList = new ArrayList<Integer>(list.length);
		for(int i = 0; i < list.length; ++i) {
			int id;
			try {
				id = Integer.parseInt(list[i]);
			}catch (NumberFormatException e) {
				continue;
			}
			tmpList.add(id);
		}

		int[] result = new int[tmpList.size()];
		for(int i = 0; i < result.length; ++i) {
			result[i] = tmpList.get(i);
		}
		
		return result;
	}
}
