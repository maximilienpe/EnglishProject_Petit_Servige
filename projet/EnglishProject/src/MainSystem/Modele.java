package MainSystem;

import java.util.ArrayList;

import GrammarSystem.GrammarFactory;
import GrammarSystem.GrammarGame;
import GrammarSystem.GrammarQuestion;
import GrammarSystem.TopicGrammar;
import LogSystem.LogLevel;
import LogSystem.Logger;
import ObserverSystem.Observer;
import VocabularySystem.TopicFactory;
import VocabularySystem.TopicVocabulary;
import VocabularySystem.TypeOfGame;
import VocabularySystem.VocabularyGame;
import VocabularySystem.VocabularyGame2;
import VocabularySystem.Word;

public class Modele {

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Initialisation des variables du modèle
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Variables générales
	private ArrayList<Observer> observers;

	// variables logger
	private Logger modeleLogger;
	private Boolean showLog;
	private Boolean addLog;

	// Variables relatives au vocabulaire
	private ArrayList<TopicVocabulary> topics;
	private TopicFactory topicFactory;
	// variable vocabulaire jeu
	private TopicVocabulary selectedTopic;
	private ArrayList<TopicVocabulary> allSelectedTopics;
	private VocabularyGame vocabGame;
	private VocabularyGame2 vocabGameGraphic;

	// variables relatives à la grammaire
	private ArrayList<TopicGrammar> grammarTopics;
	private GrammarFactory grammarFactory;
	// variable grammaire jeu
	private TopicGrammar selectedGrammarTopic;
	private GrammarGame grammarGame;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Constructor
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Modele() {
		// general var
		this.observers = new ArrayList<Observer>();

		// variable logger
		this.modeleLogger = new Logger();
		this.showLog = true;
		if (this.showLog) {
			this.modeleLogger.showLogOn();
		} else {
			this.modeleLogger.showLogOff();
		}
		this.addLog = true;

		// variables relatives au vocabulaire
		this.topics = new ArrayList<TopicVocabulary>();
		this.topicFactory = new TopicFactory(this.modeleLogger, this.addLog);
		this.selectedTopic = null;
		this.allSelectedTopics = new ArrayList<TopicVocabulary>();
		this.vocabGame = null;
		this.vocabGameGraphic = null;

		// variables relatives à la grammaire
		this.grammarTopics = new ArrayList<TopicGrammar>();
		this.grammarFactory = new GrammarFactory(this.modeleLogger, this.addLog);
		this.selectedGrammarTopic = null;
		this.grammarGame = null;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Observer part
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Logger part
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setLoggerShow(Boolean b) {
		if (b) {
			this.modeleLogger.showLogOn();
			if (this.addLog) {
				this.modeleLogger.addLog("MODELE ShowLog when added On.", LogLevel.INFO);
			}
		} else {
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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Vocabulary Part
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// initialisation
	public void loadTopics() {
		this.topics = topicFactory.makeAllTopic();
		if (addLog) {
			this.modeleLogger.addLog("MODELE Ask topicFactory makeAllTopic.", LogLevel.INFO);
		}
	}

	public void selectTopic(String titleTopic) {
		Boolean found = false;
		for (TopicVocabulary t : this.topics) {
			if (t.getTitleTopic().equals(titleTopic.trim())) {
				this.selectedTopic = t;
				found = true;
				break;
			}
		}
		if (found) {
			this.modeleLogger.addLog(
					"MODELE The topic named " + this.selectedTopic.getTitleTopic() + " has been selected.",
					LogLevel.INFO);
		} else {
			this.modeleLogger.addLog("MODELE The topic named " + titleTopic + " can't be found.", LogLevel.ERROR);
		}
	}

	public void addSelectedTopic(String titleTopic) {
		Boolean found = false;
		for (TopicVocabulary t : this.topics) {
			if (t.getTitleTopic().equals(titleTopic.trim())) {
				this.allSelectedTopics.add(t);
				found = true;
				break;
			}
		}
		if (found) {
			this.modeleLogger.addLog("MODELE The topic named " + titleTopic + " has been selected.", LogLevel.INFO);
		} else {
			this.modeleLogger.addLog("MODELE The topic named " + titleTopic + " can't be found.", LogLevel.ERROR);
		}
	}

	public void deleteSelectedTopic(String titleTopic) {
		Boolean found = false;
		for (TopicVocabulary t : this.allSelectedTopics) {
			if (t.getTitleTopic().equals(titleTopic.trim())) {
				this.allSelectedTopics.remove(t);
				found = true;
				break;
			}
		}
		if (found) {
			this.modeleLogger.addLog("MODELE The topic named " + titleTopic + " has been diselected.", LogLevel.INFO);
		} else {
			this.modeleLogger.addLog("MODELE The topic named " + titleTopic + " can't be found.", LogLevel.ERROR);
		}
	}

	public void mergeTopicVocabulary() {
		TopicVocabulary mergedTopic = new TopicVocabulary();
		for (TopicVocabulary tv : this.allSelectedTopics) {
			for (Word w : tv.getTopicVocabulary()) {
				mergedTopic.addAWord(w);
			}
		}
		this.selectedTopic = mergedTopic;
	}
	
	public ArrayList<String> getAllTitleSelectedTopic() {
		ArrayList<String> allTitles = new ArrayList<String>();
		for (TopicVocabulary tv : this.allSelectedTopics) {
			allTitles.add(tv.getTitleTopic());
		}
		return allTitles;
	}

	public TopicVocabulary getSelectedTopic() {
		return this.selectedTopic;
	}

	public ArrayList<TopicVocabulary> getTopics() {
		if (this.topics != null) {
			return this.topics;
		} else {
			return null;
		}
	}

	// vocab game part
	public void launchVocabGame(String titleTopic, TypeOfGame type, int numberOfWords) {
		this.selectTopic(titleTopic);
		this.vocabGame = new VocabularyGame(this.selectedTopic, type, numberOfWords, this.modeleLogger);
		this.modeleLogger.addLog("MODELE A new game is launched.", LogLevel.INFO);
	}

	public void launchVocabGame(ArrayList<String> titlesTopics, TypeOfGame type, int numberOfWords) {
		this.mergeTopicVocabulary();
		this.vocabGame = new VocabularyGame(this.selectedTopic, type, numberOfWords, this.modeleLogger);
		this.modeleLogger.addLog("MODELE A new game is launched.", LogLevel.INFO);
	}

	public void nextWordVocabGame() {
		if (!(this.vocabGame == null)) {
			this.vocabGame.playVocabGame();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
		}
	}

	public Boolean checkAnswerVocabGame(String answer) {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.checkVocabAnswer(answer);
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return false;
		}
	}

	public String getTheWordToAskVocabGame() {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.getGameAskedExpression();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return null;
		}
	}

	public String getAnAnswer() {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.getGameAnswerExpression();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return null;
		}
	}

	public int getVocabGameScore() {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.getVocabularyGameScore();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return -1;
		}

	}

	public int getVocabGameScoreMax() {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.getVocabularyGameScoreMax();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return -1;
		}
	}

	public Boolean isVocabGameEndded() {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.getEndVocabularyGame();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return false;
		}
	}

	public ArrayList<Word> getWordsToPlay() {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.getWordsToPlay();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return null;
		}
	}

	public ArrayList<String> getVocabGamePropositions(int numberProps) {
		if (!(this.vocabGame == null)) {
			return this.vocabGame.getPropositions(numberProps);
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game launched.", LogLevel.ERROR);
			return null;
		}
	}

	// console part
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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Grammar part
	 * 
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void loadGrammarTopics() {
		this.grammarTopics = this.grammarFactory.makeAllTopic();
		if (addLog) {
			this.modeleLogger.addLog("MODELE Ask GrammarFactory makeAllTopic.", LogLevel.INFO);
		}
	}

	public void selectGrammarTopic(String titleTopic) {
		Boolean found = false;
		for (TopicGrammar t : this.grammarTopics) {
			if (t.getTopicTitle().equals(titleTopic.trim())) {
				this.selectedGrammarTopic = t;
				found = true;
				break;
			}
		}
		if (found) {
			this.modeleLogger.addLog("MODELE The grammar topic named " + this.selectedGrammarTopic.getTopicTitle()
					+ " has been selected.", LogLevel.INFO);
		} else {
			this.modeleLogger.addLog("MODELE The grammar topic named " + titleTopic + " can't be found.",
					LogLevel.ERROR);
		}
	}

	public TopicGrammar getSelectedGrammarTopic() {
		return this.selectedGrammarTopic;
	}

	// grammar game part

	public void launchGrammarGame(String titleTopic, int numberOfQuestions) {
		this.selectGrammarTopic(titleTopic);
		this.grammarGame = new GrammarGame(this.selectedGrammarTopic, numberOfQuestions, this.modeleLogger);
		this.modeleLogger.addLog("MODELE A new game is launched.", LogLevel.INFO);
	}

	public void nextQuestionGrammarGame() {
		this.grammarGame.playGrammarGame();
	}

	public Boolean checkAnswerGrammarGame(String answer) {
		// System.out.println("Check the validity of the answer.");
		return this.grammarGame.checkGrammarAnswer(answer);
	}

	public String getTheQuestionToAskGrammarGame() {
		return this.grammarGame.getGameAskedExpression();
	}

	public ArrayList<String> getThPropositionsGrammarGame() {
		return this.grammarGame.getGameAskedPropositions();
	}

	public String getAGrammarAnswer() {
		return this.grammarGame.getGameAnswerExpression();
	}

	public int getGrammarGameScore() {
		return this.grammarGame.getGrammarGameScore();
	}

	public int getGrammarGameScoreMax() {
		return this.grammarGame.getGrammarGameScoreMax();
	}

	public Boolean isGrammarGameEndded() {
		return this.grammarGame.getEndGrammarGame();
	}

	public ArrayList<GrammarQuestion> getQuestionsToPlay() {
		return this.grammarGame.getQuestionsToPlay();
	}

	// console part
	public void showGrammarTopicsTitle() {
		if (addLog) {
			this.modeleLogger.addLog("MODELE Show all grammar topics title.", LogLevel.INFO);
		}
		for (TopicGrammar topic : this.grammarTopics) {
			System.out.println(topic.getTopicTitle());
		}
	}

	public void showGrammarTopics() {
		if (addLog) {
			this.modeleLogger.addLog("MODELE Show all grammar topics.", LogLevel.INFO);
		}
		for (TopicGrammar topic : this.grammarTopics) {
			topic.displayGrammarTopic();
		}
	}

	public void showGrammarTopicsDetailed() {
		if (addLog) {
			this.modeleLogger.addLog("MODELE Show all grammar topics in detail.", LogLevel.INFO);
		}
		for (TopicGrammar topic : this.grammarTopics) {
			topic.displayGrammarTopicDetailed();
		}
	}

	/*
	 * Part of the VocabularyGame for the graphic interface
	 * 
	 */

	public ArrayList<String> getAllTopics() {
		ArrayList<String> k = new ArrayList<String>();
		for (TopicVocabulary topic : this.topics) {
			k.add(topic.getTitleTopic());
		}
		return k;
	}

	public void launchVocabGameGraphic(String titleTopic, TypeOfGame type, int numberOfWords, int numberOfPropositions,
			int life) {
		this.selectTopic(titleTopic);
		this.vocabGameGraphic = new VocabularyGame2(this.selectedTopic, type, numberOfWords, numberOfPropositions,
				this.modeleLogger, life);
		this.modeleLogger.addLog("MODELE A new game is launched.", LogLevel.INFO);
	}

	public ArrayList<String> getAllTheAskedWordsVocabGameGraphic() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getAllTheAskedWords();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return null;
		}
	}

	public void launchVocabGameGraphic(ArrayList<String> titlesTopics, TypeOfGame type, int numberOfWords,
			int numberOfPropositions, int life) {
		for (String s : titlesTopics) {
			System.out.println(s);
			this.addSelectedTopic(s);
		}
		this.mergeTopicVocabulary();
		this.vocabGameGraphic = new VocabularyGame2(this.selectedTopic, type, numberOfWords, numberOfPropositions,
				this.modeleLogger, life);
		this.modeleLogger.addLog("MODELE A new game is launched.", LogLevel.INFO);
	}

	public ArrayList<String> getLeftPropositionsVocabGameGraphic(int indexOfTheWord) {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getLeftPropositions(indexOfTheWord);
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return null;
		}
	}

	public ArrayList<String> getRightPropositionsVocabGameGraphic(int indexOfTheWord) {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getRightPropositions(indexOfTheWord);
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return null;
		}
	}

	public Boolean playVocabGameGraphic(String word, String answer) {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.playVocabGame(word, answer);
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return null;
		}
	}

	public int getVocabGameGraphicScore() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getVocabularyGameScore();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return -1;
		}

	}

	public int getVocabGameGraphicScoreMax() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getVocabularyGameScoreMax();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return -1;
		}
	}

	public Boolean isVocabGameGraphicEndded() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getEndVocabularyGame();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return false;
		}
	}

	public void setVocabGameGraphicEndded(Boolean b) {
		if (!(this.vocabGameGraphic == null)) {
			this.vocabGameGraphic.setEndVocabularyGame(b);
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
		}
	}

	public int getVocabGameGraphicLife() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getLife();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return -1;
		}

	}
	
	public int getVocabGameGraphicMaxLife() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getMaxLife();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return -1;
		}

	}
	
	public int getVocabGameGraphicNumberOfWords() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getNumberOfWords();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return -1;
		}

	}
	
	public int getVocabGameGraphicNumberOfPropositions() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getNumberOfPropositions();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return -1;
		}

	}
	
	public TypeOfGame getVocabGameGraphicTypeOfGame() {
		if (!(this.vocabGameGraphic == null)) {
			return this.vocabGameGraphic.getTypeOfGame();
		} else {
			this.modeleLogger.addLog("MODELE There is no vocabulary game graphic launched.", LogLevel.ERROR);
			return null;
		}
	}

	/*
	 * Test of the Modele
	 * 
	 */
	/*
	 * public static void main(String args[]) { Modele modeleTest = new
	 * Modele(); Scanner sc = new Scanner(System.in);
	 * System.out.println("Vocab Game or Grammar Game ? (answer v ou g)");
	 * String g = sc.nextLine().trim(); if (g.equals("v")) {
	 * modeleTest.loadTopics(); modeleTest.showTopicsTitle();
	 * System.out.println("Please choose a topic :"); sc = new
	 * Scanner(System.in); String titleTopic = sc.nextLine();
	 * System.out.println("Please enter how many word you want :"); sc.close();
	 * sc = new Scanner(System.in); int numberOfWord = sc.nextInt();
	 * modeleTest.launchVocabGame(titleTopic,TypeOfGame.ENGLISH, numberOfWord);
	 * for (Word w : modeleTest.getWordsToPlay()) { w.showWord(); } while
	 * (!modeleTest.isVocabGameEndded()) {
	 * System.out.println("What is the french for : " +
	 * modeleTest.getTheWordToAskVocabGame()); sc = new Scanner(System.in);
	 * String answer = sc.nextLine(); if
	 * (modeleTest.checkAnswerVocabGame(answer)) {
	 * System.out.println("Good job !"); modeleTest.nextWordVocabGame(); } else
	 * { System.out.println("Sniff, the right answer is : " +
	 * modeleTest.getAnAnswer() + " ! :("); modeleTest.nextWordVocabGame(); } }
	 * int score = modeleTest.getVocabGameScore(); int scoreMax =
	 * modeleTest.getVocabGameScoreMax();
	 * System.out.println("This game is over ! You have " + score + " out of " +
	 * scoreMax + "!"); } else if (g.equals("g")) {
	 * modeleTest.loadGrammarTopics(); modeleTest.showGrammarTopicsTitle();
	 * System.out.println("Please choose a topic :"); sc = new
	 * Scanner(System.in); String titleTopic = sc.nextLine();
	 * System.out.println("Please enter how many questions you want :"); sc =
	 * new Scanner(System.in); int numberOfQuestions = sc.nextInt();
	 * modeleTest.launchGrammarGame(titleTopic, numberOfQuestions); for
	 * (GrammarQuestion gq : modeleTest.getQuestionsToPlay()) {
	 * System.out.println(gq.getGrammarQuestion()); } while
	 * (!modeleTest.isGrammarGameEndded()) { System.out.
	 * println("Choose the right answer : (write all the proposition without the number)"
	 * ); System.out.println(modeleTest.getTheQuestionToAskGrammarGame()); int
	 * counter = 1; for (String p : modeleTest.getThPropositionsGrammarGame()) {
	 * System.out.println(counter + ". " + p); counter++; } sc = new
	 * Scanner(System.in); String answer = sc.nextLine(); if
	 * (modeleTest.checkAnswerGrammarGame(answer)) {
	 * System.out.println("Good job !"); modeleTest.nextQuestionGrammarGame(); }
	 * else { System.out.println("Sniff, the right answer is : " +
	 * modeleTest.getAGrammarAnswer() + " ! :(");
	 * modeleTest.nextQuestionGrammarGame(); } int score =
	 * modeleTest.getGrammarGameScore(); int scoreMax =
	 * modeleTest.getGrammarGameScoreMax();
	 * System.out.println("This game is over ! You have " + score + " out of " +
	 * scoreMax + "!"); } } sc.close(); }
	 */
}
