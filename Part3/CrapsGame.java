/*
Name: Ankit Jain, Natasha Gawande 
ID: 96065117, 29802946
UCINetID: jaina2, ngawande

Class responsible for facilitating a single 
game of craps in the simulation
 */
import java.util.Random;

public class CrapsGame {
	private int rolls;
	private CrapsMetricsMonitor monitor;
	private FileCreator fileX ;
	
	public CrapsGame(CrapsMetricsMonitor monitor) { //Default constructor
	
		rolls = 0;
		this.monitor = monitor;
	}

	/**
	 * creates game file for CrapsGame obj 
	 * @param name name of thread file 
	 */
	public void createGameFile(String name){
		this.fileX = new FileCreator(name);
	}
	
	/**
	 * closes the file for the game in CrapsGame obj
	 * @param name is the name of the thread file 
	 */
	public void closeFile(String name) {
		this.fileX.closeFile(name);
	}
	
	public void updateRolls(){ //Updates total number of rolls
		rolls += 1;
	}
	
	/**
	 * changes number of rolls back to 0 
	 */
	public void newGame() { //Sets number of rolls to zero 
		rolls = 0;
	}
	/**
	 * 
	 * @param entry String that is written to the file, which is stored in CrapsGame object 
	 */
	
	public void writeFile(String entry) {
		this.fileX.writeFile(entry);
	}
	
	/**
	 * Algorithm for exactly one craps game to execute 
	 * @return true if player wins, and false if player loses game 
	 */
	public boolean playGame() {
		/*
		Method is responsible for facilating a single game 
		of craps while updating the metric monitor each time
		 */
		monitor.updateTotalGames();
		Random rand = new Random();
		fileX.writeFile("Rolling dice 1  at " + System.currentTimeMillis() + "\r\n");
		int diceRoll1 = rand.nextInt(6) + 1;
		fileX.writeFile("Rolling dice 2  at " + System.currentTimeMillis() + "\r\n");
		int diceRoll2 = rand.nextInt(6)+ 1; 
		int diceRoll = diceRoll1 + diceRoll2; 
		
		String rollString = "\r\nRolled a " + Integer.toString(diceRoll) + "\r\n";
		fileX.writeFile(rollString);
		updateRolls();
		if (diceRoll == 11 || diceRoll == 7) {
			monitor.updateWonGames();
			monitor.updateNaturalCount();
			monitor.updateMaxRolls(rolls);
			String result = "\r\n*****Natural! You win!*****";
			fileX.writeFile(result);
			return true;
		} 
		else if (diceRoll == 2 || diceRoll == 3 || diceRoll == 12) {
			monitor.updateLostGames();
			monitor.updateCrapsCount();
			monitor.updateMaxRolls(rolls);
			String result = "\r\n*****Craps! You lose!*****";
			fileX.writeFile(result);
			return false;
		}
		else {
			int point_num = diceRoll;
			while (true) {
				updateRolls();
				fileX.writeFile("Rolling dice 1  at " + System.currentTimeMillis() + "\r\n");
				int newRoll1 = rand.nextInt(6)+ 1;
				fileX.writeFile("Rolling dice 2 at " + System.currentTimeMillis() + "\r\n");
				int newRoll2 = rand.nextInt(6)  + 1;
				int newRoll = newRoll1 + newRoll2; 
				String rollString2 = "\r\nRolled a " + Integer.toString(newRoll) + "\r\n";
				fileX.writeFile(rollString2);
				if (newRoll == point_num) {
					monitor.updateWonGames();
					String result = "\r\n*****Rolled the point! You win!*****";
					fileX.writeFile(result);
					monitor.updateMaxRolls(rolls);
					return true;
				}
				else if (newRoll == 7){
					monitor.updateLostGames();
					String result = "\r\n*****Crap out! You lose.*****";
					fileX.writeFile(result);
					monitor.updateMaxRolls(rolls);
					return false;
				}
			}
		}
	}
}
