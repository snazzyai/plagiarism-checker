
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/*
 * DotPlot
 * @author Abdulsalam Ibrahim
 */

//T7 works independently
public class DotPlot extends GraphicsProgram implements Constants {
	
	private final double BLOCK_SIZE = 108;
	private int block_pos_change_x = 0;
	private int block_pos_change_y = 0;
	GRect mainBox;
	GRect pixel;

	private int y = 0;
	private int x = 0;
	
	
	public void run() {
		drawDotPlot();
	}
	
	private void createPoints(String s1, String s2) {
		s1 = removeWhiteSpaces(s1);
		s2 = removeWhiteSpaces(s2);
		//Code snippet from Variationen zum Thema: Algorithmen By Prof. Ralph Lano
		for (int i = 0; i < s1.length()/2; i += 5 ) {
			for (int j = 0; j < s2.length()/2; j += 5) {
				if ( s1.charAt(i) == s2.charAt(j) ) {
					//create dots inside the box
				    pixel = new GRect(0.2, 0.2);
					pixel.setFillColor(Color.BLACK);
					setBackground(Color.lightGray);
					add(pixel,	(block_pos_change_x > 0 ? block_pos_change_x + i*0.3:i*0.3), y > 0 ? block_pos_change_y + j*0.3: (j*0.3));
				}
			} 
		} 	
	}
	
	
	private void showSimilarity(String s1, String s2, int iPos, int jPos) {
		//create a box
		mainBox = new GRect(BLOCK_SIZE, BLOCK_SIZE);
		mainBox.setFillColor(Color.BLACK);
		if(jPos < 8 ){
			add(mainBox, x * BLOCK_SIZE, y);
			x++;
			createPoints(s1,s2);
			block_pos_change_x += BLOCK_SIZE;
		}
		else{
			add(mainBox, x * BLOCK_SIZE,y);
			createPoints(s1, s2);
		
			y += BLOCK_SIZE;
			x = 0;
			block_pos_change_y = y;
			block_pos_change_x = 0;
		}
	}
	
	public void drawDotPlot(){
		try{ 
			
			 File f = new File(FILEDIRECTORY); 
		     File[] files = f.listFiles();
		     StringBuilder sbFileOne = new StringBuilder();
		     StringBuilder sbFileTwo = new StringBuilder();   
		     for(int i = 0; i < files.length; i++){
		    	 for(int j = 0; j < files.length ; j++){
		    		 
		    		    BufferedReader br = new BufferedReader(new FileReader(FILEDIRECTORY + files[i].getName()));
			    	    BufferedReader br2 = new BufferedReader(new FileReader(FILEDIRECTORY + files[j].getName()));
		    			String lineOne;
		    			String lineTwo;
		    			while(true){
		    				lineOne = br.readLine();
		    				
		    				if(lineOne != null){
		    					sbFileOne.append(lineOne);
		    				}
		    				else{
		    					break;
		    				}
		    				
		    			}
		    			while(true){
		    				lineTwo = br2.readLine();
		    				
		    				if(lineTwo != null){
		    					sbFileTwo.append(lineTwo);
		    				}
		    				else{
		    					break;
		    				}
		    			}
		    			
		    			br.close();
		    			br2.close();
		    			showSimilarity(sbFileOne.toString(), sbFileTwo.toString(),i , j);
		    			sbFileOne.setLength(0);
		    			sbFileTwo.setLength(0);
		    	 }
		     }
		}catch(Exception e){
			System.out.println(e);
		}
	}


	//Code snippet from Exam_Studienarbeit, 
	private String removeWhiteSpaces(String s){
			s = s.replaceAll("[^a-zA-Z0-9]", "");
			return s;
	}
	
}
