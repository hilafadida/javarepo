package com.java.project.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import exceptions.BalanceException;
import exceptions.PortfolioFullException;
import exceptions.StockAlreadyExistsException;
import exceptions.StockNotExistException;

public class Portfolio implements PortfolioInterface {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	public enum ALGO_RECOMMENDATION{
		BUY,SELL ,REMOVE ,HOLD; 
	}

	private String title;
	private StockInterface[] stocks;
	private int portfolioSize;
	private float balance;

	/**This constructor creates a portfolio object*/	
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		this.title = null;
		this.balance = 0;
	}
	/**This constructor creates a portfolio object  
	 * @param gets title of portfolio, logical size of portfolio stocks and the balance*/
	public Portfolio(String title, int portfolioSize, float balance) {
		this.title = title;
		this.portfolioSize=portfolioSize;
		this.balance = balance;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
	/**The copy constructor copies given portfolio object by value  */
	public Portfolio(Portfolio portfolio) {
		this(new String (portfolio.getTitle()), portfolio.getPortfolioSize(), portfolio.getBalance());

		Stock[] copyied = portfolio.getStocks();
		for(int i = 0; i< this.portfolioSize; i++){
			this.stocks[i] = new Stock(copyied[i]);
		}
	}
	/**This constructor creates a portfolio object */
	public Portfolio(Stock[] stockArray) {
		this.portfolioSize = 0;
		this.balance = 0 ;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = null;
		for(int i = 0; i< stockArray.length; i++){
			this.stocks[i] =  stockArray[i];
			this.portfolioSize++;
		}
	}
	public float getBalance() {
		return balance;
	}
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public void setPortfolioSize(int PortfolioSize) {
		this.portfolioSize = PortfolioSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Stock[] getStocks() {
		return (Stock[]) stocks;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	/**adds a new stock to the portfolio  */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException, StockNotExistException {
		
		if(stock == null){
			throw new StockNotExistException();
		}
		
		else if(this.portfolioSize == MAX_PORTFOLIO_SIZE){
			throw new PortfolioFullException();
		}
		
		else{
			int index = findIndexBySymbol(stock.getSymbol());
			if (index == this.portfolioSize){//if not exists
				this.stocks[this.portfolioSize] = stock;
				((Stock) this.stocks[this.portfolioSize]).setStockQuantity(0);
				this.portfolioSize++;
			}
			else{
				throw new StockAlreadyExistsException();
			}
		}
	}
	
	/**prints stocks in the portfolio */
	public String getHtmlString() {
		String portfolioString = new String("<h1>" + getTitle() + "</h1>"+"<br>"+ "<b>Total poertfolio value</b>: "+getTotalValue()+"$"+ ", "+"<b>Total stock value</b>: "+getStockValue()+"$"+ ", "+"<b>Balance</b>: "+getBalance()+"$" + "<br>");

		for(int i = 0;  i < portfolioSize; i++)
		{
			portfolioString = portfolioString + ((Stock) this.stocks[i]).getHtmlDescription() + "<br>";
		}
		return portfolioString;
	}

	/**removes stock from the portfolio */
	public void removeStock(String stockSymbol) throws StockNotExistException {
		
		if(stockSymbol.equals(null)){
			throw new StockNotExistException();
		}
		
		else{
		int index= findIndexBySymbol(stockSymbol);
			if(index < this.portfolioSize){//exist
				if(((Stock) this.stocks[index]).getStockQuantity() != 0 ){
				sellStock(stockSymbol, -1);
				this.stocks[index] = this.stocks[this.portfolioSize-1];
				this.stocks[this.portfolioSize-1] = null;
				this.portfolioSize--;
				}
				
				else{
					this.stocks[index] = this.stocks[this.portfolioSize-1];
					this.stocks[this.portfolioSize-1] = null;
					this.portfolioSize--;
				}
			}
			else{
				throw new StockNotExistException();
			}
	    }
	}
	/**sells stock from portfolio*/
	public void sellStock(String stockSymbol, int quantity) throws StockNotExistException{
		
		if(stockSymbol.equals(null)){
			throw new StockNotExistException();
		}
		else if(quantity == 0 || quantity < -1){
			throw new StockNotExistException();
		}
		
		else{
			int index= findIndexBySymbol(stockSymbol);
			if(index == this.portfolioSize){
				throw new StockNotExistException();
			}
			
			else if(((Stock) this.stocks[index]).getStockQuantity() == 0){
				throw new StockNotExistException();
			}
			
			else if(quantity > ((Stock) this.stocks[index]).getStockQuantity()){
				throw new StockNotExistException();
			}
			
			else if (quantity == -1){
				this.balance =this.balance + (((Stock) this.stocks[index]).getStockQuantity()* this.stocks[index].getBid());
				((Stock) this.stocks[index]).setStockQuantity(0);
			}
			
			else{
				int stockQuantity = ((Stock) this.stocks[index]).getStockQuantity();
				this.balance =this.balance + (stockQuantity* this.stocks[index].getBid());
				((Stock) this.stocks[index]).setStockQuantity(stockQuantity - quantity);
			}
		}
	}
	
	/**can buy new or existing stocks to the portfolio */
	public void buyStock(Stock stock, int quantity) throws BalanceException, PortfolioFullException, StockAlreadyExistsException, StockNotExistException{
		if(stock == null){
			throw new StockNotExistException();
		}
		
		else if(quantity == 0 || quantity < -1){
			throw new StockNotExistException();
		}
		
		else if (this.balance == 0){
			throw new BalanceException();
		}
		
		else{
			int index = findIndexBySymbol(stock.symbol);
			if(index == this.portfolioSize){//not exists
				addStock(stock);
				
				if(quantity == -1){
					int quantityOfStock = (int)(this.balance / stock.getAsk());
					if(quantityOfStock > 0){
						float tempBalnce = quantityOfStock*stock.getAsk();
						if(tempBalnce <= this.balance){
								updateBalance(-tempBalnce);
							stock.setStockQuantity(((Stock) this.stocks[index]).getStockQuantity() + quantityOfStock);
						}
						else{
							throw new BalanceException();
						}
					}
				}
				
				else{
				float tempCountOfSell = quantity * stock.getAsk(); 
					if(tempCountOfSell <= this.balance){
							updateBalance(-tempCountOfSell);
						stock.setStockQuantity(((Stock) this.stocks[index]).getStockQuantity() + quantity);
					}
					else{
						throw new BalanceException();
					}
				}
			}
			
			else{//if exists
				if(quantity == -1){
					int quantityOfStock = (int)(this.balance / stock.getAsk());
					if(quantityOfStock > 0){
						float tempBalnce = quantityOfStock * stock.getAsk();
						if(tempBalnce <= this.balance){
							updateBalance(-tempBalnce);
							stock.setStockQuantity(((Stock) this.stocks[index]).getStockQuantity() + quantityOfStock);
						}
						else{
							throw new BalanceException();
						}
					}
				}
				
				else{
				float tempCountOfSell = quantity * stock.getAsk(); 
					if(tempCountOfSell <= this.balance){
						updateBalance(-tempCountOfSell);
						stock.setStockQuantity(((Stock) this.stocks[index]).getStockQuantity() + quantity);
					}
					else{
						throw new BalanceException();
					}
				}
			}
		}
	}
	
	/**displays total value of all stocks*/
	public float getStockValue(){
		float totalValueOfStocksNotLiquid = 0;
		for(int i = 0; i < this.portfolioSize; i++){
			totalValueOfStocksNotLiquid = totalValueOfStocksNotLiquid + (((Stock) this.stocks[i]).getStockQuantity() * this.stocks[i].getBid());
		}
		return totalValueOfStocksNotLiquid;
	}
	/**displays total value of portfolio*/
	public float getTotalValue(){
		float totalValueOfStocks = getStockValue() + getBalance();
		return totalValueOfStocks;
	}
	/** changes stock's bid*/
	public void changeStockBid(String stockSymbol, float newBid) {
		if(stockSymbol.equals(null)){
			System.out.print("Error, an incorrect stock symbol was received");
		}
		else{
			int index = findIndexBySymbol(stockSymbol);
			if(index == this.portfolioSize){
				System.out.print("Error, an incorrect stock symbol was received");
			}
			else{
				((Stock) this.stocks[index]).setBid(newBid);
			}
		}
	}
	/** gets stock symbol and finds its index in the array */
	private int findIndexBySymbol(String stockSymbol){
		int index = -2;
		if(stockSymbol.equals(null)){
			System.out.print("\nError, an incorrect stock symbol was received");
		}
		else if(this.portfolioSize == 0){
			return 0;
		}
		else{
			for(index = 0; index< this.portfolioSize; index++){
				if(this.stocks[index].getSymbol().equals(stockSymbol)){
					break;
				}
			}
		}
		return index;	
	}
	
	/** finds stock in portfolio*/
	public Stock findStock(String stockSymbol){
		int index = 0;
		if(stockSymbol.equals(null)){
			System.out.print("/nError, an incorrect stock symbol was received");
			return null;
		}
		else if(this.portfolioSize == 0){
			return null;
		}
		else{
			for(index = 0; index < this.portfolioSize; index++){
				if(this.stocks[index].getSymbol().equals(stockSymbol)){
					break;
				}
			}
		}
		return (Stock) stocks[index];	
	}

	/**updates balance*/
	public void updateBalance(float amount) throws BalanceException{
		
		float sumBalanceTemp = this.balance + (amount);
		
		if(sumBalanceTemp >= 0){
			this.balance = sumBalanceTemp;
			System.out.print("\nblanace has been updated");
		}
		else{
			throw new BalanceException();
		}
	}
	/** returns the max size of the stocks array*/
	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}
}