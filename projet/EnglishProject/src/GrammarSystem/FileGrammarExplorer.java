package GrammarSystem;

import java.io.File;
import java.util.ArrayList;

import LogSystem.LogLevel;
import LogSystem.Logger;


public class FileGrammarExplorer {
		
	//main variables
	ArrayList<File> topicFiles;
	String topicDirectory;
	
	//logger variables
	private Logger logger;
	private Boolean addLog;
	
	public FileGrammarExplorer() {
		this.topicDirectory = "Grammar";
		this.topicFiles = new ArrayList<File>();
		this.logger = null;
		this.addLog = false;
	}
	
	public FileGrammarExplorer(Logger logger, Boolean addLog) {
		this();
		this.logger = logger;
		this.addLog = addLog;
	}

	public ArrayList<File> searchAllTopicFile() {
		File fd = new File(this.topicDirectory);
		File[] topicFilesIntermediaire = null; 
		topicFilesIntermediaire = fd.listFiles();
		if (topicFilesIntermediaire != null) {
			for (int i=0 ; i < topicFilesIntermediaire.length ; i++) {
				if (topicFilesIntermediaire[i].isFile()) {
					this.topicFiles.add(topicFilesIntermediaire[i]);
				}
			}
		}
		else {
			if (logger != null && this.addLog) {
				this.logger.addLog("FILETOPICEXPLORER File not found.", LogLevel.ERROR);
			}
		}
		if (logger != null && this.addLog) {
			this.logger.addLog("FILETOPICEXPLORER All topics file found successfully.", LogLevel.INFO);
		}
		return this.topicFiles;
	}
	
	public void setAddLog(Boolean addLog) {
		this.addLog = addLog;
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	/*
	 * Test of FileTopicExplorer
	 */
	
	/*public static void main(String args[]) {
		FileTopicExplorer testFile = new FileTopicExplorer();
		ArrayList<File> topics = testFile.searchAllTopicFile();
		for (File f : topics) {
			System.out.println("Name of the file : " + f.getName());
		}
	}
	*/
}
