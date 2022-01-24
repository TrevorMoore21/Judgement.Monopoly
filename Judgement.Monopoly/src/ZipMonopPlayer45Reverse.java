import java.util.ArrayList;
import java.util.Scanner;


public class ZipMonopPlayer45Reverse
	{

		
//Reverse Menu
		public static void reverseMenu()
		{
			ZooMonopPlayer45.playerLocation ++;
			
//			else
//				{
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
			//	}
		}
//Move in Reverse
		public static void moveReverse()
		{
			
			//int playerRoll = 20;
			int playerRoll = DiceRoller2.rollDice(2,6);
			int negativeRoll = playerRoll * ZooMonopPlayer45.reverseDat;
			
			
			
		//regulating playerLocation
			if((ZooMonopPlayer45.playerLocation + negativeRoll) >= 0)
				{
					ZooMonopPlayer45.playerLocation += negativeRoll;
					System.out.println("You can now move " + playerRoll + " places backwards");
				}
			else
				{
					ZooMonopPlayer45.playerLocation = (ZooMonopPlayer45.playerLocation + negativeRoll) + 40;
					ZooMonopPlayer45.playerMoney += 200;
					System.out.println("You can now move " + playerRoll + " places backwards");
					System.out.println("You passed GO and collected $200");
				}
			
		//nuance if land on Jail but just visiting
			if(MonopDriver2.board[ZooMonopPlayer45.playerLocation].getName().equals("Jail"))
				{
					System.out.println("You landed on Jail, but you are just visiting.");
					reverseMenu();
				}
			else if (MonopDriver2.board[ZooMonopPlayer45.playerLocation].getName().equals("Free_Parking"))
				{
					System.out.println("Now that you have landed back on Free Parking,"
							+ " you are free to move clockwise");
					System.out.println("You also won $" + ZooMonopPlayer45.freeParkingMoney + "!");
					ZooMonopPlayer45.playerMoney += ZooMonopPlayer45.freeParkingMoney;
					ZooMonopPlayer45.freeParkingMoney = 0;
					ZooMonopPlayer45.reverseFreeParking = false;
					
				}
			else
				{
					System.out.println("You landed on " + MonopDriver2.board[ZooMonopPlayer45.playerLocation].getName());
				}
			
			ZooMonopPlayer45.landOnSquare();
			
		//doubles rule (3 doubles == Jail)
						if(DiceRoller2.doubles == true)
							{
								ZooMonopPlayer45.timesRolledDoubles++;
								
								if(ZooMonopPlayer45.timesRolledDoubles == 3)
									{
										ZooMonopPlayer45.goToJail();
									}
								else
									{
										System.out.println("You rolled doubles, so you get to roll again!");
										moveReverse();
									}
							}
						else
							{
								ZooMonopPlayer45.timesRolledDoubles = 0;
							}
			
			//moveReverse();
			
			
		}
		
	}
