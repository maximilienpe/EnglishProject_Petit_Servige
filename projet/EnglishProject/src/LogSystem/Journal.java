package LogSystem;
import java.util.ArrayList;

public class Journal {

	private ArrayList<Log> journal;
	
	public Journal() {
		this.journal = new ArrayList<Log>();
	}
	
	public void addLog(Log log) {
		this.journal.add(log);
	}
	
	public void rmLog(Log log) {
		this.journal.remove(log);
	}
	
	public void displayLogs() {
		for (Log log : journal) {
			log.displayLog();
		}
	}
}
