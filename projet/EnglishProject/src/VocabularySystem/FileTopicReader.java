package VocabularySystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import LogSystem.LogLevel;
import LogSystem.Logger;

public class FileTopicReader implements TopicReader {

	//main variables
	private TopicVocabulary topic;
	private Scanner sc;
	private File topicFile;
	
	//logger variables
	private Logger logger;
	private Boolean addLog;
	
	public FileTopicReader() {
		this.topic = new TopicVocabulary();
		this.topicFile = null;
		this.logger = null;
		this.addLog = false;
	}
	
	public FileTopicReader(Logger logger, Boolean addLog) {
		this();
		this.logger = logger;
		this.addLog = addLog;
	}
	
	@Override
	public TopicVocabulary extractTopic(File topicFile) throws FileNotFoundException {
		this.topicFile = topicFile;
		this.topic = new TopicVocabulary();
		String buffer = "";
		try {
			sc = new Scanner(topicFile);
			buffer = sc.nextLine();
			this.topic.setTitleTopic(buffer.trim());
			while (sc.hasNextLine()) {
				buffer = sc.nextLine();
				if (!(buffer.trim().equals(""))) {
					String words[];
					words = buffer.split(",");
					/*
					//System.out.println(words[0] + " : " + words[1]);
					this.topic.addAWord(words[0], words[1]);
					*/
					ArrayList<String> englishExpressions = new ArrayList<String>();
					ArrayList<String> frenchExpressions = new ArrayList<String>();
					
					String englishWords[];
					String frenchWords[];
					
					englishWords = words[0].split(";");
					frenchWords = words[1].split(";");
					
					for (String Eexp : englishWords) {
						String EexpUTF8 = new String(Eexp.getBytes(), Charset.forName("UTF-8"));
						englishExpressions.add(EexpUTF8.trim());
					}
					for (String Fexp : frenchWords) {
						String FexpUTF8 = new String(Fexp.getBytes(), Charset.forName("UTF-8"));
						frenchExpressions.add(FexpUTF8.trim());
					}
					Word newWord = new Word(englishExpressions,frenchExpressions);
					this.topic.addAWord(newWord);
				}
			}
		}
		catch (FileNotFoundException e) {
			if (logger != null && this.addLog) {
				this.logger.addLog("FILETOPICREADER File not found.", LogLevel.ERROR);
			}
		}
		
		if (logger != null && this.addLog) {
			this.logger.addLog("FILETOPICREADER Topic extracted successfully.", LogLevel.INFO);
		}
		return this.topic;
	}
		
	public void setAddLog(Boolean addLog) {
		this.addLog = addLog;
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	/*
	 * Test of FileTopicReader
	 */
	
	/*public static void main(String args[]) throws FileNotFoundException {
		FileTopicReader testReader = new FileTopicReader();
		File testFile = new File("Vocabulary/testTopic.csv");
		TopicVocabulary testTopic = testReader.extractTopic(testFile);
		System.out.println("Le titre du topic est : " + testTopic.getTitleTopic());
	}
	*/
}
