import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class RunBasicMonopoly1P
	{

		public static void runBasicMonop1P()
		{
		      
//ask Player Name
		      ZooMonopPlayer45.askPlayer1Name();
//Runs Player Class
		      while (ZooMonopPlayer45.stillPlaying == true)
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
		    	  }
			
		}
		
	}
