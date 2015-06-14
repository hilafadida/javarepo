package com.java.project.exceptions;

import org.algo.exception.PortfolioException;

@SuppressWarnings("serial")
public class BalanceException extends PortfolioException {
	
		public BalanceException() {
			super("not enough balance");
			 }
		
		public BalanceException(String errorString){
			super(errorString);
		}
	}
