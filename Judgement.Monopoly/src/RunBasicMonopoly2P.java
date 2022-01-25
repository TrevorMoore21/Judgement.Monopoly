import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class RunBasicMonopoly2P
	{

		public static void runBasicMonop2P()
		{
		      
//ask Players' Names
		      ZooMonopPlayer45.askPlayer1Name();
		      ZooMonopPlayer46.askPlayer2Name();
//Runs both Player Classes	
		      while (ZooMonopPlayer45.stillPlaying == true && ZooMonopPlayer46.stillPlaying2 == true)
		    	  {
//Player 1
		    		  if (ZooMonopPlayer45.inJail == false)
		    			  {
		    				  if (ZooMonopPlayer45.reverseFreeParking == false)
				    			  {
				    				  ZooMonopPlayer45.turnMenu();
				    			  }
				    		  else
				    			  {
				    				  ZipMonopPlayer45Reverse.reverseMenu();
				    			  }
		    			  }
		    		  else
		    			  {
		    				  ZooMonopPlayer45.inJailTurn();
		    			  }
//Player 2			    	
		    		  if (ZooMonopPlayer46.inJail == false)
		    			  {
		    				  if (ZooMonopPlayer46.reverseFreeParking == false)
				    			  {
				    				  ZooMonopPlayer46.turnMenu();
				    			  }
				    		  else
				    			  {
				    				  ZipMonopPlayer46Reverse.reverseMenu();
				    			  }
		    			  }
		    		  else
		    			  {
		    				  ZooMonopPlayer46.inJailTurn();
		    			  }
		    		
		    	  }
			}
	}
