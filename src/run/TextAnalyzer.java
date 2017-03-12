package run;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	private Map<Character, Double> firstLetterFrequency;	// will be used in later versions
	private Map<Character, Double> lastLetterFrequency;
	
	private ArrayList<String> statisticsLog;
	
	public TextAnalyzer(String text) {
		
		this.text = text.toLowerCase().trim().replace("\uFEFF", "");	// trim and remove BOM
		
		totalLetters 		= 0;
		totalFirstLetters 	= 0;
		totalLastLetters 	= 0;
		
		letterCounter 		= new CounterHashMap();
		firstLetterCounter 	= new CounterHashMap();
		lastLetterCounter 	= new CounterHashMap();
		
		letterFrequency 	= new FrequencyHashMap();
		firstLetterFrequency= new FrequencyHashMap();
		lastLetterFrequency = new FrequencyHashMap();
		
		statisticsLog = new ArrayList<String>();
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
			
			statisticsLog.add("\t\"" + pair.getKey() + "\""
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
		
		statisticsLog.add("\n");
		
		statisticsLog.add("\tTotal number of letters: " + totalLetters);
		statisticsLog.add("\tTotal number of first letters: " + totalFirstLetters);
		statisticsLog.add("\tTotal number of last letters: " + totalLastLetters);
	}
	
	public void decrypt() {
		
		// TODO
		
		// determine what character is which character in decrypted form (assume the language is english for now, should change in future versiones)
		
		// save decrypted text in variable
		
		// print text
		
		
		// Sort the letters in letterFrequency (from highest freq to lowest).
		// Loop over every letter in language and get frequency 
		// difference between letter from text and statistics of language.
		// Save available letters of statistics in list, assign the one
		// that has the smallest difference between the two values to the letter from the text
		// and remove it from the list.
		
		String [] sortedLetters = ((FrequencyHashMap) letterFrequency).getSortedLetters(FrequencyHashMap.DESCENDING);
		ArrayList<String> availableLetters = new ArrayList<String>(Arrays.asList(Language.English.getAvailableLetters()));
		Map<String, String> letterAssignment = new HashMap<String, String>();
		
		for(int i = 0; i < sortedLetters.length; i++) {
			
			String letterFromText = sortedLetters[i];
			double textFreq = letterFrequency.get(letterFromText);
			
			int matchingLetterIndex = -1;
			double lowestDiff = 1.0;
			
			for(int k = 0; k < availableLetters.size(); k++) {
				
				double statsFreq = Language.English.getFrequency(availableLetters.get(k));
				
				double diff = Math.abs(textFreq - statsFreq);
				
				if(diff < lowestDiff) {
					
					lowestDiff = diff;
					matchingLetterIndex = k;
				}
			}
			
			if(matchingLetterIndex != -1) {
				String matchingLetter = availableLetters.remove(matchingLetterIndex);
				letterAssignment.put(letterFromText, matchingLetter);
				
				System.out.println("Letter: " + letterFromText + " is " + matchingLetter + " diff: " + lowestDiff);
			}
		}
		
		String newText = "";
		for(int k = 0; k < text.length(); k++) {
			
			String c = text.charAt(k) + "";
			
			String res = letterAssignment.get(c);
			if(res != null)
				newText += res;
			else
				newText += c;
		}
		
		System.out.println(newText);
	}
	
	
	
	public void printResults() {				// prints statistics
		
		for(int i = 0; i < statisticsLog.size(); i++) {
			
			System.out.println(statisticsLog.get(i));
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
