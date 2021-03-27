import org.junit.Test;
/**
Name: Ankit Jain
ID: 96065117
UCINetID: jaina2

Name: Natasha Gawande
ID: 29802946
UCINetID: ngawande

Class including test cases for the craps game program 
*/
public class TestCases1 {
	private CrapsSimulation crapSim = new CrapsSimulation();
	//Test Cases for InvalidUserName
		@Test
		public void normalCases1() throws InvalidPlayerNameException { 
			//Normal Case which sets username successfully
			crapSim.getCrapsGame().setUserName("  aNkit1Natasha ");
		}
		
		@Test
		public void boundaryCases1_1() throws InvalidPlayerNameException{
			//Boundary Case which sets username even with boundaries
			crapSim.getCrapsGame().setUserName("A     ");
		}
		
		@Test
		public void boundaryCases1_2() throws InvalidPlayerNameException{
			//Boundary Case which sets username even with boundaries
			crapSim.getCrapsGame().setUserName("     a");
		}
	
		@Test(expected=InvalidPlayerNameException.class)
		public void errorCases1() throws InvalidPlayerNameException{
			//Error Case which raises error upon function called
			crapSim.getCrapsGame().setUserName(" ");
		}
	
	//Test Cases for NegativeBalanceException
	@Test
		public void normalCases2() throws NegativeBalanceException { 
			//Normal Case which allows balance to be set successfully
			crapSim.getCrapsGame().setBalance(100.5);
		}
		
		@Test(expected=NegativeBalanceException.class)
		public void boundaryCases2_1() throws NegativeBalanceException{
			//Boundary Case which raises exception due to negative balance
			crapSim.getCrapsGame().setBalance(-1.0);
		}
		
		@Test
		public void boundaryCases2_2() throws NegativeBalanceException{
			//Boundary Case which doesn't raise exception due to positive balance 
			crapSim.getCrapsGame().setBalance(1.0);
		}
		
		@Test(expected=NegativeBalanceException.class)
		public void boundaryCases2_3() throws NegativeBalanceException{
			//Boundary Case which raises exception due to zero balance
			crapSim.getCrapsGame().setBalance(0.0);
		}
	
		@Test(expected=NegativeBalanceException.class)
		public void errorCases2() throws NegativeBalanceException{
			//Error Case which raises exception due to negative balance
			crapSim.getCrapsGame().setBalance(-1567835.0);
		}
	
	
	//Test Cases for NegativeBetException
	@Test
		public void normalCases3() throws NegativeBetException,BalanceLimitException,NegativeBalanceException { 
			//Normal Case which sets bet successfully 
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(5.0);
		}
		
		@Test(expected=NegativeBetException.class)
		public void boundaryCases3_1() throws NegativeBetException,BalanceLimitException,NegativeBalanceException{
			//Boundary Case which raises exception due to negative entry
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(-1.0);
		}
		
		@Test
		public void boundaryCases3_2() throws NegativeBetException,BalanceLimitException,NegativeBalanceException{
			//Boundary Case which successfully sets bet 
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(1.0);
		}
		
		@Test(expected=NegativeBetException.class)
		public void boundaryCases3_3() throws NegativeBetException,BalanceLimitException,NegativeBalanceException{
			//Boundary Case which raises exception due to zero bet entry
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(0.0);
		}
	
		@Test(expected=NegativeBetException.class)
		public void errorCases3() throws NegativeBetException,BalanceLimitException,NegativeBalanceException{
			//Error Case which raises exception due to negative bet entry
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(-1567835.0);
		}
	
	//Test Cases for BalanceLimitException
		@Test
		public void normalCases4() throws NegativeBetException,BalanceLimitException,NegativeBalanceException { 
			//Normal Case which successfully sets bet as entry is in range
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(99.0);
		}
		
		@Test
		public void boundaryCases4_1() throws NegativeBetException,BalanceLimitException,NegativeBalanceException{
			//Boundary Case which raises sets bet successfully as entry is equal to balance
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(100.0);
		}
		
		@Test
		public void boundaryCases4_2() throws NegativeBetException,BalanceLimitException,NegativeBalanceException{
			//Boundary Case which successfully sets balance as entry is more than zero and less than balance
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(1.0);
		}

		@Test(expected=BalanceLimitException.class)
		public void errorCases4() throws NegativeBetException,BalanceLimitException,NegativeBalanceException{
			//Error Case which raises exception as entry is more than the balance
			crapSim.getCrapsGame().setBalance(100.0);
			crapSim.getCrapsGame().setBet(101.0);
		}
		
	//Test Cases(1) for UnknownAnswerException
		
		@Test
		public void normalCases5() throws UnknownAnswerException{ 
			//Normal Case for replay bet entry
			crapSim.getCrapsGame().getBetAnswer("y");
		}
		
		@Test
		public void boundaryCases5_1() throws UnknownAnswerException{
			//Boundary Case for replay bet entry
			crapSim.getCrapsGame().getBetAnswer("y");
		}
		
		@Test
		public void boundaryCases5_2() throws UnknownAnswerException{
			//Boundary Case for replay bet entry
			crapSim.getCrapsGame().getBetAnswer("n");
		}
		
		@Test(expected=UnknownAnswerException.class)
		public void errorCases5() throws UnknownAnswerException{
			//Error Case for replay bet entry
			crapSim.getCrapsGame().getBetAnswer("a");
		}
	
	//Test Cases(2) for UnknownAnswerException
		
		@Test
		public void normalCases6() throws UnknownAnswerException{ 
			//Normal Case for replay balance entry
			crapSim.getCrapsGame().getreplayAnswer("y");
		}
		
		@Test
		public void boundaryCases6_1() throws UnknownAnswerException{
			//Boundary Case for replay balance entry
			crapSim.getCrapsGame().getreplayAnswer("y");
		}
		
		@Test
		public void boundaryCases6_2() throws UnknownAnswerException{
			//Boundary Case for replay balance entry
			crapSim.getCrapsGame().getreplayAnswer("n");
		}
				
		@Test(expected=UnknownAnswerException.class)
		public void errorCases6() throws UnknownAnswerException{
			//Error Case for replay balance entry
			crapSim.getCrapsGame().getreplayAnswer("a");
		}		
}