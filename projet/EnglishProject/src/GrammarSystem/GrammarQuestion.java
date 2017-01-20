package GrammarSystem;

import java.util.ArrayList;

public class GrammarQuestion {

	private String question;
	private ArrayList<String> propositions;
	private int answer;
	
	public GrammarQuestion(String q, ArrayList<String> p, int a) {
		this.question = q;
		this.propositions = p;
		this.answer = a;
	}
	
	public String getGrammarQuestion() {
		return this.question;
	}
	
	public ArrayList<String> getGrammarProposition() {
		return this.propositions;
	}
	
	public int getGrammarAnswer() {
		return this.answer;
	}
}
