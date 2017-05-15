package ms01288_COM1028_textprocessor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JMenuBar;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class Window {

	private JFrame frame;
	private JPanel panel;
	private JTextArea tArea, txtrQuotedText;
	private JButton showTable;
	private JLabel mostUsedWords, sentenceNumber, sentenceLength, wordCount;
	
	private JMenuBar menuBar;
	private JMenu mnFile, mnParseOptions, mnParse;
	private JMenuItem mntmNew, mntmLoadFile, mntmSave, mntmParse;
	private JCheckBoxMenuItem chckbxmntmMostUsedWords, chckbxmntmAverageSentenceLength
			, chckbxmntmNumberOfSentences, chckbxmntmWordCount, chckbxmntmQuotedText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window(100, 100, 800, 600);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window(int x, int y, int width, int height) {
		initialize();
		frame.setBounds(x, y, width, height);
	}
	public Rectangle getBounds(){
		return frame.getBounds();
	}
	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {

		FileHandler handler = new FileHandler("txt");
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{411, 59, 310, 0};
		gridBagLayout.rowHeights = new int[]{134, 60, 16, 53, 29, 215, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridheight = 6;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		
		tArea = new JTextArea(34, 32);
		tArea.setEditable(true);
		tArea.setLineWrap(true);
		tArea.setWrapStyleWord(true);
		tArea.setMargin( new Insets(10,10,10,10) );
		JScrollPane scroll = new JScrollPane(tArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		showTable = new JButton("Show Table");
		showTable.setVisible(false);
		showTable.setBorderPainted(false);
		showTable.setForeground(Color.blue);
		GridBagConstraints gbc_showTable = new GridBagConstraints();
		gbc_showTable.insets = new Insets(0, 0, 5, 0);
		gbc_showTable.gridx = 2;
		gbc_showTable.gridy = 0;
		frame.getContentPane().add(showTable, gbc_showTable);
		
		mostUsedWords = new JLabel("Most Used Words");
		GridBagConstraints gbc_mostUsedWords = new GridBagConstraints();
		gbc_mostUsedWords.fill = GridBagConstraints.BOTH;
		gbc_mostUsedWords.insets = new Insets(0, 0, 5, 0);
		gbc_mostUsedWords.gridx = 2;
		gbc_mostUsedWords.gridy = 0;
		frame.getContentPane().add(mostUsedWords, gbc_mostUsedWords);
		
		sentenceNumber = new JLabel("Number of Sentences: ");
		GridBagConstraints gbc_sentenceNumber = new GridBagConstraints();
		gbc_sentenceNumber.fill = GridBagConstraints.BOTH;
		gbc_sentenceNumber.insets = new Insets(0, 0, 5, 0);
		gbc_sentenceNumber.gridx = 2;
		gbc_sentenceNumber.gridy = 1;
		frame.getContentPane().add(sentenceNumber, gbc_sentenceNumber);
		
		sentenceLength = new JLabel("Average Sentence Length: ");
		GridBagConstraints gbc_sentenceLength = new GridBagConstraints();
		gbc_sentenceLength.anchor = GridBagConstraints.NORTH;
		gbc_sentenceLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_sentenceLength.insets = new Insets(0, 0, 5, 0);
		gbc_sentenceLength.gridx = 2;
		gbc_sentenceLength.gridy = 2;
		frame.getContentPane().add(sentenceLength, gbc_sentenceLength);
		
		wordCount = new JLabel("Word Count: ");
		GridBagConstraints gbc_wordCount = new GridBagConstraints();
		gbc_wordCount.fill = GridBagConstraints.BOTH;
		gbc_wordCount.insets = new Insets(0, 0, 5, 0);
		gbc_wordCount.gridx = 2;
		gbc_wordCount.gridy = 4;
		frame.getContentPane().add(wordCount, gbc_wordCount);
		
		txtrQuotedText = new JTextArea();
		txtrQuotedText.setLineWrap(true);
		txtrQuotedText.setBackground(SystemColor.window);
		txtrQuotedText.setEditable(false);
		txtrQuotedText.setText("Quoted Text: ");
		GridBagConstraints gbc_txtrQuotedText = new GridBagConstraints();
		gbc_txtrQuotedText.anchor = GridBagConstraints.WEST;
		gbc_txtrQuotedText.fill = GridBagConstraints.VERTICAL;
		gbc_txtrQuotedText.gridx = 2;
		gbc_txtrQuotedText.gridy = 5;
		frame.getContentPane().add(txtrQuotedText, gbc_txtrQuotedText);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmNew = new JMenuItem("New File");
		mnFile.add(mntmNew);
		mntmNew.addActionListener(new ActionListener() {
			/** 
			 * Method prompts the user to Save the document before
			 * clearing the content in the JTextArea
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				tArea.setText("");
			}
		});

		mntmLoadFile = new JMenuItem("Load");
		mnFile.add(mntmLoadFile);
		mntmLoadFile.addActionListener(new ActionListener() {
			/**
			 * Method opens a JFileChooser and user chooses file to open; calls
			 * loadFile() from FileHandler class and prints ArrayList in
			 * JTextArea
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				ArrayList<ArrayList<String>> file = null;

				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt", "rtf");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// user the FileHandler to check the file extension 
					// to ensure the correct file-type has been loaded
					if(!handler.checkFileValid(chooser.getSelectedFile())){
						JOptionPane.showMessageDialog(frame, "Must use correct file format");
					}else{
						file = handler.loadFile(chooser.getSelectedFile());
					}
				}
				// print the StringBuilder as a string in text area
				tArea.setText(printNestedArrayList(file));
			}
		});
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmSave.addActionListener(new ActionListener() {
			/**
			 * Method opens a JFileChooser for the user to find a directory to
			 * save the contents of the TextArea in Calls saveFile from
			 * FileHAndler
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle open button action.
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt", "rtf");
				//
				// Only show folders not files when looking for directory to
				// save file to
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						//
						// send the file path the user has taken as a string
						// argument to saveFile
						handler.saveFile(chooser.getSelectedFile().getAbsolutePath(), tArea.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		mnParseOptions = new JMenu("Parse Options");
		menuBar.add(mnParseOptions);

		chckbxmntmMostUsedWords = new JCheckBoxMenuItem("Most Used Words");
		mnParseOptions.add(chckbxmntmMostUsedWords);

		chckbxmntmAverageSentenceLength = new JCheckBoxMenuItem("Average Sentence Length");
		mnParseOptions.add(chckbxmntmAverageSentenceLength);

		chckbxmntmNumberOfSentences = new JCheckBoxMenuItem("Number of Sentences");
		mnParseOptions.add(chckbxmntmNumberOfSentences);


		chckbxmntmWordCount = new JCheckBoxMenuItem("Word Count");
		mnParseOptions.add(chckbxmntmWordCount);
		
		chckbxmntmQuotedText = new JCheckBoxMenuItem("Quoted Texts");
		mnParseOptions.add(chckbxmntmQuotedText);
		
		mnParse = new JMenu("Parse");
		menuBar.add(mnParse);
		
		mntmParse = new JMenuItem("Ok");
		mnParse.add(mntmParse);
		
		mntmParse.addActionListener(new ActionListener() {

			/**
			 * When Parse button is clicked, menu items that are selected in
			 * mnParseOptions will determine what functions are called by the
			 * Parser and thus what data is displayed on the screen
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
				wordCount.setText("Word Count: ");
				sentenceLength.setText("Average Sentence Length: ");
				sentenceNumber.setText("Number of Sentences: ");
				mostUsedWords.setText("Most Used Words: ");
				
				if(tArea.getText().isEmpty()){
					JOptionPane.showMessageDialog(frame, "Must have text inside text-area");
				}else{
					
					Parser parser = new Parser();
					ArrayList<ArrayList<String>> fileAsSentences = handler.readFileDirectInput(tArea.getText());
					ArrayList<String> sortedWords = handler.sortedWordsFromString(fileAsSentences);
					
					if (chckbxmntmMostUsedWords.isSelected()) {
						HashMap<String, Integer> mostCommonWords = parser.getMostUsedWords(sortedWords);
						showTable.setVisible(true);
						showTable.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								createTableFrame(fileAsSentences, mostCommonWords, sortedWords);
							}
						});
					}
					if (chckbxmntmAverageSentenceLength.isSelected()) {
						double avgLengthSentences = parser.getAvgLengthSentences(fileAsSentences);
						sentenceLength.setText(sentenceLength.getText() + avgLengthSentences);
					}
					if (chckbxmntmNumberOfSentences.isSelected()) {
						int numSentences = parser.getNumSentences(fileAsSentences);
						sentenceNumber.setText(sentenceNumber.getText() + numSentences);	
					}
					if(chckbxmntmWordCount.isSelected()){
						int numWords = parser.getWordCount(sortedWords);
						wordCount.setText(wordCount.getText() + numWords);
					}
					if(chckbxmntmQuotedText.isSelected()){
						ArrayList<ArrayList<String>> quotedText = 
								parser.getQuotedText(handler.readFileDirectInput(tArea.getText()));
						txtrQuotedText.setText(txtrQuotedText.getText() + printNestedArrayList(quotedText));
					}
				}
			}
		});	
	}
	/**
	 * @param ArrayList<ArrayList<String>> - quotes
	 * @return the file as a String
	 */
	private String printNestedArrayList(ArrayList<ArrayList<String>> sentences){
		if(sentences.isEmpty()) return "";
		Iterator<ArrayList<String>> outerIter = sentences.iterator();
		StringBuilder sb = new StringBuilder();
		while(outerIter.hasNext()){
			Iterator<String> innerIter = outerIter.next().iterator();
			while(innerIter.hasNext()){
				sb.append(" " + innerIter.next());
			}
			sb.append(".");
		}
		return sb.toString();
	}
	/**
	 * @param ArrayList<ArrayList<String>> - fileAsSentences
	 * @param HashMap<String, Integer> - mostCommonWords
	 * @param ArrayList<String> - sortedWords
	 */
	public static void createTableFrame(ArrayList<ArrayList<String>> fileAsSentences, 
										HashMap<String, Integer> mostCommonWords,
										ArrayList<String> sortedWords){
		
		String[] columnNames = {"Word", "Occurences"};
		Object[][] tableData = new Object[mostCommonWords.keySet().size()][mostCommonWords.keySet().size()];
		EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
            	JFrame tableFrame = new JFrame();
            	tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch (Exception e) {
                   e.printStackTrace();
                }
                tableFrame.setVisible(true);
                tableFrame.setBounds(100, 100, 200, 600);
                int dataIndex = 0;
                // populate two-dimensional array of objects
				for(String word: mostCommonWords.keySet()){
					tableData[dataIndex][0] = word; // key (String)
					tableData[dataIndex][1] = mostCommonWords.get(word); //value (Integer)
					dataIndex++;
				}
				JTable table = new JTable(tableData, columnNames);
                tableFrame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER );
            }
        });
	}
}