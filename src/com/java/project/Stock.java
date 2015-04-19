package com.java.project;
import java.util.Date;

public class Stock {

	private String symbol;
	private float ask, bid;
	private Date date;
	
	public Stock(String symbol, float ask, float bid, Date date){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
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


public String getHtmlDescription(){
	return "<b>Symbol</b> = " + getSymbol() + ", <b>Ask</b> = " + getAsk() + ", <b>Bid</b> = " + getBid() +
			", <b>Date</b> = " + getDate().getMonth() + "/" + getDate().getDate() + "/" +(1900 + getDate().getYear())+"<br>";
}
}
