import java.util.ArrayList;
import java.util.Scanner;


public class ZooMonopPlayer46
	{

		static String playerName2;
		static int playerLocation2 = 0;
		static int playerMoney2 = 1500;
		static int timesRolledDoubles = 0;
		static int reverseDat = -1;
		static int menuInput;
		static int menuInput2;
		static int freeParkingMoney;
		static int jailCounter;
		static int inventoryCounter;
		static int turnCounter2;
		static boolean inJail = false;
		static boolean reverseFreeParking = false;
		static boolean stillPlaying2 = true;
		static Scanner userInput2 = new Scanner(System.in);
		static ArrayList<String> inventory2 = new ArrayList<String>();
		
		
//Ask Player 1's Name
		public static void askPlayer2Name()
			{
				System.out.println("What is your name, player 2?");
				playerName2 = userInput2.nextLine();
				System.out.println();
				System.out.println("Welcome, " + playerName2 + "!");
			}
		
//Main Menu
		public static void turnMenu()
			{
				
				System.out.println("It's your turn " + playerName2 + "\nWould you like to..."
						+ "\n\n\t1) Roll the dice"
						+ "\n\t2) View your stats");
				menuInput = userInput2.nextInt();
				
				if(menuInput == 1)
					{
						movePlayer();
					}
				else if(menuInput == 2)
					{
						turnCounter2++;
						displayPlayerStats();
					}
				else
					{
						System.out.println("That's not an option. Please try again.");
						turnMenu();
					}
			}
		
		
				
//Move Player	
				public static void movePlayer()
					{
						//int playerRoll = 4;
						int playerRoll = DiceRoller2.rollDice(2,6);
						
			//regulating playerLocation
						if((playerLocation2 + playerRoll) <= 39)
							{
								playerLocation2 += playerRoll;
								System.out.println("You can now move " + playerRoll + " places");
							}
						else
							{
								playerLocation2 = (playerLocation2 + playerRoll) - 40;
								playerMoney2 += 200;
								System.out.println("You can now move " + playerRoll + " places");
								System.out.println("You passed GO and collected $200");
							}
			//nuance if land on Jail but just visiting
						if(MonopDriver2.board[playerLocation2].getName().equals("Jail"))
							{
								System.out.println("You landed on Jail, but you are just visiting.");
								
							}
						else
							{
								System.out.println("You landed on " + MonopDriver2.board[playerLocation2].getName());
							}
						
						landOnSquare();
			//doubles rule (3 doubles == Jail)
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
//Land on Square
				public static void landOnSquare()
					{
//Properties
						if(MonopDriver2.board[playerLocation2].getType().equals("Property"))
							{
								if(MonopDriver2.board[playerLocation2].getOwner().equals("none"))
									{
									
									System.out.println("This property is not owned, would you like to... "
											+ "\n\n\t1) Buy this property"
											+ "\n\t2) View property price"
											+ "\n\t3) End turn");
				
									menuInput = userInput2.nextInt();
									
									if(menuInput == 1)
										{
											MonopDriver2.board[playerLocation2].setOwner(playerName2);
											playerMoney2 -= MonopDriver2.board[playerLocation2].getCost();
											inventory2.add(MonopDriver2.board[playerLocation2].getName());
											checkForBankruptcy();
											
										}
									else if(menuInput == 2)
										{
											System.out.println(MonopDriver2.board[playerLocation2].getName() + " costs $" + MonopDriver2.board[playerLocation2].getCost());
											System.out.println("Would you like to buy this property?"
													+ "\n\n\t1) Yes"
													+ "\n\t2) No");
											menuInput2 = userInput2.nextInt();
											
												if(menuInput2 == 1)
													{
														MonopDriver2.board[playerLocation2].setOwner(playerName2);
														playerMoney2 -= MonopDriver2.board[playerLocation2].getCost();
														inventory2.add(MonopDriver2.board[playerLocation2].getName());
														checkForBankruptcy();
														
													}
												else
													{
														System.out.println("Your loss...");
														
													}
										}
									else if(menuInput == 3)
										{
									
											System.out.println("Your loss...");
											
										}
									}
								
								//need to implement the 'developing' feature (where you can only buy houses if you own all of that color)
								//in if statment below... do && all colors are owned or something
									else if(MonopDriver2.board[playerLocation2].getOwner().equals(playerName2))
									{
										System.out.println("You already own this property...\nWould you like to buy a house or hotel?"
												+ "\n\t1) Yes"
												+ "\n\t2) No");
										menuInput = userInput2.nextInt();
										
										if(menuInput == 1)
											{
												if(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHousesOwned() >= 4)
													{
														System.out.println("Sorry, you have already bought the maximum amount of property on this space. \n"
																			+ "Would you like to buy a hotel? "
																			+ "\n\n\t1) Yes"
																			+ "\n\t2) No");
														
														menuInput2 = userInput2.nextInt();
														
														if(menuInput2 == 1)
															{
																
																((Properties2) MonopDriver2.board[playerLocation2]).setNumberOfHotelsOwned(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHotelsOwned()+1);
																playerMoney2 -= ((Properties2) MonopDriver2.board[playerLocation2]).getHouseHotelPrice();
																checkForBankruptcy();
																
															}
													}
												else
													{
													
													((Properties2) MonopDriver2.board[playerLocation2]).setNumberOfHousesOwned(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHousesOwned()+1);
													playerMoney2 -= ((Properties2) MonopDriver2.board[playerLocation2]).getHouseHotelPrice();
													checkForBankruptcy();
													
													}
											}
										else
											{
												System.out.println("Oops, that could bite you later :(");
											}
									}
								
								else
									{
										System.out.println("This property is already owned by" + MonopDriver2.board[playerLocation2].getOwner() + ", now you must pay rent.");
										if(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHousesOwned() == 0)
											{
												playerMoney2 -= ((Properties2) MonopDriver2.board[playerLocation2]).getBasicRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHousesOwned() == 1)
											{
												playerMoney2 -= ((Properties2) MonopDriver2.board[playerLocation2]).getOneHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHousesOwned() == 2)
											{
												playerMoney2 -= ((Properties2) MonopDriver2.board[playerLocation2]).getTwoHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHousesOwned() == 3)
											{
												playerMoney2 -= ((Properties2) MonopDriver2.board[playerLocation2]).getThreeHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation2]).getNumberOfHousesOwned() == 4)
											{
												playerMoney2 -= ((Properties2) MonopDriver2.board[playerLocation2]).getHotelRent();
											}
										
										checkForBankruptcy();
									}
	
							}
//Railroads					
						else if(MonopDriver2.board[playerLocation2].getType().equals("Railroads"))
							{
								
								if(MonopDriver2.board[playerLocation2].getOwner().equals("none"))
									{
										
										System.out.println("This railroad is not owned, would you like to... "
												+ "\n\n\t1) Buy this railroad"
												+ "\n\t2) View railroad price"
												+ "\n\t3) End turn");
					
										menuInput = userInput2.nextInt();
										
										if(menuInput == 1)
											{
												MonopDriver2.board[playerLocation2].setOwner(playerName2);
												playerMoney2 -= MonopDriver2.board[playerLocation2].getCost();
												inventory2.add(MonopDriver2.board[playerLocation2].getName());
												checkForBankruptcy();
												
											}
										else if(menuInput == 2)
											{
												System.out.println(MonopDriver2.board[playerLocation2].getName() + " costs $" + MonopDriver2.board[playerLocation2].getCost());
												System.out.println("Would you like to buy this railroad?"
														+ "\n\n\t1) Yes"
														+ "\n\t2) No");
												menuInput2 = userInput2.nextInt();
												
													if(menuInput2 == 1)
														{
															MonopDriver2.board[playerLocation2].setOwner(playerName2);
															playerMoney2 -= MonopDriver2.board[playerLocation2].getCost();
															inventory2.add(MonopDriver2.board[playerLocation2].getName());
															checkForBankruptcy();
														
														}
													else
														{
															System.out.println("Your loss...");
															
														}
											}
										else if(menuInput == 3)
											{
										
												System.out.println("Your loss...");
												
											}
										}
								else if(MonopDriver2.board[playerLocation2].getOwner().equals(playerName2))
									{
										System.out.println("You already own this railroad");
									}
								else
									{
										//once again need to figure the comment from above out
										
										System.out.println("This property is already owned by " + MonopDriver2.board[playerLocation2].getOwner() + ", now you must pay rent.");
										if(((Railroads2) MonopDriver2.board[playerLocation2]).getNumberOfRailroadsOwned() == 1)
											{
												playerMoney2 -= ((Railroads2) MonopDriver2.board[playerLocation2]).getOneOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation2]).getNumberOfRailroadsOwned() == 2)
											{
												playerMoney2 -= ((Railroads2) MonopDriver2.board[playerLocation2]).getTwoOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation2]).getNumberOfRailroadsOwned() == 3)
											{
												playerMoney2 -= ((Railroads2) MonopDriver2.board[playerLocation2]).getThreeOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation2]).getNumberOfRailroadsOwned() == 4)
											{
												playerMoney2 -= ((Railroads2) MonopDriver2.board[playerLocation2]).getFourOwnedRent();
											}
										
										checkForBankruptcy();
										
									}
							}
//Utilities
						else if(MonopDriver2.board[playerLocation2].getType().equals("Utility"))
							{
								if(MonopDriver2.board[playerLocation2].getOwner().equals("none"))
									{
										
										System.out.println("This utility is not owned, would you like to... "
												+ "\n\n\t1) Buy this utility"
												+ "\n\t2) View utility price"
												+ "\n\t3) End turn");
					
										menuInput = userInput2.nextInt();
										
										if(menuInput == 1)
											{
												MonopDriver2.board[playerLocation2].setOwner(playerName2);
												playerMoney2 -= MonopDriver2.board[playerLocation2].getCost();
												inventory2.add(MonopDriver2.board[playerLocation2].getName());
												checkForBankruptcy();
												
												
												//numberOfPropertiesOwned++;
											}
										else if(menuInput == 2)
											{
												System.out.println(MonopDriver2.board[playerLocation2].getName() + " costs $" + MonopDriver2.board[playerLocation2].getCost());
												System.out.println("Would you like to buy this utility?"
														+ "\n\n\t1) Yes"
														+ "\n\t2) No");
												menuInput2 = userInput2.nextInt();
												
													if(menuInput2 == 1)
														{
															MonopDriver2.board[playerLocation2].setOwner(playerName2);
															playerMoney2 -= MonopDriver2.board[playerLocation2].getCost();
															inventory2.add(MonopDriver2.board[playerLocation2].getName());
															checkForBankruptcy();
															
														}
													else
														{
															System.out.println("Your loss...");
														
														}
											}
										else if(menuInput == 3)
											{
										
												System.out.println("Your loss...");
											
											}
										}
								else if(MonopDriver2.board[playerLocation2].getOwner().equals(playerName2))
									{
										System.out.println("You already own this utility");
										
									}
								else
									{
										System.out.println("This utility is already owned by " + MonopDriver2.board[playerLocation2].getOwner() + ", now you must pay for the service.");
										
										if (((Utilities2) MonopDriver2.board[playerLocation2]).isOneOwnedUtility() == true)
											{
												
												int dr1 = DiceRoller2.rollDice(2,6)*4;
												playerMoney2 -= dr1;
												System.out.println("You lost " + dr1);
												checkForBankruptcy();

											}
										else if (((Utilities2) MonopDriver2.board[playerLocation2]).isTwoOwnedUtilities() == true)
											{
												
												int dr2 = DiceRoller2.rollDice(2,6)*10;
												playerMoney2 -= dr2;
												System.out.println("You lost " + dr2);
												checkForBankruptcy();
											
											}		
									}
							}
//Other locations
						else
							{
//Go
								if(MonopDriver2.board[playerLocation2].getName().equals("GO"))
									{
										playerMoney2 += MonopDriver2.board[playerLocation2].getCost();
										System.out.println("You collected $" + MonopDriver2.board[playerLocation2].getCost());
										
									}
//check if you landed on Go to Jail
								else if(MonopDriver2.board[playerLocation2].getName().equals("Go_To_Jail"))
									{
										goToJail();
									}
//Free Parking
								else if(MonopDriver2.board[playerLocation2].getName().equals("Free_Parking"))
									{
										System.out.println("You won $" + freeParkingMoney + "!");
										System.out.println("Now, you will move counterclockwise around the board");
										playerMoney2 += freeParkingMoney;
										freeParkingMoney = 0;
										reverseFreeParking = true;
										//moveReverse();
									}
//Taxes	
								else if(MonopDriver2.board[playerLocation2].getName().equals("Income_Tax") || MonopDriver2.board[playerLocation2].getName().equals("Luxury_Tax"))
									{
										System.out.println("You have been taxed $" + MonopDriver2.board[playerLocation2].getCost());
										freeParkingMoney += MonopDriver2.board[playerLocation2].getCost();
										playerMoney2 -= MonopDriver2.board[playerLocation2].getCost();;
										checkForBankruptcy();
									
									}
//Chance
								else if(MonopDriver2.board[playerLocation2].getName().equals("Chance"))
									{
										int chanceNumber = (int) (Math.random() * 3) + 1;
										
										if(chanceNumber == 1)
											{
										System.out.println("You're wife left you. Give 50% of your money to her.");
										playerMoney2 = playerMoney2 / 2;
										checkForBankruptcy();
											}
										
										else if(chanceNumber == 2)
											{
												System.out.println("You got your secretary pregnant. Pay $300.");
												playerMoney2 = playerMoney2 - 300;
												checkForBankruptcy();
											}
										else
											{
												System.out.println("You found $400 on the street.");
												playerMoney2 = playerMoney2 + 400;
											}
									
									}
//Community Chest
								else if(MonopDriver2.board[playerLocation2].getName().equals("Community_Chest"))
									{
										int chestNumber = (int) (Math.random() * 3) + 1;
										
										if(chestNumber == 1)
											{
										System.out.println("Your dog needs surgery. Pay the vet $100.");
										playerMoney2 -= 100;
										checkForBankruptcy();
											}
										
										else if(chestNumber == 2)
											{
												System.out.println("You have turned to a life of crime. You mugged a teenage girl for $5.");
												System.out.println("SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME");
												playerMoney2 = playerMoney2 + 5;
											}
										else
											{
												System.out.println("You bought the Fortnite battle pass for $10.");
												playerMoney2 = playerMoney2 - 10;
												checkForBankruptcy();
											}
									
									}
							}
					}	
//Player Stats				
				public static void displayPlayerStats()
					{
						System.out.println();
						System.out.println(playerName2 + "'s current balance: $" + playerMoney2);

						for (String i: inventory2)
							{
								String ownedSpaces = i;
								inventoryCounter++;
								
								if(inventoryCounter == 0)
									{
										System.out.println("You do not own any properties");
										inventoryCounter = 0;
										
									}
								else if (inventoryCounter == 1)
									{
										System.out.print("You own: ");
										System.out.print(ownedSpaces);
									}
								else if (inventoryCounter >= 2)
									{
										System.out.print(", ");
										System.out.print(ownedSpaces);
									}
							}
						inventoryCounter = 0;
						System.out.println();
						System.out.println();
						
						if(turnCounter2 == 1)
							{
								turnCounter2 = 0;
								turnMenu();
							}
					}
//Check Bankruptcy
				public static void checkForBankruptcy()
					{
						if(playerMoney2 <= 0)
							{
								System.out.println("You've gone bankrupt!");
								stillPlaying2 = false;
								System.exit(0);
							}		
					}
//Go to Jail
				public static void goToJail()
					{
						playerLocation2 = 10;
						//System.out.println("You are now in jail");
						inJail = true;
					}
//Stuck in Jail	
				public static void inJailTurn()
					{
						//need to implement cards soon
						System.out.println("You are in jail, would you like to..."
								+ "\n\n\t1) Pay the $50 fee"
								+ "\n\t2) Roll for doubles (If you roll doubles 3 consecutive times, you will pay $100)");
						menuInput = userInput2.nextInt();
						
						if(menuInput == 1)
							{
								freeParkingMoney += 50;
								playerMoney2 -= 50;
								System.out.println("You have now payed the fee of $50 and are free to go.");
								inJail = false;
								
							}
						else if(menuInput == 2)
							{
								DiceRoller2.rollDice(2,6);
								jailCounter++;
								
								if(DiceRoller2.doubles == true)
									{
										System.out.println("You rolled doubles and are free to go.");
										jailCounter = 0;
										inJail = false;
									}
								else
									{
										if(jailCounter == 3)
											{
												System.out.println("You have failed to roll a doubles after 3 attempts, you owe $100");
												freeParkingMoney += 100;
												playerMoney2 -= 100;
												System.out.println("You have now payed the fee and are free to go.");
												inJail = false;
												
												
											}
										else
											{
												System.out.println("You failed to roll doubles.");
											}
									}
							}
					}
		
	}
