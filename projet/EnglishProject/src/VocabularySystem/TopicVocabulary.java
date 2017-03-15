package VocabularySystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class TopicVocabulary {

	String title;
	//private HashMap<String,String> englishToFrench;
	private ArrayList<Word> englishToFrench;
	
	public TopicVocabulary() {
		//this.englishToFrench = new HashMap<String,String>();
		this.englishToFrench = new ArrayList<Word>();
	}
	
	/*public TopicVocabulary(HashMap<String,String> eTf) {
		this.englishToFrench = eTf;
	}
	*/
	
	public TopicVocabulary(ArrayList<Word> eTf) {
		this.englishToFrench = eTf;
	}
	
	/*public void addAWord(String englishExpression, String frenshExpression) {
		this.englishToFrench.put(englishExpression, frenshExpression);
	}
	*/
	
	public void addAWord(String englishExpression, String frenchExpression) {
		Boolean found = false;
		for (Word w : this.englishToFrench) {
			if (w.getEnglishWords().contains(englishExpression) && w.getFrenchWords().contains(frenchExpression)) {
				found = true;
				break;
			}
			else if (w.getEnglishWords().contains(englishExpression)) {
				w.addFrenchWord(frenchExpression);
				found = true;
				break;
			}
			else if (w.getFrenchWords().contains(frenchExpression)) {
				w.addEnglishWord(englishExpression);
				found = true;
				break;
			}
		}
		if (!found) {
			//System.out.println("Word not found");
			Word new_word = new Word(englishExpression, frenchExpression);
			this.englishToFrench.add(new_word);
		}
	}
	
	public void addAWord(Word newWord) {
		Boolean found = false;
		for (Word w : this.englishToFrench) {
			if (w.getEnglishWords().contains(newWord.getEnglishWords().get(0)) && w.getFrenchWords().contains(newWord.getFrenchWords().get(0))) {
				found = true;
				break;
			}
			else if (w.getEnglishWords().contains(newWord.getEnglishWords().get(0))) {
				w.addFrenchWord(newWord.getFrenchWords().get(0));
				found = true;
				break;
			}
			else if (w.getFrenchWords().contains(newWord.getFrenchWords().get(0))) {
				w.addEnglishWord(newWord.getEnglishWords().get(0));
				found = true;
				break;
			}
		}
		if (!found) {
			//System.out.println("Word not found");
			this.englishToFrench.add(newWord);
		}
	}
	
	/*public void rmWord(String englishExpression, String frenshExpression) {
		this.englishToFrench.remove(englishExpression);
	}
	*/
	
	public void rmFrenchWord(String englishExpression, String frenchExpression) {
		Boolean found = false;
		for (Word w : this.englishToFrench) {
			if (w.getEnglishWords().contains(englishExpression) && w.getFrenchWords().contains(frenchExpression)) {
				w.rmFrenchWord(frenchExpression);
				found = true;
				break;
			}
		}
	}
	
	public void rmEntirelyWord(String englisExpression) {
		Boolean found = false;
		for (Word w : this.englishToFrench) {
			if (w.getEnglishWords().contains(englisExpression)) {
				this.englishToFrench.remove(w);
				found = true;
				break;
			}
		}	
	}
	

	public void setTitleTopic(String topicTitle) {
		this.title = topicTitle;
	}
	
	public String getTitleTopic() {
		return this.title;
	}
	
	/*public HashMap<String,String> getTopicVocabulary() {
		return this.englishToFrench;
	}
	*/
	
	public ArrayList<Word> getTopicVocabulary() {
		return this.englishToFrench;
	}
	
	/*
	public void displayTopic() {
		for (Entry<String, String> words : this.englishToFrench.entrySet()) {
			System.out.println(words.getKey() + " : " + words.getValue());
		}
	}
	
	public void displayTopicDetailed() {
		System.out.println("Topic Title : " + this.title);
		for (Entry<String, String> words : this.englishToFrench.entrySet()) {
			System.out.println(words.getKey() + " : " + words.getValue());
		}
	}
	*/
	
	public void displayTopic() {
		for (Word w : this.englishToFrench) {
			Boolean first = true;
			for (String ew : w.getEnglishWords()) {
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
			for (String ef : w.getFrenchWords()) {
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
	
	public void displayTopicDetailed() {
		System.out.println("Topic Title : " + this.title);
		for (Word w : this.englishToFrench) {
			Boolean first = true;
			for (String ew : w.getEnglishWords()) {
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
			for (String ef : w.getFrenchWords()) {
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
	
	
}
