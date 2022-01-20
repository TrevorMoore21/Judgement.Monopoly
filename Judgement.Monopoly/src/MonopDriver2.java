import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MonopDriver2
	{

		static BoardSpace[] board = new BoardSpace[40];
		
		public static void main(String[] args)  throws FileNotFoundException
			{
				
				Scanner file = new Scanner(new File( "AmericanMonopoly.txt" ));
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
			        					  int hr = file.nextInt();
			        					  int hn = file.nextInt();
			        					  String co = file.next();
			        					  
			        			  board[i] = new Properties("Property", n, o, c, l, d, br, hh, r1, r2, r3, hr, hn, co);
			        			  
			        					
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
			        					board[i] = new Railroads("Railroads", n, o, c, l, r1,r2, r3, r4);
			        		  }
			        	  
			        	  else if(type.equals("Utilities"))
			        		  {
			        			 
			        			  String n = file.next();
		        			  		String o = file.next();
		        			  		int c = file.nextInt();
		        			  		int l = file.nextInt();
			        				int dr1 = DiceRoller.rollDice(2,6)*4;
			        				int dr2 = DiceRoller.rollDice(2,6)*10;
			        					  
			        			    board[i] = new Utilities("Utilities", n, o, c, l, dr1, dr2);
			        					  
			        					
			        		  }
			        	  else if(type.equals("Other")) 
			        		  {
			        			  String n = file.next();
		        			  		String o = file.next();
		        			  		int c = file.nextInt();
		        			  		int l = file.nextInt();
			        					  
		        			  		board[i] = new BoardSpace("Other", n, o, c, l);
			        		  }
			          }
			      
			      
			      for (BoardSpace b : board)
			    	  {
			    		  System.out.println(b);

			    	  }
			      
			      Player.greetPlayer();
			      while(Player.stillPlaying == true)
			    	  {
			    		  if(Player.inJail == false)
			    			  {
			    				  Player.turnMenu();
			    			  }
			    		  else
			    			  {
			    				  Player.inJailTurn();
			    			  }
			    	  }

			}

	}
