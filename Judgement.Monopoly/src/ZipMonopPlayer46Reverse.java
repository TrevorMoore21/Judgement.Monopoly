import java.util.ArrayList;
import java.util.Scanner;


public class ZipMonopPlayer46Reverse
	{

		
//Reverse Menu
		public static void reverseMenu()
		{
			ZooMonopPlayer46.playerLocation2 ++;
			
					System.out.println("It's your turn " + ZooMonopPlayer46.playerName2 + "\nWould you like to..."
							+ "\n\n\t1) Roll the dice"
							+ "\n\t2) View your stats");
					ZooMonopPlayer46.menuInput = ZooMonopPlayer46.userInput2.nextInt();
					
					if(ZooMonopPlayer46.menuInput == 1)
						{
							moveReverse();
						}
					else if(ZooMonopPlayer46.menuInput == 2)
						{
							ZooMonopPlayer46.displayPlayerStats();
						}
					else
						{
							System.out.println("That's not an option. Please try again.");
							reverseMenu();
						}
		}
//Move in Reverse
		public static void moveReverse()
		{
			
			//int playerRoll = 20;
			int playerRoll = DiceRoller2.rollDice(2,6);
			int negativeRoll = playerRoll * ZooMonopPlayer46.reverseDat;
			
			
			
		//regulating playerLocation
			if((ZooMonopPlayer46.playerLocation2 + negativeRoll) >= 0)
				{
					ZooMonopPlayer46.playerLocation2 += negativeRoll;
					System.out.println("You can now move " + playerRoll + " places backwards");
				}
			else
				{
					ZooMonopPlayer46.playerLocation2 = (ZooMonopPlayer46.playerLocation2 + negativeRoll) + 40;
					ZooMonopPlayer46.playerMoney2 += 200;
					System.out.println("You can now move " + playerRoll + " places backwards");
					System.out.println("You passed GO and collected $200");
				}
			
		//nuance if land on Jail but just visiting
			if(MonopDriver2.board[ZooMonopPlayer46.playerLocation2].getName().equals("Jail"))
				{
					System.out.println("You landed on Jail, but you are just visiting.");
					reverseMenu();
				}
			else if (MonopDriver2.board[ZooMonopPlayer46.playerLocation2].getName().equals("Free_Parking"))
				{
					System.out.println("Now that you have landed back on Free Parking,"
							+ " you are free to move clockwise");
					System.out.println("You also won $" + ZooMonopPlayer46.freeParkingMoney + "!");
					ZooMonopPlayer46.playerMoney2 += ZooMonopPlayer46.freeParkingMoney;
					ZooMonopPlayer46.freeParkingMoney = 0;
					ZooMonopPlayer46.reverseFreeParking = false;
					
				}
			else
				{
					System.out.println("You landed on " + MonopDriver2.board[ZooMonopPlayer46.playerLocation2].getName());
				}
			
			ZooMonopPlayer46.landOnSquare();
			
		//doubles rule (3 doubles == Jail)
						if(DiceRoller2.doubles == true)
							{
								ZooMonopPlayer46.timesRolledDoubles++;
								
								if(ZooMonopPlayer46.timesRolledDoubles == 3)
									{
										ZooMonopPlayer46.goToJail();
									}
								else
									{
										System.out.println("You rolled doubles, so you get to roll again!");
										moveReverse();
									}
							}
						else
							{
								ZooMonopPlayer46.timesRolledDoubles = 0;
							}	
		}
	}
