/**
Name: Ankit Jain
ID: 96065117
UCINetID: jaina2

Name: Natasha Gawande
ID: 29802946
UCINetID: ngawande

Class responsible for facilitating a single 
game of craps in the simulation
 **/
import java.util.Random;
import java.util.Scanner;

public class CrapsGame {
	private int rolls;
	private double balance;
	private double bet;
	private String betString;
	private String playerName;
	private int gameCount = 0;  
	private CrapsMetricsMonitor monitor;
	private Scanner input;
	private boolean playChecker = false;
	private String balanceEntrymessage = "Enter the amount of money you will bring to the table: ";
	private String entryMessage = "Welcome to SimCraps! Enter your user name: ";
	private String rebetString = "Bet again? ‘y’ or ‘n’: ";
	private String replayString = "Replay? Enter 'y' or 'n': ";
	private String replayAnswer = "random start string"; 
	private String bettingAgain = "random start string"; 
	private int currentLoseStreak = 0; 
	private int currentWinStreak= 0; 
	
	public CrapsGame(CrapsMetricsMonitor monitor) { //Default constructor
		rolls = 0;
		this.monitor = monitor;
	}
	public void incGameCount() { //used to increment the number of games so played
		this.gameCount +=1; 
	}
	public int getGameCount()  { //used to receive the game count at the time so entered
		return this.gameCount; 
	}
	
	public int getCurrentLoseStreak() { //used to return the losing streak at that particular 
										//point of time
		return this.currentLoseStreak; 
	}
	
	public int getCurrentWinStreak() { //Used to obtain the winning streak at that particular 
										//point of time
		return this.currentWinStreak; 
	}
	
	public void incCurrentLoseStreak() { //Used to reset winning streak and increment losing streak
		this.currentWinStreak = 0; 
		this.currentLoseStreak +=1; 
	}
	public void incCurrentWinStreak() { //Used to reset losing streak and increment winning streak
		this.currentLoseStreak = 0; 
		this.currentWinStreak +=1; 
	}
	/** resets information for a CrapsSimulation
	 * resets the balance and bet amount, as well as input strings
	 */
	public void reset() {
		this.rolls = 0;
		this.balance = 0;
		this.bet = 0;
		this.playChecker = false;
		this.balanceEntrymessage = "Enter the amount of money you will bring to the table: ";
		this.entryMessage = "Welcome to SimCraps! Enter your user name: ";
		this.monitor.reset();	
	}
	/**
	 * resets game count for each simulation
	 */
	public void resetGameCount() { 
		this.gameCount = 0;
	}
	/** 
	 * sets users answer as to whether or not they would like to replay the game
	 * @param answer which the user indicates if they want to replay
	 */
	public void setReplayingSimulation(String answer) { //Sets the answer whether player wants to replay or not
		this.replayAnswer = answer; 
	}
	public String getReplayingSimulation() { //Returns whether user wants to play again or not
		return this.replayAnswer; 
	}
	public String getUserName() { // Returns user name
		return playerName;
	}
	
	public Double getbalance() { //Returns current balance
		return balance;
	}
	
	public Double getbet() { //Returns bet
		return bet;
	}
	
	public void DetailsSet() { //Is used to show that initial details of simulation are set
		this.playChecker = true;
	}
	
	public void updateRolls(){ //Updates total number of rolls
		rolls += 1;
	}
	public int getRolls() { //Returns number of rolls 
		return this.rolls; 
	}
	

	public void newGame() { //Sets number of rolls to zero 
		rolls = 0;
	}
	
	public void setReplayAnswer(String answer) { //Sets replay answer as per the string entry
		this.replayAnswer = answer; 
	}
	public String getReplayAnswer_change() { //Returns replay answer
		return this.replayAnswer; 
	}
	
	public Double inputBalanceMoney(){ //Obtains user input for balance
		System.out.println(balanceEntrymessage);
		Double balMoney = input.nextDouble(); 
		return balMoney;
	}
	/** asks user to input balance for the crapsGame
	 * @throws NegativeBalanceException if user enters a balance 0 or less
	 */
	public void setBalance(Double balMoney) throws NegativeBalanceException{
		//Checks whether the balance is valid or not and then sets the value
		if (balMoney <= 0) {
			throw new NegativeBalanceException();
		}
		this.balance = balMoney; 
		betString = "Enter the bet amount between $1 and $" + Double.toString(this.balance) +": ";
	}
	/**
	 * asks user whether or not they would like to replay the game
	 * @return String which is either y or n 
	 * @throws UnknownAnswerException throws Exception if user answers something other than y or n
	 */
	
	public String inputReplayAnswer() {
		//Prompts and returns user answer for replay answer
		input = new Scanner(System.in);
		System.out.println(replayString); //output String changes depending on whether player has made an invalid entry or not
		String answer = input.nextLine();
		answer = answer.trim(); 
		return answer;
	}
	
	public String getreplayAnswer(String ans) throws UnknownAnswerException{
		//Returns reply if user response is valid otherwise throws exception
		String answer = ans;
		if (answer.equals("y") || answer.equals("n"))  {
			return answer; //return if answer is y or n 
		} else {
				throw new UnknownAnswerException(); //throws exception if user answer is not y or n 
			}
		}
	
	public String inputBetAnswer() {
		//Prompts user to enter whether they want to bet again or not
		input = new Scanner(System.in);
		System.out.println(rebetString); //output String changes depending on whether player has made an invalid entry or not
		String answer = input.nextLine();
		answer = answer.trim(); 
		return answer;
	}
	/**
	 * asks user if they would like to play another simulation
	 * @return String which is either y or n 
	 * @throws UnknownAnswerException
	 */
	public String getBetAnswer(String ans) throws UnknownAnswerException{
		String answer = ans;
		if (answer.equals("y") || answer.equals("n"))  {
			return answer;
		} else {
				throw new UnknownAnswerException();
			}
		}
	public void setBettingAnswer(String answer) {
		//Sets betting again response
		this.bettingAgain = answer; 
	}
	public String getBettingAnswer() { 
		//Returns the betting again response
		return this.bettingAgain; 
	}
	
	
	public void setUserName(String name) throws InvalidPlayerNameException{ 
		//Checks validity of string entry and sets name 
		if (name.trim().length() <= 0) {
			throw new InvalidPlayerNameException();
		}
		this.playerName = name;
	}
	
	public String inputUserName() {
		//Prompts user to enter username
		input = new Scanner(System.in);
		System.out.println(entryMessage);
		String name = input.nextLine();
		return name;
	}

	public Double inputBetAmount() throws BalanceLimitException { 
		//Prompts user to enter bet amount
		System.out.println(betString);
		Double betMoney = input.nextDouble();
		return betMoney;
		}
	
	public void setBet(Double betMoney) throws NegativeBetException,BalanceLimitException{
		//Checks valid of the bet entry so made and sets the amount if valid
		if (betMoney <= 0) {
			throw new NegativeBetException();
		}
		else if (betMoney > this.balance) {
			throw new BalanceLimitException();
		}
		else { 
			this.bet = betMoney;
		}
	}
	
	
	
	public void updateBalanceMax(double entry) { //Setting the maximum balance as per the updates 
		//Updates the maximum balance
		monitor.setBalanceMax(entry);
	} 
	
	public boolean playGame() {
		/*
		Method is responsible for facilating a single game 
		of craps while updating the metric monitor each time
		 */
		//boolean returnValue = false; 
		if (playChecker == false) {
			while (true){
				try {
					String name = inputUserName();
					setUserName(name);
					System.out.println("Hello " + playerName + "!");
					while(true) { 
						try {
							setBalance(this.inputBalanceMoney());
							updateBalanceMax(balance);
							while (true){
								try {
									
									setBet(this.inputBetAmount());
									break;
								}
								catch (NegativeBetException e) {
									betString = "Invalid Bet, bet must be greater than zero. Try Again:";
								}
								catch (BalanceLimitException e) {
									betString = "Invalid Bet, bet must be lesser than $" + Double.toString(balance) + " Try Again: ";
								}
								
						}
							break;
						}
						catch (NegativeBalanceException e){
							balanceEntrymessage = "Invalid Entry, Balance must be greater than zero! Try Again!";
						}
					}
					break;
				}
				catch (InvalidPlayerNameException e) {
					entryMessage = "Invalid Username. Please enter a name!";
				}
			}
			return true; // return statement simply to exit out of playGame once initial values have been set. 
		}
			
		else {
				// try { 
					rebetString = "Bet again? ‘y’ or ‘n’: ";
					replayString = "Replay? Enter 'y' or 'n': ";
				System.out.println(playerName + "'s balance: $" + Double.toString(balance) + ". Playing a new game... " 
									+ playerName + " bets $" + Double.toString(bet));
				monitor.updateTotalGames();
				Random rand = new Random();
				int diceRoll = rand.nextInt(6) + rand.nextInt(6) + 1 + 1;
				String rollString = "Rolled a " + Integer.toString(diceRoll);
				System.out.println(rollString);
				this.updateRolls();
				this.incGameCount();
				System.out.println("Game Count: "+ this.getGameCount());
				if (diceRoll == 11 || diceRoll == 7) {
					monitor.updateWonGames();
					monitor.updateNaturalCount();
					monitor.updateMaxRolls(rolls);
					balance += bet;
					
					
					String result = "*****Natural. You Win!***** " + playerName + "'s balance: $" + Double.toString(balance);
					System.out.println(result);
					//returnValue = true;
					monitor.updateMaxRolls(this.getRolls()); 
					this.newGame(); 
					this.incCurrentWinStreak();
					monitor.updateWinningStreak(this.getCurrentWinStreak());
					monitor.updateMaxBalance(this.getbalance(), this.getGameCount());
				} 
				else if (diceRoll == 2 || diceRoll == 3 || diceRoll == 12) {
					monitor.updateLostGames();
					monitor.updateCrapsCount();
					monitor.updateMaxRolls(rolls);
					balance -= bet;
					String result = "*****Crap out! You lose.***** " + playerName + "'s balance: $" + Double.toString(balance);
					System.out.println(result);
					//returnValue = false;
					monitor.updateMaxRolls(this.getRolls());
					this.newGame(); 
					this.incCurrentLoseStreak();
					monitor.updateloseStreak(this.getCurrentLoseStreak()); 
					monitor.updateMaxBalance(this.getbalance(), this.getGameCount());

				}
				else {
					int point_num = diceRoll;
					while (true) {
						updateRolls();
						int newRoll = rand.nextInt(6) + rand.nextInt(6) + 1 + 1;
						String rollString2 = "Rolled a " + Integer.toString(newRoll);
						System.out.println(rollString2);
						if (newRoll == point_num) {
							monitor.updateWonGames();
							monitor.updateMaxRolls(rolls);
							//returnValue = true;
							balance += bet;
							String result = "*****Rolled the point! You win!***** " + playerName + "'s balance: $" + Double.toString(balance);
							System.out.println(result);
							monitor.updateMaxRolls(this.getRolls()); 
							this.newGame(); 
							this.incCurrentWinStreak();
							monitor.updateWinningStreak(this.getCurrentWinStreak());
							monitor.updateMaxRolls(this.getRolls()); 
							monitor.updateMaxBalance(this.getbalance(), this.getGameCount());

							break;
						}
						else if (newRoll == 7){
							monitor.updateLostGames();
							monitor.updateMaxRolls(rolls);
						//	returnValue = false;
							balance -= bet;
							String result = "*****Crap out! You lose.***** " + playerName + "'s balance: $" + Double.toString(balance);
							System.out.println(result);
							monitor.updateMaxRolls(this.getRolls()); 
							this.newGame(); 
							this.incCurrentLoseStreak();
							monitor.updateloseStreak(this.getCurrentLoseStreak()); 
							monitor.updateMaxRolls(this.getRolls());
							monitor.updateMaxBalance(this.getbalance(), this.getGameCount());
							
							break;
						}
					}// end of the while loop 
					
				}
				if (this.balance == 0 ) { 
							monitor.printStatistics(); 
							while (true) { 
								try { 
								    String replayingSimulationAnswer = this.getreplayAnswer(inputReplayAnswer()); 
								    this.setReplayingSimulation(replayingSimulationAnswer);
									break;
								}
								catch (UnknownAnswerException e) { 
									System.out.println("Invalid. Please enter y if you would like to play again and n if you would like to quit." );
								}
							}//end of while loop 
							String replayingSimulationAnswer1 = this.getReplayingSimulation(); 
							if (replayingSimulationAnswer1.equals("n")) {
								 
								return false; 
							}
							else {
								return true; 
							}
				} //end of if block 

				while (true) { 
					try { 
						
					    String user_bet = this.getBetAnswer(inputBetAnswer()); 
					    this.setBettingAnswer(user_bet);
						break;
					}
					catch (UnknownAnswerException e) { 
						System.out.print("Invalid Answer. Please enter y if you would like to bet again. " );
						System.out.println("Enter n if you would not like to bet again"); 
					}
				} // end of while loop 
		
				String user_bet = this.getBettingAnswer(); 
				if (user_bet.equals("y")) { 
				
					while (true) {
						try { 
							betString = "Enter the bet amount between $1 and $" + Double.toString(this.balance) +": ";
							this.setBet(this.inputBetAmount()); 
							break; 
						} 
						catch(NegativeBetException e) { 
							System.out.println("Invalid Bet. You must enter a bet amount greater than 0! Try Again: "); 
							
						}
						catch (BalanceLimitException e) { 
							System.out.println("Invalid Bet, bet must be lesser than $" + Double.toString(this.balance) + " Try Again: "); 
						}
					} //end while loop 
				} //end iff block 
			
				else  { 
					monitor.printStatistics(); 
					while (true) { 
						try { 
						    String replayingSimulationAnswer = this.getreplayAnswer(inputReplayAnswer()); 
						    this.setReplayingSimulation(replayingSimulationAnswer);
							break;
						}
						catch (UnknownAnswerException e) { 
							System.out.println("Invalid. Enter y if you would like to play again and n if you do not want to play again: " );
						}
					} // end of while loop 
			
				
				String replayingSimulationAnswer = this.getReplayingSimulation(); 
				if (replayingSimulationAnswer.equals("n")) {
					 
					return false; 
				}
				else {
					return true; 
				
				} //   else block 
		} //end else block 
		}
		
				return playGame(); 
				//play the game because the user indicated they wanted to play again, new bets have been made
					}
						
	}	


	



