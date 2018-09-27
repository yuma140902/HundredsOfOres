package yuma140902.hundredsofores.ore_feature_set;

import yuma140902.hundredsofores.core.IOreDictionary;
import yuma140902.hundredsofores.core.IRegisterable;

public interface IOreFamilyFeature extends IRegisterable, IOreDictionary {
	public static final IOreFamilyFeature EMPTY = new IOreFamilyFeature() {
		
		@Override
		public String getOreDictionaryKey() {
			return null;
		}
		
		@Override
		public void register() {
			
		}
	};
	
	public static boolean isEmpty(IOreFamilyFeature obj) {
		return EMPTY.equals(obj);
	}
}
