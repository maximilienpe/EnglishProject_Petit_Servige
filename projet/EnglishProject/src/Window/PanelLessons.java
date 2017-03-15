package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import MainSystem.Modele;
import VocabularySystem.TopicVocabulary;
import VocabularySystem.Word;


public class PanelLessons extends JPanel {

	//main variables
	private JPanel centerPanel;
	private JPanel northPanel;
	private JLabel titleLabel;
	private JScrollPane scrollContent;
	private JTextArea contentTextArea;
	
	//buttons
	private JPanel buttonPanel;
	private GridLayout gridlayout;
	private JButton left;
	private JButton right;
	private ListenerLeftButtonLesson listenerLeft;
	private ListenerRightButtonLesson listenerRight;
	
	//content variables
	private Modele modele;
	private ArrayList<TopicVocabulary> topics;
	private int currentTopicIndex;
	private String contentVocabulary;
	
	public PanelLessons(Modele m) {
		
		//content variables initialisation
		this.modele = m;
		this.topics = this.modele.getTopics();
		this.currentTopicIndex = 0;
		
		//north panel initialisation
		this.northPanel = new JPanel();
		this.northPanel.setLayout(new GridLayout(2,1));
		Border line = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.titleLabel = new JLabel("          " 
					+ this.topics.get(this.currentTopicIndex).getTitleTopic()
				+ "          ");
        this.titleLabel.setBorder(line);
        this.titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.titleLabel.setVerticalAlignment(JLabel.CENTER);
		this.northPanel.add(this.titleLabel);
		this.titleLabel.setPreferredSize(new Dimension(300,50));
		
		this.buttonPanel = new JPanel();
		this.gridlayout = new GridLayout(1,2);
		this.gridlayout.setHgap(400);
		this.buttonPanel.setLayout(this.gridlayout);
		this.left = new JButton("	<<	");
		this.right = new JButton("	>>	");
		this.left.setPreferredSize(new Dimension(50,25));
		this.right.setPreferredSize(new Dimension(50,25));
		this.listenerLeft = new ListenerLeftButtonLesson(this);
		this.listenerRight = new ListenerRightButtonLesson(this);
		this.left.addActionListener(listenerLeft);
		this.right.addActionListener(listenerRight);
		this.buttonPanel.add(this.left);
		this.buttonPanel.add(this.right);
		this.northPanel.add(this.buttonPanel);
		
		
		
		//center panel initialisation
		this.centerPanel = new JPanel();
		
		//initialize content 
		this.contentVocabulary = getContent(this.topics.get(currentTopicIndex));
		this.contentTextArea = new JTextArea(this.contentVocabulary);
		this.contentTextArea.setEditable(false);
		this.scrollContent = new JScrollPane(this.contentTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollContent.setPreferredSize(new Dimension(400,400));
		this.scrollContent.repaint();
		this.scrollContent.revalidate();
		this.centerPanel.add(this.scrollContent, BorderLayout.CENTER);
		this.centerPanel.repaint();
		this.centerPanel.revalidate();
		
		//add to the PanelLessons
		this.setLayout(new FlowLayout());
		this.add(this.northPanel);
		this.add(this.centerPanel);
		this.repaint();
		this.revalidate();
	}
	
	private String concatenatedWord(Word w) {
		String concatenation = "";
		Boolean first = true;
		for (String ew : w.getEnglishWords()) {
			if (!first) {
				concatenation = concatenation + ", ";
			}
			else {
				first = false;
				concatenation = concatenation + "     ";
			}
			concatenation = concatenation + ew ;
		}
		concatenation = concatenation + " : " ;
		first = true;
		for (String ef : w.getFrenchWords()) {
			if (!first) {
				concatenation = concatenation + ", " ;
			}
			else {
				first = false;
			}
			concatenation = concatenation + ef;
		}
		concatenation = concatenation + "\n \n";
		return concatenation;
	}
	
	private String getContent(TopicVocabulary tv) {
		String content = "\n";
		for (Word w : tv.getTopicVocabulary()) {
			content = content + concatenatedWord(w);
		}
		return content;
	}
	
	public void setTopicIndex(int i) {
		this.currentTopicIndex = i;
	}
	
	public int getTopicIndex() {
		return this.currentTopicIndex;
	}
	
	public ArrayList<TopicVocabulary> getTopics() {
		return this.topics;
	}
	
	public void update() {
		this.titleLabel.setText("          " 
				+ this.topics.get(this.currentTopicIndex).getTitleTopic()
			+ "          ");
		this.contentVocabulary = getContent(this.topics.get(currentTopicIndex));
		this.contentTextArea.setText(contentVocabulary);
		
		this.contentTextArea.repaint();
		this.contentTextArea.revalidate();
		
		this.scrollContent.repaint();
		this.scrollContent.revalidate();
		
		this.centerPanel.repaint();
		this.centerPanel.revalidate();
		
		this.repaint();
		this.revalidate();
		
	}
}
