package VocabularySystem;

import java.util.ArrayList;

import LogSystem.LogLevel;
import LogSystem.Logger;

public class VocabularyGame {

	//variables logger
	Logger logger;
	Boolean addLog;

	//variable vocabulaire jeu
	TopicVocabulary selectedTopic;
	Word askedWord;
	ArrayList<Integer> wordPlayable;
	TypeOfGame type;
	int VocabScore;
	int VocabScoreMax;
	Boolean VocabGameEnd;
	Boolean WordChecked;

	//vocabulary game part
	public VocabularyGame(TopicVocabulary selectedTopic, TypeOfGame typeOfGame, Logger logger) {
		if (selectedTopic != null && typeOfGame != null) {
			this.logger = logger;
			this.selectedTopic = selectedTopic;
			this.WordChecked = false;
			this.VocabGameEnd = false;
			this.VocabScore = 0;
			this.VocabScoreMax = this.selectedTopic.getTopicVocabulary().size();
			this.type = typeOfGame;
			this.wordPlayable = new ArrayList<Integer>();
			for (int i=0; i < this.selectedTopic.getTopicVocabulary().size() ; i++) {
				this.wordPlayable.add(i);
				//System.out.println(i+ "has been added.");
			}
			askNewWord();
			this.logger.addLog("VOCABULARYGAME Initilisation of a new game.", LogLevel.INFO);
		}
		else {
			this.logger.addLog("VOCABULARYGAME No topic has been selected.", LogLevel.ERROR);
		}
	}

	
	public void playVocabGame() {
		if (!this.VocabGameEnd && this.WordChecked) {
			this.askNewWord();
		}
		else if (!this.VocabGameEnd && ! this.WordChecked) {
			this.logger.addLog("VOCABULARYGAME Can't play a new word without having checked before.", LogLevel.WARNING);
		}
		else {
			this.logger.addLog("VOCABULARYGAME This game has already been finished.", LogLevel.INFO);
		}
	}

	public Boolean checkVocabAnswer(String answer) {
		Boolean valideAnswer = false;
		if (this.type.equals(TypeOfGame.ENGLISH)) {
			for (String fw : this.askedWord.getFrenchWords()) {
				if (fw.equals(answer)) {
					this.VocabScore++;
					valideAnswer = true;
					break;
				}
			}
		}
		else if (this.type.equals(TypeOfGame.FRENCH)) {
			for (String ew : this.askedWord.getEnglishWords()) {
				if (ew.equals(answer)) {
					this.VocabScore++;
					valideAnswer = true;
					break;
				}
			}
		}
		this.WordChecked = true;
		return valideAnswer;
	}

	public void askNewWord() {
		if (this.wordPlayable.size() != 0) {
			int numberChoice = (int)(Math.random() * ((this.wordPlayable.size()-1) + 1));
			int choice = this.wordPlayable.get(numberChoice);
			this.askedWord = this.selectedTopic.getTopicVocabulary().get(choice);
			//System.out.println(numberChoice);
			//System.out.println(choice);
			this.wordPlayable.remove(numberChoice);
			this.logger.addLog("VOCABULARYGAME A new word has been asked.", LogLevel.INFO);
		}
		else {
			this.logger.addLog("VOCABULARYGAME No more word to test.", LogLevel.INFO);
			this.VocabGameEnd = true;
		}

	}

	public String getEnglishAskedWord() {
		int numberChoice = (int)(Math.random() * ((this.askedWord.getEnglishWords().size()-1) + 1));
		return this.askedWord.getEnglishWords().get(numberChoice);
	}

	public String getFrenchAskedWord() {
		int numberChoice = (int)(Math.random() * ((this.askedWord.getFrenchWords().size()-1) + 1));
		return this.askedWord.getFrenchWords().get(numberChoice);
	}

	public Word getAskedWord() {
		return this.askedWord;
	}

	public TopicVocabulary getSelectedTopic() {
		return this.selectedTopic;
	}
	
	//if typeOfGame = TypeOfGame.ENGLISH it return an english word and the player have to give the french one
	//if typeOfGame = TypeOfGame.FRENCH it return a french word and the player have to give the english one
	public String getGameAskedExpression() {
		if (this.type.equals(TypeOfGame.ENGLISH)) {
			return getEnglishAskedWord();
		}
		else if (this.type.equals(TypeOfGame.FRENCH)) {
			return getFrenchAskedWord();
		}
		else {
			this.logger.addLog("VOCABULARYGAME Wrong type of game.", LogLevel.ERROR);
		}
		return null;
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
	
	
	

}
