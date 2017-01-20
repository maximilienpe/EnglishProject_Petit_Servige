package GrammarSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import LogSystem.LogLevel;
import LogSystem.Logger;
import VocabularySystem.FileTopicExplorer;
import VocabularySystem.FileTopicReader;
import VocabularySystem.TopicVocabulary;

public class GrammarFactory {

	//main variables
		private FileGrammarExplorer topicExplorer;
		private ArrayList<File> topicFiles;
		private FileGrammarReader topicReader;
		private ArrayList<TopicGrammar> topics;
		
		//logger variables
		private Logger logger;
		private Boolean addLog;
		
		public GrammarFactory() {
			this.topicExplorer = new FileGrammarExplorer();
			this.topicFiles = null;
			this.topicReader = new FileGrammarReader();
			this.topics = null;
			this.logger = null;
			this.addLog = false;
		}
		
		public GrammarFactory(Logger logger, Boolean addLog) {
			this();
			this.logger = logger;
			this.addLog = addLog;
			this.setLoggerSubClasses(logger);
			this.setAddLogSubClasses(addLog);

		}
		
		public ArrayList<TopicGrammar> makeAllTopic() {
			this.topics = new ArrayList<TopicGrammar>();
			this.topicFiles = this.topicExplorer.searchAllTopicFile();
			for (File f : this.topicFiles) {
				try {
					this.topics.add(this.topicReader.extractTopic(f));
				} catch (FileNotFoundException e) {
					if (logger != null && this.addLog) {
						this.logger.addLog("GRAMMARFACTORY File not found.", LogLevel.ERROR);
					}
				}
			}
			if (logger != null && this.addLog) {
				this.logger.addLog("GRAMMARFACTORY All grammar topics successfully created.", LogLevel.INFO);
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
	
}
