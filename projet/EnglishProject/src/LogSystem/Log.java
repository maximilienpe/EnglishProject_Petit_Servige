package LogSystem;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private String content;
	private LogLevel level;
	private Date logDate;
	private SimpleDateFormat format;
	
	public Log(String content, LogLevel level) {
		this.content = content;
		this.level = level;
		this.logDate = new Date();
		this.format = new SimpleDateFormat("dd/MM/yyyy 'at' hh'h' mm'min' ss's'");
	}
	
	public void displayLog() {
		System.out.println(this.format.format(this.logDate) + " : " 
				+ this.level + " " + this.content);
	}
	
	
	
	/*
	 * Test of Log class
	 */
	/*
	public static void main(String args[]) {
		Log log = new Log("LOG test of log", LogLevel.INFO);
		log.displayLog();
	}
	*/
}
