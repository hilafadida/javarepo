package com.java.project.model;
import java.util.Date;

import com.java.project.model.Portfolio.ALGO_RECOMMENDATION;

public class Stock {
	
	private String symbol;
	private float ask, bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	public Stock(String symbol, float ask, float bid, Date date, int stockQuantity){ //create stock
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
	}
	
	public Stock(Stock copyStock){ // duplicate constructor stock
		this.symbol = copyStock.getSymbol();
		this.ask = copyStock.getAsk();
		this.bid = copyStock.getBid();
		this.date = copyStock.getDate();
		this.recommendation = copyStock.getRecommendation();
		this.stockQuantity = copyStock.getStockQuantity();
	}

public String getSymbol(){
	return symbol;
}

public void setSymbol(String value){
	this.symbol = value;
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

public void setDate(Date date) {
	this.date = date;
}

public ALGO_RECOMMENDATION getRecommendation(){
	return recommendation;
}

public void setRecommendation(ALGO_RECOMMENDATION recommendation){
	this.recommendation = recommendation;
}

public int getStockQuantity(){
	return stockQuantity;
}

public void setStockQuantity(int stockQuantity){
	this.stockQuantity = stockQuantity;
}

public String getHtmlDescription(){ // prints stock
	return "<b>Symbol</b> = " + getSymbol() + ", <b>Ask</b> = " + getAsk() + ", <b>Bid</b> = " + getBid() +
			", <b>Date</b> = " + getDate().getMonth() + "/" + getDate().getDate() + "/" +(1900 + getDate().getYear()) + 
			"<b> Quantity</b> = " + getStockQuantity() + "</br>";
}
}
