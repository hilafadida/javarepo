package com.java.project.service;

import java.util.Calendar;
import java.util.Date;
import com.java.project.Stock;
import com.java.project.model.Portfolio;

public class PortfolioManager {

	
		public Portfolio getPortfolio(){
			Portfolio portfolio = new Portfolio();
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 11, 15);
			
			Date date1 = cal.getTime();
			Date date2 = cal.getTime();
			Date date3 = cal.getTime();
			
			//stock 1
			Stock stock1 = new Stock("PIH", 13.1f, 12.4f, date1);
			
			//stock 2
			Stock stock2 = new Stock("ALL", 5.78f, 5.5f, date2);
			
			//stocks 3
			Stock stock3 = new Stock("CAAS", 32.2f, 31.5f, date3);
			
			portfolio.setTitle("Hila's Portfolio");
			portfolio.addStock(stock1);
			portfolio.addStock(stock2);
			portfolio.addStock(stock3);
			
			return portfolio;
		}
			 
}