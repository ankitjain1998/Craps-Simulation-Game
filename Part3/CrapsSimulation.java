/*
Name: Ankit Jain, Natasha Gawande 
ID: 96065117, 29802946
UCINetID: jaina2, ngawande

Class is responsible for facilitating 
simulations with updates in the metrics
monitor
 */
import java.util.Scanner;
import java.io.*; 

public class CrapsSimulation {
	private double balance;
	private double bet;
	private String playerName;
	private CrapsGame crapGame;
	private CrapsMetricsMonitor monitor;
	private Scanner input;
	private PrintWriter pw1;

	private int numThreads; 
	
	/**
	 * default constructor for the CrapsSimulation 
	 * sets values == 0 
	 */
	public CrapsSimulation() { //Default constructor
		balance = 0;
		bet = 0;
		numThreads = 5; 
		try {
			pw1 = new PrintWriter("src/master_file.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		
	}
	/** getter methods -- not actually used?? **/ 
	public String getStart() {
		return "done";
		//return crapGame.getStart();
	}
	
	public String getEnd() {
		return "done";
	//	return crapGame.getEnd();
	}
	
	/**updates the max balance and the game in which the max balance was occured 
	 * 	
	 * @param entry double value which could possibly be the max Balance 
	 */
	public void updateBalanceMax(double entry) { //Setting the maximum balance as per the updates 
		monitor.setBalanceMax(entry); 
		/*for (int i = 0; i < this.numThreads; i++) { 
			monitor[i].setBalanceMax(entry);
		}*/
	}
	
	/** returns balance of the simulation **/ 
	public Double returnBalance() {
		return balance;
	}
	/** returns bet of the simulation **/ 
	public Double returnBet() {
		return bet;
	}
	/** returns the user name of the simulation **/ 
	public String returnID() {
		return playerName;
	}
	
	/**asks for user balance**/ 
	public double getBalance() {
		//Getting the entry for balance brought by user
		System.out.println("Enter the amount of money you will bring to the table: ");
		balance = input.nextDouble();
		return balance;
	}
	/**
	 * sets the details of the simulation 
	 * ask the username, balance, bet and invoked a new CrapsGame object 
	 */
	public void setDetails() {
		//Setting details such as username, balance and bet for user for beginning the simulation		
		monitor = new CrapsMetricsMonitor();
		crapGame = new CrapsGame(monitor);
		input = new Scanner(System.in);
		System.out.println("Welcome to SimCraps! Enter your user name: ");
		playerName = input.next();
		if (playerName.length() < 1) {
			while(true) {
				System.out.println("Invalid Username Entry, Try again: ");
				String entry = input.next();
				if (entry.trim().length() < 1) {
					playerName = entry;
					break;
				}
			}
		}
		System.out.println("Hello " + playerName + "!");
		balance = getBalance();
		updateBalanceMax(balance);
		System.out.println("Enter the bet amount between $1 and $" + Double.toString(balance) +": ");
		bet = input.nextDouble();
		if (bet > balance || bet < 1) {
			while (true) {
				System.out.println("Invalid bet! Please enter a bet between $1 and $" + 
									Double.toString(balance) + ": ");
				int entry = input.nextInt();
				if (entry <= balance && entry >= 1) {
					bet = entry;
					break;
				}
			}
		}

		
	}
	/**take @param and writes to the fileobject created in this class **/ 
	public void writeFile(String entry) { 
		pw1.write(entry);
	}
	/**closes file which is an object in this class **/ 
	public void closeFile() { 
		pw1.close(); 
	
	}
	public void createFile(String name) {
		for (int i = 0; i< this.numThreads; i++) { 
		String	fileName = name + i ;
		crapGame.createGameFile(fileName);
	}
	}
	/** asks user if they want to replay the game or not
	 * 
	 * @return boolean value - true if yes to replay, false if user chooses n 
	 */
	public boolean replayGame() {
		//System.out.println("Total Games in all threads = " + Integer.toString(totalGames));
	//	pw1.write("Total Games in all threads = " + Integer.toString(totalGames) +"\r\n");
		System.out.println("Replay? Enter 'y' or 'n': ");
		String reply = input.next();
		if (reply.equals("n")) {
			 
			return false; 
		}else if (reply.equals("y")){
			pw1.write("\r\nNEW MULTITHREADING SIMULATION\r\n\r\n");
			return true; 
			}
		else { 
		return false; }
		} 
	/** start method for CrapsSimulation classS*
	 * @param bet, balance, playerName, fileName -> information for the specific thread simulation
	 * 
	 */ 
	
	}
	

		