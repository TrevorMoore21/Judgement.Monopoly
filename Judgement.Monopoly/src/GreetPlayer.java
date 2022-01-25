import java.util.Scanner;

public class GreetPlayer
	{

		static int monopVersionNum;
		static Scanner monopVersionInput = new Scanner(System.in); 
		static int playerCount;
		static Scanner playerNumber = new Scanner(System.in); 
		
		
//Monopoly Intro Greeting
				public static void monopIntro()
					{
						int introNumber = (int) (Math.random() * 3) + 1;
						if(introNumber == 1)
							{
								System.out.println("Hi, welcome to Monopoly");
							}
						else if(introNumber == 2)
							{
								System.out.println("Wassup Fart-Catcher, welcome to Monopoly");
							}
						else
							{
								System.out.println("Welcome to Monopoly fool");
							}
					}
//Choose Monopoly Version	
				public static void chooseMonopVersion()
				{
						
						System.out.println("What Monopoly version would you like to play?"
								+ "\n\t1) American Monopoly"
								+ "\n\t2) SpongeBob Monoply"
								+ "\n\t3) Aventure Time Monopoly");
						
						monopVersionNum = monopVersionInput.nextInt();
				}		
//Player Count	
				public static void chooseMonopPlayerCount()
				{
						System.out.println("How many players wish to play?"
								+ "\n\t1) 1"
								+ "\n\t2) 2");
						
						playerCount = playerNumber.nextInt();
				}
					
	}
