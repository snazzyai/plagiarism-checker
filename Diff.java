import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import acm.program.Program;
/*
 * Diff
 * @author Abdulsalam Ibrahim
 */

//T6
//Can be opened from Plag
public class Diff extends Program implements Constants{
	
	private JTextField tfOne;
	private JTextField tfTwo;
	private JTextArea taSideOne;
	private JTextArea taSideTwo;
	private JFrame secondWindow;
	
	public void setupDiff() {
		secondWindow = new JFrame("Diff");		
		secondWindow.setLayout(new BorderLayout());
		secondWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	
		
	
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.setPreferredSize(new Dimension(400,400));
	
		JPanel first = new JPanel();
		first.setLayout(new BorderLayout());
		taSideOne = new JTextArea(350,350);
		taSideOne.setText("Side one");
		first.add(taSideOne,CENTER);
		JScrollPane scroll = new JScrollPane(taSideOne, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
		first.add(scroll);
		
		
		JPanel second = new JPanel();
		second.setLayout(new BorderLayout());
		taSideTwo = new JTextArea(350,350);	
		taSideTwo.setText("Side two");
		second.add(taSideTwo,CENTER);
		JScrollPane scroll2 = new JScrollPane(taSideTwo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
		second.add(scroll2);
		
		
		
		JPanel panel2 = new JPanel();
		
		JButton btnOpenFirst = new JButton("Open First File");
		btnOpenFirst.addActionListener(this);
		btnOpenFirst.setActionCommand("Open first");
		
		tfOne = new JTextField("Label One");
		panel2.add(tfOne, SOUTH);
		panel2.add(btnOpenFirst, SOUTH);
		
		
		JButton btnOpenSecond = new JButton("Open Second File");
		btnOpenSecond.addActionListener(this);
		btnOpenSecond.setActionCommand("Open second");
		
		tfTwo = new JTextField("Label Two");
		
		panel2.add(tfTwo, SOUTH);
		panel2.add(btnOpenSecond, SOUTH);
		
		panel.add(first);
		panel.add(second);
		
		
		secondWindow.add(panel, CENTER);
		secondWindow.add(panel2, SOUTH);


		secondWindow.setSize(SCREENWIDTH, SCREENHEIGHT);
		secondWindow.setVisible(true);
	}
	
	
	
	//helper selector function
		private void openFiles(JTextArea textArea, JTextField tf) {

			FileDialog fd = new FileDialog(secondWindow, "Open", FileDialog.LOAD );
			fd.setDirectory(DIALOGFILEDIRECTORY);
			fd.setVisible(true);
		
			String selectedFile = null;
			
			
			if(fd.getFile() !=  null){
			
				selectedFile = fd.getFile();
				try {	
					BufferedReader br = new BufferedReader(new FileReader(FILEDIRECTORY + selectedFile));
					tf.setText(selectedFile);
					println(br);
					textArea.setText("");
					String line;
					while(true){
						line = br.readLine();
						if(line != null){
							textArea.append(line + "\n");
						}
						else{
							break;
						}
					}
					br.close();
					
				}
				catch(Exception e){
					println("Unable to read file");
				}
			}
			
		 }
		
	
	private void openFirstFile() {

		openFiles(taSideOne, tfOne);
	
	}
	private void openSecondFile() {

		openFiles(taSideTwo, tfTwo);
		
	}
	public void run(){
		setupDiff();
	}
	
	public void actionPerformed(ActionEvent e){
		String actionString = e.getActionCommand();
		
		switch(actionString){
		case "Open first":
			openFirstFile();
			break;
		case "Open second":
			openSecondFile();
			break;
		}	
	}


}
