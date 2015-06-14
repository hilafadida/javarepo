package exceptions;

import org.algo.exception.PortfolioException;

@SuppressWarnings("serial")
public class StockNotExistException extends PortfolioException{
	
	public StockNotExistException(){
		super("Stock wasn't found in portfolio");
	}

}
