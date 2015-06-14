package com.java.project.exceptions;

import org.algo.exception.PortfolioException;

@SuppressWarnings("serial")
public class StockAlreadyExistsException extends PortfolioException {

	public StockAlreadyExistsException(){
		super("Stock has already been added to portfolio");
	}
	
}
