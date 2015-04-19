package com.java.project;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet{
	
			public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			resp.setContentType("text/html");
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 11, 15);
			
			Date date1 = cal.getTime();
			Date date2 = cal.getTime();
			Date date3 = cal.getTime();
					
				
	//Stock 1			
			float as1 = (float) 13.1;
			float bi1 = (float) 12.4;
			Stock st1 = new Stock("PIH", as1, bi1, date1);

			
	//Stock 2
			float as2 = (float) 5.78;
			float bi2 = (float) 5.5;
			Stock st2 = new Stock("ALL", as2, bi2, date2);

					
	//Stock 3
			float as3 = (float) 32.2;
			float bi3 = (float) 31.5;
			Stock st3 = new Stock("CAAS", as3, bi3, date3);

			
			resp.getWriter().println(st1.getHtmlDescription());			
			resp.getWriter().println(st2.getHtmlDescription());			
			resp.getWriter().println(st3.getHtmlDescription());		
}
			
}
		
