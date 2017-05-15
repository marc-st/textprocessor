/**
 * 
 */
package ms01288_COM1028_textprocessor_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import ms01288_COM1028_textprocessor.Parser;

/**
 * @author marcstevens
 *
 */
public class ParserTest {

	@Test
	public void objectCreate() {
		/** class takes no parameters 
		 * testing a method in Parser to ensure
		 * object works
		 */
		Parser parser = new Parser();
		ArrayList<String> words = new ArrayList<>();
		
		for(int i = 0; i < 11; i++){
			words.add("element" + i);
		}
		assertEquals(11, parser.getWordCount(words));
	}
	@Test
	public void testMostUsedWords(){
		
		Parser p = new Parser();
		ArrayList<String> listOfWords = new ArrayList<>();
		
		listOfWords.add("ben");
		listOfWords.add("ben");
		listOfWords.add("ben");
		listOfWords.add("stacey");
		listOfWords.add("sean");
		listOfWords.add("sean");
		listOfWords.add("sean");
		listOfWords.add("terry");
			
		HashMap<String, Integer> result = p.getMostUsedWords(listOfWords);
		
		assertEquals(3, result.get("ben"),  0);
		assertEquals(1, result.get("stacey"), 0);
		assertEquals(3, result.get("sean"), 0);
		assertEquals(1, result.get("terry"), 0);
		
	}
	@Test
	public void testNumSentences(){
		
		Parser p = new Parser();
		ArrayList<ArrayList<String>> sentences = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < 10; i++){ // add 10 new elements to ArrayList
			sentences.add(new ArrayList<String>());
		}
		assertEquals(10, p.getNumSentences(sentences), 0);
	}
	@Test
	public void testAverageLengthSentences(){
		
		Parser p = new Parser();
		ArrayList<ArrayList<String>> sentences = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> sentence1 = new ArrayList<String>();
		for(int i = 0; i < 10; i++){ // add 10 new elements to ArrayList
			sentence1.add("word" + i);
		}
		ArrayList<String> sentence2 = new ArrayList<String>();
		for(int i = 0; i < 8; i++){ // add 8 new elements to ArrayList
			sentence2.add("word" + i);
		}
		ArrayList<String> sentence3 = new ArrayList<String>();
		for(int i = 0; i < 2; i++){ // add 2 new elements to ArrayList
			sentence3.add("word" + i);
		}
		// expected average of 20/3
		
		sentences.add(sentence1);
		sentences.add(sentence2);
		sentences.add(sentence3);
		assertEquals(new Integer(10 + 8 + 2).doubleValue() / 3, p.getAvgLengthSentences(sentences), 0.01);
	}
	@Test
	public void testQuotedText(){
		
		Parser p = new Parser();
		ArrayList<ArrayList<String>> sentences = new ArrayList<>();
		
		// ADD QUOTED SENTENCE
		ArrayList<String> sentence1 = new ArrayList<String>();
		sentence1.add("\"");
		for(int i = 0; i < 10; i++){ // add 10 new elements to ArrayList
			sentence1.add("word" + i);
		}
		sentence1.add("\"");
		
		// add a NON-QUOTED sentence to ensure it
		// it doesn't include it in result
		ArrayList<String> sentence2 = new ArrayList<String>();
		for(int i = 0; i < 10; i++){ // add 10 new elements to ArrayList
			sentence2.add("word" + i);
		}
		
		// ADD QUOTED SENTENCE
		ArrayList<String> sentence3 = new ArrayList<String>();
		sentence3.add("\"");
		for(int i = 0; i < 4; i++){ // add 10 new elements to ArrayList
			sentence3.add("word" + i);
		}
		sentence3.add("\"");
		
		sentences.add(sentence1); sentences.add(sentence2); sentences.add(sentence3);
		
		// prepare ArrayList of the expected result
		ArrayList<ArrayList<String>> expectedResult = new ArrayList<ArrayList<String>>();
		
		//sentence1 is a quote so should be added
		expectedResult.add(sentence1);
		
		//sentence2 is not a quote so just an empty ArrayList here
		expectedResult.add(new ArrayList<String>());
		
		//sentence3 is a quote so should be added
		expectedResult.add(sentence3);
		
		assertEquals(expectedResult, p.getQuotedText(sentences));
	}
}
