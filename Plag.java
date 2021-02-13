
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import acm.program.Program;

/*
 * Plag
 * @author Abdulsalam Ibrahim
 */

//Starter class for starting application
public class Plag extends Program implements Constants{
	private JTextArea ta;
	private JFrame window;
	private String[] pathNames;
	private StringBuilder sbFileOne = new StringBuilder();
    private StringBuilder sbFileTwo = new StringBuilder();
    private BufferedReader br;
    private BufferedReader br2;

	public void setup(){
		window = new JFrame("Exam");
		setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ta = new JTextArea(SCREENWIDTH,SCREENHEIGHT);
		
		
		JScrollPane scroll = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );		
		add(scroll, CENTER);
		
		JButton btnListCppFiles = new JButton("List Files");
		btnListCppFiles.addActionListener(this);
		btnListCppFiles.setActionCommand("List");
		add(btnListCppFiles, SOUTH);
		
		JButton lcsMatches = new JButton("Check Matches");
		 lcsMatches.addActionListener(this);
		 lcsMatches.setActionCommand("Matches");
		add( lcsMatches, SOUTH);
		
		JButton openSideFrames = new JButton("Open Diff");
		openSideFrames.addActionListener(this);
		openSideFrames.setActionCommand("Open sideframes");
		add(openSideFrames, SOUTH);

		
	}
	
	//T3
	//codesnippet for listing files from Stackabuse.com, link: https://stackabuse.com/java-list-files-in-a-directory/
	private void listFilesAction() {
		try{
			 File f = new File(FILEDIRECTORY);
		     pathNames = f.list();
		     File[] files = f.listFiles();
		     for(int i = 0; i < files.length; i++){
		    	 ta.append(i + ": "+ files[i].getName() + "\n");
		     }
		     
		     for (String pathName : pathNames) {
		         ta.append("--------" + pathName + "--------" + "\n");
		         try{
		        		BufferedReader br = new BufferedReader(new FileReader(FILEDIRECTORY + pathName));
		    			String line;
		    			while(true){
		    				line = br.readLine();

		    				if(line != null){
		    					ta.append(line + "\n");

		    				}
		    				else{
		    					break;
		    				}
		    			}
		    			br.close();
		    			ta.append("-------" + "End" + "-------" + "\n");
		        	 
		         }catch(Exception e){
		        	 System.out.println(e);
		         }
		     }
		}catch(Exception e){
					
		}
	}
	
	
	//T4 and T5, StringBuilder snippet from https://stackoverflow.com/questions/23996097/matching-two-files-in-java/23996423
	private void lcs() {
		
		try{ 
		     ta.append("Part T4 \n");
			 ta.append("     |    0    |    1    |    2    |    3     |    4    |    5    |    6     |   7      |    8    |" + "\n");
			 File f = new File(FILEDIRECTORY); 
		     File[] files = f.listFiles();
		     int result;
		     LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		     
		     
		     String row = " ";
		     
	
		     for(int i = 0; i < files.length; i++){
		    	 for(int j = 0; j < files.length; j++){
		    		 	reader(files[i], files[j]);
		    			result =  lcs.lcs(sbFileOne.toString(), sbFileTwo.toString());	
		    			sbFileOne.setLength(0);
		    			sbFileTwo.setLength(0);
		    			if(result != 100){
		    				row = row + "    "+ result + " | ";
		    			}
		    			else{
		    				row = row + "  "+ result + " | ";
		    			} 
		    	 }
		    	 ta.append(i + ":  " + row + "\n");
		    	 row = " ";
		     }
		     
		     ta.append("Part T5 \n");
		     ta.append("     |    0    |    1    |    2    |    3     |    4    |    5    |    6     |   7      |    8    |" + "\n");
		     for(int i = 0; i < files.length; i++){
		    	 for(int j = 0; j < files.length; j++){
		    		 	reader(files[i], files[j]);
		    			String preparedStringOne = lcs.prepareCode(sbFileOne.toString());
		    			String preparedStringTwo = lcs.prepareCode(sbFileTwo.toString());
		    			result =  lcs.lcs(preparedStringOne, preparedStringTwo);	
		    			sbFileOne.setLength(0);
		    			sbFileTwo.setLength(0);
		    			if(result != 100){
		    				row = row + "    "+ result + " | ";
		    			}
		    			else{
		    				row = row + "  "+ result + " | ";
		    			} 
		    	 }
		    	 ta.append(i + ":  " + row + "\n");
		    	 row = " ";
		     }
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}

	private void reader(File fileOne, File fileTwo) throws IOException{
	    br = new BufferedReader(new FileReader(FILEDIRECTORY + fileOne.getName()));
	    br2 = new BufferedReader(new FileReader(FILEDIRECTORY + fileTwo.getName()));
		String lineBrOne = null;
		String lineBrTwo;
		while(true){
			try {
				lineBrOne = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(lineBrOne != null){
				sbFileOne.append(lineBrOne);
			}
			else{
				break;
			}
			
		}
		while(true){
			lineBrTwo = br2.readLine();
			
			if(lineBrTwo != null){
				sbFileTwo.append(lineBrTwo);
			}
			else{
				break;
			}
		}
		
		br.close();
		br2.close();
	}

	private void openSideBySide(){
		Diff showSecond = new Diff();
		showSecond.setupDiff();
	}
	
	private void openDotPlot() {
		DotPlot dp = new DotPlot();
		dp.run();
	}	
	
	public void run() {
		// your code goes here...
		setup();	
	}
	
	public void actionPerformed(ActionEvent e){
		String actionString = e.getActionCommand();
		
		switch(actionString){
		case "List": 
			listFilesAction();
			break;
		case "Matches":
			lcs();
			break;
		case "Open sideframes":
			openSideBySide();
			break;
		}


	}
	
	}

	
	



	
