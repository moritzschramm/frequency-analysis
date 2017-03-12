package tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FrequencyHashMap extends HashMap<Character, Double> {

	private static final long serialVersionUID = 6736975560010365828L;

	public static final int ASCENDING = 0x0;
	public static final int DESCENDING = 0x1;
	
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
	
	public String[] getSortedLetters(int order) {
		
		List<Map.Entry<Character, Double>> list = new LinkedList<Map.Entry<Character, Double>>( this.entrySet() );
		
		if(order == ASCENDING) {
			
			Collections.sort( list, new Comparator<Map.Entry<Character, Double>>()
	        {
	            public int compare( Map.Entry<Character, Double> o1, Map.Entry<Character, Double> o2 )
	            {
	                return (o1.getValue()).compareTo( o2.getValue() );
	            }
	        } );	
			
		} else {
			
			Collections.sort( list, new Comparator<Map.Entry<Character, Double>>()
	        {
	            public int compare( Map.Entry<Character, Double> o1, Map.Entry<Character, Double> o2 )
	            {
	                return (o2.getValue()).compareTo( o1.getValue() );
	            }
	        } );
		}
		
		String [] sortedList = new String[list.size()];
		int i = 0;
        for (Map.Entry<Character, Double> entry : list)
        {
            sortedList[i++] = entry.getKey() + "";
        }
		
		return sortedList;
	}
}
