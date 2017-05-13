package ms01288_COM1028_textprocessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class FileHandler {
	
	/**
	 * @param File - file (file that user has inputed)
	 * @return a nested ArrayList. each element is a list of strings - 
	 * represents sentence structure
	 */
	public ArrayList<ArrayList<String>> loadFile(File file){
		ArrayList<ArrayList<String>> strings = null;
		
		try {
			strings = readFileString(file);
		} catch (FileNotFoundException e) { // catch if file does not exist
			e.printStackTrace();
		}
		return strings;
	}
	/**
	 * @param File - file (file that user has inputed)
	 * @return true if file is of valid file type
	 */
	public Boolean checkFileValid(File file){
		return (getFileExtension(file).equals("txt"));
	}
	private String getFileExtension(File file){
		String name = file.getName();
		try {
			// get part of string after the '.'
			return name.substring(name.lastIndexOf(".") + 1);
		} catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * @param File -  file (that the user has inputed)
	 * @return an ArrayList of words sorted alphabetically
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> sortedWords(File file) throws FileNotFoundException{
		ArrayList<String> sortedList = new ArrayList<String>();
		Iterator<ArrayList<String>> listOfSentences = null;
		try {
			listOfSentences = readFileString(file).iterator();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(listOfSentences.hasNext()){
			
			// store iterator element in an ArrayList
			ArrayList<String> sentence = listOfSentences.next();
			
			// Sort strings in collection ascending alphabetically
			Collections.sort(sentence.subList(1, sentence.size()));
			Iterator<String> words = sentence.iterator();
			while(words.hasNext()){
				sortedList.add(words.next());
			}
		}
		return sortedList;
	}
	/**
	 * @param ArrayList<ArrayList<String>> -  document (the text converted to nested ArrayList
	 * of sentences)
	 * @return an ArrayList of words sorted alphabetically
	 */
	public ArrayList<String> sortedWordsFromString(ArrayList<ArrayList<String>> document){
		
		ArrayList<String> sortedList = new ArrayList<String>();
		Iterator<ArrayList<String>> listOfSentences = document.iterator();
		
		while(listOfSentences.hasNext()){
			ArrayList<String> sentence = listOfSentences.next();
			Collections.sort(sentence.subList(1, sentence.size()));
			Iterator<String> words = sentence.iterator();
			while(words.hasNext()){
				sortedList.add(words.next());
			}
		}
		return sortedList;
	}
	/**
	 * @param String - string (the contents of the JTextArea)
	 * @return a nested ArrayList. each element is a list of strings - 
	 * represents sentence structure
	 * @throws IllegalArgumentException
	 */
	public ArrayList<ArrayList<String>> readFileDirectInput(String string) throws IllegalArgumentException{
		
		Scanner sentences = new Scanner(string);
		sentences.useDelimiter("\\. "); // separate element when period appears. forming sentences
		ArrayList<ArrayList<String>> document = new ArrayList<ArrayList<String>>();
		
		while (sentences.hasNext()){
			
			   ArrayList<String> sentence = new ArrayList<String>();
			   Scanner words = new Scanner(sentences.next());
			   String word = "";
			   
			   while(words.hasNext()){
				   word = words.next();
				   sentence.add(" " + word);
			   }
			   // append each sentence with period
			   sentence.set(sentence.size()-1, word + ".");
			   document.add(sentence);
			   words.close();
		}
		sentences.close();
		return document;
	}
	/**
	 * @param File - file (the file the user has inputed)
	 * @return @return a nested ArrayList. each element is a list of strings - 
	 * represents sentence structure
	 * @throws FileNotFoundException
	 */
	public ArrayList<ArrayList<String>> readFileString(File file) throws FileNotFoundException {
		
		Scanner sentences = new Scanner(file);
		sentences.useDelimiter("\\. ");
		ArrayList<ArrayList<String>> document = new ArrayList<ArrayList<String>>();
		
		while (sentences.hasNext()){
			   ArrayList<String> sentence = new ArrayList<String>();
			   Scanner words = new Scanner(sentences.next());
			   
			   while(words.hasNext()){
				   sentence.add(words.next());
			   }
			  document.add(sentence);
			  words.close();
		}
		sentences.close();
		return document;
	}
	/**
	 * @param String - filename 
	 * @param Stirng - text (contents of JTextArea to save to the file)
	 * @throws IOException
	 */
	public void saveFile(String filename, String text) throws IOException{
		File file = createNewFile(filename + "/document.txt");
		// create instance of BufferedWriter to write to File
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		try{
			out.write(text);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//ensure BufferedWriter gets closed
			out.close();
		}
	}
	/**
	 * @param String - filename (includes the file path as well as file name)
	 * @return the File that the document will be saved to
	 */
	private File createNewFile(String filename){
		File file = null;
		try{
			file = new File(filename);
			if(file.createNewFile()){
				System.out.println("File created");
			}else{
				System.out.println("File exists");
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		return file;
	}
}