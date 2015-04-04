package com.java.project;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Java_ProjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		int num1, num2, num3;

		num1 = 4;
		num2 = 3;
		num3 = 7;
		int result = (num1+num2)*num3;
		
		resp.getWriter().println("<h1>Result of " + "(" + num1 + "+" + num2 +")" + "*" + num3 + "=" + result + "</h1>");

	}
}
