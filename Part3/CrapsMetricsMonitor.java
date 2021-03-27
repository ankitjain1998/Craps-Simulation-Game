/*
Name: Ankit Jain, Natasha Gawande 
ID: 96065117, 29802946
UCINetID: jaina2, ngawande

This class is responsible 
for updating and storing the 
statistics of one simulation as 
a whole
 */
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
	
	public String getStatistics() { //Returns the statistics related to a simulation as a string
		String toReturn = "\r\n" + "******************************\n"
				+ "\r\n" + "*** SIMULATION STATISTICS ***\n"
				+ "\r\n" + "******************************"
				+ "\r\n" +"\nGames Played: " + Integer.toString(totalGames)
				+ "\r\n" +"\nGames Won: " + Integer.toString(wonGames)
				+ "\r\n" +"\nGames Lost: " + Integer.toString(lostGames)
				+ "\r\n" +"\nMaximum Rolls in a single game: " + Integer.toString(maxRolls)
				+ "\r\n" +"\nNatural Count: " + Integer.toString(rollcountNatural)
				+ "\r\n" +"\nCraps Count: " + Integer.toString(rollcountCraps)
				+ "\r\n" +"\nMaximum Winning Streak: " + Integer.toString(winStreakMax)
				+ "\r\n" +"\nMaximum Loosing Streak: " + Integer.toString(loseStreakMax)
				+ "\r\n" +"\nMaximum Balance: " + Double.toString(gamesBalanceMax) + " "
				+"during game " + Integer.toString(gameNumBalanceMax) + "\r\n";
		return toReturn;
	}
	
	public void setBalanceMax(double entry) {//Updates the balance as per the current balance in simulation
		if (entry > gamesBalanceMax){
			gamesBalanceMax = entry;
			gameNumBalanceMax = totalGames;
		}
	}
	
	public int getGamesPlayed() {
		return totalGames;
	}
	public void updateNaturalCount(){ //Updates Natural count
		rollcountNatural += 1;
	}
	
	public void updateCrapsCount(){ //Updates Craps Count
		rollcountCraps += 1;
	}
	
	public void updateTotalGames(){ //Updates total number of Craps games played in a simulation
		totalGames += 1;
	}
	
	public void updateWonGames(){//Updates number of games won
		wonGames += 1;
	}
	
	public void updateLostGames(){//Updates number of games lost
		lostGames += 1;
	}
	
	public void updateWinningStreak(int entry){//Updates winning streak as per simulation
		if (entry > winStreakMax){
			winStreakMax = entry;
		}
	}
	
	public void updateloseStreak(int entry){//Updates losing streak as per simulation
		if (entry > loseStreakMax){
			loseStreakMax = entry;
		}
	}
	
	public void updateMaxRolls(int entry){//Updates max number of rolls in a game
		if (entry > maxRolls){
			maxRolls = entry;
		}
	}
	
	public void updateMaxBalance(double entry){
		if (entry > gamesBalanceMax){ //Updates max balance at a point in a simulation 
			gamesBalanceMax = entry;
		}
	}
	public void setMaxmimumBalance (double entry) { //Sets max balance variable
		gamesBalanceMax = entry; 
		gameNumBalanceMax = this.getGamesPlayed(); 
	}
	/**
	 * resets the game so that all saved statistics are reset to 0 for different simulations
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
	}
}
