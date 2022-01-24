import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MonopDriver2
	{

		static BoardSpace2[] board = new BoardSpace2[40];
		
		public static void main(String[] args)  throws FileNotFoundException
			{
				
				Scanner file = new Scanner(new File("AmericanMonopoly.txt" ));
				int numberOfLines = file.nextInt();
			     
			      for( int i = 0; i < numberOfLines; i++ )
			          {
			        	  
			        	  String type = file.next();
			        	  
			        	  
			        	  if(type.equals("Property"))
			        		  {
			        			 
			        					  String n = file.next();
			        					  String o = file.next();
			        					  int c = file.nextInt();
			        					  int l = file.nextInt();
			        					  boolean d = file.nextBoolean();
			        					  int br = file.nextInt();
			        					  int hh = file.nextInt();
			        					  int r1 = file.nextInt();
			        					  int r2 = file.nextInt();
			        					  int r3 = file.nextInt();
			        					  int r4 = file.nextInt();
			        					  int hr = file.nextInt();
			        					  int hown = file.nextInt();
			        					  int hotn = file.nextInt();
			        					  String co = file.next();
			        					  
			        			  board[i] = new Properties2("Property", n, o, c, l, d, br, hh, r1, r2, r3, r4, hr, hown, hotn, co);
			        			  
			        					
			        		  }
			        	  
			      
			        	  else if(type.equals("Railroad"))
			        		  {
			        			  
			        					String n = file.next();
			        			  		String o = file.next();
			        			  		int c = file.nextInt();
			        			  		int l = file.nextInt();
			        					int r1 = file.nextInt();
			        					int r2 = file.nextInt(); 
			        					int r3 = file.nextInt(); 
			        					int r4 = file.nextInt();
			        					int rro = file.nextInt();
			        					board[i] = new Railroads2("Railroads", n, o, c, l, r1,r2, r3, r4, rro);
			        		  }
			        	  
			        	  else if(type.equals("Utility"))
			        		  {
			        			 
			        			    String n = file.next();
		        			  		String o = file.next();
		        			  		int c = file.nextInt();
		        			  		int l = file.nextInt();
		        			  		boolean ou = file.nextBoolean();
		        			  		boolean tu = file.nextBoolean();
//			        				int dr1 = DiceRoller2.rollDice(2,6)*4;
//			        				int dr2 = DiceRoller2.rollDice(2,6)*10;
			        					  
			        			
			        			    board[i] = new Utilities2("Utility", n, o, c, l, ou, tu);
			        					
			        		  }
			        	  else if(type.equals("Other")) 
			        		  {
			        			    String n = file.next();
		        			  		String o = file.next();
		        			  		int c = file.nextInt();
		        			  		int l = file.nextInt();
			        					  
		        			  		board[i] = new BoardSpace2("Other", n, o, c, l);
			        		  }
			          }
			      
//			      MonopPlayer2.greetPlayer();
//			      while(MonopPlayer2.stillPlaying == true)
//			    	  {
//			    		  if(MonopPlayer2.inJail == false)
//			    			  {
//			    				  MonopPlayer2.turnMenu();
//			    			  }
//			    		  else
//			    			  {
//			    				  MonopPlayer2.inJailTurn();
//			    			  }
//			    	  }

			      
			      //THIS IS THE REAL ONE
			      
			      
			      ZooMonopPlayer45.greetPlayer();
			      while (ZooMonopPlayer45.stillPlaying == true)
			    	  {
			    		  if (ZooMonopPlayer45.inJail == false)
			    			  {
			    				  ZooMonopPlayer45.turnMenu();
			    			  }
			    		  else
			    			  {
			    				  ZooMonopPlayer45.inJailTurn();
			    			  }
			    		  if (ZooMonopPlayer45.reverseFreeParking == false)
			    			  {
			    				  ZooMonopPlayer45.turnMenu();
			    			  }
			    		  else
			    			  {
			    				  ZipMonopPlayer45Reverse.reverseMenu();
			    			  }
			    	  }
			      
			      
//			      ZooMonopPlayer50.greetPlayer();
//			      while(ZooMonopPlayer50.stillPlaying == true)
//			    	  {
//			    		  if(ZooMonopPlayer50.inJail == false)
//			    			  {
//			    				  ZooMonopPlayer50.turnMenu();
//			    			  }
//			    		  else
//			    			  {
//			    				  ZooMonopPlayer50.inJailTurn();
//			    			  }
//			    	  }
//			      
			}

	}
