/*
Name: Ankit Jain
ID: 96065117
UCINetID: jaina2

Class is responsible for facilitating 
simulations with updates in the metrics
monitor
 */
import java.util.Scanner;

public class CrapsSimulation {
	private double balance;
	private double bet;
	private int winStreak;
	private int loseStreak;
	private String playerName;
	private CrapsGame crapGame;
	private CrapsMetricsMonitor monitor;
	private Scanner input;
	
	public CrapsSimulation() { //Default constructor
		balance = 0;
		bet = 0;
		winStreak = 0;
		loseStreak = 0;
		monitor = new CrapsMetricsMonitor();
		crapGame = new CrapsGame(monitor);
		setDetails();
	}
		
	public void updateBalanceMax(double entry) { //Setting the maximum balance as per the updates 
		monitor.setBalanceMax(entry);
	}
	
	public double getBalance() {
		//Getting the entry for balance brought by user
		System.out.println("Enter the amount of money you will bring to the table: ");
		balance = input.nextDouble();
		return balance;
	}
	public void setDetails() {
		//Setting details such as username, balance and bet for user for beginning the simulation
		input = new Scanner(System.in);
		System.out.println("Welcome to SimCraps! Enter your user name: ");
		playerName = input.next();
		if (playerName.trim().length() < 1) {
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
	
	public void start() {
		//Facilitates several games of Craps for the simulation
		//and prints the simulation statistics at the end
		//also helps run it again if requested
		System.out.println(playerName + " bets $" + Double.toString(bet));
		crapGame.newGame();
		double originalBet = bet;
		boolean result = crapGame.playGame();
		if (result == true) {
			System.out.println("It goes");
			balance += bet;
		}
		else{
			balance -= bet;
		}
		while (true) {
			updateBalanceMax(balance);
			if (balance == 0) {
				System.out.println(playerName + "'s Balance: " + Double.toString(balance) + '\n');
				monitor.printStatistics();
				System.out.println("\nReplay? Enter 'y' or 'n': ");
				String reply = input.next();
				if (reply.equals("n")) {
					System.out.println("Thank You for playing!");
					break;
				}
				else {
					System.out.println("\nRestarting....\n");
					setDetails();
					winStreak = 0;
					loseStreak = 0;
					monitor = new CrapsMetricsMonitor();
					crapGame = new CrapsGame(monitor);
				}
			}
			else {
				System.out.println(playerName + "'s Balance: " + Double.toString(balance) 
									+ ". Playing a new game...");
				if (bet > balance) {
					bet = balance;
					System.out.println(playerName + " bets $" + Double.toString(bet));
					crapGame.newGame();
					result = crapGame.playGame();
					if (result == true) {
						balance += bet;
						bet = originalBet;
					}
					else {
						balance = 0;
					}
				}
				else {
					System.out.println(playerName + " bets $" + Double.toString(bet));
					if (result == true) {
						balance += bet;
						winStreak += 1;
						monitor.updateloseStreak(loseStreak);
						monitor.updateWinningStreak(winStreak);
						loseStreak = 0;
						crapGame.newGame();
						result = crapGame.playGame();
					}
					else {
						balance -= bet;
						loseStreak += 1;
						monitor.updateloseStreak(loseStreak);
						monitor.updateWinningStreak(winStreak);
						winStreak = 0;
						crapGame.newGame();
						result = crapGame.playGame();
					}
				}
			}
		}
	}
}
