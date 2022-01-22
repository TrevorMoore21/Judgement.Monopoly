import java.util.ArrayList;
import java.util.Scanner;

import jdk.internal.misc.FileSystemOption;


public class ZooMonopPlayer45
	{

		static int playerLocation = 0;
		static boolean inJail = false;
		static int playerMoney = 1500;
		static boolean stillPlaying = true;
		static String playerName;
		static int menuInput;
		static int menuInput2;
//		static int numberOfPropertiesOwned;
//		static int numberOfUtilitiesOwned;
//		static int numberOfRailroadsOwned;
		static int freeParkingMoney;
		static int timesRolledDoubles = 0;
		static Scanner userInput = new Scanner(System.in);
		static ArrayList<BoardSpace2> inventory = new ArrayList<BoardSpace2>();
		
		
		
		public static void greetPlayer()
			{
				System.out.println("What is your name?");
				playerName = userInput.nextLine();
				System.out.println();
				System.out.println("Welcome, " + playerName + "!");
			}
		
		
		public static void turnMenu()
			{
				
				System.out.println("It's your turn!" + playerName + "\nWould you like to...\n\n1) Roll the dice\n2) View your stats");
				menuInput = userInput.nextInt();
				
				if(menuInput == 1)
					{
						movePlayer();
					}
				else if(menuInput == 2)
					{
						displayPlayerStats();
					}
				else
					{
						System.out.println("That's not an option.\nPlease try again.");
						turnMenu();
					}
				
				
				
				public static void movePlayer()
					{
						
						int playerRoll = DiceRoller2.rollDice(2,6);
						
						
						
						if((playerLocation + playerRoll) <= 39)
							{
								playerLocation += playerRoll;
							}
						else
							{
								playerLocation = (playerLocation + playerRoll) - 40;
								
								playerMoney += 200;
								System.out.println("You passsed GO and collected $200");
							}
						
						System.out.println("You can now move " + playerRoll + " places" + "\nYou landed on " + MonopDriver2.board[playerLocation].getName());
						
						landOnSquare();
						
						if(DiceRoller2.doubles == true)
							{
								timesRolledDoubles++;
								
								if(timesRolledDoubles == 3)
									{
										goToJail();
									}
								else
									{
										System.out.println("You rolled doubles, so you get to roll again!");
										movePlayer();
									}
							}
						else
							{
								timesRolledDoubles = 0;
							}
			}
		
		
				public static void landOnSquare()
					{
						if(MonopDriver2.board[playerLocation].getType().equals("Property"))
							{
								if(MonopDriver2.board[playerLocation].getOwner().equals("none"))
									{
									System.out.println("This location is not owned, would you like to buy it?\n\n1) Yes\n2) No");
									menuInput = userInput.nextInt();
									
									if(menuInput == 1)
										{
										
											checkForBankruptcy();
											MonopDriver2.board[playerLocation].setOwner(playerName);
											playerMoney -= MonopDriver2.board[playerLocation].getCost();
											inventory.add(MonopDriver2.board[playerLocation]);
											turnMenu();
											
											//numberOfPropertiesOwned++;
										}
									else
										{
									
											System.out.println("Your loss...");
											turnMenu();
										}
									}
								
								//need to implement the 'developing' feature (where you can only buy houses if you own all of that color)
								//in if statment below... do && all colors are owned or something
									else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this property...\nWould you like to buy a house or hotel?\n1) Yes\n2) No");
										menuInput = userInput.nextInt();
										
										if(menuInput == 1)
											{
												if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() >= 4)
													{
														System.out.println("Sorry, you have already bought the maximum amount of property on this space. \n"
																			+ "Would you like to buy a hotel?\n "
																			+ "1) Yes\\n2) No");
														
														menuInput2 = userInput.nextInt();
														
														if(menuInput2 == 1)
															{
																
																((Properties2) MonopDriver2.board[playerLocation]).setNumberOfHotelsOwned(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHotelsOwned()+1);
																playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getHouseHotelPrice();
																turnMenu();
															}
														else
															{
																turnMenu();
															}
													}
												else
													{
													
													((Properties2) MonopDriver2.board[playerLocation]).setNumberOfHousesOwned(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned()+1);
													playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getHouseHotelPrice();
													turnMenu();
													
													}
											}
									}
								
								else
									{
										System.out.println("This property is already owned by" + MonopDriver2.board[playerLocation].getOwner() + ", you now must pay rent.");
										if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 0)
											{
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getBasicRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 1)
											{
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getOneHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 2)
											{
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getTwoHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 3)
											{
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getThreeHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 4)
											{
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getHotelRent();
											}
										
										checkForBankruptcy();
									}
	
							}
						
						else if(MonopDriver2.board[playerLocation].getType().equals("Utility"))
							{
								if(MonopDriver2.board[playerLocation].getOwner().equals("none"))
									{
									System.out.println("This location is not owned, would you like to buy it?\n1) Yes\n2) No");
									menuInput = userInput.nextInt();
									
									if(menuInput == 1)
										{
											checkForBankruptcy();
										
											MonopDriver2.board[playerLocation].setOwner(playerName);
											playerMoney -= MonopDriver2.board[playerLocation].getCost();
											inventory.add(MonopDriver2.board[playerLocation]);
										}
									}
								else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this utility");
									}
								else
									{
										System.out.println("This utility is already owned by " + MonopDriver2.board[playerLocation].getOwner() + ", you now must pay for the service.");
										
										if (((Utilities2) MonopDriver2.board[playerLocation]).isOneOwnedUtility() == true)
											{
												
												int dr1 = DiceRoller2.rollDice(2,6)*4;
												playerMoney -= dr1;
												turnMenu();
												
											}
										else if (((Utilities2) MonopDriver2.board[playerLocation]).isTwoOwnedUtilities() == true)
											{
												
												int dr2 = DiceRoller2.rollDice(2,6)*10;
												playerMoney -= dr2;
												turnMenu();
												
											}
										
				        				
										
										
										
										
										
										
										
										
										
										
										
										
										
										
										
										
										
										//need to figure out how to search the array for everything that has one specific field
									}
							}
						else if(MonopDriver2.board[playerLocation].getType().equals("Railroad"))
							{
								if(MonopDriver2.board[playerLocation].getOwner().equals("none"))
									{
									System.out.println("This location is not owned, would you like to buy it?\n1) Yes\n2) No");
									menuInput = userInput.nextInt();
									
									if(menuInput == 1)
										{
											MonopDriver2.board[playerLocation].setOwner(playerName);
											playerMoney -= MonopDriver2.board[playerLocation].getCost();
											checkForBankruptcy();
											inventory.add(MonopDriver2.board[playerLocation]);
										}
									}
								else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this railroad");
									}
								else
									{
										//once again need to figure the comment from above out
										
										/*System.out.println("This property is already owned by" + MonopDriver.board[playerLocation].getOwner() + ", you now must pay rent.");
										if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 1)
											{
												playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getOneOwnedRent();
											}
										else if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 2)
											{
												playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getTwoOwnedRent();
											}
										else if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 3)
											{
												playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getThreeOwnedRent();
											}
										else if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 4)
											{
												playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getFourOwnedRent();
											}*/
										
										checkForBankruptcy();
									}
							}
						else
							{
								if(MonopDriver2.board[playerLocation].getName().equals("GO"))
									{
										playerMoney += MonopDriver2.board[playerLocation].getCost();
										System.out.println("You collected $" + MonopDriver2.board[playerLocation].getCost());
									}
								else if(MonopDriver2.board[playerLocation].getName().equals("Go_To_Jail"))
									{
										goToJail();
									}
								else if(MonopDriver2.board[playerLocation].getName().equals("Free_Parking"))
									{
										System.out.println("You won " + freeParkingMoney + "!");
										playerMoney += freeParkingMoney;
										freeParkingMoney = 0;
									}
								else if(MonopDriver2.board[playerLocation].getName().equals("Income_Tax") || MonopDriver2.board[playerLocation].getName().equals("Luxury_Tax"))
									{
										System.out.println("You have been taxed $" + MonopDriver2.board[playerLocation].getCost());
										freeParkingMoney += MonopDriver2.board[playerLocation].getCost();
										playerMoney -= MonopDriver2.board[playerLocation].getCost();;
									}
								
								//chance cards
								else if(MonopDriver2.board[playerLocation].getName().equals("Chance"))
									{
										int chanceNumber = (int) (Math.random() * 3) + 1;
										if(chanceNumber == 1)
											{
										System.out.println("You're wife left you. Give 50% of your money to her.");
										playerMoney = playerMoney / 2;
											}
										
										else if(chanceNumber == 2)
											{
												System.out.println("You got your secretary pregnant. Pay $50.");
												playerMoney = playerMoney - 50;
											}
										else
											{
												System.out.println("You found $100 on the street.");
												playerMoney = playerMoney + 100;
											}
									}
								//community chest
								else if(MonopDriver2.board[playerLocation].getName().equals("Community Chest"))
									{
										int chestNumber = (int) (Math.random() * 3) + 1;
										if(chestNumber == 1)
											{
										System.out.println("Your dog needs a surgery. Pay the vet $100.");
										playerMoney = playerMoney / 2;
											}
										
										else if(chestNumber == 2)
											{
												System.out.println("You have turned to a life of crime. You mugged a teenage girl for $35.");
												playerMoney = playerMoney + 35;
											}
										else
											{
												System.out.println("You bought the Fortnite battle pass ofr $10.");
												playerMoney = playerMoney - 10;
											}
									}
							}
					}
		
		
		
		
		
		
		
		
		
	}
