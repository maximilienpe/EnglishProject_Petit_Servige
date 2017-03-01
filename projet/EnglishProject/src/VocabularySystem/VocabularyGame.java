package VocabularySystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import LogSystem.LogLevel;
import LogSystem.Logger;

public class VocabularyGame {

	//variables logger
	private Logger logger;
	private Boolean addLog;

	//variable topic du jeu
	private TopicVocabulary selectedTopic;
	
	//variable words du jeu
	private int currentIndex;
	private Word askedWord;
	/*
	private String currentAskedExpression;
	private String currentAnswer;*/
	private ArrayList<Integer> wordPlayable;
	private ArrayList<Word> wordsToPlay;

	
	//données du jeu
	private TypeOfGame type;
	private int VocabScoreMax;
	private int VocabScore;
	
	//variables de condition du jeu
	private Boolean WordChecked;
	private Boolean VocabGameEnd;

	
	//vocabulary game part
	public VocabularyGame(TopicVocabulary selectedTopic, TypeOfGame typeOfGame, int numberOfWords, Logger logger) {
		if (selectedTopic != null && typeOfGame != null) {
			this.logger = logger;
			this.selectedTopic = selectedTopic;
			this.WordChecked = false;
			this.VocabGameEnd = false;
			this.VocabScore = 0;
			this.VocabScoreMax = Math.min(numberOfWords, this.selectedTopic.getTopicVocabulary().size());
			this.type = typeOfGame;
			this.wordPlayable = new ArrayList<Integer>();
			for (int i=0; i < this.selectedTopic.getTopicVocabulary().size() ; i++) {
				this.wordPlayable.add(i);
				//System.out.println(i+ "has been added.");
			}
			this.wordsToPlay = new ArrayList<Word>();
			this.wordsToPlay = this.generateRandomListOfWord(numberOfWords);
			this.currentIndex = 0;
			/*this.currentAnswer =null;
			this.currentAskedExpression= null;*/
			this.logger.addLog("VOCABULARYGAME Initilisation of a new game.", LogLevel.INFO);
		}
		else {
			this.logger.addLog("VOCABULARYGAME No topic has been selected.", LogLevel.ERROR);
		}
	}

	//verify if the word has already been checked and do nothing if the game is finished
	public void playVocabGame() {
		if (!this.VocabGameEnd && this.WordChecked) {
			if (this.currentIndex != this.wordsToPlay.size()-1) {
				this.askedWord = this.wordsToPlay.get(currentIndex);
				this.currentIndex++;
			}
			else {
				this.VocabGameEnd = true;
			}
		}
		else if (!this.WordChecked) {
			this.logger.addLog("VOCABULARYGAME Can't play a new word without having checked before.", LogLevel.WARNING);
		}
		else {
			this.logger.addLog("VOCABULARYGAME This game has already been finished.", LogLevel.INFO);
		}
		this.WordChecked = false;
	}

	public Boolean checkVocabAnswer(String answer) {
		Boolean valideAnswer = false;
		if (this.type.equals(TypeOfGame.ENGLISH)) {
			for (String fw : this.askedWord.getFrenchWords()) {
				if (fw.equals(answer.trim())) {
					this.VocabScore++;
					valideAnswer = true;
					break;
				}
			}
		}
		else if (this.type.equals(TypeOfGame.FRENCH)) {
			for (String ew : this.askedWord.getEnglishWords()) {
				if (ew.equals(answer.trim())) {
					this.VocabScore++;
					valideAnswer = true;
					break;
				}
			}
		}
		this.WordChecked = true;
		return valideAnswer;
	}

	public boolean askNewWord() {
		if (this.wordPlayable.size() != 0) {
			int numberChoice = (int)(Math.random() * ((this.wordPlayable.size()-1) + 1));
			int choice = this.wordPlayable.get(numberChoice);
			this.askedWord = this.selectedTopic.getTopicVocabulary().get(choice);
			//System.out.println(numberChoice);
			//System.out.println(choice);
			this.wordPlayable.remove(numberChoice);
			this.logger.addLog("VOCABULARYGAME A new word has been asked.", LogLevel.INFO);
			return true;
		}
		else {
			this.logger.addLog("VOCABULARYGAME No more word to test.", LogLevel.INFO);
			return false;
		}
	}
	
	public ArrayList<Word> generateRandomListOfWord(int numberOfWord) {
		Boolean outOfBounds = false;
		
		ArrayList<Word> listOfWords = new ArrayList<Word>();
		for (int i=0 ; i < numberOfWord ; i++) {
			if (askNewWord()) {
				listOfWords.add(this.askedWord);
			}
			else {
				this.logger.addLog("VOCABULARYGAME The number of word wanted exceed the number of word of this topic.", LogLevel.WARNING);
				outOfBounds = true;
				break;
			}
		}
		if (!outOfBounds) {
			this.logger.addLog("VOCABULARYGAME The words to play have been generated successfully.", LogLevel.INFO);
		}
		else {
			this.logger.addLog("VOCABULARYGAME The words to play have been generated.", LogLevel.INFO);
		}
		return listOfWords;
	}

	private String getEnglishAskedWord() {
		int numberChoice = (int)(Math.random() * ((this.askedWord.getEnglishWords().size()-1) + 1));
		return this.askedWord.getEnglishWords().get(numberChoice);
	}

	private String getFrenchAskedWord() {
		int numberChoice = (int)(Math.random() * ((this.askedWord.getFrenchWords().size()-1) + 1));
		return this.askedWord.getFrenchWords().get(numberChoice);
	}
	
	

	private Word getAskedWord() {
		return this.askedWord;
	}

	public TopicVocabulary getSelectedTopic() {
		return this.selectedTopic;
	}
	
	//if typeOfGame = TypeOfGame.ENGLISH it return an english word and the player have to give the french one
	//if typeOfGame = TypeOfGame.FRENCH it return a french word and the player have to give the english one
	public String getGameAskedExpression() {
		if (this.type.equals(TypeOfGame.ENGLISH)) {
			this.logger.addLog("VOCABULARYGAME An english word is given.", LogLevel.INFO);
			return this.getEnglishAskedWord();
		}
		else if (this.type.equals(TypeOfGame.FRENCH)) {
			this.logger.addLog("VOCABULARYGAME A french word is given.", LogLevel.INFO);
			return this.getFrenchAskedWord();
		}
		/*else if (this.type.equals(TypeOfGame.RANDOM)) {
			this.logger.addLog("VOCABULARYGAME An english of a french word is given.", LogLevel.INFO);
			int numberChoice = (int)(Math.random() * ((1) + 1));
			if (numberChoice == 0) {
				return this.getEnglishAskedWord();
			}
			else {
				return this.getFrenchAskedWord();
			}
		}*/
		else {
			this.logger.addLog("VOCABULARYGAME Wrong type of game.", LogLevel.ERROR);
			return null;
		}
	}
	
	public String getGameAnswerExpression() {
		if (this.type.equals(TypeOfGame.ENGLISH)) {
			this.logger.addLog("VOCABULARYGAME The answer in french is given.", LogLevel.INFO);
			return this.getFrenchAskedWord();
		}
		else if (this.type.equals(TypeOfGame.FRENCH)) {
			this.logger.addLog("VOCABULARYGAME The answer in english is given.", LogLevel.INFO);
			return this.getEnglishAskedWord();
		}
		/*else if (this.type.equals(TypeOfGame.RANDOM)) {
			this.logger.addLog("VOCABULARYGAME An english of a french word is given.", LogLevel.INFO);
			int numberChoice = (int)(Math.random() * ((1) + 1));
			if (numberChoice == 0) {
				return this.getEnglishAskedWord();
			}
			else {
				return this.getFrenchAskedWord();
			}
		}*/
		else {
			this.logger.addLog("VOCABULARYGAME Wrong type of game.", LogLevel.ERROR);
			return null;
		}
	}
	
	public int getVocabularyGameScore() {
		return this.VocabScore;
	}
	
	public int getVocabularyGameScoreMax() {
		return this.VocabScoreMax;
	}
	
	public Boolean getEndVocabularyGame() {
		return this.VocabGameEnd;
	}
	
	public ArrayList<Word> getWordsToPlay() {
		return this.wordsToPlay;
	}
 	
	public ArrayList<String> getPropositions(int numberProps) {
		if (this.selectedTopic.getTopicVocabulary().size() >= numberProps) {
			ArrayList<String> props = new ArrayList<String>();
			String answer;
			if (this.type.equals(TypeOfGame.ENGLISH)) {
				answer = this.getFrenchAskedWord();
			}
			else {
				answer = this.getEnglishAskedWord();
			}
			for (int i=0 ; i < this.wordsToPlay.size() ; i++) {
				if (i != this.currentIndex) {
					if (this.type.equals(TypeOfGame.ENGLISH)) {
						props.add(this.wordsToPlay.get(i).getFrenchWords().get(1));
					}
					else {
						props.add(this.wordsToPlay.get(i).getEnglishWords().get(1));
					}
				}
			}
			props.add(answer);
			Collections.sort(props.subList(1, props.size()));
			return props;
		}
		else {
			this.logger.addLog("VOCABULARYGAME There are not enough words in this topic.", LogLevel.ERROR);
			return null;
		}
	}
}
