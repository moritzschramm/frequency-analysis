package run;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import languages.Language;
import tools.CounterHashMap;
import tools.FrequencyHashMap;

public class TextAnalyzer {

	private String text;
	
	private int totalLetters;
	private int totalFirstLetters;
	private int totalLastLetters;
	
	private Map<Character, Integer> letterCounter;
	private Map<Character, Integer> firstLetterCounter;
	private Map<Character, Integer> lastLetterCounter;
	
	private Map<Character, Double> letterFrequency;
	private Map<Character, Double> firstLetterFrequency;
	private Map<Character, Double> lastLetterFrequency;
	
	private ArrayList<String> statistics;
	
	public TextAnalyzer(String text) {
		
		this.text = text.toLowerCase().trim().replace("\uFEFF", "");	// trim and remove BOM
		
		totalLetters = 0;
		totalFirstLetters = 0;
		totalLastLetters = 0;
		
		letterCounter = new CounterHashMap();
		firstLetterCounter = new CounterHashMap();
		lastLetterCounter = new CounterHashMap();
		
		letterFrequency = new FrequencyHashMap();
		firstLetterFrequency = new FrequencyHashMap();
		lastLetterFrequency = new FrequencyHashMap();
		
		statistics = new ArrayList<String>();
	}
	
	public void analyze() {
		
		System.out.println("Starting to analyse the text...");
		
		totalLetters = text.length();
		
		boolean firstLetterFlag = false;
		
		for(int position = 0; position < totalLetters; position++) {
			
			char letter = text.charAt(position);
			char nextLetter = ' ';
			
			if(position + 1 < text.length()) 
				nextLetter = text.charAt(position + 1);
			
			if(firstLetterFlag && letter != ' ') {
				
				firstLetterCounter.put(letter, firstLetterCounter.get(letter) + 1);
				
				totalFirstLetters++;
				
				firstLetterFlag = false;
				
			} else if(nextLetter == ' ' && letter != ' ') {
				
				lastLetterCounter.put(letter, lastLetterCounter.get(letter) + 1);
				
				totalLastLetters++;
			}
			
			if(!TextAnalyzer.isEmptyChar(letter))
				letterCounter.put(letter, letterCounter.get(letter) + 1);
			
			if(letter == ' ')
				firstLetterFlag = true;
		}
		
		
		calculateFrequency();
		
		System.out.println("Finished analysing.");
		
		decrypt();	
		
		System.out.println("\n");
	}
	
	public void calculateFrequency() {
		
		System.out.println("Calculating frequency...");
		
		Iterator<Entry<Character, Integer>> it = letterCounter.entrySet().iterator();
		
		while(it.hasNext()) {
			
			Map.Entry<Character, Integer> pair = (Map.Entry<Character, Integer>)it.next();
			
			double freq = ((double)(int)pair.getValue() / (double)totalLetters);
			
			DecimalFormat df = new DecimalFormat("0.00");
			String freqString = df.format(freq * 100.0);
			
			statistics.add("\t\"" + pair.getKey() + "\""
	        + ":\t" + pair.getValue() + "\t" 
	        + freqString + "%");
			
			letterFrequency.put(pair.getKey(), freq);
			
			it.remove();
		}
		
		it = firstLetterCounter.entrySet().iterator();
		
		while(it.hasNext()) {
			
			Map.Entry<Character, Integer> pair = (Map.Entry<Character, Integer>)it.next();
			
			firstLetterFrequency.put(pair.getKey(), ((double)(int)pair.getValue() / (double)totalFirstLetters));
		
			it.remove();
		}
		
		it = lastLetterCounter.entrySet().iterator();
		
		while(it.hasNext()) {
			
			Map.Entry<Character, Integer> pair = (Map.Entry<Character, Integer>)it.next();
			
			lastLetterFrequency.put(pair.getKey(), ((double)(int)pair.getValue() / (double)totalLastLetters));
		
			it.remove();
		}
		
		statistics.add("\n");
		
		statistics.add("\tTotal number of letters: " + totalLetters);
		statistics.add("\tTotal number of first letters: " + totalFirstLetters);
		statistics.add("\tTotal number of last letters: " + totalLastLetters);
	}
	
	public void decrypt() {
		
		// TODO
		
		// determine which what character is which character in decrypted form (assume the language is english for now, should change in future versiones)
		
		// save decrypted text in variable
		
		// print text
		
		
	}
	
	
	
	public void printResults() {				// prints statistics
		
		for(int i = 0; i < statistics.size(); i++) {
			
			System.out.println(statistics.get(i));
		}
	}
	
	public static boolean isEmptyChar(char letter) {
		
		return (letter == ' ' 
				|| (letter+"").equals("") 
				|| (letter+"").equals("\n") 
				|| (letter+"").length() == 0
				|| (letter+"").equals("\uFEFF"));
	}
}
