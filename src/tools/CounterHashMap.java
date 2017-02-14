package tools;

import java.util.HashMap;

public class CounterHashMap extends HashMap<Character, Integer> {

	private static final long serialVersionUID = 7608962429052256092L;

	public CounterHashMap() {
		
		super();
	}
	
	@Override
	public Integer get(Object key) {
		
		if(super.containsKey(key)) {
			
			return (int)super.get(key);
		
		} else {
			
			return 0;
		}
	}
}
