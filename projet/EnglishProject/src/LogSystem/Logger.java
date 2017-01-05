package LogSystem;

public class Logger {

	
	private Journal journal;
	private Boolean showLog;
	
	public Logger() {
		this.journal = new Journal();
		this.showLog = true;
	}
	
	public void showLogOn() {
		this.showLog = true;
	}

	public void showLogOff() {
		this.showLog = false;
	}
	
	public void addLog(String content, LogLevel level) {
		Log newLog = new Log(content,level);
		if (this.showLog) {
			newLog.displayLog();
		}
	}

	public void readLog() {
		this.journal.displayLogs();
	}

}
