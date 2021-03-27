/**
 * Name: Ankit Jain, Natasha Gawande 
ID: 96065117, 29802946
UCINetID: jaina2, ngawande

This class extends the Thread Class and holds the main run function for Lab 4. 
Runs each simulation simultaneously and logs information in files as well as to the console. 
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock; 
public class CrapsThread extends Thread{
	private String userName;
	private Double bal;
	private Double betAmount;
	private CrapsSimulation sim;
	private String name;
	private Lock lock; 
	private int winStreak; 
	private int loseStreak; 
	private long endTime; 
	@SuppressWarnings("unused")
	private long startTime; 
	private int totalGames = 0; 
	private double maxBal; 
	@SuppressWarnings("unused")
	private int maxBalGames; 
	private String startTimeStr; 
	private String endStr; 
	
	public CrapsThread(String name, Double balEntry, Double betEntry,
						CrapsSimulation simulation,String className){ //Constructor
		this.userName = name;
		this.bal = balEntry;
		this.betAmount = betEntry;
		this.sim = simulation;
		this.name = className;
		lock = new ReentrantLock(); 
		this.winStreak = 0; 
		this.loseStreak = 0; 
		endTime = 0; 
		startTime = 0; 
		totalGames = 0; 
		maxBal = 0; 
		maxBalGames = 0; 	
	}
	/** getter methods **/ 
	
	public String getStart() {
		return sim.getStart();
	}
	
	public String getEnd() {
		return sim.getEnd();
	}
	
	public int getTotalNumGames() { 
		return this.totalGames; 
	}
	/**creates file for the thread
	 * not actually used 
	 * @param name file name 
	 */
	public void createFile(String name){
		sim.createFile(name);
	}
	
	public float getTime() { //Returns current time
		return System.currentTimeMillis();
	}
	/**
	 * implemented from Thread Class - is main loop for each simulation
	 * this function is executed per thread, and is executed simultaneously -> used lock to make sure that the threads occur at the same time 
	 */
	public void run() {
			lock.lock() ;
			this.startTimeStr = "Starting (" + name + ") simulation: "
					+ System.currentTimeMillis();
			sim.writeFile(startTimeStr + "\r\n");
			System.out.println(startTimeStr);
			CrapsMetricsMonitor monitor = new CrapsMetricsMonitor(); 
			CrapsGame crapGame = new CrapsGame(monitor); 
			crapGame.createGameFile(name);
				crapGame.newGame(); 
				crapGame.writeFile("\r\n"+startTimeStr+"\r\n");
				crapGame.writeFile("\r\nusername: " +userName);
				crapGame.writeFile("\r\n" + userName + "'s Balance: " + Double.toString(bal) + "\r\n");
				crapGame.writeFile("\r\n" + userName + " bets $" + Double.toString(betAmount)+ "\r\n");
				maxBal = bal; 
				maxBalGames = monitor.getGamesPlayed(); 
				monitor.setMaxmimumBalance(maxBal);
				double originalBet = betAmount; 
				while (true) { 
					crapGame.newGame(); 
					crapGame.writeFile("\r\n" + userName + "'s Balance: " + Double.toString(bal) + "\r\n");
					crapGame.writeFile("Making the bet at " + System.currentTimeMillis()+ " \r\n");
					crapGame.writeFile("\r\n" + userName + " bets $" + Double.toString(betAmount)+ "\r\n");
					boolean result = crapGame.playGame(); 
					if (result == true) { 
						bal = bal + betAmount;  
						winStreak += 1;
						monitor.updateloseStreak(loseStreak);
						monitor.updateWinningStreak(winStreak);
						loseStreak = 0;
					} else { 
						bal = bal - betAmount; 
						loseStreak += 1;
						monitor.updateloseStreak(loseStreak);
						monitor.updateWinningStreak(winStreak);
						winStreak = 0;
					}
				if (bal >= maxBal ) { 
					maxBal = bal; 
					maxBalGames = monitor.getGamesPlayed(); 
					monitor.setMaxmimumBalance(maxBal);
				}
				sim.updateBalanceMax(bal); //fix method 
				if (bal < originalBet ) { 
					betAmount = bal; 
				}
				else { 
					betAmount = originalBet; 
				}
				if ( bal == 0 ) {
					crapGame.writeFile("\r\n" + userName + "'s Balance: " + Double.toString(bal) + '\n');
					String stats = monitor.getStatistics();
					crapGame.writeFile(stats); 
					endTime = System.currentTimeMillis();
					this.endStr = "Ending (" + name + ") simulation: "
						+ endTime; 
					crapGame.writeFile(endStr);
					this.totalGames += monitor.getGamesPlayed();
					sim.writeFile("Games played in " + name + " = " + Integer.toString(monitor.getGamesPlayed())+ "\r\n");
					System.out.println("Games played in " + name + " = " + Integer.toString(monitor.getGamesPlayed()));
					crapGame.closeFile(name);
					break; 
				}
				}
				crapGame.closeFile(name);
				sim.writeFile(endStr + "\r\n"); 
				System.out.println(endStr);
				lock.unlock(); 		
				} //while loop
	}	

