package com.java.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyNewServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.setContentType("text/html");
			resp.getWriter().println("Hello my new Servlet<br><br>");
			
			//1
			String line1;
			int radius = 50;
			int circleArea = (int) ((radius*radius)* Math.PI);
			
			line1 = new String("Calculation 1: Area circle with radius "  + radius + " is: "  + circleArea + " square-cm");
			
			//2
			String line2;
			float degree = 30;
			float hypotenuse = 50; //cm
			double opposite = hypotenuse * Math.sin(Math.toRadians(degree));
			line2 = new String("Calculation 2: Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: " + opposite+ " cm");
			
			//3
			String line3;
			double base = 20;
			double exp = 13;
			
			line3 = new String("Calculation 3: Power of 20 with exponent of 13 is: " + Math.pow(base, exp));
			
			String resultStr = line1 + "<br>" + line2 +"<br>" + line3;
			
			resp.getWriter().println(resultStr);
	}
	
}
