/**
Name: Ankit Jain
ID: 96065117
UCINetID: jaina2

Name: Natasha Gawande
ID: 29802946
UCINetID: ngawande

Class is responsible for facilitating simulations. Runs the game and resets metrics statistics for each new simulation
 **/

public class CrapsSimulation {
	private CrapsGame crapGame;
	private CrapsMetricsMonitor monitor;
	
	public CrapsGame getCrapsGame() { //Returns the crap game object
		return this.crapGame;
	}
	
	public CrapsSimulation() { //Default constructor
		monitor = new CrapsMetricsMonitor();
		crapGame = new CrapsGame(monitor);
	}
	
	public void updateBalanceMax(double entry) { 
		//Setting the maximum balance as per the updates 
		monitor.setBalanceMax(entry);
	}
	
	public void start() {
		crapGame.playGame(); // calls play game to set up initial details of game 
		crapGame.DetailsSet(); //playChecker to true
		crapGame.newGame();  // resets rolls 
		boolean result = crapGame.playGame(); //runs game for crapsGame algorithm 
		
		if (result == true) { //if player chooses to play again 
			crapGame.resetGameCount(); //resets gameCount for simulation
			crapGame.reset();//resets metrics field
			
			this.start();  //recursion to start a brand new simulation
		}
		else {
			System.out.println("Thank you for playing!");
		}
	} // end start method 
	} // end class


