package ms01288_COM1028_textprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Parser {

	private HashMap<String, Integer> wordUsage = null;
	private Iterator<ArrayList<String>> sentences = null;
	/**
	 * @param ArrayList - list (ArrayList of all words in the document)
	 * @return the size of the list a.k.a the number of words in the document
	 */
	public int getWordCount(ArrayList<String> listOfWords){
		return listOfWords.size();
	}
	/**
	 * @param ArrayList - list (ArrayList of all words in the document)
	 * @return A HashMap that maps the word to the number of occurrences
	 */
	public HashMap<String, Integer> getMostUsedWords(ArrayList<String> list){
		int counter = 1;
		wordUsage = new HashMap<String, Integer>();
		for(int i = 1; i < list.size(); i++){
			if(list.get(i-1).equals(list.get(i))){
				counter++;
			}else if (i == list.size()-1){
				wordUsage.put(list.get(i), counter);
			}else{
				wordUsage.put(list.get(i-1), counter);
				counter = 1;
			}
		}
		return wordUsage;
	}
	/**
	 * @param ArrayList<ArrayList<String>> - list (an ArrayList of sentences, each sentence being 
	 * an ArrayList of words)
	 * @return size of ArrayList equalling the number of sentences
	 */
	public int getNumSentences(ArrayList<ArrayList<String>> listOfSentences){
		return listOfSentences.size();
	}
	/**
	 * @param ArrayList<ArrayList<String>> - list (an ArrayList of sentences, each sentence being 
	 * an ArrayList of words)
	 * @return the average length of sentences as a Double
	 */
	public double getAvgLengthSentences(ArrayList<ArrayList<String>> listOfSentences){
		ArrayList<Integer> length = new ArrayList<Integer>();
		sentences = listOfSentences.iterator();
		while(sentences.hasNext()){
			length.add(sentences.next().size());
		}
		Integer sum = 0;
		  if(!length.isEmpty()) {
		    for (Integer size : length) {
		        sum += size;
		    }
		    return sum.doubleValue() / length.size();
		  }
		  return sum;
	}
	public ArrayList<ArrayList<String>> getQuotedText(ArrayList<ArrayList<String>> listOfSentences){
		sentences = listOfSentences.iterator();
		ArrayList<ArrayList<String>> listOfQuotes = new ArrayList<ArrayList<String>>();
		while(sentences.hasNext()){
			Iterator<String> words = sentences.next().iterator();
			ArrayList<String> quote = new ArrayList<String>();
			boolean inQuotation = false;
			while(words.hasNext()){
				String word = words.next();
				if(inQuotation){
					quote.add(word);
					if(word.charAt(word.length()-1) == '"' || word.charAt(word.length()-2) == '"') {
						inQuotation = false;
					}
				}
				else if (word.charAt(0) == '"' || word.charAt(1) == '"'){
					inQuotation = true;
					quote.add(word);
				}	
			}
			listOfQuotes.add(quote);
		}
		return listOfQuotes;	
	}
}