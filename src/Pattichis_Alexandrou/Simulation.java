package Pattichis_Alexandrou;

import edu.princeton.cs.introcs.StdDraw; // Library to import the StdDraw to the package

import java.util.ArrayList;
import java.util.Scanner;



/**
 * Simulation Class represents the simulation that will connect all the other
 * class together and run the simulation.
 * 
 * Jar file should be in the workspace so that it locates it automatically.
 * 
 * @author apatti01
 * @author aalexa02
 */
public class Simulation {

	/**
	 * delay() method is used for better view of the simulation and the process of
	 * infection
	 */
	public static void delay() {
		try {
			Thread.sleep(1 * 2000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // Using Scanner to read from the console
		String time; // Indicates the time that the simulation will run (in minutes)
		String height; // Indicates the height of the board
		String width; // Indicates the width of the grid
		String crowd; // Indicates the number of people that will be used for the simulation
		String duration; // Indicates that each place will be affected

		// The user enters manually the health of the people
		String Infected;
		String Immune;

		String option; // The user is asked if he/she wants to add manually the probabilities of
		String option1;				// infection
		String ppVirus; // Person-to-Person probability of infection without mask
		String ppMask; // Person-to-Person probability of infection with mask
		String plVirus; // Place-to-Person probability of infection without mask
		String plMask; // Place-to-Person probability of infection with mask
		String areas;
		String borders;
		String points;
		
		int peopleVirus = 0;
		int peopleMask = 0;
		int placeVirus = 0;
		int placeMask = 0;
		int opt = 0;
		int opt1 = 0;
		int userTime = 0;
		int userHeight = 0;
		int userWidth = 0;
		int userCrowd = 0;
		int userDuration = 0;
		int sumInfected = 0;
		int sumHealthy = 0;
		int sumImmune = 0;
		int sumAreas = 0;
		int sumBorders = 0;
		
		boolean done = true;

		int max = 0;

		// Prints a message to inform the user about the simulation
		System.out.println("                   SIMULATION OF THE PROCESS                  ");
		System.out.println("         OF TRANSMITING A VIRUS TO A SET OF INDIVIDUALS        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("       The virus is spread from person to person through       ");
		System.out.println("         personal contact with an infected person or by        ");
		System.out.println("            contact with an infected area of the site.       \n");
		System.out.println("A person can be: 1) healthy  (wearing a GREEN shirt)           ");
		System.out.println("                 2) infected (wearing a RED shirt)             ");
		System.out.println("                 3) immune   (wearing a BLUE shirt)            ");
		System.out.println("                 && wearing a mask                           \n");
		System.out.println("When floor is colored: 1) ORANGE, it just got infected         ");
		System.out.println("                       2) YELLOW, it will stop being infected\n");
		System.out.println("*Every person stays at the place for 1 minute.                ");

		// While loop to read all the inputs, that stops whenever none of the exception
		// are caught
		int cnt = 0;
		int sub = 0;
		while (done) {
			try {
				System.out.println("---------------------------------------------------------------");
				System.out.println("Now you get to decide the specifications of the simulation: \n");
				// Reads the number of people
				System.out.println("Would you like to set your own propabilities for the affection "
						+ "\nof the Virus to the people?");
				System.out.print("\nPress 0 for NO or 1 for YES: ");
				option = input.nextLine();
				opt = Integer.parseInt(option);
				if (opt > 1 || opt < 0)
					throw new ProbabilitiesOptionException("Insert a number that is either 0 or 1");

				if (opt == 0) {
					System.out.print("\nDEFAULT PROBABILITIES:\n");
					System.out.println("\n(1) Person-to-Person probability of infection without mask:(%) 70");
					System.out.println("\n(2) Person-to-Person probability of infection with mask:(%) 40");
					System.out.println("\n(3) Place-to-Person probability of infection without mask:(%) 60");
					System.out.println("\n(4) Place-to-Person probability of infection with mask:(%) 30");
				}

				if (opt == 1) {
					System.out.print("\n\nGive the percantages about:\n");

					System.out.print("\n(1) Person-to-Person probability of infection without mask:(%) ");
					ppVirus = input.nextLine();
					peopleVirus = Integer.parseInt(ppVirus);
					if (peopleVirus < 0 || peopleVirus > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");

					System.out.print("\n(2) Person-to-Person probability of infection with mask:(%) ");
					ppMask = input.nextLine();
					peopleMask = Integer.parseInt(ppMask);
					if (peopleMask < 0 || peopleMask > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");

					System.out.print("\n(3) Place-to-Person probability of infection without mask:(%) ");
					plVirus = input.nextLine();
					placeVirus = Integer.parseInt(plVirus);
					if (placeVirus < 0 || placeVirus > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");

					System.out.print("\n(4) Place-to-Person probability of infection with mask:(%) ");
					plMask = input.nextLine();
					placeMask = Integer.parseInt(plMask);
					if (placeMask < 0 || placeMask > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");
				}
				System.out.println("---------------------------------------------------------------");
				System.out.println("Also please tell us:");
				System.out.println("The number of areas that people will interact: ");
				areas = input.nextLine();
				
				sumAreas = Integer.parseInt(areas);
				if (sumAreas < 0)
					throw new NegativeNumberException();
				String towns;
				int[] town=new int[sumAreas];
				for (int i = 0; i < sumAreas; i++) {
					
				
				// Reads the number of people
				System.out.print("\nThe number of people "+"for Area "+(char)('A'+i));
				crowd = input.nextLine();
				userCrowd = Integer.parseInt(crowd);
				if (userCrowd < 0)
					throw new NegativeNumberException();

				// Reads the number of infected people
				System.out.print("\nThe number of infected people "+"for Area "+(char)('A'+i));
				Infected = input.nextLine();
				sumInfected = Integer.parseInt(Infected);
				if (sumInfected < 0)
					throw new NegativeNumberException();
				if (sumInfected < 1)
					throw new InfectedLessThanOneException();
				if (sumInfected > userCrowd)
					throw new OvercrowdedException("\nThe number of infected is bigger than the crowd.\nTry again!!");
				cnt = sumInfected;
				sub = userCrowd - sumInfected;

				// Reads the number of immune people
				System.out.print("\nGive the number of the immune people "+"for Area "+(char)('A'+i));
				Immune = input.nextLine();
				sumImmune = Integer.parseInt(Immune);
				if (sumImmune < 0)
					throw new NegativeNumberException();
				if (sumImmune > sub)
					throw new OvercrowdedException(
							"\nThe number of Immune + the infected is bigger than the crowd. \nTry again!!");
				sumHealthy = userCrowd - (sumInfected + sumImmune);

				// Reads the height of the grid
				System.out.print("\nGive the height of the board "+"for Area "+(char)('A'+i));
				height = input.nextLine();
				userHeight = Integer.parseInt(height);
				if (userHeight < 0)
					throw new NegativeNumberException();
				if (userHeight >= 50)
					throw new GridSizeException();

				// Reads the width of the grid
				System.out.print("\nGive the width of the board "+"for Area "+(char)('A'+i));
				width = input.nextLine();
				userWidth = Integer.parseInt(width);
				if (userWidth < 0)
					throw new NegativeNumberException();
				if (userWidth >= 50)
					throw new GridSizeException();
				max = Math.max(userWidth, userHeight);
				if (userCrowd > userWidth * userHeight)
					throw new OvercrowdedException();
				if(sumAreas>1) {
				System.out.print("\nDo you want this Area to have borders?\nPress 0 for NO or 1 for YES:");
				option = input.nextLine();
				opt = Integer.parseInt(option);
				if (opt > 1 || opt < 0)
					throw new ProbabilitiesOptionException("Insert a number that is either 0 or 1");
				if(opt==1) {
					System.out.print("\nWith which area do you want area "+(char)('A'+i)+" to be bordered with");
					towns = input.nextLine();
					if(towns.length()>1||towns.charAt(0)-65>sumAreas-1||towns.charAt(0)-65<0||towns.charAt(0)-65>91-65||towns.charAt(0)-65-i==0)
						throw new ProbabilitiesOptionException("Give the name of the area that exist e.g 'A' ");
					town[i] =towns.charAt(0)-65+1;
					if (userCrowd < 0)
						throw new NegativeNumberException();

					System.out.println("How many borders do you want for the area?");
					borders = input.nextLine();
					sumBorders = Integer.parseInt(borders);
					if (sumBorders <=0)
						throw new ProbabilitiesOptionException("The borders sould be more than 1 or more!");
					if(sumBorders>(userHeight*2+userWidth*2))
						throw new ProbabilitiesOptionException("Insert a number that is equal or less than the perimeter of the border!");
					System.out.println("Give the area on the grid for the borders in points(e.g 2,0"
							+ "\n(The points should be on the border of the grid, successively and you should give them in order!");
					Point []point=new Point[sumBorders];
					for (int j = 0; j < sumBorders; j++) {
						System.out.println(j+1+") ");
						points = input.nextLine();
						point[j]=Input(points);
				    	if(point[j].getX()==-1||point[j].getY()==-1) 
				    		throw new ProbabilitiesOptionException("Give the points correctly..\ne.g. Give a point like (2,1)!!");
				    	if(point[j].getX()>userHeight||point[j].getY()>userWidth) 
				    		throw new ProbabilitiesOptionException("Give the points that are in the grid..\ne.g. Give a point like (2,1)!!");
				    	if(!(point[j].getX()<=userHeight&&point[j].getY()==userWidth||point[j].getX()==userHeight&&point[j].getY()<=userWidth||point[j].getX()==0&&point[j].getY()<=userWidth||point[j].getX()<=userHeight&&point[j].getY()==0)) 
				    		throw new ProbabilitiesOptionException("Give the points that are on the border of the grid..\ne.g. Give a point like (height,0)!!");
				    	if(j!=0) {
				    	if(!(point[j].getX()==point[j-1].getX()+1&&point[j].getY()==point[j-1].getY()||point[j].getX()==point[j-1].getX()-1&&point[j].getY()==point[j-1].getY()||point[j].getX()==point[j-1].getX()&&point[j].getY()==point[j-1].getY()+1||point[j].getX()==point[j-1].getX()&&point[j].getY()==point[j-1].getY()-1)) 
				    		throw new ProbabilitiesOptionException("Give the points successively and you should give them in order!..\ne.g. Give points like (2,0)->(3,0)->(4,0)!!");
				    	}
				    	
					}
				}
				}
				}
				for (int j = 0; j < town.length; j++) {
					System.out.println(town[j]+" ");
				}
				// Reads the time of the program
				System.out.print("\nGive the time of the program (in minutes): ");
				time = input.nextLine();
				userTime = Integer.parseInt(time);
				if (userTime < 0)
					throw new NegativeNumberException();

				// Reads the duration that each place will be affected
				System.out.print("\nGive the duration that each place will be affected: ");
				duration = input.nextLine();
				userDuration = Integer.parseInt(duration);
				if (userDuration < 0)
					throw new NegativeNumberException();
				done = false;
			} catch (ProbabilitiesOptionException error) {
				System.out.println(error.getMessage());
			} catch (OvercrowdedException error) {
				System.out.println(error.getMessage());
			} catch (InfectedLessThanOneException error) {
				System.out.println(error.getMessage());
			} catch (NegativeNumberException error) {
				System.out.println(error.getMessage());
			} catch (GridSizeException error) {
				System.out.println(error.getMessage());
			} catch (NumberFormatException error) {
				System.out.println("\nSorry, something went wrong.");
				System.out.println("Put a number to the input" + "\n");
			}
		}
		Person[] pl = new Person[userCrowd];
		ArrayList ppl=new ArrayList();
		
		for (int i = 0; i < sumImmune; i++) {
			pl[i] = new Person(0, 0, false, true);
		}
		for (int i = sumImmune; i < sumImmune + sumInfected; i++) {
			pl[i] = new Person(0, 0, true, false);
		}
		for (int i = sumImmune + sumInfected; i < userCrowd; i++) {
			pl[i] = new Person(0, 0, false, false);
		}
		if (userHeight >= 20 && userWidth >= 20)
			System.out.println("\nPlease wait a few seconds...\n");
		Grid a = new Grid(userHeight, userWidth); // Creates the grid
		Movement x = new Movement(userHeight, userWidth, userCrowd); // x will be used for the people to be set and move
																		// through the grid
		PlaceInfected places = new PlaceInfected(x.height, x.width, x.crowd, userDuration, x.getPeople()); // This will

		StdDraw.clear(StdDraw.LIGHT_GRAY);
		a.createGrid(max); // Draws the grid
		x.setPeople(pl); // Draws the people
		a.createGrid(max); // Draws the grid again

		System.out.println("---------------------------------------------------------------");
		// Prints the statistics
		System.out.println("AT THE BEGINNING THERE ARE: \n");
		if (sumHealthy > 1 || sumHealthy == 0)
			System.out.println(sumHealthy + " people that are healthy and not immune.\n");
		if (sumHealthy == 1)
			System.out.println(sumHealthy + " person that is healthy and not immune.\n");
		if (sumInfected > 1 || sumInfected == 0)
			System.out.println(sumInfected + " people that are infected.\n");
		if (sumInfected == 1)
			System.out.println(sumInfected + " person that is infected.\n");
		if (sumImmune > 1 || sumImmune == 0)
			System.out.println(sumImmune + " people that are immune.\n");
		if (sumImmune == 1)
			System.out.println(sumImmune + " person that is immune.\n");


		// Runs the simulation for the time that is given from the user
		StdDraw.enableDoubleBuffering();
		//Person[] Arr=new Person[userCrowd];
		for (int i = 0; i < userTime - 1; i++) {
			StdDraw.clear(StdDraw.LIGHT_GRAY);
			places.setDuration(x.getPeople());
			places.PrintInfection();
			if (opt == 1)
				places.placeAffectsPeople(x.getPeople(), placeVirus, placeMask);
			else
				places.placeAffectsPeople(x.getPeople(), -1, -1);
			if (opt == 1)
				x.move(max, peopleVirus, peopleMask);
			else
				x.move(max, -1, -1);
			delay();
			StdDraw.show();
			StdDraw.pause(6);
			
		}
	/*	ArrayList Array=new ArrayList();
		for (int i = 0; i < Arr.length; i++) {
			Array.add(Arr[i]);
		}
		for (int i = 0; i < Array.size(); i++) {
			
		}*/
		int finalSumInfected = 0;
		int finalSumHealthy = 0;
		int finalSumImmune = 0;
		ppl = x.getPeople();
		for (int i = 0; i < x.getCrowd(); i++) {
			
			if (((Person) ppl.get(i)).isImmune() )
				finalSumImmune++;
			else	if (((Person) ppl.get(i)).isInfected())
				finalSumInfected++;
			else
				if (!((Person) ppl.get(i)).isInfected() && !((Person) ppl.get(i)).isImmune())
				finalSumHealthy++;
			
		}

		System.out.println("---------------------------------------------------------------");
		// Prints the statistics
		System.out.println("FINALLY THERE ARE:\n");
		if (finalSumHealthy > 1 || finalSumHealthy == 0)
			System.out.println(finalSumHealthy + " people that are healthy and not immune.\n");
		if (finalSumHealthy == 1)
			System.out.println(finalSumHealthy + " person that is healthy and not immune.\n");
		if (finalSumInfected > 1 || finalSumInfected == 0)
			System.out.println(finalSumInfected + " people that are infected.\n");
		if (finalSumInfected == 1)
			System.out.println(finalSumInfected + " person that is infected.\n");
		if (finalSumImmune > 1 || finalSumImmune == 0)
			System.out.println(finalSumImmune + " people that are immune.\n");
		if (finalSumImmune == 1)
			System.out.println(finalSumImmune + " person that is immune.\n");
		if (finalSumInfected - sumInfected >= 2)
			System.out.println("As you can see, with these results is important to STAY HOME!!\n");
	}
	public static Point Input(String s) {
		String str="";
		Point x=new Point(0,0);
		String newStr="";
		String newStr2="";
		boolean b=false;
		for (int i = 0; i <s.length(); i++) {
			if(s.charAt(i)!=',')
				str+=s.charAt(i);
		}
		if(isNumeric(str)&&str.length()==s.length()-1) {
			for (int i = 0; i < s.length(); i++) {
			
				if(s.charAt(i)==','||s.charAt(i)=='.'||s.charAt(i)==' ')
					b=true;
				else if(s.charAt(i)=='-') {
					x=new Point(-1,-1);
				}
				else if((Integer.parseInt(String.valueOf(s.charAt(i))))>=0&&(Integer.parseInt(String.valueOf(s.charAt(i))))<=9&&b==false)
					newStr+=s.charAt(i);
				else newStr2+=s.charAt(i);
			}
			if(newStr.length()==1&&newStr2.length()==1) {
				x=new Point(Integer.parseInt(String.valueOf(newStr.charAt(0))),Integer.parseInt(String.valueOf(newStr2.charAt(0))));}
			else if(newStr.length()==2&&newStr2.length()==1){
				x=new Point(Integer.parseInt(String.valueOf(newStr.charAt(0))+String.valueOf(newStr.charAt(1))),Integer.parseInt(String.valueOf(newStr2.charAt(0))));}
			else if(newStr.length()==2&&newStr2.length()==2)
				x=new Point(Integer.parseInt(String.valueOf(newStr.charAt(0))+String.valueOf(newStr.charAt(1))),Integer.parseInt(String.valueOf(newStr2.charAt(0))+String.valueOf(newStr2.charAt(1))));
			else if(newStr.length()==1&&newStr2.length()==2)
				x=new Point(Integer.parseInt(String.valueOf(newStr.charAt(0))),Integer.parseInt(String.valueOf(newStr2.charAt(0))+String.valueOf(newStr2.charAt(1))));
			else if(newStr.length()>2)
				x=new Point(Integer.parseInt(newStr),0);
			else if(newStr2.length()>2)
				x=new Point(Integer.parseInt(newStr2),0);
			else x=new Point(-1,-1);
			return x;}
		else {
			x=new Point(-1,-1);
			
		}
		return x;
	}
	/**
	 * This is a method that checks if a string is numeric.
	 * @param String str.
	 * @return true if it is and false if is not.
	 */
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		  }  
		}
}