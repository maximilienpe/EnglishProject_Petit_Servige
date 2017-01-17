package MainSystem;
import java.util.ArrayList;
import java.util.Scanner;

import LogSystem.LogLevel;
import LogSystem.Logger;
import ObserverSystem.Observer;
import VocabularySystem.TopicFactory;
import VocabularySystem.TopicVocabulary;
import VocabularySystem.TypeOfGame;
import VocabularySystem.VocabularyGame;
import VocabularySystem.Word;

public class Modele {

	/*
	 * Initialisation des variables du modèle
	 * 
	 */
	//Variables générales
	private ArrayList<Observer> observers;
	
	//variables logger
	private Logger modeleLogger;
	private Boolean showLog;
	private Boolean addLog;
	
	//Variables relatives au vocabulaire
	private ArrayList<TopicVocabulary> topics;
	private TopicFactory topicFactory;
	//variable vocabulaire jeu
	private TopicVocabulary selectedTopic;
	private VocabularyGame vocabGame;
	
	
	/*
	 * Constructor
	 * 
	 */
	public Modele() {
		//general var
		this.observers = new ArrayList<Observer>();
		
		//variable logger
		this.modeleLogger = new Logger();
		this.showLog = true;
		if (this.showLog) {
			this.modeleLogger.showLogOn();
		}
		else {
			this.modeleLogger.showLogOff();
		}
		this.addLog = true;
		
		//variables relatives au vocabulaire
		this.topics = new ArrayList<TopicVocabulary>();
		this.topicFactory = new TopicFactory(this.modeleLogger, this.addLog);
		this.selectedTopic = null;
		this.vocabGame = null;
	}
	
	/*
	 * Observer part
	 * 
	 */
	public void addObserver(Observer o) {
		this.observers.add(o);
		if (this.addLog) 
			this.modeleLogger.addLog("MODELE Observer added.", LogLevel.INFO);
	}
	
	public void notifyObservers() {
		for (Observer o : this.observers) {
			o.update();
		}
		if (this.addLog)
			this.modeleLogger.addLog("MODELE Observers notified.", LogLevel.INFO);
	}
	
	
	/*
	 * Logger part
	 * 
	 */
	public void setLoggerShow(Boolean b) {
		if (b) {
			this.modeleLogger.showLogOn();
			if (this.addLog) {
				this.modeleLogger.addLog("MODELE ShowLog when added On.", LogLevel.INFO);
			}
		}
		else {
			this.modeleLogger.showLogOff();
			if (this.addLog) {
				this.modeleLogger.addLog("MODELE ShowLog when added Off.", LogLevel.INFO);
			}
		}
	}
	
	public void setAddLog(Boolean addLog) {
		this.addLog = addLog;
	}
	
	public void setAddLogTopicFactory(Boolean addLog) {
		this.topicFactory.setAddLog(addLog);
	}
	
	public void setAddLogSubClassesTopicFactory(Boolean addLog) {
		this.topicFactory.setAddLogSubClasses(addLog);
	}
	
	public Logger getModeleLogger() {
		return this.modeleLogger;
	}
	
	/*
	 * Vocabulary Part
	 * 
	 */
	//initialisation
	public void loadTopics() {
		this.topics = topicFactory.makeAllTopic();
		if (addLog) {
			this.modeleLogger.addLog("MODELE Ask topicFactory makeAllTopic.", LogLevel.INFO);
		}
	}
	
	public void selectTopic(String titleTopic) {
		Boolean found = false;
		for (TopicVocabulary t : this.topics) {
			if (t.getTitleTopic().equals(titleTopic)) {
				this.selectedTopic = t;
				found = true;
				break;
			}
		}
		if (found) {
			this.modeleLogger.addLog("MODELE The topic named " + this.selectedTopic.getTitleTopic() + " has been selected.", LogLevel.INFO);
		}
		else {
			this.modeleLogger.addLog("MODELE The topic named " + titleTopic + " can't be found.", LogLevel.ERROR);
		}
	}
	
	public TopicVocabulary getSelectedTopic() {
		return this.selectedTopic;
	}
	
	//vocab game part
	public void launchVocabGame(String titleTopic, TypeOfGame type, int numberOfWords) {
		this.selectTopic(titleTopic);
		this.vocabGame = new VocabularyGame(this.selectedTopic,type,numberOfWords,this.modeleLogger);
		this.modeleLogger.addLog("MODELE A new game is launched.", LogLevel.INFO);
	}
	
	public void nextWordVocabGame() {
		this.vocabGame.playVocabGame();
	}
	
	public Boolean checkAnswerVocabGame(String answer) {
		return this.vocabGame.checkVocabAnswer(answer);
	}
	
	public String getTheWordToAskVocabGame() {
		return this.vocabGame.getGameAskedExpression();
	}
	
	public String getAnAnswer() {
		return this.vocabGame.getGameAnswerExpression();
	}
	
	public int getVocabGameScore() {
		return this.vocabGame.getVocabularyGameScore();
	}
	
	public int getVocabGameScoreMax() {
		return this.vocabGame.getVocabularyGameScoreMax();
	}
	
	public Boolean isVocabGameEndded() {
		return this.vocabGame.getEndVocabularyGame();
	}
	
	public ArrayList<Word> getWordsToPlay() {
		return this.vocabGame.getWordsToPlay();
	}
	
	//console part
	public void showTopicsTitle() {
		if (addLog) {
			this.modeleLogger.addLog("MODELE Show all topics title.", LogLevel.INFO);
		}
		for (TopicVocabulary topic : this.topics) {
			System.out.println(topic.getTitleTopic());
		}
	}
	
	public void showTopics() {
		if (addLog) {
			this.modeleLogger.addLog("MODELE Show all topics.", LogLevel.INFO);
		}
		for (TopicVocabulary topic : this.topics) {
			topic.displayTopic();
		}
	}
	
	public void showTopicsDetailed() {
		if (addLog) {
			this.modeleLogger.addLog("MODELE Show all topics in detail.", LogLevel.INFO);
		}
		for (TopicVocabulary topic : this.topics) {
			topic.displayTopicDetailed();
		}
	}
	
	
	/*
	 * Test of the Modele
	 * 
	 */
	public static void main(String args[]) {
		Modele modeleTest = new Modele();
		modeleTest.loadTopics();
		modeleTest.showTopicsTitle();
		System.out.println("Please choose a topic :");
		Scanner sc = new Scanner(System.in);
		String titleTopic = sc.nextLine();
		System.out.println("Please enter how many word you want :");
		sc = new Scanner(System.in);
		int numberOfWord = sc.nextInt();
		modeleTest.launchVocabGame(titleTopic,TypeOfGame.ENGLISH, numberOfWord);
		for (Word w : modeleTest.getWordsToPlay()) {
			w.showWord();
		}
		while (!modeleTest.isVocabGameEndded()) {
			System.out.println("What is the french for : " + modeleTest.getTheWordToAskVocabGame());
			sc = new Scanner(System.in);
			String answer = sc.nextLine();
			if (modeleTest.checkAnswerVocabGame(answer)) {
				System.out.println("Good job !");
				modeleTest.nextWordVocabGame();
			}
			else {
				System.out.println("Sniff, the right answer is : " + modeleTest.getAnAnswer() + " ! :(");
				modeleTest.nextWordVocabGame();
			}
		}
		int score = modeleTest.getVocabGameScore();
		int scoreMax = modeleTest.getVocabGameScoreMax();
		System.out.println("This game is over ! You have " + score + " out of " + scoreMax + "!");
	}	
	
}
