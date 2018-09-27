package yuma140902.hundredsofores.ore_feature_set;

import yuma140902.hundredsofores.core.IOreDictionary;
import yuma140902.hundredsofores.core.IRegisterable;

public interface IOreFeature extends IRegisterable, IOreDictionary {
	public static final IOreFeature EMPTY = new IOreFeature() {
		
		@Override
		public String getOreDictionaryKey() {
			return null;
		}
		
		@Override
		public void register() {
			
		}
	};
	
	public static boolean isEmpty(IOreFeature obj) {
		return EMPTY.equals(obj);
	}
}
