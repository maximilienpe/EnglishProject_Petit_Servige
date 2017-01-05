package VocabularySystem;
import java.util.HashMap;
import java.util.Map.Entry;

public class TopicVocabulary {

	String title;
	private HashMap<String,String> englishToFrench;
	
	public TopicVocabulary() {
		this.englishToFrench = new HashMap<String,String>();
	}
	
	public TopicVocabulary(HashMap<String,String> eTf) {
		this.englishToFrench = eTf;
	}
	
	public void addAWord(String englishExpression, String frenshExpression) {
		this.englishToFrench.put(englishExpression, frenshExpression);
	}

	public void rmWord(String englishExpression, String frenshExpression) {
		this.englishToFrench.remove(englishExpression);
	}

	public void setTitleTopic(String topicTitle) {
		this.title = topicTitle;
	}
	
	public String getTitleTopic() {
		return this.title;
	}
	
	public HashMap<String,String> getTopicVocabulary() {
		return this.englishToFrench;
	}
	
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
}
