package com.java.project.model;

import com.java.project.Stock;

public class Portfolio {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private Stock[] stocks;
	private int portfolioSize = 0;

	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
		
		public void addStock(Stock stocks){
			if (stocks != null && portfolioSize < MAX_PORTFOLIO_SIZE)
			{
				this.stocks[portfolioSize] = stocks;
				portfolioSize++;
			}
			else{
				System.out.println("Portfolio is full OR stock is null");
			}
		}
	
		public String getHtmlString() {
			
			String ret = new String( "<h1>" + getTitle() + "</h1>" );
			
			for(int i = 0; i < portfolioSize ;i++) {
				
				ret += this.stocks[i].getHtmlDescription() + "<br>";
			}
			
			return ret;
		}
		
		public Stock[] getStocks(){
			return stocks;
		}
		
		public void setStocks(Stock[] stock){
			this.stocks = stock;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
}

