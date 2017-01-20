package GrammarSystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import LogSystem.LogLevel;
import LogSystem.Logger;

public class FileGrammarReader {

	//main variables
	private TopicGrammar topic;
	private Scanner sc;
	private File topicFile;
	
	//logger variables
	private Logger logger;
	private Boolean addLog;
	
	public FileGrammarReader() {
		this.topic = new TopicGrammar();
		this.topicFile = null;
		this.logger = null;
		this.addLog = false;
	}
	
	public FileGrammarReader(Logger logger, Boolean addLog) {
		this();
		this.logger = logger;
		this.addLog = addLog;
	}
	
	public TopicGrammar extractTopic(File topicFile) throws FileNotFoundException {
		this.topicFile = topicFile;
		this.topic = new TopicGrammar();
		String buffer = "";
		try {
			sc = new Scanner(topicFile);
			buffer = sc.nextLine();
			this.topic.setTopicTitle(buffer.trim());
			while (sc.hasNextLine()) {
				buffer = sc.nextLine();
				if (!(buffer.trim().equals(""))) {
					String question = buffer.trim();
					ArrayList<String> propositions = new ArrayList<String>();
					int answer = -1;
					if (sc.hasNextLine()) {
						buffer = sc.nextLine();
						String props[];
						props = buffer.split(";");
						for (String p : props) {
							propositions.add(p);
						}
						if (sc.hasNextLine()) {
							answer = sc.nextInt();
						}
						else {
							this.logger.addLog("FILEGRAMMARREADER File not correctly constructed.", LogLevel.ERROR);
						}
					}
					else {
						this.logger.addLog("FILEGRAMMARREADER File not correctly constructed.", LogLevel.ERROR);
					}
					if (answer != -1) {
						GrammarQuestion gq = new GrammarQuestion(question,propositions,answer);
						this.topic.addQuestion(gq);
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			if (logger != null && this.addLog) {
				this.logger.addLog("FILEGRAMMARREADER File not found.", LogLevel.ERROR);
			}
		}
		
		if (logger != null && this.addLog) {
			this.logger.addLog("FILEGRAMMARREADER Topic extracted successfully.", LogLevel.INFO);
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
