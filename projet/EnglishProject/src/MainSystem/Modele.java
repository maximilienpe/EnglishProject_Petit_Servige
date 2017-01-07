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
	
	//console part
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
		//modeleTest.showTopicsDetailed();
		modeleTest.selectTopic("TestTopic");
		VocabularyGame game = new VocabularyGame(modeleTest.getSelectedTopic(),TypeOfGame.ENGLISH, modeleTest.getModeleLogger());
		while (!game.getEndVocabularyGame()) {
			System.out.println("What is the french for : " + game.getGameAskedExpression());
			Scanner sc = new Scanner(System.in);
			String answer = sc.nextLine();
			if (game.checkVocabAnswer(answer)){
				System.out.println("Good job !");
				game.playVocabGame();
			}
			else {
				System.out.println("Sniff, the right answer is : " + game.getFrenchAskedWord() + " ! :(");
				game.playVocabGame();
			}
		}
		int score = game.getVocabularyGameScore();
		int scoreMax = game.getVocabularyGameScoreMax();
		System.out.println("This game is over ! You have " + score + " out of " + scoreMax + "!");
	}	
	
}
