package VocabularySystem;

import java.util.ArrayList;

public class Word {

	private ArrayList<String> englishWords;
	private ArrayList<String> frenchWords;
	
	public Word() {
		this.englishWords = new ArrayList<String>();
		this.frenchWords = new ArrayList<String>();
	}
	
	public Word(String englishWord, String frenchWord) {
		this();
		this.englishWords.add(englishWord);
		this.frenchWords.add(frenchWord);
	}
	
	public Word(ArrayList<String> englishExpressions, ArrayList<String> frenchExpressions) {
		this();
		this.englishWords = englishExpressions;
		this.frenchWords = frenchExpressions;
	}
	
	public void addEnglishWord(String ew) {
		this.englishWords.add(ew);
	}
	
	public void rmEnglishWord(String ew) {
		this.englishWords.remove(ew);
	}
	
	public void addFrenchWord(String ew) {
		this.frenchWords.add(ew);
	}
	
	public void rmFrenchWord(String ew) {
		this.frenchWords.remove(ew);
	}
	
	public ArrayList<String> getEnglishWords() {
		return this.englishWords;
	}
	
	public ArrayList<String> getFrenchWords() {
		return this.frenchWords;
	}
	
	public void showWord() {
		Boolean first = true;
		for (String ew : this.getEnglishWords()) {
			if (!first) {
				System.out.print(", ");
			}
			else {
				first = false;
			}
			System.out.print(ew);
		}
		System.out.print(" : ");
		first = true;
		for (String ef : this.getFrenchWords()) {
			if (!first) {
				System.out.print(", ");
			}
			else {
				first = false;
			}
			System.out.print(ef);
		}
		System.out.println();
	}
	
}
