package view;

import model.IView;
import model.JottoModel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ProgressView extends JPanel{

	private JottoModel model;	
	
	// current jtable row size
	private int currentSize = 0;
	
	public ProgressView(JottoModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();
		
		// There's no need to do this for such a simple view, but for a complex
		// view you might have several ViewUpdate objects registered with
		// the model.
		
		this.model.addView(new IView() {
			public void updateView() {
				// add data to jtable
				String str = model.getwordTyped();
				int c1 = model.getExact();
				int c2 = model.getPartial();
				int c3 = model.getPercentMatched();
				if(model.wordTried.size() > currentSize){
					Object [] obj = {str, c1, c2, c3};
					model.tableAddRow(obj);
			        currentSize++;
				}
			}
		});	
		
	}
	
	private void layoutView() {
		this.setLayout(new BorderLayout());	
		this.add(model.getJScrollPane());
	}

	private void registerControllers() {
	}
}
