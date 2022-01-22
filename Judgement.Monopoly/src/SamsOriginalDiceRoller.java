
public class SamsOriginalDiceRoller
	{

static boolean doubles;
		
		public static int rollDice(int i, int j)
		    {
	    	int dieOne = 0;
	    	int dieTwo = 0;
	    	doubles = false;
			 int sumOfDice = 0;
			 for(int k = 0 ; k < i ; k++)
			    {
			      //roll the dice
				  int diceRollNumber = (int) (Math.random()*j)+1;
				  sumOfDice = diceRollNumber+sumOfDice;
				  System.out.println("You rolled a "+diceRollNumber+".");
			      
			      if(k == 0)
			    	  {
			    		  dieOne = diceRollNumber;
			    	  }
			      else if(k == 1)
			    	  {
			    		  dieTwo = diceRollNumber;
			    	  }
			    }
			 if(dieOne == dieTwo)
				 {
					doubles = true;
					System.out.println("You rolled doubles!");
				 }
			// System.out.print("Your total is ");
			 return sumOfDice;
			
		    }
		
		
		
		
	}
