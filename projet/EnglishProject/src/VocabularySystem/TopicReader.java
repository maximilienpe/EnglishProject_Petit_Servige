package VocabularySystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public interface TopicReader {
	
	public TopicVocabulary extractTopic(File topicFile) throws FileNotFoundException;

}
