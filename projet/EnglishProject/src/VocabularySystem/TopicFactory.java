package VocabularySystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import LogSystem.LogLevel;
import LogSystem.Logger;

public class TopicFactory {

	//main variables
	private FileTopicExplorer topicExplorer;
	private ArrayList<File> topicFiles;
	private FileTopicReader topicReader;
	private ArrayList<TopicVocabulary> topics;
	
	//logger variables
	private Logger logger;
	private Boolean addLog;
	
	public TopicFactory() {
		this.topicExplorer = new FileTopicExplorer();
		this.topicFiles = null;
		this.topicReader = new FileTopicReader();
		this.topics = null;
		this.logger = null;
		this.addLog = false;
	}
	
	public TopicFactory(Logger logger, Boolean addLog) {
		this();
		this.logger = logger;
		this.addLog = addLog;
		this.setLoggerSubClasses(logger);
		this.setAddLogSubClasses(addLog);

	}
	
	public ArrayList<TopicVocabulary> makeAllTopic() {
		this.topics = new ArrayList<TopicVocabulary>();
		this.topicFiles = this.topicExplorer.searchAllTopicFile();
		for (File f : this.topicFiles) {
			try {
				this.topics.add(this.topicReader.extractTopic(f));
			} catch (FileNotFoundException e) {
				if (logger != null && this.addLog) {
					this.logger.addLog("TOPICFACTORY File not found.", LogLevel.ERROR);
				}
			}
		}
		if (logger != null && this.addLog) {
			this.logger.addLog("TOPICFACTORY All topics successfully created.", LogLevel.INFO);
		}
		return this.topics;
	}
	
	public void setAddLogSubClasses(Boolean addLog) {
		this.topicExplorer.setAddLog(addLog);
		this.topicReader.setAddLog(addLog);
	}
	
	public void setLoggerSubClasses(Logger logger) {
		this.topicExplorer.setLogger(logger);
		this.topicReader.setLogger(logger);
	}
	
	public void setAddLog(Boolean addLog) {
		this.addLog = addLog;
	}
	
	
	/*
	 * Test of TopicFactory
	 */
	/*public static void main(String args[]) {
		TopicFactory testFactory = new TopicFactory();
		ArrayList<TopicVocabulary> testTopics = testFactory.makeAllTopic();
		for (TopicVocabulary tv : testTopics) {
			System.out.println("Title of the topic : " + tv.getTitleTopic());
			tv.displayTopic();
		}
	}
	*/
	
}
