package com.java.project.model;

import java.util.Date;

public class Portfolio {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	
	private String title;
	private Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
	private int portfolioSize = 0;

	public Portfolio(String title, Stock[] stock, int size) { //create constructor
		this.title = title;
		this.stocks = stock;
		this.portfolioSize = size;
	}
		
	public Portfolio(Portfolio copyPortfolio){ // copy constructor Portfolio
		this.title = getTitle();
		
		for (int i=0; i < copyPortfolio.getPortfolioSize(); i++)
		{
			String symbol = copyPortfolio.stocks[i].getSymbol();
			float ask = copyPortfolio.stocks[i].getAsk();
			float bid = copyPortfolio.stocks[i].getBid();
			Date date = copyPortfolio.stocks[i].getDate();
			Stock stock = new Stock(symbol,ask,bid,date);
			this.stocks[i] = stock;
		}
		this.portfolioSize = copyPortfolio.getPortfolioSize();

	}
		public Portfolio() {
			
	}

		public void addStock(Stock stocks){ // adds stock to portfolio
			if (stocks != null && portfolioSize < MAX_PORTFOLIO_SIZE)
			{
				this.stocks[portfolioSize] = stocks;
				portfolioSize++;
			}
			else{
				System.out.println("Portfolio is full OR stock is null");
			}
		}
	
		public void removeStock(String eraseSymbol) // removes stock from portfolio
		{
			if (stocks[portfolioSize-1].getSymbol().equals(eraseSymbol))
			{
				stocks[portfolioSize-1] = null;
				portfolioSize--;
			}
			else
			{
				for (int i=0; i < portfolioSize; i++)
				{
					if (this.stocks[i].getSymbol().equals(eraseSymbol))
					{
						this.stocks[i] = this.stocks[portfolioSize-1];
						this.stocks[portfolioSize-1] = null;
						portfolioSize--;
					}
				}
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
		
		public int getPortfolioSize()
		{
			return portfolioSize; 	
		}

}

