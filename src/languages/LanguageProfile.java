package languages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class LanguageProfile {

	protected Map<String, Double> letterFrequency;
	
	protected Map<String, Double> firstLetterFrequency;
	protected Map<String, Double> lastLetterFrequency;
	
	LanguageProfile() {
		
		this.letterFrequency = new HashMap<String, Double>();
		
		this.firstLetterFrequency = new HashMap<String, Double>();
		this.lastLetterFrequency = new HashMap<String, Double>();
	}
	
	private double getValue(Map<String, Double> map, String key) {
		
		if(map.containsKey(key)) {
			
			return map.get(key);
			
		} else {
			
			return 0.0;
		}
	}
	
	public void setFrequency(String letter, double frequency) {
		
		this.letterFrequency.put(letter, frequency);	
	}
	
	public double getFrequency(String letter) {
		
		return getValue(this.letterFrequency, letter);
	}
	
	public void setFirstLetterFrequency(String letter, double frequency) {
		
		this.firstLetterFrequency.put(letter, frequency);
	}
	
	public double getFirstLetterFrequency(String letter) {
		
		return getValue(this.firstLetterFrequency, letter);
	}
	
	public void setLastLetterFrequency(String letter, double frequency) {
		
		this.lastLetterFrequency.put(letter, frequency);
	}
	
	public double getLastLetterFrequency(String letter) {
		
		return getValue(this.lastLetterFrequency, letter);
	}
	
	public String[] getAvailableFirstLetters() {
		
		Set<String> set = this.firstLetterFrequency.keySet();
		
		return set.toArray(new String[set.size()]);
	}
	
	public String[] getAvailableLastLetters() {
		
		Set<String> set = this.lastLetterFrequency.keySet();
		
		return set.toArray(new String[set.size()]);
	}
}
