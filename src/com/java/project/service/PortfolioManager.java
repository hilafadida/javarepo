package com.java.project.service;

import java.util.Calendar;
import java.util.Date;

import com.java.project.model.Portfolio;
import com.java.project.model.Stock;

public class PortfolioManager {

	
		public Portfolio getPortfolio(){
			
			Portfolio myPortfolio = new Portfolio();
			myPortfolio.setTitle("Exercise 7 portfolio");
			myPortfolio.setBalance(10000);
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 11, 15);
			
			Date date1 = cal.getTime();
			Date date2 = cal.getTime();
			Date date3 = cal.getTime();
			
			Stock stock1 =  new Stock("PIH", (float)10.0, (float)8.5, date1,0);
			Stock stock2 = new Stock("AAL", (float)30.0, (float)25.5, date2, 0);
			Stock stock3 =  new Stock("CAAS", (float)20.0, (float)15.5, date3, 0);

			
			myPortfolio.buyStock(stock1, 20);
			myPortfolio.buyStock(stock2, 30);
			myPortfolio.buyStock(stock3, 40);
			
			myPortfolio.sellStock("AAL", -1); 
			myPortfolio.removeStock("CAAS");
			
			return myPortfolio;
		}
			 
}