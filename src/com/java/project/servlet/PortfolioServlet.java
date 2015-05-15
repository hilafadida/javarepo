package com.java.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.project.model.Portfolio;
import com.java.project.service.PortfolioManager;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");

		PortfolioManager pManager = new PortfolioManager();
		Portfolio portfolio = pManager.getPortfolio();
		portfolio.getHtmlString();
		resp.getWriter().println(portfolio.getHtmlString());
		
	}


}