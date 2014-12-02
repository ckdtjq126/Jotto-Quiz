package model;

//Note!  Nothing from the view package is imported here.
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JottoModel extends Object
{
	public static int NUM_LETTERS = 5;
	public static final String[] LEVELS = {
      "Easy", "Medium", "Hard", "Any Difficulty"};

	// privates
	private ArrayList<IView> views = new ArrayList<IView>();
	
	private String wordTyped = "";	
	private int lives = 0;
	private int difficulty = 0;
	private int exact = 0;
	private int partial = 0;
	private int percentMatched = 0;
	private boolean hintAvail = true;
	private boolean plusLife = true;
	
	private WordList wl = new WordList("model/words.txt");
	private Word wordFromList; 

	
	private String[] columnData = {"Word", "Exact", "Partial", "% Matched"};
	private Object rowData[][] = {};
	
	// jtable
	private DefaultTableModel defaultTableModel = 
		new DefaultTableModel(rowData, columnData){
			// disable cell edit
			public boolean isCellEditable(int row, int column){
				return false;
			}};		
	private JTable table = new JTable(defaultTableModel);
	private JScrollPane jScrollPane = new JScrollPane(table);
	
	/** publics */
	
	// list containing typed words
	public ArrayList<String> wordTried = new ArrayList<String>();	
	// true if 'begin' button is clicked
	public boolean startClicked = false;
	// hint available?
	
	public String targetWord = "";

	
	// Override the default constructor, making it private.
	public JottoModel() {}
	
	public void setwordTyped(String str){
		this.wordTyped = str;
		this.updateAllViews();
	}

	public void selectWord(int diff){
		if(diff == 3){
			wordFromList = wl.randomWord();
		}
		else{
			wordFromList = wl.randomWord(diff);
		}
		
		targetWord = wordFromList.getWord();
		int diffNum = wordFromList.getDifficulty();
		
		this.difficulty = diffNum;
		
		if(diffNum == 0)
			lives = 20;
		else if(diffNum == 1)
			lives = 15;
		else{
			lives = 10;
		}
		//System.out.println("targetWord: "+targetWord);
		this.updateAllViews();
	}
	
	public void setHintAvailable(boolean bool){
		this.hintAvail = false;
		this.updateAllViews();
	}
	public void setPlusLife(boolean bool){
		this.plusLife = false;
		this.updateAllViews();
	}
	
	public void setLives(int i){
		this.lives = i;
		this.updateAllViews();
	}
	public void setDiff(int diff){
		this.difficulty = diff;
		this.updateAllViews();
	}
	public void setResult(String str){
		String tempPartial = "";
		String tempPartialTyped = "";
		exact = 0;
		partial = 0;
		percentMatched = 0;
        for(int i = 0; i < targetWord.length(); i++) {
            if(targetWord.charAt(i) == str.charAt(i)){
            	exact++;
            }
            else{
            	tempPartial += targetWord.charAt(i);
            	tempPartialTyped += str.charAt(i);
            }
        }
        for(int i = 0; i < tempPartialTyped.length(); i++){
        	for(int j = 0; j < tempPartial.length(); j++) {
        		if(tempPartial.charAt(i) == tempPartialTyped.charAt(j))
        			partial++;
        	}
        }
        this.percentMatched = exact*20 + partial*10;
		this.updateAllViews();
	}

	public void tableAddRow(Object[] obj){
		defaultTableModel.addRow(obj);
	}
	public boolean checkCorrect(String str){
		return targetWord.equals(str);
	}
	
	public void addTried(String wordTried){
		this.wordTried.add(wordTried);
		this.updateAllViews();
	}
	
	public boolean hintAvailable(){
		return this.hintAvail;
	}
	public boolean plusAvailable(){
		return this.plusLife;
	}
	public void lostLife(){
		this.lives--;
		this.updateAllViews();
	}

	/** Get the letter inside of the box */
	public String getwordTyped() {
		return this.wordTyped;
	}
	public JScrollPane getJScrollPane(){
		return jScrollPane;
	}
	/** returns how many lives left */
	public int getLives(){
		return this.lives;
	}
	/** returns difficulty selected */
	public int getDiff(){
		return this.difficulty;
	}
	/** returns target word */
	public String getTarget(){
		return this.targetWord;
	}
	/** returns number of partially corrected letters */
	public int getPartial(){
		return this.partial;
	}
	/** returns number of exactly matched letters */
	public int getExact(){
		return this.exact;
	}
	/** returns the percentage of word matched */
	public int getPercentMatched(){
		return this.percentMatched;
	}
	

    /** resets everything and start again with same difficulty */
    public void retry() {
        wordTried.clear();
        if (defaultTableModel.getRowCount() > 0) {
            for (int i = defaultTableModel.getRowCount() - 1; i > -1; i--) {
                defaultTableModel.removeRow(i);
            }
        }
    	defaultTableModel = new DefaultTableModel(rowData, columnData){
    				// disable cell edit
    				public boolean isCellEditable(int row, int column){
    					return false;
    				}};		
    	table = new JTable(defaultTableModel);
    	jScrollPane = new JScrollPane(table);
        this.selectWord(difficulty);
        
        this.updateAllViews();
    }
	/** Add a new view of this game. */
	public void addView(IView view) {
		this.views.add(view);
		view.updateView();
	}

	/** Remove a view from this game. */
	public void removeView(IView view) {
		this.views.remove(view);
	}

	/** Update all the views that are viewing this triangle. */
	private void updateAllViews() {
		for (IView view : this.views) {
			view.updateView();
		}
	}
}

