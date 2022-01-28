import java.util.ArrayList;
import java.util.Scanner;


public class ZooMonopPlayer45
	{
		static String playerName;
		static int playerLocation = 0;
		static int playerMoney = 1500;
		static int timesRolledDoubles = 0;
		static int reverseDat = -1;
		static int menuInput;
		static int menuInput2;
		static int freeParkingMoney;
		static int jailCounter;
		static int inventoryCounter;
		static int turnCounter1;
		static boolean inJail = false;
		static boolean reverseFreeParking = false;
		static boolean stillPlaying = true;
		static Scanner userInput = new Scanner(System.in);
		static Scanner playerNumber = new Scanner(System.in); 
		static ArrayList<String> inventory = new ArrayList<String>();
		
		
//Ask Player 1's Name
		public static void askPlayer1Name()
			{
				System.out.println("What is your name, player 1?");
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
						turnCounter1++;
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
						//int playerRoll = 6;
						int playerRoll = DiceRoller2.rollDice(2,6);
						
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
								System.out.println("You passed GO and collected $200");
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
											MonopDriver2.board[playerLocation].setOwner(playerName);
											playerMoney -= MonopDriver2.board[playerLocation].getCost();
											inventory.add(MonopDriver2.board[playerLocation].getName());
											checkForBankruptcy();
											
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
														MonopDriver2.board[playerLocation].setOwner(playerName);
														playerMoney -= MonopDriver2.board[playerLocation].getCost();
														inventory.add(MonopDriver2.board[playerLocation].getName());
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
																checkForBankruptcy();
																
															}
													}
												else
													{
													
													((Properties2) MonopDriver2.board[playerLocation]).setNumberOfHousesOwned(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned()+1);
													playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getHouseHotelPrice();
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
										System.out.println("This property is already owned by" + MonopDriver2.board[playerLocation].getOwner() + ", now you must pay rent.");
										if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 0)
											{
												System.out.println("You lost $" + ((Properties2) MonopDriver2.board[playerLocation]).getBasicRent());
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getBasicRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 1)
											{
												System.out.println("You lost $" + ((Properties2) MonopDriver2.board[playerLocation]).getOneHouseRent());
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getOneHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 2)
											{
												System.out.println("You lost $" + ((Properties2) MonopDriver2.board[playerLocation]).getTwoHouseRent());
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getTwoHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 3)
											{
												System.out.println("You lost $" + ((Properties2) MonopDriver2.board[playerLocation]).getThreeHouseRent());
												playerMoney -= ((Properties2) MonopDriver2.board[playerLocation]).getThreeHouseRent();
											}
										else if(((Properties2) MonopDriver2.board[playerLocation]).getNumberOfHousesOwned() == 4)
											{
												System.out.println("You lost $" + ((Properties2) MonopDriver2.board[playerLocation]).getHotelRent());
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
												MonopDriver2.board[playerLocation].setOwner(playerName);
												playerMoney -= MonopDriver2.board[playerLocation].getCost();
												inventory.add(MonopDriver2.board[playerLocation].getName());
												checkForBankruptcy();
												
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
															MonopDriver2.board[playerLocation].setOwner(playerName);
															playerMoney -= MonopDriver2.board[playerLocation].getCost();
															inventory.add(MonopDriver2.board[playerLocation].getName());
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
								else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this railroad");
									}
								else
									{
										//once again need to figure the comment from above out
										
										System.out.println("This property is already owned by " + MonopDriver2.board[playerLocation].getOwner() + ", now you must pay rent.");
										if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 1)
											{
												System.out.println("You lost $" + ((Railroads2) MonopDriver2.board[playerLocation]).getOneOwnedRent());
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getOneOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 2)
											{
												System.out.println("You lost $" + ((Railroads2) MonopDriver2.board[playerLocation]).getTwoOwnedRent());
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getTwoOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 3)
											{
												System.out.println("You lost $" + ((Railroads2) MonopDriver2.board[playerLocation]).getThreeOwnedRent());
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getThreeOwnedRent();
											}
										else if(((Railroads2) MonopDriver2.board[playerLocation]).getNumberOfRailroadsOwned() == 4)
											{
												System.out.println("You lost $" + ((Railroads2) MonopDriver2.board[playerLocation]).getFourOwnedRent());
												playerMoney -= ((Railroads2) MonopDriver2.board[playerLocation]).getFourOwnedRent();
											}
										
										checkForBankruptcy();
										
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
												MonopDriver2.board[playerLocation].setOwner(playerName);
												playerMoney -= MonopDriver2.board[playerLocation].getCost();
												inventory.add(MonopDriver2.board[playerLocation].getName());
												checkForBankruptcy();
												
												
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
															MonopDriver2.board[playerLocation].setOwner(playerName);
															playerMoney -= MonopDriver2.board[playerLocation].getCost();
															inventory.add(MonopDriver2.board[playerLocation].getName());
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
								else if(MonopDriver2.board[playerLocation].getOwner().equals(playerName))
									{
										System.out.println("You already own this utility");
										
									}
								else
									{
										System.out.println("This utility is already owned by " + MonopDriver2.board[playerLocation].getOwner() + ", now you must pay for the service.");
										
										if (((Utilities2) MonopDriver2.board[playerLocation]).isOneOwnedUtility() == true)
											{
												
												int dr1 = DiceRoller2.rollDice(2,6)*4;
												playerMoney -= dr1;
												System.out.println("You lost $" + dr1);
												checkForBankruptcy();

											}
										else if (((Utilities2) MonopDriver2.board[playerLocation]).isTwoOwnedUtilities() == true)
											{
												
												int dr2 = DiceRoller2.rollDice(2,6)*10;
												playerMoney -= dr2;
												System.out.println("You lost $" + dr2);
												checkForBankruptcy();
											
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
										System.out.println("Now, you will move counterclockwise around the board");
										playerMoney += freeParkingMoney;
										freeParkingMoney = 0;
										reverseFreeParking = true;
										//moveReverse();
									}
//American-->Taxes	
								else if(MonopDriver2.board[playerLocation].getName().equals("Income_Tax") || MonopDriver2.board[playerLocation].getName().equals("Luxury_Tax"))
									{
										System.out.println("You have been taxed $" + MonopDriver2.board[playerLocation].getCost());
										freeParkingMoney += MonopDriver2.board[playerLocation].getCost();
										playerMoney -= MonopDriver2.board[playerLocation].getCost();;
										checkForBankruptcy();
									}
//American-->Community Chest
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
									}
//American-->Chance
								else if(MonopDriver2.board[playerLocation].getName().equals("Chance"))
									{
										int chanceNumber = (int) (Math.random() * 3) + 1;
										
										if(chanceNumber == 1)
											{
										System.out.println("Your wife left you. Give 50% of your money to her.");
										playerMoney = playerMoney / 2;
										checkForBankruptcy();
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
									}
//SpongeBob-->Taxes	
								else if(MonopDriver2.board[playerLocation].getName().equals("BUBBLE_TAX") || MonopDriver2.board[playerLocation].getName().equals("EMPLOYEE_OF_THE_MONTH_TAX"))
									{
										System.out.println("You have been taxed $" + MonopDriver2.board[playerLocation].getCost());
										freeParkingMoney += MonopDriver2.board[playerLocation].getCost();
										playerMoney -= MonopDriver2.board[playerLocation].getCost();;
										checkForBankruptcy();
									}
//SpongeBob-->TREASURE CHEST				
								else if(MonopDriver2.board[playerLocation].getName().equals("TREASURE_CHEST"))
									{
										int treasureChest = (int) (Math.random() * 3) + 1;
										
										if(treasureChest == 1)
											{
										System.out.println("Your snail needs surgery. Pay the vet $100.");
										playerMoney -= 100;
										checkForBankruptcy();
											}
										
										else if(treasureChest == 2)
											{
												System.out.println("You have turned to a life of crime. You mugged a teenage gold fish for $5.");
												System.out.println("SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME");
												playerMoney = playerMoney + 5;
											}
										else
											{
												System.out.println("You bought the Fortwave battle pass for $10.");
												playerMoney = playerMoney - 10;
												checkForBankruptcy();
											}
									}
//SpongeBob-->LIFE PRESERVER
								else if(MonopDriver2.board[playerLocation].getName().equals("LIFE_PRESERVER"))
									{
										int lifePreserver = (int) (Math.random() * 3) + 1;
										
										if(lifePreserver == 1)
											{
										System.out.println("Squidward sues you. Give 50% of your money to him.");
										playerMoney = playerMoney / 2;
										checkForBankruptcy();
											}
										
										else if(lifePreserver == 2)
											{
												System.out.println("Patrick punched you. Doctor's fee: pay $100.");
												playerMoney = playerMoney - 100;
												checkForBankruptcy();
											}
										else
											{
												System.out.println("You found $150 on a jellyfish.");
												playerMoney = playerMoney + 150;
											}
									}
//Adventure Time-->Taxes	
								else if(MonopDriver2.board[playerLocation].getName().equals("Jerk_Tax") || MonopDriver2.board[playerLocation].getName().equals("Jacked_Up!"))
									{
										System.out.println("You have been taxed $" + MonopDriver2.board[playerLocation].getCost());
										freeParkingMoney += MonopDriver2.board[playerLocation].getCost();
										playerMoney -= MonopDriver2.board[playerLocation].getCost();;
										checkForBankruptcy();
									}
//Adventure Time-->What The Lump?			
								else if(MonopDriver2.board[playerLocation].getName().equals("What_The_Lump?"))
									{
										int wtl = (int) (Math.random() * 3) + 1;
										
										if(wtl == 1)
											{
										System.out.println("BMO needs surgery. Pay the vet $200.");
										playerMoney -= 200;
										checkForBankruptcy();
											}
										
										else if(wtl == 2)
											{
												System.out.println("You have turned to a life of crime and joined the Ice King."
														+ " You mugged Princess Bubblegum for $7.");
												System.out.println("SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME SHAME");
												playerMoney = playerMoney + 7;
											}
										else
											{
												System.out.println("Lumpy Space Princess needs plastic surgery: donate $100.");
												playerMoney = playerMoney - 100;
												checkForBankruptcy();
											}
									}		
//Adventure Time-->Algebraic!
								else if(MonopDriver2.board[playerLocation].getName().equals("Algebraic!"))
									{
										int algebraic = (int) (Math.random() * 3) + 1;
										
										if(algebraic == 1)
											{
										System.out.println("Jake left you. Give 50% of your money to him.");
										playerMoney = playerMoney / 2;
										checkForBankruptcy();
											}
										
										else if(algebraic == 2)
											{
												System.out.println("You pissed Marceline off. Bribe her $400 for your safety.");
												playerMoney = playerMoney - 400;
												checkForBankruptcy();
											}
										else
											{
												System.out.println("Lady Rainicorn gifted you $250");
												playerMoney = playerMoney + 250;
											}
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
						
						if(turnCounter1 == 1)
							{
								turnCounter1 = 0;
								turnMenu();
							}
					}
//Check Bankruptcy
				public static void checkForBankruptcy()
					{
						if(playerMoney <= 0)
							{
								System.out.println("You've gone bankrupt!");
								System.out.println("You lose " + playerName);
								System.out.println(ZooMonopPlayer46.playerName2 + " wins");
								stillPlaying = false;
								System.exit(0);
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
						System.out.println("It's your turn " + playerName);
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
												playerMoney -= 100;
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
