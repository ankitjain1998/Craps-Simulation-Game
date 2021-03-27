/**
Name: Ankit Jain
ID: 96065117
UCINetID: jaina2

Name: Natasha Gawande
ID: 29802946
UCINetID: ngawande

This class is responsible 
for updating and storing the 
statistics of one simulation as 
a whole
 **/
public class CrapsMetricsMonitor {
	private int totalGames;
	private int wonGames;
	private int lostGames;
	private int maxRolls;
	private int rollcountNatural;
	private int rollcountCraps;
	private int winStreakMax;
	private int loseStreakMax;
	private double gamesBalanceMax;
	private int gameNumBalanceMax;
	
	public CrapsMetricsMonitor() //Default Constructor
	{
		totalGames = 0;
		wonGames = 0;
		lostGames = 0;
		maxRolls = 0;
		rollcountNatural = 0;
		rollcountCraps = 0;
		winStreakMax = 0;
		loseStreakMax = 0;
		gamesBalanceMax = 0;
	}
	
	public void printStatistics() { //Prints the statistics related to a simulation
		System.out.println("******************************\n"
				+ "*** SIMULATION STATISTICS ***\n"
				+ "******************************"
				+ "\nGames Played: " + Integer.toString(totalGames)
				+ "\nGames Won: " + Integer.toString(wonGames)
				+ "\nGames Lost: " + Integer.toString(lostGames)
				+ "\nMaximum Rolls in a single game: " + Integer.toString(maxRolls)
				+ "\nNatural Count: " + Integer.toString(rollcountNatural)
				+ "\nCraps Count: " + Integer.toString(rollcountCraps)
				+ "\nMaximum Winning Streak: " + Integer.toString(winStreakMax)
				+ "\nMaximum Loosing Streak: " + Integer.toString(loseStreakMax)
				+ "\nMaximum Balance: " + Double.toString(gamesBalanceMax) + " "
				+ "during game " + Integer.toString(gameNumBalanceMax)
				);
	}
	
	/** sets maximum balance of the simulation, if the parameter value is
	 *  greater than the current max Balance of the game
	 * @param entry  double that is the balance once a single game has ended
	 */
	public void setBalanceMax(double entry) {//Updates the balance as per the current balance in simulation
		if (entry > gamesBalanceMax){
			gamesBalanceMax = entry;
			gameNumBalanceMax = totalGames;
		}
	}
	/**
	 * increments natural count if game ends in natural win
	 */
	public void updateNaturalCount(){ //Updates Natural count
		rollcountNatural += 1;
	}
	/**
	 *increments craps count if game ends in craps lose
	 */
	public void updateCrapsCount(){ //Updates Craps Count
		rollcountCraps += 1;
	}
	/**
	 * increments number of total games played for a simulation
	 */
	public void updateTotalGames(){ //Updates total number of Craps games played in a simulation
		totalGames += 1;
	}
	/**
	 * increments number of games won if game ends in a win
	 */
	public void updateWonGames(){//Updates number of games won
		wonGames += 1;
	}
	
	/**
	 * increments number of games lost if game ends in a loss
	 */
	public void updateLostGames(){//Updates number of games lost
		lostGames += 1;
	}
	
	/** updates max winning streak if paramter is greater than current longest Winning streak
	 * 
	 * @param entry integer value with the number of consecutive wins
	 */
	public void updateWinningStreak(int entry){//Updates winning streak as per simulation
		if (entry > winStreakMax){
			winStreakMax = entry;
		}
	}
	/** updates max lose streak if parameter is greater than current longest losing streak
	 * 
	 * @param entry integer value with the number of consecutive losses
	 */
	public void updateloseStreak(int entry){//Updates losing streak as per simulation
		if (entry > loseStreakMax){
			loseStreakMax = entry;
		}
		
	}
	/**
	 * if the parameter value is greater than the current number of highest rolls, then will update maxRolls information
	 * @param entry max number of rolls 
	 */
	public void updateMaxRolls(int entry){//Updates max number of rolls in a game
		if (entry > maxRolls){
			this.maxRolls = entry;
		}
	}
	 
	/** method used to obtain information about the the game number with the highest Balance
	 * @param num the game number, used for simulation statistics for game in which highest balance was obtained
	 */
	public void setGameNumBalanceMax(int num) { 
		this.gameNumBalanceMax = num; 
	}
	
	/**
	 * updates highest balance and the game number in which highest balance was obtained if the 
	 * param values are greater than the current highest balance saved. 
	 * @param entry highest balance of the game
	 * @param gameNum in which highest balance was obtained
	 */
	public void updateMaxBalance(double entry, int gameNum){
	
		if (entry >= gamesBalanceMax){ //Updates max balance at a point in a simulation 
			gamesBalanceMax = entry;
			this.setGameNumBalanceMax(gameNum);
			
		}
	}
	/**
	 * resets metrics for entire simulation, used for restarting a simulation
	 */
	public void reset() { //Resets the metric monitor to initial state
		totalGames = 0;
		wonGames = 0;
		lostGames = 0;
		maxRolls = 0;
		rollcountNatural = 0;
		rollcountCraps = 0;
		winStreakMax = 0;
		loseStreakMax = 0;
		gamesBalanceMax = 0;
		gameNumBalanceMax = 0; 
	}
}
