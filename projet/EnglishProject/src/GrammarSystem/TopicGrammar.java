package GrammarSystem;

import java.util.ArrayList;

import VocabularySystem.Word;

public class TopicGrammar {

	private String topicTitle;
	private ArrayList<GrammarQuestion> questions;
	
	public TopicGrammar() {
		this.topicTitle = null;
		this.questions = new ArrayList<GrammarQuestion>();
	}
	
	public TopicGrammar(String tT, ArrayList<GrammarQuestion> q) {
		this.topicTitle = tT;
		this.questions = q;
	}
	
	public void setTopicTitle(String tT) {
		this.topicTitle = tT;
	}
	
	public void addQuestion(GrammarQuestion q) {
		Boolean isFound = false;
		for (GrammarQuestion question : this.questions) {
			if (question.getGrammarQuestion().trim().equals(q.getGrammarQuestion().trim())) {
				isFound = true;
				break;
			}
		}
		if (!isFound) {
			//si la question de grammaire n'a pas déjà été ajouté alors on l'ajoute
			this.questions.add(q);
		}
	}
	
	public String getTopicTitle() {
		return this.topicTitle;
	}
	
	public ArrayList<GrammarQuestion> getGrammarQuestion() {
		return this.questions;
	}
	
	
	//console part
	public void displayGrammarTopic() {
		for (GrammarQuestion gq : this.getGrammarQuestion()) {
			System.out.println(gq.getGrammarQuestion());
			int counter = 1;
			for (String p : gq.getGrammarProposition()) {
				System.out.println(counter + ". " + p);
			}
		}
	}
	
	public void displayGrammarTopicDetailed() {
		System.out.println("Topic Title : " + this.topicTitle);
		for (GrammarQuestion gq : this.getGrammarQuestion()) {
			System.out.println(gq.getGrammarQuestion());
			int counter = 1;
			for (String p : gq.getGrammarProposition()) {
				System.out.println(counter + ". " + p);
			}
		}
	}
	
}
