package com.java.project.model;

import java.util.Date;

public class Portfolio {

	enum ALGO_RECOMMENDATION {BUY, SELL, REMOVE, HOLD}

	private final static int MAX_PORTFOLIO_SIZE = 5;

	private String title;
	private Stock[] stocks;
	private int portfolioSize;
	private float balance = 0;


	public Portfolio() { //create constructor
		this.title = "unnamed";
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.balance = 0;
	}

	public Portfolio(Portfolio copyPortfolio){ // copy constructor Portfolio

		this.title = copyPortfolio.getTitle();

		for (int i=0; i < copyPortfolio.getPortfolioSize(); i++)
		{
			String symbol = copyPortfolio.stocks[i].getSymbol();
			float ask = copyPortfolio.stocks[i].getAsk();
			float bid = copyPortfolio.stocks[i].getBid();
			Date date = copyPortfolio.stocks[i].getDate();
			int quantity = copyPortfolio.stocks[i].getStockQuantity();
			Stock stock = new Stock(symbol, ask, bid, date, quantity);
			this.stocks[i] = stock;
		}
		this.portfolioSize = copyPortfolio.getPortfolioSize();
	}


	public void updateBalance(float amount){ // update balance method
		if (this.balance + amount < 0) // if negative
		{
			this.balance = 0;
		}

		else
		{
			this.balance += amount;
		}

	}

	public void addStock(Stock stock){ // add stock to portfolio

		boolean isExist = false;
		String returnVal = null;

		if (this.getPortfolioSize() == MAX_PORTFOLIO_SIZE) // if there's room
		{
			returnVal = "Portfolio is full";
			System.out.println(returnVal);
			return;
		}

		for (int i=0; i < portfolioSize; i++){

			if (this.stocks[i].getSymbol().equals(stock.getSymbol()))
			{
				isExist = true;
				returnVal = "Stock already exists";
				System.out.println(returnVal);
				return;
			}
		}
		if (isExist == false) 
		{
			this.stocks[portfolioSize] = new Stock(stock.getSymbol(), stock.getAsk(), stock.getBid(),stock.getDate(), stock.getStockQuantity());
			portfolioSize++;
			returnVal = "Added a stock";
			System.out.println(returnVal);	
		}

	}

	public boolean sellStock(String symbol, int quantity){ // sell (no deletion) stocks

		if(symbol == null || quantity < -1){
			System.out.println("stock symbol or stock quntity are not correct");
			return false;
		}

		int i = this.searchStock (symbol);

		if(i>-1){	
			if(this.stocks[i].getStockQuantity() - quantity < 0){
				System.out.println("Not enough stocks to sell");
				return false;

			}else if(quantity == -1){
				this.updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
				this.stocks[i].setStockQuantity(0);
				System.out.println("requested stock quantity was sold succesfully");
				return true;
			}
			else{

				this.updateBalance(quantity * this.stocks[i].getBid());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity() - quantity);
				System.out.println("the wanted amount was sold succefully");
				return true;
			}
		}
		System.out.println("Stock is not in this Portfolio");
		return false; 
	}


	public boolean removeStock(String eraseSymbol) // remove stock from portfolio
	{
		if (eraseSymbol == null){
			System.out.println("Error, stock is null");
			return false;
		}

		int i = this.searchStock (eraseSymbol);

		if(i>-1){
			if (portfolioSize > 1){

				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = stocks[this.portfolioSize-1];
				stocks[this.portfolioSize-1]=null;	
			}
			else  if (this.portfolioSize == 1){

				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = null;
			}

			portfolioSize--;
			System.out.println("Requested stock is deleted");
			return true;
		}

		System.out.println("Stock is not inside this portfolio");
		return false;
	}


	public boolean buyStock(Stock stock, int quantity) // buy stocks
	{
		if(stock == null || quantity < -1){
			System.out.println("Error, stock symbol or stock quntity are incorrect");
			return false;
		}
		if(quantity*stock.getAsk() > this.balance){
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}

		int i = this.searchStock (stock.getSymbol());

		if(i>-1){
			if(quantity == -1){
				int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
				this.updateBalance(-howManyToBuy*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+howManyToBuy);
				System.out.println("The requested stock quantity, that could be bought, was succefully bought");
				return true;
			}
			else{
				this.updateBalance(-quantity*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
				System.out.println("The requested amount was bought");
				return true;
			}
		}


		if(i == MAX_PORTFOLIO_SIZE-1){
			System.out.println("Portfolio has no room...");
			return false;
		}

		if (quantity == -1){
			this.addStock(stock);
			int toBuy = (int)this.balance/(int)this.stocks[i].getAsk();
			this.updateBalance(-(toBuy*this.stocks[this.portfolioSize-1].getAsk()));
			this.stocks[i].setStockQuantity(this.stocks[this.portfolioSize-1].getStockQuantity()+toBuy);
			System.out.println("The requested stock quantity, that could be bought, was succefully bought");
			return true;
		} 

		else{
			
			this.addStock(stock);
			this.updateBalance(-quantity*this.stocks[portfolioSize -1].getAsk());
			this.stocks[this.portfolioSize -1].setStockQuantity(quantity);
			System.out.println("Stock was added  to the portfolio");
			return true;

		}
	}

	private int searchStock (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}


	// getters + setters

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

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(double balanceToSet)
	{
		this.balance = (float)balanceToSet;
	}

	public void setBalance(float balance){
		this.balance = balance;
	}

	public double getStocksValue(){
		double total = 0;
		for (int i=0; i < portfolioSize; i++){

			total = total + (this.stocks[i].getStockQuantity() * this.stocks[i].getBid());
		}
		return total;
	}

	public double getTotalValue(){

		return this.getStocksValue()+getBalance();
	}

	public String getHtmlString(){

		String ret = new String( "<h1>" + getTitle() + "</h1>" );

		for(int i = 0; i < portfolioSize ;i++) {

			ret += this.stocks[i].getHtmlDescription() + "<br>";
		}

		ret += "<br>" + "Total Portfolio Value: " + getTotalValue() + "$<br>"
				+ "Total Stocks Value: " + this.getStocksValue() + "$<br>"
				+ "Balance: " + getBalance() + "$";

		return ret;
	}

}
