/*
Name: Ankit Jain
ID: 96065117
UCINetID: jaina2

Class responsible for facilitating a single 
game of craps in the simulation
 */
import java.util.Random;

public class CrapsGame {
	private int rolls;
	private CrapsMetricsMonitor monitor;
	
	public CrapsGame(CrapsMetricsMonitor monitor) { //Default constructor
		rolls = 0;
		this.monitor = monitor;
	}
	
	public void updateRolls(){ //Updates total number of rolls
		rolls += 1;
	}
	
	public void newGame() { //Sets number of rolls to zero 
		rolls = 0;
	}
	
	public boolean playGame() {
		/*
		Method is responsible for facilating a single game 
		of craps while updating the metric monitor each time
		 */
		monitor.updateTotalGames();
		Random rand = new Random();
		int diceRoll = rand.nextInt(12) + 1;
		String rollString = "Rolled a " + Integer.toString(diceRoll);
		System.out.println(rollString);
		updateRolls();
		if (diceRoll == 11 || diceRoll == 7) {
			monitor.updateWonGames();
			monitor.updateNaturalCount();
			monitor.updateMaxRolls(rolls);
			String result = "*****Natural! You win!*****";
			System.out.println(result);
			return true;
		} 
		else if (diceRoll == 2 || diceRoll == 3 || diceRoll == 12) {
			monitor.updateLostGames();
			monitor.updateCrapsCount();
			monitor.updateMaxRolls(rolls);
			String result = "*****Craps! You lose!*****";
			System.out.println(result);
			return false;
		}
		else {
			int point_num = diceRoll;
			while (true) {
				updateRolls();
				int newRoll = rand.nextInt(12) + 1;
				String rollString2 = "Rolled a " + Integer.toString(newRoll);
				System.out.println(rollString2);
				if (newRoll == point_num) {
					monitor.updateWonGames();
					String result = "*****Rolled the point! You win!*****";
					System.out.println(result);
					monitor.updateMaxRolls(rolls);
					return true;
				}
				else if (newRoll == 7){
					monitor.updateLostGames();
					String result = "*****Crap out! You lose.*****";
					System.out.println(result);
					monitor.updateMaxRolls(rolls);
					return false;
				}
			}
		}
	}
}
