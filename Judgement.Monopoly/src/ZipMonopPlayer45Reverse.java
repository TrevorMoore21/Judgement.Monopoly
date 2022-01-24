import java.util.ArrayList;
import java.util.Scanner;

public class ZipMonopPlayer45Reverse
	{

		
//Reverse Menu
		public static void reverseMenu()
		{
			ZooMonopPlayer45.playerLocation ++;
			
			if (MonopDriver2.board[ZooMonopPlayer45.playerLocation].getName().equals("Free_Parking"))
				{
					System.out.println("Now that you have landed back on Free Parking,"
							+ " you are free to move clockwise");
					ZooMonopPlayer45.reverseFreeParking = false;
					ZooMonopPlayer45.turnMenu();
				}
			else
				{
					System.out.println("It's your turn " + ZooMonopPlayer45.playerName + "\nWould you like to..."
							+ "\n\n\t1) Roll the dice"
							+ "\n\t2) View your stats");
					ZooMonopPlayer45.menuInput = ZooMonopPlayer45.userInput.nextInt();
					
					if(ZooMonopPlayer45.menuInput == 1)
						{
							moveReverse();
						}
					else if(ZooMonopPlayer45.menuInput == 2)
						{
							ZooMonopPlayer45.displayPlayerStats();
						}
					else
						{
							System.out.println("That's not an option. Please try again.");
							reverseMenu();
						}
				}
		}
//Move in Reverse
		public static void moveReverse()
		{
			
			ZooMonopPlayer45.playerLocation = ZooMonopPlayer45.playerLocation * ZooMonopPlayer45.reverseDat;
			
			if (ZooMonopPlayer45.playerLocation <= 0)
			
		}
		
	}
