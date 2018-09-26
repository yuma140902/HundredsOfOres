package yuma140902.hundredsofores.orefamilies;

import java.util.Arrays;

public class OreID implements Comparable<String[]>{
	
	private String[] _name;
	public String[] getRawData() {
		return _name;
	}
	
	public OreID(String name) {
		this(new String[] {name});
	}
	
	public OreID(String[] name) {
		this._name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof OreID) {
			return this.equals((OreID)obj);
		}
		return false;
	}
	
	public boolean equals(OreID oreId) {
		return Arrays.equals(_name, oreId._name);
	}
	
	@Override
	public int compareTo(String[] o) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	
}
