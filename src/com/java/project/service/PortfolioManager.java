package com.java.project.service;

import java.util.Calendar;
import java.util.Date;

import com.java.project.model.Portfolio;
import com.java.project.model.Stock;

public class PortfolioManager {

	
		public Portfolio getPortfolio(){
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 11, 15);
			
			Date date1 = cal.getTime();
			Date date2 = cal.getTime();
			Date date3 = cal.getTime();
			
			String portfolioTitle = "Portfolio 1";
			Stock stock1 = new Stock("PIH", 13.1f, 12.4f, date1);
			Stock stock2 = new Stock("ALL", 5.78f, 5.5f, date2);
			Stock stock3 = new Stock("CAAS", 32.2f, 31.5f, date3);
			int numOfStocks = 3;
			Stock[] stockArr = {stock1, stock2, stock3};
			
			Portfolio portfolio = new Portfolio(portfolioTitle,stockArr, numOfStocks);
			
			
			return portfolio;
		}
			 
}