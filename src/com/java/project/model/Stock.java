package com.java.project.model;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.algo.model.StockInterface;

import com.java.project.model.Portfolio.ALGO_RECOMMENDATION;

public class Stock implements StockInterface{
	String symbol;
	private float ask; 
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	/**This constructor creates a stock object  
	 * @param gets symbol of stock, ask, bid and date */
	public Stock (String symbol, float ask, float bid, Date date) {
		super();
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.stockQuantity = 0;
	}
	
	/**The copy constructor copies given stock object by value 
	 * @param gets stock object */
	public Stock(Stock stock){
		this(new String (stock.getSymbol()), stock.getAsk(), stock.getBid(), new Date(stock.getDate(). getTime() ));	
	} 
	/**create stock constructor*/
	public Stock() {
		this.symbol = null;
		this.ask = 0;
		this.bid = 0;
		this.date = null;
		this.stockQuantity = 0;
	}

	//getters and setters
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date time) {
		this.date = time;
	}
	public void setDate(long time) {
		this.date = new Date(time);
	}
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**The method prints a stock  
	 * @return stock's details(symbol, ask, bid and date) */
	public String getHtmlDescription(){
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY");
		String stocksDetails = "<b>Stock symbol</b> = " +getSymbol()+","+""
				+ "<b> Ask </b> = "+getAsk()+","
				+ "<b> Bid </b> = "+getBid()+","
				+ "<b> Date </b> = "+df.format(getDate())+", "
				+ "<b> Quantity</b> = "+ getStockQuantity();
		
		return stocksDetails;
	}

}
