import java.util.ArrayList;
import java.util.Scanner;


public class ZooMonopPlayer45
	{

		static String playerName;
		static int playerLocation = 0;
		static int playerMoney = 1500;
		static int timesRolledDoubles = 0;
		static int menuInput;
		static int menuInput2;
		static int freeParkingMoney;
		static int jailCounter;
		static int inventoryCounter;
		static boolean inJail = false;
		static boolean stillPlaying = true;
		static Scanner userInput = new Scanner(System.in);
		static ArrayList<String> inventory = new ArrayList<String>();
		
		
//Greet Player
		public static void greetPlayer()
			{
				int introNumber = (int) (Math.random() * 3) + 1;
				if(introNumber == 1)
					{
						System.out.println("Hi, welcome to Monopoly");
					}
				else if(introNumber == 2)
					{
						System.out.println("Wassup Fart-Catcher, welcome to dIgItAl_MoNoPlY");
					}
				else
					{
						System.out.println("Welcome to Monopoly fool");
					}
				
				System.out.println("What is your name?");
				playerName = userInput.nextLine();
				System.out.println();
				System.out.println("Welcome, " + playerName + "!");
			}
		
//Main Menu
		public static void turnMenu()
			{
				
				System.out.println("It's your turn " + playerName + "\nWould you like to..."
						+ "\n\n\t1) Roll the dice"
						+ "\n\t2) View your stats");
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
						System.out.println("That's not an option. Please try again.");
						turnMenu();
					}
			}
		
		
				
//Move Player	
				public static void movePlayer()
					{
						int playerRoll = 4;
						//int playerRoll = DiceRoller2.rollDice(2,6);
						
			//regulating playerLocation
						if((playerLocation + playerRoll) <= 39)
							{
								playerLocation += playerRoll;
								System.out.println("You can now move " + playerRoll + " places");
							}
						else
							{
								playerLocation = (playerLocation + playerRoll) - 40;
								playerMoney += 200;
								System.out.println("You can now move " + playerRoll + " places");
								System.out.println("You passsed GO and collected $200");
							}
			//nuance if land on Jail but just visiting
						if(MonopDriver2.board[playerLocation].getName().equals("Jail"))
							{
								System.out.println("You landed on Jail, but you are just visiting.");
							}
						else
							{
								System.out.println("You landed on " + MonopDriver2.board[playerLocation].getName());
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
						if(MonopDriver2.board[playerLocation].getType().equals("Property"))
							{
								if(MonopDriver2.board[playerLocation].getOwner().equals("none"))
									{
									
									System.out.println("This property is not owned, would you like to... "
											+ "\n\n\t1) Buy this property"
											+ "\n\t2) View property price"
											+ "\n\t3) End turn");
				
									menuInput = userInput.nextInt();
									
									if(menuInput == 1)
										{
										
											checkForBankruptcy();
											MonopDriver2.board[playerLocation].setOwner(playerName);
											playerMoney -= MonopDriver2.board[playerLocation].getCost();
											inventory.add(MonopDriver2.board[playerLocation].getName());
											turnMenu();
										}
									else if(menuInput == 2)
										{
											System.out.println(MonopDriver2.board[playerLocation].getName() + " costs $" + MonopDriver2.board[playerLocation].getCost());
											System.out.println("Would you like to buy this property?"
													+ "\n\n\t1) Yes"
													+ "\n\t2) No");
											menuInput2 = userInput.nextInt();
											
												if(menuInput2 == 1)
													{
														checkForBankruptcy();
														MonopDriver2.board[playerLocation].setOwner(playerName);
														playerMoney -= MonopDriver2.board[playerLocation].getCost();
														inventory.add(MonopDriver2.board[playerLocation].getName());
														turnMenu();
													}
												else
													{
														System.out.println("Your loss...");
														turnMenu();
													}
										}
									else if(menuInput == 3)
										{
									
											System.out.println("Your loss...");
											turnMenu();
										}
									else
										{
											turnMenu();
										}
									}
								
								//need to implement the 'developing' feature (where you can only buy houses if you own all of that color)
								//in if statment below... do && all colors are owned or something
									else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this property...\nWould you like to buy a house or hotel?"
												+ "\n\t1) Yes"
												+ "\n\t2) No");
										menuInput = userInput.nextInt();
										
										if(menuInput == 1)
											{
												if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() >= 4)
													{
														System.out.println("Sorry, you have already bought the maximum amount of property on this space. \n"
																			+ "Would you like to buy a hotel? "
																			+ "\n\n\t1) Yes"
																			+ "\n\t2) No");
														
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
										else
											{
												System.out.println("Oops, that could bite you later :(");
												turnMenu();
											}
									}
								
								else
									{
										System.out.println("This property is already owned by" + MonopDriver2.board[playerLocation].getOwner() + ", now you must pay rent.");
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
//Railroads					
						else if(MonopDriver2.board[playerLocation].getType().equals("Railroads"))
							{
								
								if(MonopDriver2.board[playerLocation].getOwner().equals("none"))
									{
										
										System.out.println("This railroad is not owned, would you like to... "
												+ "\n\n\t1) Buy this railroad"
												+ "\n\t2) View railroad price"
												+ "\n\t3) End turn");
					
										menuInput = userInput.nextInt();
										
										if(menuInput == 1)
											{
											
												checkForBankruptcy();
												MonopDriver2.board[playerLocation].setOwner(playerName);
												playerMoney -= MonopDriver2.board[playerLocation].getCost();
												inventory.add(MonopDriver2.board[playerLocation].getName());
												turnMenu();
												
												//numberOfPropertiesOwned++;
											}
										else if(menuInput == 2)
											{
												System.out.println(MonopDriver2.board[playerLocation].getName() + " costs $" + MonopDriver2.board[playerLocation].getCost());
												System.out.println("Would you like to buy this railroad?"
														+ "\n\n\t1) Yes"
														+ "\n\t2) No");
												menuInput2 = userInput.nextInt();
												
													if(menuInput2 == 1)
														{
															checkForBankruptcy();
															MonopDriver2.board[playerLocation].setOwner(playerName);
															playerMoney -= MonopDriver2.board[playerLocation].getCost();
															inventory.add(MonopDriver2.board[playerLocation].getName());
															turnMenu();
														}
													else
														{
															System.out.println("Your loss...");
															turnMenu();
														}
											}
										else if(menuInput == 3)
											{
										
												System.out.println("Your loss...");
												turnMenu();
											}
										else
											{
												turnMenu();
											}
										}
								else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this railroad");
										turnMenu();
									}
								else
									{
										//once again need to figure the comment from above out
										
										System.out.println("This property is already owned by " + MonopDriver2.board[playerLocation].getOwner() + ", now you must pay rent.");
										if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 1)
											{
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getOneOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 2)
											{
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getTwoOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 3)
											{
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getThreeOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 4)
											{
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getFourOwnedRent();
											}
										
										checkForBankruptcy();
										turnMenu();
									}
							}
//Utilities
						else if(MonopDriver2.board[playerLocation].getType().equals("Utility"))
							{
								if(MonopDriver2.board[playerLocation].getOwner().equals("none"))
									{
										
										System.out.println("This utility is not owned, would you like to... "
												+ "\n\n\t1) Buy this utility"
												+ "\n\t2) View utility price"
												+ "\n\t3) End turn");
					
										menuInput = userInput.nextInt();
										
										if(menuInput == 1)
											{
											
												checkForBankruptcy();
												MonopDriver2.board[playerLocation].setOwner(playerName);
												playerMoney -= MonopDriver2.board[playerLocation].getCost();
												inventory.add(MonopDriver2.board[playerLocation].getName());
												turnMenu();
												
												//numberOfPropertiesOwned++;
											}
										else if(menuInput == 2)
											{
												System.out.println(MonopDriver2.board[playerLocation].getName() + " costs $" + MonopDriver2.board[playerLocation].getCost());
												System.out.println("Would you like to buy this utility?"
														+ "\n\n\t1) Yes"
														+ "\n\t2) No");
												menuInput2 = userInput.nextInt();
												
													if(menuInput2 == 1)
														{
															checkForBankruptcy();
															MonopDriver2.board[playerLocation].setOwner(playerName);
															playerMoney -= MonopDriver2.board[playerLocation].getCost();
															inventory.add(MonopDriver2.board[playerLocation].getName());
															turnMenu();
														}
													else
														{
															System.out.println("Your loss...");
															turnMenu();
														}
											}
										else if(menuInput == 3)
											{
										
												System.out.println("Your loss...");
												turnMenu();
											}
										else
											{
												turnMenu();
											}
										}
								else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this utility");
										turnMenu();
									}
								else
									{
										System.out.println("This utility is already owned by " + MonopDriver2.board[playerLocation].getOwner() + ", now you must pay for the service.");
										
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
									}
							}
//Other locations
						else
							{
//Go
								if(MonopDriver2.board[playerLocation].getName().equals("GO"))
									{
										playerMoney += MonopDriver2.board[playerLocation].getCost();
										System.out.println("You collected $" + MonopDriver2.board[playerLocation].getCost());
										
									}
//check if you landed on Go to Jail
								else if(MonopDriver2.board[playerLocation].getName().equals("Go_To_Jail"))
									{
										goToJail();
									}
								
								
								
								
								
								
								
								
								
//Free Parking
								else if(MonopDriver2.board[playerLocation].getName().equals("Free_Parking"))
									{
										System.out.println("You won $" + freeParkingMoney + "!");
										playerMoney += freeParkingMoney;
										freeParkingMoney = 0;
										turnMenu();
									}
								
								
								
								
								
								
								
								
								
								
								
								
								
								else if(MonopDriver2.board[playerLocation].getName().equals("Income_Tax") || MonopDriver2.board[playerLocation].getName().equals("Luxury_Tax"))
									{
										System.out.println("You have been taxed $" + MonopDriver2.board[playerLocation].getCost());
										freeParkingMoney += MonopDriver2.board[playerLocation].getCost();
										playerMoney -= MonopDriver2.board[playerLocation].getCost();;
										turnMenu();
									}
//Chance
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
												System.out.println("You got your secretary pregnant. Pay $300.");
												playerMoney = playerMoney - 300;
												checkForBankruptcy();
											}
										else
											{
												System.out.println("You found $400 on the street.");
												playerMoney = playerMoney + 400;
											}
										turnMenu();
									}
//Community Chest
								else if(MonopDriver2.board[playerLocation].getName().equals("Community_Chest"))
									{
										int chestNumber = (int) (Math.random() * 3) + 1;
										if(chestNumber == 1)
											{
										System.out.println("Your dog needs surgery. Pay the vet $100.");
										playerMoney -= 100;
										checkForBankruptcy();
											}
										
										else if(chestNumber == 2)
											{
												System.out.println("You have turned to a life of crime. You mugged a teenage girl for $5.");
												System.out.println("SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME");
												playerMoney = playerMoney + 5;
											}
										else
											{
												System.out.println("You bought the Fortnite battle pass for $10.");
												playerMoney = playerMoney - 10;
												checkForBankruptcy();
											}
										turnMenu();
									}
							}
					}	
//Player Stats				
				public static void displayPlayerStats()
					{
						System.out.println();
						System.out.println(playerName + "'s current balance: $" + playerMoney);

						for (String i: inventory)
							{
								String ownedSpaces = i;
								inventoryCounter++;
								
								if(inventoryCounter == 0)
									{
										inventoryCounter = 0;
										turnMenu();
									}
								else if (inventoryCounter == 1)
									{
										System.out.print("You own: ");
										System.out.print(ownedSpaces);
									}
								else 
									{
										System.out.print(", ");
										System.out.print(ownedSpaces);
									}
							}
						inventoryCounter = 0;
						System.out.println();
						System.out.println();
						turnMenu();
					}
//Check Bankruptcy
				public static void checkForBankruptcy()
					{
						if(playerMoney <= 0)
							{
								System.out.println("You've gone bankrupt!");
								stillPlaying = false;
							}		
					}
//Go to Jail
				public static void goToJail()
					{
						playerLocation = 10;
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
						menuInput = userInput.nextInt();
						
						if(menuInput == 1)
							{
								freeParkingMoney += 50;
								playerMoney -= 50;
								System.out.println("You have now payed the fee of $50 and are free to go.");
								inJail = false;
								turnMenu();
							}
						else
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
												playerMoney -= 100;
												System.out.println("You have now payed the fee and are free to go.");
												inJail = false;
												turnMenu();
												
											}
										else
											{
												System.out.println("You failed to roll doubles.");
											}
									}
							}
					}
	}
