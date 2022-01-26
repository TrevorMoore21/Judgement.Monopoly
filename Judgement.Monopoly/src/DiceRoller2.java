
public class DiceRoller2
	{


static boolean doubles = false;
		
		public static int rollDice(int i, int j)
		    {
	    	int dieOne = 0;
	    	int dieTwo = 0;
			int sumOfDice = 0;
			 
			 for(int k = 0 ; k < i ; k++)
			    {
			      //roll the dice
				  int diceRollNumber = (int) (Math.random()*j)+1;
				  sumOfDice = diceRollNumber+sumOfDice;
			      
			      if(k == 0)
			    	  {
			    		  dieOne = diceRollNumber;
			    	  }
			      else if(k == 1)
			    	  {
			    		  dieTwo = diceRollNumber;
			    	  }
			    }
			 
			 System.out.println("You rolled a " + dieOne + " & " + dieTwo + ".");
			 
			 if(dieOne == dieTwo)
				 {
					doubles = true;
					System.out.println("Well look at that, you rolled doubles!");
				 }
			 else
				 {
					 doubles = false;
				 }
			 return sumOfDice;
			
		    }
		
	}
