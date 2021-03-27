/**
Name: Ankit Jain, Natasha Gawande 
ID: 96065117, 29802946
UCINetID: jaina2, ngawande

Facilitating the multi-threaded simulations which are running simultaneously
 */
public class Lab4 {
	public static void main(String[] args) {
		CrapsSimulation sim = new CrapsSimulation();
		int total_games = 0; 
		while (true) { 
			
		sim.setDetails();
		//start 
		CrapsThread thread0 = new CrapsThread(sim.returnID(),sim.returnBalance(),sim.returnBet(),sim,"thread0");
		thread0.start();
		//end
		
		CrapsThread thread1 = new CrapsThread(sim.returnID(),sim.returnBalance(),sim.returnBet(),sim,"thread1");
		thread1.start();
		
		CrapsThread thread2 = new CrapsThread(sim.returnID(),sim.returnBalance(),sim.returnBet(),sim, "thread2");
		thread2.start();
		
		CrapsThread thread3 = new CrapsThread(sim.returnID(),sim.returnBalance(),sim.returnBet(),sim,"thread3");
		thread3.start();
		
		CrapsThread thread4 = new CrapsThread(sim.returnID(),sim.returnBalance(),sim.returnBet(),sim,"thread4");
		thread4.start();
		
		try {
			//join methods so that simulations do not interfere with one another 
			thread0.join();
			thread1.join(); 
			thread2.join(); 
			thread3.join(); 
			thread4.join(); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		boolean replay = sim.replayGame(); //make a list of the file names??? 
		if (replay == false) { 
			total_games += thread0.getTotalNumGames() + thread1.getTotalNumGames()  + thread2.getTotalNumGames() + thread3.getTotalNumGames() + thread4.getTotalNumGames(); 
			break; 
		}
		else {
			total_games += thread0.getTotalNumGames() + thread1.getTotalNumGames()  + thread2.getTotalNumGames() + thread3.getTotalNumGames() + thread4.getTotalNumGames(); 
		}
		} //end while 
		sim.writeFile("Total Number of Games: " + total_games + " \r\n");
		System.out.println("Total Number of Games: " +total_games);
		System.out.println("all information can be found in src/master_file.txt"); 
		sim.closeFile(); 
		} //main
}