package VocabularySystem;

import java.util.ArrayList;

import LogSystem.LogLevel;
import LogSystem.Logger;

public class VocabularyGame2 {

	// variables logger
	private Logger logger;
	private Boolean addLog;

	// variable topic du jeu
	private TopicVocabulary selectedTopic;

	// variable words du jeu
	private ArrayList<Word> wordsToPlay;
	private ArrayList<Integer> englishWordsIndex;
	private ArrayList<Integer> frenchWordsIndex;
	private ArrayList<String> allAskedWords;
	private ArrayList<String> allAnswer;
	private ArrayList<Integer> indexOfWordsOff;
	private ArrayList<ArrayList<Word>> wordsPropositions;
	private ArrayList<ArrayList<Integer>> propositionsIndex;
	private int selectedIndex;
	private Word selectedWord;

	// données du jeu
	private TypeOfGame type;
	private int numberOfWords;
	private int numberOfPropositions;
	private int VocabScoreMax;
	private int VocabScore;

	// variables de condition du jeu
	private Boolean WordChecked;
	private Boolean VocabGameEnd;

	// vocabulary game part
	public VocabularyGame2(TopicVocabulary selectedTopic, TypeOfGame typeOfGame, int numberOfWords,
			int numberOfPropositions, Logger logger) {
		if (selectedTopic != null && typeOfGame != null) {
			this.numberOfWords = numberOfWords;
			this.numberOfPropositions = numberOfPropositions;
			this.logger = logger;
			this.selectedTopic = selectedTopic;
			this.VocabGameEnd = false;
			this.VocabScore = 0;
			this.VocabScoreMax = Math.min(numberOfWords, this.selectedTopic.getTopicVocabulary().size());
			this.type = typeOfGame;
			this.selectedIndex = 0;
			this.selectedWord = null;
			this.indexOfWordsOff = new ArrayList<Integer>();
			// initialisation de la liste de mots aléatoire
			this.wordsToPlay = generateRandomListOfWord(numberOfWords, this.selectedTopic, -1);
			// generate the propositions of each words in wordsToPlay
			this.wordsPropositions = new ArrayList<ArrayList<Word>>();
			for (Word w : wordsToPlay) {
				// generate a list of proposition for the word w and add the
				// proposition w to have an access to the answer
				ArrayList<Word> wPropositions = generateRandomListOfWord(numberOfPropositions - 1, this.selectedTopic,
						this.selectedTopic.getTopicVocabulary().indexOf(w));
				int randomNumber = (int) (Math.random() * (numberOfPropositions) - 1);
				wPropositions.add(randomNumber, w);
				// add propositions to the list of propositions
				this.wordsPropositions.add(wPropositions);
			}
			// choix des expressions de chaques mots
			this.englishWordsIndex = generateRandomListOfIndex(this.wordsToPlay, "ENGLISH");
			this.frenchWordsIndex = generateRandomListOfIndex(this.wordsToPlay, "FRENCH");
			this.propositionsIndex = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Word> lw : this.wordsPropositions) {
				if (this.type.equals(TypeOfGame.ENGLISH)) {
					this.propositionsIndex.add(generateRandomListOfIndex(lw, "FRENCH"));
				} else if (this.type.equals(TypeOfGame.FRENCH)) {
					this.propositionsIndex.add(generateRandomListOfIndex(lw, "ENGLISH"));
				}
			}
			for (int h = 0; h < this.propositionsIndex.size() - 1; h++) {
				System.out.println(this.propositionsIndex.get(h));
			}
			// String
			this.allAskedWords = generateAllTheAskedWords();
			this.allAnswer = generateAllTheAnswer();

			this.logger.addLog("VOCABULARYGAME Initilisation of a new game.", LogLevel.INFO);
		} else {
			this.logger.addLog("VOCABULARYGAME No topic has been selected.", LogLevel.ERROR);
		}
	}

	public ArrayList<Word> generateRandomListOfWord(int numberOfWord, TopicVocabulary targetTopic, int avoidingIndex) {
		ArrayList<Integer> randomIndex = new ArrayList<Integer>();
		ArrayList<Word> randomWord = new ArrayList<Word>();
		// generate a list of random number between 0 and the number of word in
		// the topic -1 which size is numberOfWord
		while (randomIndex.size() < numberOfWord) {
			int randomNumber = (int) (Math.random() * ((targetTopic.getTopicVocabulary().size() - 1)));
			if (!(randomIndex.contains(randomNumber)) && !(avoidingIndex == randomNumber)) {
				randomIndex.add(randomNumber);
			}
		}
		// put into randomWord the word which correspond to the word at the
		// index of randomIndex
		for (Integer i : randomIndex) {
			randomWord.add(targetTopic.getTopicVocabulary().get(i));
		}
		return randomWord;
	}

	public ArrayList<Integer> generateRandomListOfIndex(ArrayList<Word> words, String languageAsked) {
		ArrayList<Integer> randomIndex = new ArrayList<Integer>();
		for (Word w : words) {
			int randomNumber = -1;
			if (languageAsked.equals("ENGLISH")) {
				randomNumber = (int) (Math.random() * ((w.getEnglishWords().size() - 1)));
			} else if (languageAsked.equals("FRENCH")) {
				randomNumber = (int) (Math.random() * ((w.getFrenchWords().size() - 1)));
			}
			randomIndex.add(randomNumber);
		}
		return randomIndex;
	}

	public ArrayList<String> generateAllTheAskedWords() {
		ArrayList<String> allAskedWords = new ArrayList<String>();
		for (Word w : this.wordsToPlay) {
			if (this.type.equals(TypeOfGame.ENGLISH)) {
				allAskedWords.add(w.getEnglishWords().get(this.englishWordsIndex.get(this.wordsToPlay.indexOf(w))));
			} else if (this.type.equals(TypeOfGame.FRENCH)) {
				allAskedWords.add(w.getFrenchWords().get(this.frenchWordsIndex.get(this.wordsToPlay.indexOf(w))));
			}
		}
		return allAskedWords;
	}

	public ArrayList<String> generateAllTheAnswer() {
		ArrayList<String> allAnswerWords = new ArrayList<String>();
		for (Word w : this.wordsToPlay) {
			if (this.type.equals(TypeOfGame.ENGLISH)) {
				allAnswerWords.add(w.getFrenchWords().get(this.frenchWordsIndex.get(this.wordsToPlay.indexOf(w))));
			} else if (this.type.equals(TypeOfGame.FRENCH)) {
				allAnswerWords.add(w.getEnglishWords().get(this.englishWordsIndex.get(this.wordsToPlay.indexOf(w))));
			}
		}
		return allAskedWords;
	}

	// verify if the word has already been checked and do nothing if the game is
	// finished
	public boolean playVocabGame(String words, String selectedProposition) {
		if (!this.VocabGameEnd) {
			// while the game hasn't finished yet
			if (selectedProposition.equals(this.allAnswer.get(this.allAskedWords.indexOf(words)))) {
				this.VocabScore++;
				this.indexOfWordsOff.add(this.allAskedWords.indexOf(words));
				return true;
			} else {
				// what should we do if he choose a bad answer ?
				return false;
			}
		} else {
			return false;
		}
	}

	public ArrayList<String> getLeftPropositions(int indexOfTheWord) {
		ArrayList<String> leftPropositions = new ArrayList<String>();
		for (int i = 0; i < numberOfPropositions / 2; i++) {
			if (this.type.equals(TypeOfGame.ENGLISH)) {
				leftPropositions.add(this.wordsPropositions.get(indexOfTheWord).get(i).getFrenchWords()
						.get(this.propositionsIndex.get(indexOfTheWord).get(i)));
			} else if (this.type.equals(TypeOfGame.FRENCH)) {
				leftPropositions.add(this.wordsPropositions.get(indexOfTheWord).get(i).getEnglishWords()
						.get(this.propositionsIndex.get(indexOfTheWord).get(i)));
			}
		}
		return leftPropositions;
	}

	public ArrayList<String> getRightPropositions(int indexOfTheWord) {
		ArrayList<String> rightPropositions = new ArrayList<String>();
		for (int i = (numberOfPropositions / 2); i < numberOfPropositions; i++) {
			if (this.type.equals(TypeOfGame.ENGLISH)) {
				rightPropositions.add(this.wordsPropositions.get(indexOfTheWord).get(i).getFrenchWords()
						.get(this.propositionsIndex.get(indexOfTheWord).get(i)));
			} else if (this.type.equals(TypeOfGame.FRENCH)) {
				rightPropositions.add(this.wordsPropositions.get(indexOfTheWord).get(i).getEnglishWords()
						.get(this.propositionsIndex.get(indexOfTheWord).get(i)));
			}
		}
		return rightPropositions;
	}

	public ArrayList<String> getAllTheAskedWords() {
		return this.allAskedWords;
	}

	public TopicVocabulary getSelectedTopic() {
		return this.selectedTopic;
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

	public void setEndVocabularyGame(Boolean b) {
		this.VocabGameEnd = b;
	}

	public ArrayList<Word> getWordsToPlay() {
		return this.wordsToPlay;
	}

}
