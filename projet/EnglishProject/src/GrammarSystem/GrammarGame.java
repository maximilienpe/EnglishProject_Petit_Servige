package GrammarSystem;

import java.util.ArrayList;

import LogSystem.LogLevel;
import LogSystem.Logger;

public class GrammarGame {

	//variables logger
	private Logger logger;
	private Boolean addLog;

	//variable topic du jeu
	private TopicGrammar selectedTopic;
	
	//variables question du jeu
	private int currentIndex;
	private GrammarQuestion askedQuestion;
	/*
	private String currentAskedExpression;
	private String currentAnswer;*/
	private ArrayList<Integer> questionsPlayable;
	private ArrayList<GrammarQuestion> questionsToPlay;

	
	//données du jeu
	private int GrammarScoreMax;
	private int GrammarScore;
	
	//variables de condition du jeu
	private Boolean answerChecked;
	private Boolean GrammarGameEnd;

	

	//grammar game part
	public GrammarGame(TopicGrammar selectedTopic, int numberOfQuestions, Logger logger) {
		if (selectedTopic != null) {
			this.logger = logger;
			this.selectedTopic = selectedTopic;
			this.answerChecked = false;
			this.GrammarGameEnd = false;
			this.GrammarScore = 0;
			this.GrammarScoreMax = Math.min(numberOfQuestions, this.selectedTopic.getGrammarQuestion().size());
			this.questionsPlayable = new ArrayList<Integer>();
			for (int i=0; i < this.selectedTopic.getGrammarQuestion().size() ; i++) {
				this.questionsPlayable.add(i);
				//System.out.println(i+ "has been added.");
			}
			this.generateRandomListOfWord(numberOfQuestions);
			this.currentIndex = 0;
			/*this.currentAnswer =null;
			this.currentAskedExpression= null;*/
			this.logger.addLog("GRAMMARGAME Initilisation of a new grammar game.", LogLevel.INFO);
		}
		else {
			this.logger.addLog("GRAMMARGAME No grammar topic has been selected.", LogLevel.ERROR);
		}
	}

	//verify if the word has already been checked and do nothing if the game is finished
	public void playGrammarGame() {
		if (!this.GrammarGameEnd && this.answerChecked) {
			if (this.currentIndex != this.questionsToPlay.size()-1) {
				this.askedQuestion = this.questionsToPlay.get(currentIndex);
				this.currentIndex++;
			}
			else {
				this.GrammarGameEnd = true;
			}
		}
		else if (!this.answerChecked) {
			this.logger.addLog("GRAMMARGAME Can't play a new word without having checked before.", LogLevel.WARNING);
		}
		else {
			this.logger.addLog("GRAMMARGAME This game has already been finished.", LogLevel.INFO);
		}
		this.answerChecked = false;
	}

	public Boolean checkGrammarAnswer(String answer) {
		Boolean valideAnswer = false;
		int answerIndex = -1;
		int counter = 1;
		for (String p : this.askedQuestion.getGrammarProposition()) {
			//System.out.println("Is : " + p + " equal to : " + answer.trim());
			if (p.trim().equals(answer.trim())) {
				answerIndex = counter;
				//System.out.println("The answer choose is the number : " + answerIndex);
				break;
			}
			counter++;
			answerIndex = counter;
		}
		//System.out.println(answerIndex);
		if (this.askedQuestion.getGrammarAnswer() == answerIndex) {
			valideAnswer = true;
			this.GrammarScore++;
		}
		else if (answerIndex > this.askedQuestion.getGrammarProposition().size()) {
			this.logger.addLog("GRAMMARGAME The answer don't match with the propositions.", LogLevel.WARNING);
		}
		this.answerChecked = true;
		return valideAnswer;
	}

	public boolean askNewQuestion() {
		if (this.questionsPlayable.size() != 0) {
			int numberChoice = (int)(Math.random() * ((this.questionsPlayable.size()-1) + 1));
			int choice = this.questionsPlayable.get(numberChoice);
			this.askedQuestion = this.selectedTopic.getGrammarQuestion().get(choice);
			//System.out.println(numberChoice);
			//System.out.println(choice);
			this.questionsPlayable.remove(numberChoice);
			this.logger.addLog("GRAMMARGAME A new question has been asked.", LogLevel.INFO);
			return true;
		}
		else {
			this.logger.addLog("GRALLARGAME No more question to test.", LogLevel.INFO);
			return false;
		}
	}
	
	public void generateRandomListOfWord(int numberOfWord) {
		Boolean outOfBounds = false;
		
		this.questionsToPlay = new ArrayList<GrammarQuestion>();
		for (int i=0 ; i < numberOfWord ; i++) {
			if (askNewQuestion()) {
				this.questionsToPlay.add(this.askedQuestion);
			}
			else {
				this.logger.addLog("GRAMMARGAME The number of questions wanted exceed the number of questions of this grammar topic.", LogLevel.WARNING);
				outOfBounds = true;
				break;
			}
		}
		if (!outOfBounds) {
			this.logger.addLog("GRAMMARGAME The questions to play have been generated successfully.", LogLevel.INFO);
		}
		else {
			this.logger.addLog("GRAMMARGAME The questions to play have been generated.", LogLevel.INFO);
		}
	}

	private GrammarQuestion getAskedQuestion() {
		return this.askedQuestion;
	}

	public TopicGrammar getSelectedTopic() {
		return this.selectedTopic;
	}
	
	public String getGameAskedExpression() {
		return this.askedQuestion.getGrammarQuestion();
	}
	
	public ArrayList<String> getGameAskedPropositions() {
		return this.askedQuestion.getGrammarProposition();
	}
	
	public String getGameAnswerExpression() {
		return this.askedQuestion.getGrammarProposition().get(this.askedQuestion.getGrammarAnswer()-1);
	}
	
	public int getGrammarGameScore() {
		return this.GrammarScore;
	}
	
	public int getGrammarGameScoreMax() {
		return this.GrammarScoreMax;
	}
	
	public Boolean getEndGrammarGame() {
		return this.GrammarGameEnd;
	}
	
	public ArrayList<GrammarQuestion> getQuestionsToPlay() {
		return this.questionsToPlay;
	}
 	

}
