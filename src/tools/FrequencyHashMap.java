package tools;

import java.util.HashMap;

public class FrequencyHashMap extends HashMap<Character, Double> {

	private static final long serialVersionUID = 6736975560010365828L;

	public FrequencyHashMap() {
		
		super();
	}
	
	@Override
	public Double get(Object key) {
		
		if(super.containsKey(key)) {
			
			return (double)super.get(key);
		
		} else {
			
			return 0.0;
		}
	}
}
