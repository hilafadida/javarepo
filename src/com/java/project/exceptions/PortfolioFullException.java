package com.java.project.exceptions;

import org.algo.exception.PortfolioException;

@SuppressWarnings("serial")
public class PortfolioFullException extends PortfolioException {
	public PortfolioFullException() {
		super("There is stocks limitation - portfolio is full");
	}
}